package afred.javademo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

/**
 * Created by afred on 17/2/11.
 */
public class AccountCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(AccountCache.class);

    private String name;

    public AccountCache() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {

//        logger.debug("查询缓存 : {}", key);
//        Account account = new Account();
//        account.setUserId(12345);
//        account.setName(key.toString());
//        return new SimpleValueWrapper(account);
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        logger.debug("存储缓存 : {}, {}", key, value);
    }

    @Override
    public void evict(Object key) {
        logger.debug("删除缓存 : {}", key);
    }

    @Override
    public void clear() {

    }
}
