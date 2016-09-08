package ru.dmm.steamservice.serviceApi;

/**
 * Created by Dmitry
 */
public enum LoginStatus {
    SUCCESS,
    VERIFICATION_ERROR,
    STEAM_GUARD,
    CAPTCHA;
}
