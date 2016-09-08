package ru.dmm.steamservice.data.rs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ru.dmm.steamservice.data.AbstractRqRs;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmitry
 */
public class InventoryRs extends AbstractRqRs {
    private boolean success;
    @JsonProperty("Error")
    private String error;
    private Map<String, RgInventory> rgInventory;
    private Map<String, RgCurrency> rgCurrency;
    private Map<String, RgDescriptions> rgDescriptions;
    private boolean more;
    private String more_start;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, RgInventory> getRgInventory() {
        return rgInventory;
    }

    public void setRgInventory(Object rgInventory) {
        if (rgInventory instanceof Map) {
            ObjectMapper mapper = new ObjectMapper();
            this.rgInventory = mapper.convertValue(rgInventory, new TypeReference<Map<String, RgInventory>>() {
            });
        } else if (rgInventory instanceof List && ((List) rgInventory).size() == 0) {
            this.rgInventory = Collections.emptyMap();
        } else {
            throw new IllegalArgumentException("Invalid value: " + rgInventory);
        }
    }

    public Map<String, RgCurrency> getRgCurrency() {
        return rgCurrency;
    }

    public void setRgCurrency(Object rgCurrency) {
        if (rgCurrency instanceof Map) {
            ObjectMapper mapper = new ObjectMapper();
            this.rgCurrency = mapper.convertValue(rgInventory, new TypeReference<Map<String, RgCurrency>>() {
            });
        } else if (rgCurrency instanceof List && ((List) rgCurrency).size() == 0) {
            this.rgCurrency = Collections.emptyMap();
        } else {
            throw new IllegalArgumentException("Invalid value: " + rgCurrency);
        }
    }

    public Map<String, RgDescriptions> getRgDescriptions() {
        return rgDescriptions;
    }

    public void setRgDescriptions(Object rgDescriptions) {
        //   this.rgDescriptions=rgDescriptions;
        if (rgDescriptions instanceof Map) {
            ObjectMapper mapper = new ObjectMapper();
            this.rgDescriptions = mapper.convertValue(rgDescriptions, new TypeReference<Map<String, RgDescriptions>>() {
            });
        } else if (rgDescriptions instanceof List && ((List) rgDescriptions).size() == 0) {
            this.rgDescriptions = Collections.emptyMap();
        } else {
            throw new IllegalArgumentException("Invalid value: " + rgDescriptions);
        }
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public String getMore_start() {
        return more_start;
    }

    public void setMore_start(String more_start) {
        this.more_start = more_start;
    }
}
