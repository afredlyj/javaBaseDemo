package afred.javademo.dispatcher.resteasy.ratelimit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


/**
 * Created by afred on 16/12/21.
 */
public class QPSLimiter {

    private static final Logger logger = LoggerFactory.getLogger(QPSLimiter.class);

    private ConcurrentMap<String, RateLimiter> configurations = Maps.newConcurrentMap();

    public QPSLimiter() {

    }

    public void refresh(Map<String, RateLimiter> rateLimiterMap) {

        Preconditions.checkNotNull(rateLimiterMap);

        configurations.clear();

        configurations.putAll(rateLimiterMap);
    }

    public double update(String url, double permitsPerSecond) {
        RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond);
        RateLimiter old = configurations.put(url, rateLimiter);
        return old.getRate();
    }

    public QPSLimiter(ConcurrentMap<String, RateLimiter> initConfigurations) {
        this.configurations = Preconditions.checkNotNull(initConfigurations);
    }

    public boolean tryAcquire(String url, long timeout, TimeUnit timeUnit) {

        RateLimiter limiter = configurations.get(url);

        if (limiter != null) {
            return limiter.tryAcquire(1, timeout, timeUnit);
        }

        return true;
    }

    public boolean tryAcquire(String url) {

        RateLimiter limiter = configurations.get(url);


        if (limiter != null) {
            return limiter.tryAcquire();
        }

        return true;
    }

    public double acquire(String url) {
        RateLimiter limiter = configurations.get(url);

        if (limiter != null) {
            return limiter.acquire();
        }

        return 0;

    }
}
