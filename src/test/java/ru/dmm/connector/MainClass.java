package ru.dmm.connector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.dmm.steamservice.data.dto.CurrencyItem;
import ru.dmm.steamservice.data.dto.InventoryItem;
import ru.dmm.steamservice.data.rs.InventoryRs;
import ru.dmm.steamservice.data.rs.ItemCurrencyRs;
import ru.dmm.steamservice.data.rs.ItemPrice;
import ru.dmm.steamservice.service.Context;
import ru.dmm.steamservice.service.CurrencyEnum;
import ru.dmm.steamservice.service.SteamInventoryService;
import ru.dmm.steamservice.service.SteamMarketService;
import ru.dmm.steamservice.serviceApi.SteamInventory;
import ru.dmm.steamservice.serviceApi.SteamLogin;
import ru.dmm.steamservice.serviceApi.SteamMarket;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Dmitry
 */
public class MainClass {
    public static void main(String[] args) {

//        Map<String, String> accountsMap = new HashMap<>();
//        accountsMap.put("bobrkraboedom", "vB6VrbMSK2");
//        accountsMap.put("crackeddima", "vB6VrbMSK2");
//
//        Set<String> loginsSet = accountsMap.keySet();
//        for (String login : loginsSet) {
//            String password = accountsMap.get(login);
//
//            SteamLogin steamLogin = new SteamLogin(login, password);
//            steamLogin.connect();
//        }

        /////////////////////////////

        SteamInventory steamInventory = new SteamInventory("bobrkraboedom");

//        List<Integer> appIdsInventory = steamInventory.getAppIdsInventory();
//        for (Integer appId : appIdsInventory) {
//            List<InventoryItem> inventoryGame = steamInventory.getInventoryGame(appId);
//            inventoryGame.forEach(System.out::println);
//            System.out.println("----------------------------");
//        }

//        List<InventoryItem> tradingCards = steamInventory.getTradingCards();
//        tradingCards.forEach(System.out::println);

//        List<InventoryItem> coupons = steamInventory.getCoupons();
//        coupons.forEach(System.out::println);

//        List<InventoryItem> rewards = steamInventory.getRewards();
//        rewards.forEach(System.out::println);
//
//        List<InventoryItem> inventorySteam = steamInventory.getInventorySteam();
//        inventorySteam.forEach(System.out::println);

        List<CurrencyItem> itemsCurrency = new ArrayList<>();
        SteamMarket steamMarket = new SteamMarket("bobrkraboedom");

        List<InventoryItem> tradingCards = steamInventory.getTradingCards();

        tradingCards.stream().filter(InventoryItem::isMarketable).forEach(inventoryItem -> {
            CurrencyItem currencyItem = steamMarket.getCurrency(inventoryItem, CurrencyEnum.RUSSIAN_RUBLE);
            itemsCurrency.add(currencyItem);
        });

        for (CurrencyItem currencyItem : itemsCurrency) {
            System.out.println(currencyItem);
            System.out.println(currencyItem.getHighestBuyPrice());
            System.out.println(currencyItem.getLowestSellPrice());
        }
    }

}
