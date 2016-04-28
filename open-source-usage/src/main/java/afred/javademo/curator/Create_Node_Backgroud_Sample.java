package afred.javademo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 15/10/9.
 */
public class Create_Node_Backgroud_Sample {

    private static final Logger logger = LoggerFactory.getLogger(Create_Node_Backgroud_Sample.class);

    static String path = "/zk-book";

    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    static CountDownLatch semaphore = new CountDownLatch(3);

    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        client.start();

        logger.debug("start");

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        logger.debug("{}, {}", curatorEvent.getType(), curatorEvent.getResultCode());
                        semaphore.countDown();
                    }
                }, executorService).forPath(path, "hello".getBytes());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        logger.debug("{}, {}", curatorEvent.getType(), curatorEvent.getResultCode());

                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                            semaphore.countDown();
                        }
                    }

                    )
                    .forPath(path, "world".getBytes()

                    );

                    client.create().

                    creatingParentsIfNeeded()

                    .

                    withMode(CreateMode.EPHEMERAL)

                    .

                    inBackground(new BackgroundCallback() {
                        public void processResult (CuratorFramework curatorFramework, CuratorEvent curatorEvent)throws
                        Exception {
                            logger.debug("{}, {}", curatorEvent.getType(), curatorEvent.getResultCode());
                            semaphore.countDown();
                        }
                    }

                    ).

                    forPath(path, "world2".getBytes()

                    );



                    semaphore.await();
                    executorService.shutdown();

                    client.close();
                }

    }
