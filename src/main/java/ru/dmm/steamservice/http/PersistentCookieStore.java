package ru.dmm.steamservice.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Created by Dmitry
 */
public class PersistentCookieStore implements CookieStore, Runnable {
    private CookieStore store;
    private String login;
    private ObjectMapper mapper = new ObjectMapper();


    public PersistentCookieStore(String login) {
        this.login = login;
        store = new CookieManager().getCookieStore();

        Map<URI, List<Cookie>> cookies = readJson();

        for (URI uri : cookies.keySet()) {
            List<Cookie> cookiesList = cookies.get(uri);
            for (Cookie cookie : cookiesList) {
                HttpCookie httpCookie = toHttpCookie(cookie);
                store.add(uri, httpCookie);
            }
        }
        // add a shutdown hook to write out the in memory cookies
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }

    @Override
    public void run() {
        List<HttpCookie> cookies = store.getCookies();
        List<URI> uris = store.getURIs();
        Map<URI, List<HttpCookie>> cookiesMap = new HashMap<>();

        for (HttpCookie cookie : cookies) {

            URI curUri = getDefUri(cookie, uris);

            List<HttpCookie> httpCookies = cookiesMap.get(curUri);
            if (httpCookies == null) {
                httpCookies = new ArrayList<>();
            }

            httpCookies.add(cookie);
            cookiesMap.put(curUri, httpCookies);
        }
        saveJson(cookiesMap);
    }

    @Override
    public void add(URI uri, HttpCookie cookie) {
        store.add(uri, cookie);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return store.get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return store.getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return store.getURIs();
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return store.remove(uri, cookie);
    }

    @Override
    public boolean removeAll() {
        return store.removeAll();
    }

    private URI getDefUri(HttpCookie cookie, List<URI> uris) {
        URI curUri = null;
        try {
            curUri = new URI(cookie.getDomain());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        for (URI uri : uris) {
            if (cookie.getDomain().equals(uri.getHost())) {
                curUri = uri;
                break;
            }
        }
        return curUri;
    }

    private void saveJson(Map<URI, List<HttpCookie>> cookiesMap) {
        try {
            String nameFile = login + ".json";
            File file = new File("C:\\Users\\Dmitry\\Desktop\\" + nameFile);
            mapper.writeValue(file, cookiesMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<URI, List<Cookie>> readJson() {
        String nameFile = login + ".json";
        File file = new File("C:\\Users\\Dmitry\\Desktop\\" + nameFile);
        if (!file.exists()) {
            return Collections.emptyMap();
        }

        HashMap<URI, List<Cookie>> cookies = null;
        try {
            cookies = mapper.readValue(file, new TypeReference<HashMap<URI, List<Cookie>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cookies;
    }

    private HttpCookie toHttpCookie(Cookie cookie) {
        HttpCookie httpCookie = new HttpCookie(cookie.getName(), cookie.getValue());
        httpCookie.setComment(cookie.getComment());
        httpCookie.setCommentURL(cookie.getCommentURL());
        httpCookie.setDiscard(cookie.isDiscard());
        httpCookie.setDomain(cookie.getDomain());
        httpCookie.setMaxAge(cookie.getMaxAge());
        httpCookie.setPath(cookie.getPath());
        httpCookie.setPortlist(cookie.getPortlist());
        httpCookie.setSecure(cookie.isSecure());
        httpCookie.setHttpOnly(cookie.isHttpOnly());
        httpCookie.setVersion(cookie.getVersion());
        return httpCookie;
    }
}