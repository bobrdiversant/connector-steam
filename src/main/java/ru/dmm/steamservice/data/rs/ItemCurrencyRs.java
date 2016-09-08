package ru.dmm.steamservice.data.rs;

import ru.dmm.steamservice.data.AbstractRqRs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry
 */
public class ItemCurrencyRs extends AbstractRqRs {
    private Integer success;
    private String sell_order_table;
    private String sell_order_summary;
    private String buy_order_table;
    private String buy_order_summary;
    private Integer highest_buy_order;
    private Integer lowest_sell_order;
    private List<ItemPrice> buy_order_graph;
    private List<ItemPrice> sell_order_graph;
    private Float graph_max_y;
    private Float graph_min_x;
    private Float graph_max_x;
    private String price_prefix;
    private String price_suffix;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getSell_order_table() {
        return sell_order_table;
    }

    public void setSell_order_table(String sell_order_table) {
        this.sell_order_table = sell_order_table;
    }

    public String getSell_order_summary() {
        return sell_order_summary;
    }

    public void setSell_order_summary(String sell_order_summary) {
        this.sell_order_summary = sell_order_summary;
    }

    public String getBuy_order_table() {
        return buy_order_table;
    }

    public void setBuy_order_table(String buy_order_table) {
        this.buy_order_table = buy_order_table;
    }

    public String getBuy_order_summary() {
        return buy_order_summary;
    }

    public void setBuy_order_summary(String buy_order_summary) {
        this.buy_order_summary = buy_order_summary;
    }

    public Integer getHighest_buy_order() {
        return highest_buy_order;
    }

    public void setHighest_buy_order(Integer highest_buy_order) {
        this.highest_buy_order = highest_buy_order;
    }

    public Integer getLowest_sell_order() {
        return lowest_sell_order;
    }

    public void setLowest_sell_order(Integer lowest_sell_order) {
        this.lowest_sell_order = lowest_sell_order;
    }

    public List<ItemPrice> getBuy_order_graph() {
        return buy_order_graph;
    }

    public void setBuy_order_graph(Object buy_order_graph) {
        if (buy_order_graph instanceof List) {
            this.buy_order_graph = new ArrayList<>();
            ArrayList<Object> arrayTemp = (ArrayList<Object>) buy_order_graph;
            for (Object object : arrayTemp) {
                this.buy_order_graph.add(parseItem(object));
            }
        } else {
            throw new IllegalArgumentException("Invalid value: " + buy_order_graph);
        }
    }

    public List<ItemPrice> getSell_order_graph() {
        return sell_order_graph;
    }

    public void setSell_order_graph(Object sell_order_graph) {
        if (sell_order_graph instanceof List) {
            this.sell_order_graph = new ArrayList<>();
            ArrayList<Object> arrayTemp = (ArrayList<Object>) sell_order_graph;
            for (Object object : arrayTemp) {
                this.sell_order_graph.add(parseItem(object));
            }
        } else {
            throw new IllegalArgumentException("Invalid value: " + sell_order_graph);
        }
    }

    public Float getGraph_max_y() {
        return graph_max_y;
    }

    public void setGraph_max_y(Float graph_max_y) {
        this.graph_max_y = graph_max_y;
    }

    public Float getGraph_min_x() {
        return graph_min_x;
    }

    public void setGraph_min_x(Float graph_min_x) {
        this.graph_min_x = graph_min_x;
    }

    public Float getGraph_max_x() {
        return graph_max_x;
    }

    public void setGraph_max_x(Float graph_max_x) {
        this.graph_max_x = graph_max_x;
    }

    public String getPrice_prefix() {
        return price_prefix;
    }

    public void setPrice_prefix(String price_prefix) {
        this.price_prefix = price_prefix;
    }

    public String getPrice_suffix() {
        return price_suffix;
    }

    public void setPrice_suffix(String price_suffix) {
        this.price_suffix = price_suffix;
    }

    private ItemPrice parseItem(Object object) {
        ArrayList<Object> item = (ArrayList<Object>) object;
        ItemPrice itemPrice = new ItemPrice();
        if (item.get(0) instanceof Double) {
            itemPrice.setPrice(BigDecimal.valueOf((Double) item.get(0)));
        }
        if (item.get(1) instanceof Integer) {
            itemPrice.setCount((Integer) item.get(1));
        }
        if (item.get(2) instanceof String) {
            itemPrice.setDescription((String) item.get(2));
        }
        return itemPrice;
    }

}
