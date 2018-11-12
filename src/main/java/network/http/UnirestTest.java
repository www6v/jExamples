package network.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.body.Body;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.request.body.RawBody;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;

/**
 * Created by wangwei110 on 2018/8/23.
 */
public class UnirestTest {
    public static void main(String args[]) throws UnirestException {
        ////////
//        String appKey = "4F0DECA63FD44DFB864214DB3C0C44E1";
//        String appSeceret = "QscLhlCBfNdoT61i3C0n";
//        String appToken = getAppTokenFromService(appKey, appSeceret);
//        String dataUrl = getAndPost(appToken);
//
//        getFeaturedb(dataUrl, appToken);

        /////
//        String appKeyMock = "41AB0123F5214BC1AD7CF633943A7DFB"; /// for test env
//        String appSecretMock = "tJR1AW56FZtcx1xnAdoJ"; /// for test env
//        String appkey = "4F0DECA63FD44DFB864214DB3C0C44E1"; /// for test env
//		  String appSecret = "QscLhlCBfNdoT61i3C0n"; /// for test env
//        String appKeyMock = "E4E8CDAD8F094E14BF4A2C6E25209259"; /// for product env
//        String appSecretMock = "g2aEVvNKHrrSjV1Sqy3x"; /// for product env
//        String appToken = getAppTokenFromService(appKeyMock, appSecretMock);


        test();

//        Content content = test1();
//        test2(1L, 1L, 1L, content, "D:\\pic\\c1.png");

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

    private static void test() {
//        byte[] data = "abc".getBytes();
        String url = "https://dev.shai.cloud/api/app/fs/targets/tag1809.jpg?access_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZlbG9wZXJJZCI6MTQ5LCJwZXJtaXNzaW9ucyI6WyJPUiJdLCJzY29wZSI6WyJ0cnVzdCIsInJlYWQiLCJ3cml0ZSJdLCJhcHBJZCI6NTM0LCJleHAiOjE1NDIxNzkxNTMsImF1dGhvcml0aWVzIjpbIlJPTEVfQVBQIl0sImp0aSI6IjAzOTQzYTg0LWY5ODEtNDYxMy04M2ZlLWEzMzM4YjliOGNkMyIsImNsaWVudF9pZCI6ImFwcC9BQTQyQkJFNDZFNTE0NzE4QTA1NTM1RkQwMjAwNkQyMyJ9.V5KH4DfPi0BcOSuTity3VwszJvOEZlcH9vqePbWLH-u7sHFl411FudnevGQeAYvqXgqegx85yL1s9nZeMw1z36RQ4zrfNrmVt5RNwHq7UzyXN8IrVobdHZBgMWijMtafu7GfLMWB_DhZmLX0yaOIyjHanEVQOQ8dNXcBEpQJwHgF58YV2v5RwkB5PjXCP0K4itK6XrVaSPTHupiFksn6ozbL3TePloVx9Io-IpHMvgXe9HJGM28KwS3sbBZsAVxe4lodOo8uOj873q4pNkSN3ZpBELrc3lAsEVbKZMykeNlOLMGMHwghdYu_4yIPDod0xh4oZAfa-uw9V7SLZD9c5g";
//        GetRequest getRequest = Unirest.get("url");
//        Body body = getRequest.getBody();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("service-param-developerId", "123");
        RestTemplate template = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<byte[]> response = template.exchange(url, HttpMethod.GET, requestEntity, byte[].class);
        byte[] result = response.getBody();
        System.out.println(result.length);

//                .header("service-param-developerId", "111")
//                .header("appId", "111");
//                .queryString("zipFileName", "dfsdfsf.zip")
//                .field("file", data, "image.jpg");
//                .body(data);

//        System.out.print(body.getBody());

    }


    private static Content test1() {

        Content content = new Content();
        content.setAppId(111L);
        content.setDeveloperId(111L);
        content.setObjectType(2);
        Content content1=null;

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://10.106.23.184:8280/api/v2/app/targets?convertToTypes=")
                    //            HttpResponse<JsonNode> jsonResponse = Unirest.post(PRODUCT_ENV_URL + "/oauth/token?grant_type=" + grantType)
                    .header("service-param-developerId", String.valueOf(11l))
                    .header("service-param-appId", String.valueOf(111))
                    .queryString("content", content)
                    .asJson();

            content1 = JSON.parseObject(jsonResponse.getBody().toString(), new TypeReference<Content>() {});

            JSONObject object = jsonResponse.getBody().getObject();
            Object id = object.get("id");

            System.out.print(id);
            System.out.print(content1);

        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return content1;
    }

    private static void test2(Long developerId,Long  appId,Long  contentId, Content contentCreated, String fileName) {

        File file = new File(fileName);
//        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
//                MediaType.TEXT_PLAIN_VALUE, true, file.getName());
//
//        logger.info("fileName: " + fileName);
//
//        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
//            IOUtils.copy(input, os);
//        }
//        catch (Exception e) {
//            throw new IllegalArgumentException("Invalid file: " + e, e);
//        }
//        MultipartFile multi = new CommonsMultipartFile(fileItem);

        String conid = "123";

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://10.106.23.184:8280/api/v2/app/targets/cad")
                    //            HttpResponse<JsonNode> jsonResponse = Unirest.post(PRODUCT_ENV_URL + "/oauth/token?grant_type=" + grantType)
                    .header("service-param-developerId", String.valueOf(11l))
                    .header("service-param-appId", String.valueOf(111))
                    .queryString("conId",conid )
                    .queryString("origin", contentCreated)
                    .field("file", new File(fileName))
                    .field("img", new File(fileName))
                    .asJson();

            System.out.print(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }


//    private static void test3(Long developerId,Long  appId,Long  contentId, Content contentCreated, String fileName) {
//        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://10.106.23.184:8280/api/v2/app/targets" + contentId)
//                //            HttpResponse<JsonNode> jsonResponse = Unirest.post(PRODUCT_ENV_URL + "/oauth/token?grant_type=" + grantType)
//                .header("service-param-developerId", String.valueOf(11l))
//                .header("service-param-appId", String.valueOf(111))
//                .queryString("conId",contentId )
//                .queryString("origin", contentCreated)
//                .field("file", new File(fileName))
//                .field("img", new File(fileName))
//                .asJson();
//    }

}


