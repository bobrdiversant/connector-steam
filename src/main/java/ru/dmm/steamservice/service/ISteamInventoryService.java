package ru.dmm.steamservice.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.dmm.steamservice.data.rs.InventoryRs;

/**
 * Created by Dmitry
 */
public interface ISteamInventoryService {

    @GET("id/{communityId}/inventory/")
    Call<ResponseBody> getInventoryRawHtmlByCommunityId(@Path("communityId") String communityId);

    @GET("profiles/{steamId64}/inventory/")
    Call<ResponseBody> getInventoryRawHtmlByProfileId(@Path("steamId64") String steamId64);

    /**
     * Если у профиля есть communityId, то будет редирект
     *
     * @param steamId64
     * @param gameId
     * @param typeInventory
     * @return
     */
    @GET("profiles/{steamId64}/inventory/json/{gameId}/{typeInventory}")
    Call<InventoryRs> getInventorySteamByProfileId(@Path("steamId64") String steamId64, @Path("gameId") long gameId, @Path("typeInventory") int typeInventory);

    /**
     * Дело в том, что при указании steamId64, если есть communityId, то стим делает редирект на уже этот адрес,
     * поэтому чтоб избавиться от лишнего 302, легче сразу вызвать, зная communityID
     *
     * @param steamId64
     * @param gameId
     * @param typeInventory
     * @return
     */
    @GET("id/{communityId}/inventory/json/{gameId}/{typeInventory}")
    Call<InventoryRs> getInventorySteamByCommunityId(@Path("steamId64") String steamId64, @Path("gameId") long gameId, @Path("typeInventory") int typeInventory);
}
