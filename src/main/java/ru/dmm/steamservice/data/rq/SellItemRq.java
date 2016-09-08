package ru.dmm.steamservice.data.rq;

import ru.dmm.steamservice.data.AbstractRqRs;

/**
 * Created by Dmitry
 */
public class SellItemRq extends AbstractRqRs {
    private String sessionId;
    private long appid;
    private int contextid;
    private long assetid;
    private int amount;
    /**
     * Хитрожопый прайс, который формируется, как целое число (45,56 руб = 4556)
     * Цена причем НЕ "в покупатель заплатит", а в "Вы получите". Соотетственно нужно самому конвертировать
     */
    private int price;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getAppid() {
        return appid;
    }

    public void setAppid(long appid) {
        this.appid = appid;
    }

    public int getContextid() {
        return contextid;
    }

    public void setContextid(int contextid) {
        this.contextid = contextid;
    }

    public long getAssetid() {
        return assetid;
    }

    public void setAssetid(long assetid) {
        this.assetid = assetid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
