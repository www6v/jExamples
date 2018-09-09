package network.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wangwei110 on 2018/8/31.
 */
public class RestTemplateTest {
    public static void main(String args[]) {
        String zipUrl = "http://tst.shai.cloud:8180/api/app/fs/contents/20180831152749-nb1.zip";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("service-param-developerId", "149");
        RestTemplate template = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<byte[]> response = template.exchange(zipUrl, HttpMethod.GET, requestEntity, byte[].class);
    }
}
