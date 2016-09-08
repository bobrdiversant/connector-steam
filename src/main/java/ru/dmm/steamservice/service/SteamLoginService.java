package ru.dmm.steamservice.service;

import retrofit2.Call;
import retrofit2.Response;
import ru.dmm.steamservice.data.rq.DoLoginRq;
import ru.dmm.steamservice.data.rq.RsaKeyRq;
import ru.dmm.steamservice.data.rs.DoLoginRs;
import ru.dmm.steamservice.data.rs.RsaKeyRs;
import ru.dmm.steamservice.data.rs.TransferRs;
import ru.dmm.steamservice.http.ServiceGenerator;

import java.io.IOException;

/**
 * Created by Dmitry
 */
public class SteamLoginService {
    private ISteamLoginService loginService;

    public SteamLoginService(String login) {
        loginService = ServiceGenerator.createService(ISteamLoginService.class, UrlServiceEnum.LOGIN_SERVICE.getUrl(), login);
    }

    public RsaKeyRs getRsaKey(RsaKeyRq rsaKeyRequest) {
        RsaKeyRs rsaKeyResponse = null;
        Call<RsaKeyRs> callRsaKey = loginService.getRsaKey(rsaKeyRequest.getFieldMap());
        try {
            Response<RsaKeyRs> response = callRsaKey.execute();
            rsaKeyResponse = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsaKeyResponse;
    }

    public DoLoginRs doLogin(DoLoginRq doLoginRequest) {
        DoLoginRs doLoginResponse = null;
        Call<DoLoginRs> callDoLogin = loginService.doLogin(doLoginRequest.getFieldMap());
        try {
            Response<DoLoginRs> response = callDoLogin.execute();
            doLoginResponse = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doLoginResponse;
    }

    public void transfer(TransferRs transferRequest) {
        Call<Void> callTransfer = loginService.transfer(transferRequest.getFieldMap());
        try {
            callTransfer.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
