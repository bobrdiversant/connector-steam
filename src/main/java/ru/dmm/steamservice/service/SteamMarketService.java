package ru.dmm.steamservice.service;

import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import retrofit2.Call;
import retrofit2.Response;
import ru.dmm.steamservice.data.rs.ItemCurrencyRs;
import ru.dmm.steamservice.data.rq.SellItemRq;
import ru.dmm.steamservice.data.rs.SellItemRs;
import ru.dmm.steamservice.http.ServiceGenerator;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitry
 */
public class SteamMarketService {
    private ISteamMarketService marketService;

    public SteamMarketService(String login) {
        marketService = ServiceGenerator.createService(ISteamMarketService.class, UrlServiceEnum.MARKET_SERVICE.getUrl(), login);
    }

    private String getMarketItemRawHtml(long appId, String marketHashName) {
        String rawHtml = "";
        Call<ResponseBody> call = marketService.getMarketItemRawHtml(appId, marketHashName);
        try {
            Response<ResponseBody> response = call.execute();
            ResponseBody body = response.body();
            rawHtml = body.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawHtml;
    }

    private long getItemNameId(long appId, String marketHashName) {
        String rawHtml = getMarketItemRawHtml(appId, marketHashName);

        Document document = Jsoup.parse(rawHtml);
        Elements scriptElements = document.getElementsByTag("script");
        Element lastScriptTag = scriptElements.get(scriptElements.size() - 1);
        for (DataNode node : lastScriptTag.dataNodes()) {
            String wholeData = node.getWholeData();

            Pattern regex = Pattern.compile("(?s)Market_LoadOrderSpread\\( (.*?) \\);");
            Matcher matcher = regex.matcher(wholeData);
            String itemNameIdString = "";
            if (matcher.find()) {
                itemNameIdString = matcher.group(1);
            }
            return Integer.parseInt(itemNameIdString.replaceAll("\\D+", ""));
        }
        //FIXME
        return 1L;
    }

    public ItemCurrencyRs getMarketItemCurrency(long appId, String marketHashName, CurrencyEnum currency) {
        ItemCurrencyRs itemCurrencyResponse = null;
        long itemNameId = getItemNameId(appId, marketHashName);
        Call<ItemCurrencyRs> itemCurrencyCall = marketService.getMarketItemCurrency("english", currency.getCodeCurrency(), itemNameId);
        try {
            Response<ItemCurrencyRs> response = itemCurrencyCall.execute();
            itemCurrencyResponse = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemCurrencyResponse;
    }

    public SellItemRs sellItem(SellItemRq sellItemRq) {
        SellItemRs sellItemRs = null;
        Call<SellItemRs> callTransfer = marketService.sellItem(sellItemRq.getFieldMap());
        try {
            Response<SellItemRs> response = callTransfer.execute();
            sellItemRs = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sellItemRs;
    }
}
