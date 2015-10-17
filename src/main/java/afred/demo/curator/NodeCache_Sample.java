package afred.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        client.start();

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath("/zk-book/node-cache", "hello".getBytes());

//        NodeCach

    }

}
