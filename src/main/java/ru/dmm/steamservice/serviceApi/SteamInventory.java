package ru.dmm.steamservice.serviceApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dmm.steamservice.data.dto.InventoryItem;
import ru.dmm.steamservice.data.rs.InventoryRs;
import ru.dmm.steamservice.data.rs.RgDescriptions;
import ru.dmm.steamservice.data.rs.RgInventory;
import ru.dmm.steamservice.service.SteamInventoryService;

import java.util.*;

/**
 * Created by Dmitry
 */
public class SteamInventory {
    private static final Logger LOGGER = LoggerFactory.getLogger(SteamInventory.class);

    /**
     * айдишник инвенторя стима
     */
    private static final long STEAM_GAME_ID = 753L;

    private String login;
    private SteamInventoryService service;

    public SteamInventory(String login) {
        this.login = login;
    }

    public List<InventoryItem> getInventorySteam() {
        InventoryRs inventoryRs = getService().getInventorySteam(STEAM_GAME_ID, 1);
        System.out.println(inventoryRs);

        if (inventoryRs != null && inventoryRs.isSuccess()) {
            return parse(inventoryRs);
        }

        return Collections.emptyList();
    }

    public List<InventoryItem> getInventoryGame(long gameId) {
        InventoryRs inventoryRs = getService().getInventorySteam(gameId, 2);
        System.out.println(inventoryRs);

        if (inventoryRs != null && inventoryRs.isSuccess()) {
            return parse(inventoryRs);
        }

        return Collections.emptyList();
    }

    public List<InventoryItem> getCoupons() {
        InventoryRs inventoryRs = getService().getInventorySteam(STEAM_GAME_ID, 3);
        System.out.println(inventoryRs);

        if (inventoryRs != null && inventoryRs.isSuccess()) {
            return parse(inventoryRs);
        }

        return Collections.emptyList();
    }

    public List<InventoryItem> getTradingCards() {
        InventoryRs inventoryRs = getService().getInventorySteam(STEAM_GAME_ID, 6);
        System.out.println(inventoryRs);

        if (inventoryRs != null && inventoryRs.isSuccess()) {
            return parse(inventoryRs);
        }

        return Collections.emptyList();
    }

    public List<InventoryItem> getRewards() {
        InventoryRs inventoryRs = getService().getInventorySteam(STEAM_GAME_ID, 7);
        System.out.println(inventoryRs);

        if (inventoryRs != null && inventoryRs.isSuccess()) {
            return parse(inventoryRs);
        }

        return Collections.emptyList();
    }

    public List<Integer> getAppIdsInventory() {
        return getService().getIdsInventoryTabs();
    }

    private SteamInventoryService getService() {
        //TODO сделать синхронайз
        if (service == null) {
            service = new SteamInventoryService(login);
        }
        return service;
    }

    private List<InventoryItem> parse(InventoryRs inventoryRs) {
        List<InventoryItem> result = new ArrayList<>();

        Map<String, RgInventory> rgInventory = inventoryRs.getRgInventory();
        Map<String, RgDescriptions> rgDescriptions = inventoryRs.getRgDescriptions();

        Set<String> keys = rgInventory.keySet();
        for (String key : keys) {
            RgInventory inventoryItemRg = rgInventory.get(key);

            InventoryItem item = new InventoryItem();
            item.setId(inventoryItemRg.getId());
            item.setClassId(inventoryItemRg.getClassid());
            item.setInstanceId(inventoryItemRg.getInstanceid());
            item.setAmount(inventoryItemRg.getAmount());
            item.setPos(inventoryItemRg.getPos());

            RgDescriptions rgDescription = rgDescriptions.get(item.getClassId() + "_" + item.getInstanceId());
            item.setIconUrl(rgDescription.getIcon_url());
            item.setIconUrlLarge(rgDescription.getIcon_url_large());
            item.setName(rgDescription.getName());
            item.setMarketHashName(rgDescription.getMarket_hash_name());
            item.setMarketName(rgDescription.getMarket_name());
            item.setType(rgDescription.getType());
            item.setTradable(rgDescription.getTradable() == 1);
            item.setMarketable(rgDescription.getMarketable() == 1);
            item.setCommodity(rgDescription.getCommodity() == 1);
            item.setMarketFeeApp(rgDescription.getMarket_fee_app());
            item.setDayUntradable(rgDescription.getMarket_tradable_restriction());
            item.setDayUnmarketable(rgDescription.getMarket_tradable_restriction());

            item.setAppId(rgDescription.getAppid());
            item.setFraudwarnings(rgDescription.getFraudwarnings());

            //TODO доделать
            item.setDescriptions(new ArrayList<>());
            result.add(item);
        }

        return result;
    }
}
