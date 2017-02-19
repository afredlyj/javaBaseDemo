package afred.javademo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by afred on 17/2/11.
 */
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Cacheable(value = "accountCache")
    public Account get(String name) {

        logger.debug("查询用户信息 : {}", name);
        Account account = new Account();
        account.setName(name);
        account.setUserId(123456);
        return account;
    }


}
