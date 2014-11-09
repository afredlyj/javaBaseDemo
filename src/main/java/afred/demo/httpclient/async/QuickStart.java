package afred.demo.httpclient.async;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Created by Afred on 14-11-9.
 */
public class QuickStart {

    private static Logger logger = LoggerFactory.getLogger(QuickStart.class);

    public static void main(String[] args) {

        logger.info("quick start ... ");

        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();

        client.start();

        String url = "http://192.168.1.104:8080?requestId=123";

        try {
            simpleRequest(client, url);
//            callbackRequest(client, url);
//            streamRequest(client, url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("quick start end ... ");
    }

    private static void simpleRequest(CloseableHttpAsyncClient client, String url) throws Exception {

        final HttpGet get = new HttpGet(url);
//        get.set

        logger.info("simple request begin ... ");
        Future<HttpResponse> future = client.execute(get, null);
        HttpResponse response = future.get();
        RequestLine requestLine = get.getRequestLine();
        logger.info("request line : {}", requestLine);

        StatusLine responseLine = response.getStatusLine();
        logger.info("response line  : {}", responseLine);

        logger.info("simple request finish ...");
    }

    private static void callbackRequest(CloseableHttpAsyncClient client, String url) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);

        logger.info("callback request begin ... ");
        final HttpGet get = new HttpGet(url);
        get.setHeader("Connection", "close");
        client.execute(get, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                latch.countDown();
                logger.info("callback request return {}", result.getStatusLine());

                try {
                    byte[] bs = getResponse(result);
                    logger.info("callback response : {}", new String(bs));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(Exception ex) {
                latch.countDown();
                logger.info("fail to get response from sever : {}", ex);
            }

            @Override
            public void cancelled() {
                logger.info("request cancelled");
            }
        });

        latch.await();
        logger.info("callback request finished ... ");
    }

    private static void streamRequest(CloseableHttpAsyncClient client, String url) throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);

        logger.info("stream request begin ... ");

        final HttpGet get = new HttpGet(url);

        HttpAsyncRequestProducer producer = HttpAsyncMethods.create(get);

        AsyncCharConsumer<HttpResponse> consumer = new AsyncCharConsumer<HttpResponse>() {

            HttpResponse response;

            @Override
            protected void onCharReceived(CharBuffer buf, IOControl ioctrl) throws IOException {

            }

            @Override
            protected void onResponseReceived(HttpResponse response) throws HttpException, IOException {
                this.response = response;
            }

            @Override
            protected HttpResponse buildResult(HttpContext context) throws Exception {
                return this.response;
            }
        };

        client.execute(producer, consumer, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                latch.countDown();
                logger.info("stream request response : {}", result.getStatusLine());
            }

            @Override
            public void failed(Exception ex) {
                latch.countDown();
                logger.info("fail to get response from server :", ex);

            }

            @Override
            public void cancelled() {
                latch.countDown();
                logger.info("stream request cancelled");
            }
        });

        latch.await();
        logger.info("stream request finished ... ");
    }

    private static byte[] getResponse(HttpResponse response) throws Exception {

        InputStream is = null;

        try {
            is = response.getEntity().getContent();
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }


}
