package ru.dmm.steamservice.http;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dmitry
 */
public class CookieStoreImpl implements CookieJar {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieStoreImpl.class);

    private final HashMap<String, List<Cookie>> _cookieStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        List<Cookie> currentCookies = _cookieStore.get(url.host());
        List<Cookie> responseCookies = new ArrayList<>(cookies);

        if (currentCookies != null && responseCookies.size() > 0 && currentCookies.size() > 0) {
            for (Cookie currentCookie : currentCookies) {
                if (!responseCookies.contains(currentCookie) && (currentCookie.value() != null && currentCookie.value() != "")) {
                    responseCookies.add(currentCookie);
                }
            }
        }
        LOGGER.debug("К {} добавили {}", url.host(), responseCookies);
        _cookieStore.put(url.host(), responseCookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {

        List<Cookie> cookies = _cookieStore.get(url.host());
        List<Cookie> requestCookies = new ArrayList<>();

        if (cookies != null && cookies.size() > 0) {
            for (Cookie currentCookie : cookies) {
                if ((currentCookie.value() != null && currentCookie.value() != "")) {
                    requestCookies.add(currentCookie);
                    LOGGER.debug("К {} добавили {}", url.host(), currentCookie);
                }
            }
        }

        return requestCookies != null ? requestCookies : new ArrayList<Cookie>();
    }
}