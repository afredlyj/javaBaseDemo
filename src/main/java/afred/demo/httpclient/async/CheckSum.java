package afred.demo.httpclient.async;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * Created by Afred on 14-11-26.
 */
public class CheckSum {

    private Logger logger;

    private String url = "http://127.0.0.1:8080?requestid=123456";

    private CloseableHttpAsyncClient client;

    @Before
    public void init() {
        logger = LoggerFactory.getLogger(this.getClass());

        client = HttpAsyncClients.createDefault();
        client.start();
    }

    @Test
    public void sendNonOkMd5() {

        String ua = "helloworld";
        String md5 = "just a test";
        try {
            request(ua, md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendOK() {
        String ua = "sendOk";
        String md5 = DigestUtils.md5Hex(ua);

        try {
            request(ua, md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void request(String ua, String md5) throws Exception {
        final HttpGet get = new HttpGet(url);
        get.addHeader("ua", ua);
        get.addHeader("checkSum", md5);

        for (Header header : get.getAllHeaders()) {
            logger.info("http header : {}, {}", header.getName(), header.getValue());
        }

        Future<HttpResponse> future = client.execute(get, null);
        HttpResponse response = null;
        try {
            response = future.get();
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        byte[] bs = QuickStart.getResponse(response);
        if (null == bs) {
            logger.error("server no response");
            Assert.fail();
        }

        String data = new String(bs);
        logger.info("server response : {}", data);
        Assert.assertNotNull(bs);
    }

}
