package ru.dmm.steamservice.data.rs;

import ru.dmm.steamservice.data.AbstractRqRs;

/**
 * Created by Dmitry
 */
public class TransferRs extends AbstractRqRs {
    private String steamid;
    private String token;
    private String auth;
    private boolean remember_login;
    private String webcookie;
    private String token_secure;

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public boolean isRemember_login() {
        return remember_login;
    }

    public void setRemember_login(boolean remember_login) {
        this.remember_login = remember_login;
    }

    public String getWebcookie() {
        return webcookie;
    }

    public void setWebcookie(String webcookie) {
        this.webcookie = webcookie;
    }

    public String getToken_secure() {
        return token_secure;
    }

    public void setToken_secure(String token_secure) {
        this.token_secure = token_secure;
    }
}
