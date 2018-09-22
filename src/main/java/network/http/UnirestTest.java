package network.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.Base64;
import java.util.Map;

/**
 * Created by wangwei110 on 2018/8/23.
 */
public class UnirestTest {
    public static void main(String args[]) throws UnirestException {
        String appKey = "4F0DECA63FD44DFB864214DB3C0C44E1";
        String appSeceret = "QscLhlCBfNdoT61i3C0n";
        String appToken = getAppTokenFromService(appKey, appSeceret);
        String dataUrl = getAndPost(appToken);

        getFeaturedb(dataUrl, appToken);

//        String appKeyMock = "41AB0123F5214BC1AD7CF633943A7DFB"; /// for test env
//        String appSecretMock = "tJR1AW56FZtcx1xnAdoJ"; /// for test env
//        String appkey = "4F0DECA63FD44DFB864214DB3C0C44E1"; /// for test env
//		  String appSecret = "QscLhlCBfNdoT61i3C0n"; /// for test env
//        String appKeyMock = "E4E8CDAD8F094E14BF4A2C6E25209259"; /// for product env
//        String appSecretMock = "g2aEVvNKHrrSjV1Sqy3x"; /// for product env
//        String appToken = getAppTokenFromService(appKeyMock, appSecretMock);
    }

    private static String getAndPost(String appToken) throws UnirestException {
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
//                System.out.println(jsonResponse);

        Target target =null;

        try {
            ///  1174
            String targetId = "1385";
            HttpResponse<JsonNode> targetResponse = Unirest.get("https://tst.shai.cloud" + "/api/app/targets/"  + targetId)
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

            target = JSON.parseObject(s, new TypeReference<Target>() {});
            System.out.print("getDataUrl" + target.getDataUrl());

        } catch (UnirestException e) {
            System.out.print(e);
        }

        return target.getDataUrl();
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

    public static final String PRODUCT_ENV_URL =  "https://lenovo-mr.com";

    static String getToken( String authorization,
                                  String grantType) {
        ///
        /// https://dev.shai.cloud
        String access_token = "";
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://tst.shai.cloud/oauth/token?grant_type=" + grantType)
//            HttpResponse<JsonNode> jsonResponse = Unirest.post(PRODUCT_ENV_URL + "/oauth/token?grant_type=" + grantType)
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

    private static InputStream getFeaturedb(String dataUrl, String appToken) throws UnirestException {
        HttpResponse<InputStream> targetResponse = null;

        targetResponse = Unirest.get(dataUrl)
                .header("Authorization", "Bearer " + appToken)
                .asBinary();

        InputStream body = targetResponse.getBody();
        return body;
    }

}
