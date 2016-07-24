package afred.javademo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/7/24.
 */
public class CacheTest {

    private static final Logger logger = LoggerFactory.getLogger(CacheTest.class);

    private static String loadCacheFromDB(String key) {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.error("interrupted exception :  {}", key, e);
        }

        return RandomStringUtils.randomAlphabetic(10);

    }

    @Test
    public void loadCache() {
        LoadingCache<String, String> caches = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {

                        String value = loadCacheFromDB(key);
                        logger.debug("load value : {}, {}", key, value);
                        return value;
                    }

                });

        logger.debug("caches : {}", caches.asMap());

        for (int repeat = 0; repeat < 2; repeat++) {
            for (int i = 0; i< 10; i++) {
                String key = String.valueOf(i);
                try {
                    logger.debug("keys : {}, value : {}", key, caches.get(key));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }




    }

}
