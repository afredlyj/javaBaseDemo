package afred.javademo.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * Created by afred on 17/2/11.
 */
public class AccountCacheManager extends AbstractCacheManager {

    private Collection<AccountCache> caches;

    public void setCaches(Collection<AccountCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return caches;
    }
}
