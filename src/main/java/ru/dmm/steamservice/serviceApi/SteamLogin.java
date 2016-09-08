package ru.dmm.steamservice.serviceApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import ru.dmm.steamservice.data.rq.DoLoginRq;
import ru.dmm.steamservice.data.rq.RsaKeyRq;
import ru.dmm.steamservice.data.rs.DoLoginRs;
import ru.dmm.steamservice.data.rs.RsaKeyRs;
import ru.dmm.steamservice.data.rs.TransferRs;
import ru.dmm.steamservice.helper.RSA;
import ru.dmm.steamservice.helper.ReadKey;
import ru.dmm.steamservice.service.Context;
import ru.dmm.steamservice.service.SteamLoginService;

import java.io.File;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Collections;

/**
 * Created by Dmitry
 */
public class SteamLogin {

    private String login;
    private String password;

    public SteamLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void connect() {
        SteamLoginService service = new SteamLoginService(login);

        RsaKeyRq rsaKeyRq = new RsaKeyRq();
        rsaKeyRq.setUsername(login);
        rsaKeyRq.setDonotcache(System.currentTimeMillis());

        RsaKeyRs rsaKeyRs = service.getRsaKey(rsaKeyRq);

        RSA rsaCrypto = new RSA(rsaKeyRs.getPublickey_mod(), rsaKeyRs.getPublickey_exp());
        String encryptPassword = rsaCrypto.encrypt(password);

        DoLoginRq doLoginRq = new DoLoginRq();
        doLoginRq.setCaptchagid(-1L);
        doLoginRq.setDonotcache(System.currentTimeMillis());
        doLoginRq.setPassword(encryptPassword);
        doLoginRq.setRsatimestamp(rsaKeyRs.getTimestamp());
        doLoginRq.setUsername(login);
        doLoginRq.setRemember_login(true);

        DoLoginRs doLoginRs = service.doLogin(doLoginRq);

        if (!doLoginRs.isSuccess() && doLoginRs.isRequires_twofactor()) {
            System.out.println("Надо ввести код из мобилки:");
            String consoleOutput = ReadKey.getConsoleOutput();

            doLoginRq.setTwofactorcode(consoleOutput);
            doLoginRs = service.doLogin(doLoginRq);

            System.out.println("doLoginRs:");
            System.out.println(doLoginRs);
            if (doLoginRs.isSuccess() && doLoginRs.isLogin_complete()) {
                TransferRs transferRs = doLoginRs.getTransfer_parameters();
                //Вообще нужно отправлять по всем ссылкам, что пришли в  doLoginRs.getTransfer_urls() но вообще можно не отправлять трансфер
                service.transfer(transferRs);

                Context context = new Context();
                context.setLogin(login);
                context.setSteamId64(transferRs.getSteamid());
                putContext(context);
            }

        }
        if (!doLoginRs.isSuccess() && doLoginRs.isEmailauth_needed()) {
            System.out.println("Надо ввести код из почты на домене " + doLoginRs.getEmaildomain());
            String consoleOutput = ReadKey.getConsoleOutput();

            doLoginRq.setEmailauth(consoleOutput);
            //Не обязательно вроде
            doLoginRq.setEmailsteamid(doLoginRs.getEmailsteamid());
            doLoginRs = service.doLogin(doLoginRq);


            System.out.println("doLoginRs:");
            System.out.println(doLoginRs);

            if (doLoginRs.isSuccess() && doLoginRs.isLogin_complete()) {
                TransferRs transferRs = doLoginRs.getTransfer_parameters();
                //Вообще нужно отправлять по всем ссылкам, что пришли в  doLoginRs.getTransfer_urls() но вообще можно не отправлять трансфер
                service.transfer(transferRs);

                Context context = new Context();
                context.setLogin(login);
                context.setSteamId64(transferRs.getSteamid());

                putContext(context);
            }
        }


    }


    //TreeModel
    //TODO Наверное в отдельный класс вынести и объединить
    private void putContext(Context context) {
        ObjectMapper mapper = new ObjectMapper();
        Context contextValueNode = context;
        try {
            ObjectNode rootNode = mapper.createObjectNode();

            String nameFile = "context.json";
            File file = new File("C:\\Users\\Dmitry\\Desktop\\" + nameFile);
            if (file.exists()) {
                rootNode = (ObjectNode) mapper.readTree(file);
            }

            JsonNode contextNode = rootNode.get(context.getLogin());

            if (contextNode != null) {
                contextValueNode = mapper.treeToValue(contextNode, Context.class);
                if (StringUtils.isNotBlank(context.getLogin())) {
                    contextValueNode.setLogin(context.getLogin());
                }
                if (StringUtils.isNotBlank(context.getSteamId64())) {
                    contextValueNode.setSteamId64(context.getSteamId64());
                }
                if (StringUtils.isNotBlank(context.getCommunityId())) {
                    contextValueNode.setCommunityId(context.getCommunityId());
                }
            }
            rootNode.putPOJO(context.getLogin(), contextValueNode);

            mapper.writeValue(file, rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
