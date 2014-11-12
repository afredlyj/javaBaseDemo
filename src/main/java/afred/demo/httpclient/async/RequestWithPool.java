package afred.demo.httpclient.async;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 * Created by winnie on 2014-11-10 .
 */
public class RequestWithPool {

    public static void main(String[] args) {
//        String url = "http://192.168.1.104:8080?requestId=123";
        String url = "https://insidepay.nearme.com.cn/insidepay/PayOrder";
        try {
            getSimpleResponse(url);
//            getResponseByGetWithoutPool(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getSimpleResponse(final  String url) throws Exception {
        final CloseableHttpAsyncClient httpClient = AsyncHttpConnectionManager.getHttpClient();
        QuickStart.simpleRequest(httpClient, url);
    }

    private static void getResponseByGetWithPool(final String url) throws Exception {
        final CloseableHttpAsyncClient httpClient = AsyncHttpConnectionManager.getHttpClient();
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        QuickStart.callbackRequest(httpClient, url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void getResponseByGetWithoutPool(final String url) throws Exception {
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom().setMaxConnPerRoute(10).build();
        httpClient.start();
        for (int i = 0; i < 20; i++) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    QuickStart.callbackRequest(httpClient, url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        }


    }
}
