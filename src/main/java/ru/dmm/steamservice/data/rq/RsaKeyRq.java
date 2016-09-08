package ru.dmm.steamservice.data.rq;

import ru.dmm.steamservice.data.AbstractRqRs;

/**
 * Created by Dmitry
 */
public class RsaKeyRq extends AbstractRqRs {
    private long donotcache;
    private String username;

    public long getDonotcache() {
        return donotcache;
    }

    public void setDonotcache(long donotcache) {
        this.donotcache = donotcache;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
