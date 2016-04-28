package afred.javademo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2016-04-28 .
 */
public class ConnectionLossDemo {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionLossDemo.class);

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 2))
            .build();

    public static void main(String[] args) throws Exception {
        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                logger.debug("接受watcher通知 : {}", curatorEvent);

                final WatchedEvent watchedEvent = curatorEvent.getWatchedEvent();
                if (watchedEvent != null) {

//                    switch (watchedEvent.getType()) {
//                        case NodeChildrenChanged:
//                            watch();
//                            break;
//                        case NodeDataChanged:
//                            watch();
//                            break;
//
//                    }
                    watch();
                }
            }
        });

        client.start();
        TimeUnit.SECONDS.sleep(100);
    }

    private static void watch() throws Exception {
        logger.debug("进入watch");
//        Stat stat = client.checkExists().forPath("/zk-connection-loss");
//        logger.debug("check 结束");
//        if (null == stat) {
//            logger.debug("节点不存在");
//            client.create().forPath("/zk-connection-loss", "hello".getBytes("utf-8"));
//        } else {
//            logger.debug("节点已经存在");
//        }

        client.getData().watched().forPath("/zk-connection-loss");
    }

}
