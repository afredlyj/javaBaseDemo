package afred.javademo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 15/10/11.
 */
public class NodeCache_Sample {

    private static final Logger logger = LoggerFactory.getLogger(NodeCache_Sample.class);

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .retryPolicy(new ExponentialBackoffRetry(5000, 3))
            .sessionTimeoutMs(30000)
            .build();

    public static void main(String[] args) throws Exception {

        String path = "/zk-book/node-cache";

        client.start();

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(path, "hello".getBytes());

        final NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);

        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {

                logger.debug("node data update, new data : {}", new String(cache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path, "world".getBytes());

        TimeUnit.SECONDS.sleep(2);

//        client.delete().deletingChildrenIfNeeded().forPath(path);

        TimeUnit.SECONDS.sleep(100);
    }

}
