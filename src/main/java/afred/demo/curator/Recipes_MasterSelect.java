package afred.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 15/10/21.
 */
public class Recipes_MasterSelect {

    static String master_path = "/curator_recipes_master_path";

    private static final Logger logger = LoggerFactory.getLogger(Recipes_MasterSelect.class);

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 2))
            .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();

        LeaderSelector selector = new LeaderSelector(client, master_path,
                new LeaderSelectorListenerAdapter() {
                    public void takeLeadership(CuratorFramework client) throws Exception {
                        logger.debug("become master");
                        TimeUnit.SECONDS.sleep(10);

                        logger.debug("release");
                    }
                });

        selector.autoRequeue();
        selector.start();

        TimeUnit.SECONDS.sleep(200);
    }
}
