package ru.dmm.steamservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import retrofit2.Call;
import retrofit2.Response;
import ru.dmm.steamservice.data.rs.InventoryRs;
import ru.dmm.steamservice.http.ServiceGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry
 */
public class SteamInventoryService {

    private ISteamInventoryService inventoryService;
    private Context context;
    /*static {
        inventoryService = ServiceGenerator.createService(ISteamInventoryService.class, UrlServiceEnum.STEAMCOMMUNITY_SERVICE.getUrl());
    }*/

    public SteamInventoryService(String login) {
        context = getContext(login);
        inventoryService = ServiceGenerator.createService(ISteamInventoryService.class, UrlServiceEnum.STEAMCOMMUNITY_SERVICE.getUrl(), login);
    }

    public List<Integer> getIdsInventoryTabs() {
        List<Integer> idsTabs = new ArrayList<>();

        String rawHtml = getInventoryRawHtml();

        Document document = Jsoup.parse(rawHtml);
        Elements games_list_tabs = document.getElementsByClass("games_list_tabs");
        if (games_list_tabs.size() != 1) {
            //Exception
        }
        Elements gameLinks = games_list_tabs.get(0).getElementsByTag("a");
        for (Element link : gameLinks) {
            String id = link.attr("id");
            int gameId = Integer.parseInt(id.replaceAll("\\D+", ""));
            idsTabs.add(gameId);
        }
        return idsTabs;
    }

    public InventoryRs getInventorySteam(long gameId, int typeInventory) {
        InventoryRs inventoryRs = null;

        Call<InventoryRs> call;
        if (StringUtils.isNotBlank(context.getCommunityId())) {
            call = inventoryService.getInventorySteamByCommunityId(context.getCommunityId(), gameId, typeInventory);
        } else {
            call = inventoryService.getInventorySteamByProfileId(context.getSteamId64(), gameId, typeInventory);
        }
        try {
            Response<InventoryRs> response = call.execute();
            inventoryRs = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inventoryRs;
    }

    //TreeModel
    //TODO Наверное в отдельный класс вынести и объединить
    private Context getContext(String login) {
        ObjectMapper mapper = new ObjectMapper();

        String nameFile = "context.json";
        File file = new File("C:\\Users\\Dmitry\\Desktop\\" + nameFile);

        try {
            ObjectNode rootNode = (ObjectNode) mapper.readTree(file);
            JsonNode jsonNode = rootNode.get(login);
            return mapper.treeToValue(jsonNode, Context.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getInventoryRawHtml() {
        String rawHtml = "";
        Call<ResponseBody> call;
        if (StringUtils.isNotBlank(context.getCommunityId())) {
            call = inventoryService.getInventoryRawHtmlByCommunityId(context.getCommunityId());
        } else {
            call = inventoryService.getInventoryRawHtmlByProfileId(context.getSteamId64());
        }
        try {
            Response<ResponseBody> response = call.execute();
            ResponseBody body = response.body();
            rawHtml = body.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawHtml;
    }
}
