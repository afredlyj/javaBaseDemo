package afred.common.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

/**
 * Created by winnie on 2014-11-10 .
 */
public class AsyncHttpConnectionManager {

    private static PoolingNHttpClientConnectionManager connectionManager;

    private static Registry<SchemeIOSessionStrategy> sessionStrategyRegistry;

    private static CloseableHttpAsyncClient httpClient;

    static {
        // SSL context for secure connections can be created either based on
        // system or application specific properties.
        SSLContext sslcontext = SSLContexts.createSystemDefault();
        // Use custom hostname verifier to customize SSL hostname verification.
//        X509HostnameVerifier hostnameVerifier = new BrowserCompatHostnameVerifier();
        sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                .register("http", NoopIOSessionStrategy.INSTANCE)
                .register("https", new SSLIOSessionStrategy(sslcontext))
                .build();

        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setConnectTimeout(1)
                .setSoTimeout(3000)
                .build();

        // Create a custom I/O reactort
        try {
            ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
            connectionManager = new PoolingNHttpClientConnectionManager(ioReactor, sessionStrategyRegistry);
            connectionManager.setMaxTotal(100);
            connectionManager.setDefaultMaxPerRoute(10);

            httpClient = HttpAsyncClients.custom()
                    .setConnectionManager(connectionManager)
                    .build();
            httpClient.start();
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
    }

    public static CloseableHttpAsyncClient getHttpClient() {
        return httpClient;
    }

    public static byte[] get(String url) throws Exception {
        final CloseableHttpAsyncClient httpClient = AsyncHttpConnectionManager.getHttpClient();

        final HttpGet get = new HttpGet(url);
        get.setProtocolVersion(HttpVersion.HTTP_1_0);
        get.setHeader(HttpHeaders.CONNECTION, "keep-alive");

        Future<HttpResponse> future = httpClient.execute(get, null);

        HttpResponse response = future.get();

        return getResponse(response);
    }

    public static byte[] getResponse(HttpResponse response) throws Exception {

        if (null == response) {
            return null;
        }

        Header[] headers = response.getAllHeaders();

        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() != 200) {
            throw new ExecutionException(statusLine.getStatusCode() + "", null);
        }

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

                }

            }
        }

        return null;
    }

}
