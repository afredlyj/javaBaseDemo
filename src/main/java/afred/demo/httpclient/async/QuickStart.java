package afred.demo.httpclient.async;

import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * Created by Afred on 14-11-9.
 */
public class QuickStart {

    private static Logger logger = LoggerFactory.getLogger(QuickStart.class);

    public static void main(String[] args) {

        logger.info("quick start ... ");

    }

    private static void simpleRequest(CloseableHttpAsyncClient client, String url) throws Exception {

        final HttpGet get = new HttpGet(url);

        Future<HttpResponse> future = client.execute(get, null);
        HttpResponse response = future.get();
        RequestLine requestLine = get.getRequestLine();
        logger.info("request line : {}", requestLine);

        StatusLine responseLine = response.getStatusLine();
        logger.info("response line  : {}", responseLine);
    }

}
