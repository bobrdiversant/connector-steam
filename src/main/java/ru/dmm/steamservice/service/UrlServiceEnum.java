package ru.dmm.steamservice.service;

/**
 * Created by Dmitry
 */
public enum UrlServiceEnum {
    //    LOGIN_SERVICE("https://store.steampowered.com/login/"),
    LOGIN_SERVICE("https://steamcommunity.com/login/"),
    STEAMCOMMUNITY_SERVICE("http://steamcommunity.com/"),
    MARKET_SERVICE("http://steamcommunity.com/market/");

    UrlServiceEnum(String url) {
        this.url = url;
    }

    private String url;

    public String getUrl() {
        return url;
    }

}
