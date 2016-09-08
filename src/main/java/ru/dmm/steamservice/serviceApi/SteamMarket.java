package ru.dmm.steamservice.serviceApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dmm.steamservice.data.dto.CurrencyItem;
import ru.dmm.steamservice.data.dto.InventoryItem;
import ru.dmm.steamservice.data.dto.PerCurrency;
import ru.dmm.steamservice.data.rs.ItemCurrencyRs;
import ru.dmm.steamservice.data.rs.ItemPrice;
import ru.dmm.steamservice.service.CurrencyEnum;
import ru.dmm.steamservice.service.SteamMarketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry
 */
public class SteamMarket {
    private static final Logger LOGGER = LoggerFactory.getLogger(SteamMarket.class);


    private String login;
    private SteamMarketService service;

    public SteamMarket(String login) {
        this.login = login;
    }

    public CurrencyItem getCurrency(InventoryItem inventoryItem, CurrencyEnum currency) {
        ItemCurrencyRs marketItemCurrency = getService().getMarketItemCurrency(inventoryItem.getAppId(), inventoryItem.getMarketHashName(), currency);

        CurrencyItem currencyItem = new CurrencyItem();
        currencyItem.setCurrency(currency);

        List<ItemPrice> buy_order_graph = marketItemCurrency.getBuy_order_graph();
        currencyItem.setBuyPrices(parseCurrency(buy_order_graph));

        List<ItemPrice> sell_order_graph = marketItemCurrency.getSell_order_graph();
        currencyItem.setSellPrices(parseCurrency(sell_order_graph));

        return currencyItem;
    }

    sellItem

    private List<PerCurrency> parseCurrency(List<ItemPrice> original) {
        List<PerCurrency> buyPrices = new ArrayList<>();

        for (ItemPrice itemPrice : original) {
            if (itemPrice.getPrice() == null) {
                continue;
            }
            PerCurrency perCurrency = new PerCurrency(itemPrice.getCount(), itemPrice.getPrice());
            buyPrices.add(perCurrency);
        }

        return buyPrices;
    }

    private SteamMarketService getService() {
        //TODO сделать синхронайз
        if (service == null) {
            service = new SteamMarketService(login);
        }
        return service;
    }
}
