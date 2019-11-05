package com.kaikeba.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

public class CuratorClientTest {
    //根据自己集群的实际情况，Zookeeper info 替换
    private static final String ZK_ADDRESS = "node01:2181,node02:2181,node03:2181";
    private static final String ZK_PATH = "/zk_test";

    static CuratorFramework client = null;

    //初始化，建立连接
    public static void init() {
        //重试连接策略，失败重试次数；每次休眠5000毫秒
        //RetryPolicy policy = new ExponentialBackoffRetry(3000, 3);
        RetryNTimes retryPolicy = new RetryNTimes(10, 5000);

        // 1.设置客户端参数，参数1：指定连接的服务器集端口列表；参数2：重试策略
        client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, retryPolicy);
        //启动客户端，连接到zk集群
        client.start();

        System.out.println("zk client start successfully!");
    }

    //关闭连接
    public static void clean() {
        System.out.println("close session");
        client.close();
    }

    // 创建永久节点
    public static void createPersistentZNode() throws Exception {
        String zNodeData = "火辣的";

        ///a/b/c
        client.create().
                creatingParentsIfNeeded().
                withMode(CreateMode.PERSISTENT).
                forPath("/beijing/goddess/tingting", zNodeData.getBytes());

    }

    public static void createEphemeralZNode() throws Exception {
        // 创建临时节点
        String zNodeData2 = "hello3";
        client.create().
                creatingParentsIfNeeded().
                withMode(CreateMode.EPHEMERAL).
                forPath("/hello3/zk", zNodeData2.getBytes());
    }

    public static void queryZNodeData() throws Exception {
        // 查询列表
        print("ls", "/");
        print(client.getChildren().forPath("/"));

        //查询节点数据
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));
    }

    public static void modifyZNodeData() throws Exception {
        // 修改节点数据
        String data2 = "world";
        print("set", ZK_PATH, data2);

        client.setData().forPath(ZK_PATH, data2.getBytes());
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));
    }

    public static void deleteZNode() throws Exception {
        // 删除节点
        print("delete", ZK_PATH);
        client.delete().forPath(ZK_PATH);

        print("ls", "/");
        print(client.getChildren().forPath("/"));
    }

    //监听ZNode
    public static void watchZNode() throws Exception {

        //设置节点的cache
        TreeCache treeCache = new TreeCache(client, "/zk_test");
        //设置监听器和处理过程
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();
                if(data !=null){
                    switch (event.getType()) {
                        case NODE_ADDED:
                            System.out.println("NODE_ADDED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_REMOVED:
                            System.out.println("NODE_REMOVED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_UPDATED:
                            System.out.println("NODE_UPDATED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;

                        default:
                            break;
                    }
                }else{
                    System.out.println( "data is null : "+ event.getType());
                }
            }
        });
        //开始监听
        treeCache.start();
        Thread.sleep(60000);
        //关闭cache
        treeCache.close();
    }

    public static void main(String[] args) throws Exception {

        init();
        createPersistentZNode();
//        createEphemeralZNode();
//        queryZNodeData();
//        modifyZNodeData();
//        deleteZNode();
//        watchZNode();
        clean();

    }

    private static void print(String... cmds) {
        StringBuilder text = new StringBuilder("$ ");
        for (String cmd : cmds) {
            text.append(cmd).append(" ");
        }
        System.out.println(text.toString());
    }

    private static void print(Object result) {
        System.out.println(
                result instanceof byte[]
                        ? new String((byte[]) result)
                        : result);
    }
}
