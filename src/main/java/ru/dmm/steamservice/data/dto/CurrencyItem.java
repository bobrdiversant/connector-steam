package ru.dmm.steamservice.data.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ru.dmm.steamservice.service.CurrencyEnum;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dmitry
 */
// FIXME Доделать получение значений по диапазону дат, чтоб потом можно было выбрать оптимальную цену
public class CurrencyItem {
    private CurrencyEnum currency;
    private List<PerCurrency> buyPrices;
    private List<PerCurrency> sellPrices;

    /**
     * Максимальная цена, которая сейчас выставлена
     */
    public BigDecimal getHighestBuyPrice() {
        List<PerCurrency> buyPrices = getBuyPrices();
        if (buyPrices.size() > 1) {
            return buyPrices.get(0).getPrice();
        }
        return BigDecimal.ZERO;
    }

    /**
     * Минимальная цена по покупке, которая сейчас выставлена
     */
    public BigDecimal getLowestSellPrice() {
        List<PerCurrency> sellPrices = getSellPrices();
        if (getSellPrices().size() > 1) {
            return sellPrices.get(0).getPrice();
        }
        return BigDecimal.ZERO;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    /**
     * Цены за которые покупают
     * Сортировка производится по возрастанию. Первые с минимальной ценой
     */
    public List<PerCurrency> getSellPrices() {
        if (this.sellPrices == null) {
            return Collections.emptyList();
        }
        this.sellPrices.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        return this.sellPrices;
    }

    public void setSellPrices(List<PerCurrency> sellPrices) {
        this.sellPrices = sellPrices;
    }


    public void setBuyPrices(List<PerCurrency> buyPrices) {
        this.buyPrices = buyPrices;
    }

    /**
     * Цены за которые продают
     * Сортировка по убыванию. Первые с высокой ценой
     */
    private List<PerCurrency> getBuyPrices() {
        if (this.buyPrices == null) {
            return Collections.emptyList();
        }
        this.buyPrices.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        Collections.reverse(this.buyPrices);
        return this.buyPrices;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
