package ru.dmm.steamservice.data.rs;

import ru.dmm.steamservice.data.AbstractRqRs;

/**
 * Created by Dmitry
 */
public class RsaKeyRs extends AbstractRqRs {

    private boolean success;
    private String publickey_mod;
    private String publickey_exp;
    private long timestamp;
    private String steamid;
    private String token_gid;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPublickey_mod() {
        return publickey_mod;
    }

    public void setPublickey_mod(String publickey_mod) {
        this.publickey_mod = publickey_mod;
    }

    public String getPublickey_exp() {
        return publickey_exp;
    }

    public void setPublickey_exp(String publickey_exp) {
        this.publickey_exp = publickey_exp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getToken_gid() {
        return token_gid;
    }

    public void setToken_gid(String token_gid) {
        this.token_gid = token_gid;
    }
}
