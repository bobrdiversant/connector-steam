package ru.dmm.steamservice.data.rs;

import ru.dmm.steamservice.data.AbstractRqRs;

import java.util.List;

/**
 * Created by Dmitry
 */
public class DoLoginRs extends AbstractRqRs {
    private boolean success;
    private boolean requires_twofactor;
    private String message;
    private boolean emailauth_needed;
    private String emaildomain;
    private String emailsteamid;
    private boolean login_complete;
    private List<String> transfer_urls;
    private TransferRs transfer_parameters;
    private boolean captcha_needed;
    private String captcha_gid;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isRequires_twofactor() {
        return requires_twofactor;
    }

    public void setRequires_twofactor(boolean requires_twofactor) {
        this.requires_twofactor = requires_twofactor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEmailauth_needed() {
        return emailauth_needed;
    }

    public void setEmailauth_needed(boolean emailauth_needed) {
        this.emailauth_needed = emailauth_needed;
    }

    public String getEmaildomain() {
        return emaildomain;
    }

    public void setEmaildomain(String emaildomain) {
        this.emaildomain = emaildomain;
    }

    public String getEmailsteamid() {
        return emailsteamid;
    }

    public void setEmailsteamid(String emailsteamid) {
        this.emailsteamid = emailsteamid;
    }

    public boolean isLogin_complete() {
        return login_complete;
    }

    public void setLogin_complete(boolean login_complete) {
        this.login_complete = login_complete;
    }

    public List<String> getTransfer_urls() {
        return transfer_urls;
    }

    public void setTransfer_urls(List<String> transfer_urls) {
        this.transfer_urls = transfer_urls;
    }

    public TransferRs getTransfer_parameters() {
        return transfer_parameters;
    }

    public void setTransfer_parameters(TransferRs transfer_parameters) {
        this.transfer_parameters = transfer_parameters;
    }

    public boolean isCaptcha_needed() {
        return captcha_needed;
    }

    public void setCaptcha_needed(boolean captcha_needed) {
        this.captcha_needed = captcha_needed;
    }

    public String getCaptcha_gid() {
        return captcha_gid;
    }

    public void setCaptcha_gid(String captcha_gid) {
        this.captcha_gid = captcha_gid;
    }
}
