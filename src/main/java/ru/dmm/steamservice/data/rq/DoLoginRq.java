package ru.dmm.steamservice.data.rq;

import ru.dmm.steamservice.data.AbstractRqRs;

/**
 * Created by Dmitry
 */
public class DoLoginRq extends AbstractRqRs {
    private String captcha_text;
    private long captchagid;
    private long donotcache;
    private String emailauth;
    private String emailsteamid;
    private String loginfriendlyname;
    private String password;
    private boolean remember_login;
    private long rsatimestamp;
    private String twofactorcode;
    private String username;

    public String getCaptcha_text() {
        return captcha_text;
    }

    public void setCaptcha_text(String captcha_text) {
        this.captcha_text = captcha_text;
    }

    public long getCaptchagid() {
        return captchagid;
    }

    public void setCaptchagid(long captchagid) {
        this.captchagid = captchagid;
    }

    public long getDonotcache() {
        return donotcache;
    }

    public void setDonotcache(long donotcache) {
        this.donotcache = donotcache;
    }

    public String getEmailauth() {
        return emailauth;
    }

    public void setEmailauth(String emailauth) {
        this.emailauth = emailauth;
    }

    public String getEmailsteamid() {
        return emailsteamid;
    }

    public void setEmailsteamid(String emailsteamid) {
        this.emailsteamid = emailsteamid;
    }

    public String getLoginfriendlyname() {
        return loginfriendlyname;
    }

    public void setLoginfriendlyname(String loginfriendlyname) {
        this.loginfriendlyname = loginfriendlyname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember_login() {
        return remember_login;
    }

    public void setRemember_login(boolean remember_login) {
        this.remember_login = remember_login;
    }

    public long getRsatimestamp() {
        return rsatimestamp;
    }

    public void setRsatimestamp(long rsatimestamp) {
        this.rsatimestamp = rsatimestamp;
    }

    public String getTwofactorcode() {
        return twofactorcode;
    }

    public void setTwofactorcode(String twofactorcode) {
        this.twofactorcode = twofactorcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
