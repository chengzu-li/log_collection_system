package org.example;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.shaded.com.google.common.base.Charsets;

import org.apache.zookeeper.CreateMode;
import org.apache.commons.io.FileUtils;
import org.I0Itec.zkclient.ZkClient;

import java.io.File;


public class CuratorHelloWorld {
    private static final String CONNECT_ADDR = "192.168.186.100:2181";
    private static final int SESSION_TIMEOUT = 10000;
    public static void main(String[] args) throws Exception {
        String path = "/flume";
        //重试策略，初试时间1秒，重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //通过工厂创建Curator
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .connectionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .build();
        //开启连接
        curator.start();
        curator.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, "data".getBytes(Charsets.UTF_8.name()));
        ZkClient clientDemo = new ZkClient("192.168.186.100:2181", 10000);
        String filePath = ZkClient.class.getClassLoader().getResource("").getPath() + "flume-docker.conf";
        String content = FileUtils.readFileToString(new File(filePath), Charsets.UTF_8);
        clientDemo.createPersistent("/flume/docker", content);
}
}