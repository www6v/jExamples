package network.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.Map;

/**
 * Created by wangwei110 on 2018/8/23.
 */
public class UnirestTest {
    public static void main(String args[]) throws UnirestException {
        getAndPost();


//        String appKeyMock = "41AB0123F5214BC1AD7CF633943A7DFB";
//        String appSecretMock = "tJR1AW56FZtcx1xnAdoJ";
//
//        String appToken = getAppTokenFromService(appKeyMock, appSecretMock);
    }

    private static void getAndPost() throws UnirestException {
        //get
//        HttpResponse<Target> targetResponse = Unirest.get("https://dev.shai.cloud/api/app/targets/11111").asObject(Target.class);
//        Target targetObject = targetResponse.getBody();

        //post
//        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://dev.shai.cloud/oauth/token?grant_type=client_credentials")
//                .header("accept", "application/json")
//
//                .header("Content-Type", "application/json")
//                .header("Cache-Control", "no-cache")
//                .header("Authorization", "Basic ZGV2ZWxvcGVyL3Rlc3QxOjEyMzQ1Ng==")
//                .header("Host", "dev.shai.cloud")
//
//                .queryString("app/appKey", "1")
//                .asJson();

        //        System.out.println(jsonResponse);

        String appToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZlbG9wZXJJZCI6MTQ5LCJwZXJtaXNzaW9ucyI6WyJPUiIsIlJFIiwiTUkiXSwic2NvcGUiOlsidHJ1c3QiLCJyZWFkIiwid3JpdGUiXSwiYXBwSWQiOjM2NywiZXhwIjoxNTM2MDUxNDY1LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiJkMTA2M2Y3OS00OWNkLTQ4ODItOTJiMC0wNjg3OWVhOGE1ODciLCJjbGllbnRfaWQiOiJhcHAvNEYwREVDQTYzRkQ0NERGQjg2NDIxNERCM0MwQzQ0RTEifQ.XHa3fVwLyqLzA5NfNm2gH4gaDVyiMH-EYcnYVp4yya2Ax_geTXEAE2Vki4aSmSL3aWUYWuG9uxbe__pVae15tk6JZjGnWW_awYmc-cCHUS4fDVquQKia5kVcjseg_tvU8m8DieDBhA4onxwrMSTfsKOPpbzWj1LypR4CGsjiZljUQSLNpTGVh72Teb4_AHVHvbGed5cehXNxYkW6nAHlRNNp2CIqoIVRbJoQMDzglonkU70_ZjUhJMwIZ4oZp8P7V5qeyDeNYGY-pwrK6BS8tCpO-WtbKigfBAvQzaBLjLwEPULTpDSE47Kufap_PvwVaZEe0YtTcF-SWFGmRXE-bQ";
        try {
            ///  1174
            HttpResponse<JsonNode> targetResponse = Unirest.get("https://tst.shai.cloud" + "/api/app/targets/"  + "11111")
                    .header("Authorization", "Bearer " + appToken)
                    .asObject(JsonNode.class);
            JsonNode body = targetResponse.getBody();
            String s = body.toString();
            int status = targetResponse.getStatus();
            String statusText = targetResponse.getStatusText();
            if(statusText.equals("404")) {
                String emptyUrl = "No target.";
                System.out.print(emptyUrl);
//                return emptyUrl;
            }
            System.out.print("s:"+s);
            System.out.print("status"+ status);

            Target target = JSON.parseObject(s, new TypeReference<Target>() {});
            System.out.print("getDataUrl" + target.getDataUrl());

        } catch (UnirestException e) {
            System.out.print(e);
        }
    }

    public static String getAppTokenFromService(String appKey, String appSecret) {
//        String appKey = "1F38A6F2F16A49E196E13D5532E554B7";
//        String appSecret = "v9gEyqVosfCUMiUj6NUM";

//        String s = String.format("app/%s:%s", appKey, appSecret);
//        String s1 = Base64.getEncoder().encodeToString(s.getBytes());
//        String value = "Basic " + s1;
        String token = getAppToken(appKey, appSecret);
        System.out.println(token);
        return token;
    }

    static String getAppToken(String appKey, String appSecret) {
        String s = String.format("app/%s:%s", appKey, appSecret);
        String s1 = Base64.getEncoder().encodeToString(s.getBytes());
        String value = "Basic " + s1;
//        ResponseEntity<Map> token = getToken(value, "client_credentials");
//        return (String) token.getBody().get("access_token");

        return getToken(value, "client_credentials");
    }

    static String getToken( String authorization,
                                  String grantType) {
        ///
        /// https://dev.shai.cloud
        String access_token = "";
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://tst.shai.cloud/oauth/token?grant_type=" + grantType)
                    .header("Authorization", authorization)
                    .asJson();
            JSONObject object = jsonResponse.getBody().getObject();
            access_token = (String)object.get("access_token");

            System.out.print(access_token);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return access_token;
    }
}
