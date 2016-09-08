package ru.dmm.steamservice.data.rs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmitry
 */
public class RgDescriptions {
    private int appid;
    private long classid;
    private long instanceid;
    private String icon_url;
    private String icon_url_large;
    private String icon_drag_url;
    private String name;
    private String market_hash_name;
    private String market_name;
    private String name_color;
    private String background_color;
    private String type;
    private int tradable;
    private int marketable;
    private int commodity;
    private List<String> fraudwarnings;
    private long market_fee_app;
    private int market_tradable_restriction;
    private int market_marketable_restriction;
    //FIXME Захочется - доделать
    @JsonIgnore
    private List<Descriptions> descriptions;
    private List<Action> actions;
    private List<OwnerAction> owner_actions;
    private List<Tag> tags;
    private AppData app_data;
    @JsonProperty("market_actions")
    private List<MarketAction> marketActions;
    @JsonProperty("owner_descriptions")
    private String ownerDescriptions;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public long getClassid() {
        return classid;
    }

    public void setClassid(long classid) {
        this.classid = classid;
    }

    public long getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(long instanceid) {
        this.instanceid = instanceid;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getIcon_url_large() {
        return icon_url_large;
    }

    public void setIcon_url_large(String icon_url_large) {
        this.icon_url_large = icon_url_large;
    }

    public String getIcon_drag_url() {
        return icon_drag_url;
    }

    public void setIcon_drag_url(String icon_drag_url) {
        this.icon_drag_url = icon_drag_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket_hash_name() {
        return market_hash_name;
    }

    public void setMarket_hash_name(String market_hash_name) {
        this.market_hash_name = market_hash_name;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getName_color() {
        return name_color;
    }

    public void setName_color(String name_color) {
        this.name_color = name_color;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTradable() {
        return tradable;
    }

    public void setTradable(int tradable) {
        this.tradable = tradable;
    }

    public int getMarketable() {
        return marketable;
    }

    public void setMarketable(int marketable) {
        this.marketable = marketable;
    }

    public int getCommodity() {
        return commodity;
    }

    public void setCommodity(int commodity) {
        this.commodity = commodity;
    }

    public List<String> getFraudwarnings() {
        return fraudwarnings;
    }

    public void setFraudwarnings(List<String> fraudwarnings) {
        this.fraudwarnings = fraudwarnings;
    }

    public long getMarket_fee_app() {
        return market_fee_app;
    }

    public void setMarket_fee_app(long market_fee_app) {
        this.market_fee_app = market_fee_app;
    }

    public int getMarket_tradable_restriction() {
        return market_tradable_restriction;
    }

    public void setMarket_tradable_restriction(int market_tradable_restriction) {
        this.market_tradable_restriction = market_tradable_restriction;
    }

    public int getMarket_marketable_restriction() {
        return market_marketable_restriction;
    }

    public void setMarket_marketable_restriction(int market_marketable_restriction) {
        this.market_marketable_restriction = market_marketable_restriction;
    }

    public List<Descriptions> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Descriptions> descriptions) {
        this.descriptions = descriptions;
       /* if (descriptions instanceof Map) {
            this.descriptions = mapper.convertValue(descriptions, new TypeReference<Map<String, Descriptions>>() {
            });
        } else if (descriptions instanceof List && ((List) descriptions).size() == 0) {
            this.descriptions = Collections.emptyMap();
        } else {
            throw new IllegalArgumentException("Invalid value: " + descriptions);
        }*/
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<OwnerAction> getOwner_actions() {
        return owner_actions;
    }

    public void setOwner_actions(List<OwnerAction> owner_actions) {
        this.owner_actions = owner_actions;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public AppData getApp_data() {
        return app_data;
    }

    public void setApp_data(AppData app_data) {
        this.app_data = app_data;
    }

    public List<MarketAction> getMarketActions() {
        return marketActions;
    }

    public void setMarketActions(List<MarketAction> marketActions) {
        this.marketActions = marketActions;
    }

    public String getOwnerDescriptions() {
        return ownerDescriptions;
    }

    public void setOwnerDescriptions(String ownerDescriptions) {
        this.ownerDescriptions = ownerDescriptions;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
