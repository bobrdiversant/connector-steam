package ru.dmm.steamservice.data.rs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by Dmitry
 */
public class Descriptions {

    private String type;
    private String value;
    @JsonProperty("app_data")
    private List<AppDataDescriptions> app_data;

    private String color;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AppDataDescriptions> getApp_data() {
        return app_data;
    }

    public void setApp_data(List<AppDataDescriptions> app_data) {
        this.app_data = app_data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
