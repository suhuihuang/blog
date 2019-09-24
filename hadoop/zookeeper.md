# 部署 zookeeper

1. 介绍

      - 一定要关闭防火墙
      - 启动成功后，一定要让 `zookeeper` 活着

2. 下载

   [zookeeper 3.4.14](https://apache.org/dist/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz)

3. 安装zookeeper

   ```shell
   tar -zxvf zookeeper-3.4.6.tar.gz -C /app/deploy
   ln -sf /app/deploy/zookeeper-3.4.14 /app/deploy/zk
   ```

4. 配置zookeeper 的环境变量

   ```
   vi /etc/profile
   PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin:/app/deploy/zk/bin
   ```

   `source /etc/profile`

5. 修改 zookeeper 配置文件

   - 修改 `zoo_sample.cfg` 配置文件  

     `cp zoo_sample.cfg zoo.cfg`

     `vim zoo.cfg`

     ```shell
     dataDir=/opt/zk/data
     dataLogDir=/opt/zk/log
     server.1=jp-node1:2888:3888
     server.2=jp-node2:2888:3888
     server.3=jp-node3:2888:3888
     ```

   - 创建一个文件myid
     
     `mkdir  /app/deploy/zk/data `
     
     `echo 1 > /app/deploy/zk/data/myid`
     
     --以此类推，在node2，node3，值分别是2， 3

6. 分发安装

   ```
   cd /app/deploy
   scp -r zookeeper-3.4.14  /etc/profile  jp-node2:/app/deploy/
   scp -r zookeeper-3.4.14  /etc/profile  jp-node2:/app/deploy/
   ```

   !!  记得修改  ` myid ` 

7. 启动 zookeeper

      - 分别在 `jp-node1`,`jp-node2`,`jp-node3` 执行

        ```shell
        source /etc/profile
        zkServer.sh start
        ```


8.  检验和测试

   - 检验

     ```
     jps
     --观察是否有QuorumPeerMain进程
     ```

   - 测试查看 主和从，或者 ，关闭主看从是否会变成主
   
     ```shell
     Mode: leader
     Mode: follower
     ```
   
   - 
