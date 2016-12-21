package afred.javademo.dispatcher.resteasy.ratelimit;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by afred on 16/12/21.
 */
public class RateLimtConfigLoader implements ZKConfigChangedListener {

    private static final Logger logger = LoggerFactory.getLogger(RateLimtConfigLoader.class);

    // zk config listener
    private final ZKNodeClient node;

    private QPSLimiter limiter;

    public RateLimtConfigLoader(ZKNodeClient node, QPSLimiter limiter) {
        this.node = Preconditions.checkNotNull(node);
        this.limiter = Preconditions.checkNotNull(limiter);
    }

    private void init() {
        node.register(this);
    }

    @Override
    public void eventReceived() {
        // refresh data

        Optional<Map<String, RateLimiter>> optional = refresh(node);
        if (optional.isPresent()) {
            limiter.refresh(optional.get());
        }
    }


    private Optional<Map<String, RateLimiter>> refresh(ZKNodeClient node) {

        Optional<ImmutableMap<String, String>> optional = node.exportProperties();
        if (optional.isPresent()) {
            ImmutableMap<String, String> properties = optional.get();

            Map<String, RateLimiter> rateLimiterMap = Maps.transformEntries(properties, new Maps.EntryTransformer<String, String, RateLimiter>() {
                @Override
                public RateLimiter transformEntry(@Nullable String key, @Nullable String value) {

                    try {
                        double permitsPerSecond = Double.parseDouble(value);
                        return RateLimiter.create(permitsPerSecond);
                    } catch (NumberFormatException e) {
                        logger.warn("rate limit parse exception : {}", value);
                    }

                    return null;
                }
            });

            return Optional.fromNullable(rateLimiterMap);
        }

        return Optional.absent();
    }

    public static void main(String[] args) {


        Map<String, String> properties = Maps.newHashMap();

        properties.put("hello", "value");

        Map<String, RateLimiter> rateLimiterMap = Maps.transformEntries(properties, new Maps.EntryTransformer<String, String, RateLimiter>() {
            @Override
            public RateLimiter transformEntry(@Nullable String key, @Nullable String value) {

                logger.debug("key : {}, value : {}", key, value);
                try {
                    double permitsPerSecond = Double.parseDouble(value);
                    return RateLimiter.create(permitsPerSecond);
                } catch (NumberFormatException e) {
                    logger.warn("rate limit parse exception : {}", value);
                }

                return null;
            }
        });

        System.out.println(rateLimiterMap);
    }

}
