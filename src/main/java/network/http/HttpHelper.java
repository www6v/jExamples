package network.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by wangwei110 on 2018/9/27.
 */
public class HttpHelper {
    CloseableHttpClient m_HttpClient;
    public HttpHelper() {        m_HttpClient = HttpClients.createDefault();    }

    public static void main(String args[]) {
        HttpHelper httpHelper = new HttpHelper();
        String url = "http://10.4.65.128:17070/api/render/cad_feature_db?zipFileName=20180927111100-huaping-realsize_22cm.zip";

        byte[] data = "abc".getBytes();

        try {
            httpHelper.post(url,data, null, "111", "111");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // send bytes and recv bytes
    public byte[] post(String url, byte[] bytes, String contentType, String developerId, String appId) throws IOException
    {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new ByteArrayEntity(bytes));

        if (contentType != null)
            httpPost.setHeader("Content-type", contentType);

        httpPost.setHeader("service-param-developerId", developerId);
        httpPost.setHeader("appId", appId);

        CloseableHttpResponse httpResponse = m_HttpClient.execute(httpPost);

        try {
            HttpEntity entityResponse = httpResponse.getEntity();
            int contentLength = (int) entityResponse.getContentLength();
            if (contentLength <= 0)
                throw new IOException("No response");
            byte[] respBuffer = new byte[contentLength];
            if (entityResponse.getContent().read(respBuffer) != respBuffer.length)
                throw new IOException("Read response buffer error");
            return respBuffer;
        } finally {
            httpResponse.close();
        }
    }

    public byte[] post(String url, byte[] bytes) throws IOException {
        return null;
//        return post(url, bytes, null);
    }

    public void postXml(String url, String str) throws IOException {
//        byte[] reqBuffer = str.getBytes(Charset.forName("UTF-8"));
//        byte[] respBuffer = post(url, reqBuffer, "application/xml; charset=UTF-8");
//        String resp = new String(respBuffer, Charset.forName("UTF-8"));
//        return resp;
    }
}

