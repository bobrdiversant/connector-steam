package ru.dmm.steamservice.data.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by Dmitry
 */
public class InventoryItem {

    private long id;
    private long classId;
    private long instanceId;
    private int amount;
    private int pos;

    private long appId;
    private String iconUrl;
    private String iconUrlLarge;
    private String name;
    private String marketHashName;
    private String marketName;
    private String type;
    private boolean tradable;
    private boolean marketable;
    private boolean commodity;
    /**
     * market_fee_app
     */
    private long marketFeeApp;

    /**
     * market_tradable_restriction
     * Сколько дней, для которых деталь будет untradable после продажи на рынке.
     */
    private int dayUntradable;
    /**
     * market_marketable_restriction
     * Сколько дней, для которых деталь будет unmarketable после продажи на рынке.
     */
    private int dayUnmarketable;

    /**
     * Берем только словесные описания
     */
    private List<String> descriptions;

    private List<String> fraudwarnings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrlLarge() {
        return iconUrlLarge;
    }

    public void setIconUrlLarge(String iconUrlLarge) {
        this.iconUrlLarge = iconUrlLarge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketHashName() {
        return marketHashName;
    }

    public void setMarketHashName(String marketHashName) {
        this.marketHashName = marketHashName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTradable() {
        return tradable;
    }

    public void setTradable(boolean tradable) {
        this.tradable = tradable;
    }

    public boolean isMarketable() {
        return marketable;
    }

    public void setMarketable(boolean marketable) {
        this.marketable = marketable;
    }

    public boolean isCommodity() {
        return commodity;
    }

    public void setCommodity(boolean commodity) {
        this.commodity = commodity;
    }

    public long getMarketFeeApp() {
        return marketFeeApp;
    }

    public void setMarketFeeApp(long marketFeeApp) {
        this.marketFeeApp = marketFeeApp;
    }

    public int getDayUntradable() {
        return dayUntradable;
    }

    public void setDayUntradable(int dayUntradable) {
        this.dayUntradable = dayUntradable;
    }

    public int getDayUnmarketable() {
        return dayUnmarketable;
    }

    public void setDayUnmarketable(int dayUnmarketable) {
        this.dayUnmarketable = dayUnmarketable;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getFraudwarnings() {
        return fraudwarnings;
    }

    public void setFraudwarnings(List<String> fraudwarnings) {
        this.fraudwarnings = fraudwarnings;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
