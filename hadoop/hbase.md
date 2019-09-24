# hbase 部署

1. 下载

2.  hbase 安装

   ```shell
   tar xf hbase-1.2.6-bin.tar.gz -C /app/deploy/
   ln -sf /app/deploy/hbase-1.2.6 /app/deploy/hbase
   ```

3. 配置

   - 修改 `hbase-site.xml`，指定 `hbase`的环境变量，使用我们自定义的 `zookeeper`

     ```shell
     export JAVA_HOME=/app/deploy/jdk1.8
     export HBASE_MANAGES_ZK=false
     ```

   - 修改 `hbase-site.xml` 的配置文件

     ```shel
     <property>
         <name>hbase.rootdir</name>
         <value>hdfs://jp-node1:9000/hbase</value>
     </property>
     <property>
         <name>hbase.cluster.distributed</name>
         <value>true</value>
     </property>
     <property>
         <name>hbase.zookeeper.quorum</name>
         <value>jp-node1,jp-node2,jp-node3</value>
     </property>
     <property>
       <name>hbase.master.info.port</name>
       <value>16010</value>
     </property>
     <property>
       <name>hbase.cluster.distributed</name>
       <value>true</value>
     </property>
     <property>
       <name>hbase.tmp.dir</name>
       <value>/opt/hbase/tmp</value>
     </property>
     ```

   - `修改regionservers文件，该文件列出所有region server主机的hostname`( `/app/deploy/hbase` )

     ```shell
     cat << EOF >>regionservers
     jp-node1
     jp-node2
     jp-node3
     EOF
     ```

4. 分发 `hbase` 安装(切换到 `/app/deploy/`)

   ```shell
   scp -r hbase node2:/app/deploy
   scp -r hbase node3:/app/deploy
   ```

5. 启动服务

   > 确认是否关闭了防火墙。

   `start-hbase.sh`

6. 查看

   - jps

     `3701 HMaster`

   - 启动浏览器访问

     http://node1:60010

7.  创建 t_student 表

   > hbase shell       #启动客户端

   ```
   create 't_student', 'cfl'
   ```

8. 查看表 `t_student`

   ```
   list     # 查看所有的表
   ```

9. 查看表结构

   ```
   desc 't_student'
   ```

10. 插入数据

    ```
    put 't_student', '007', 'cf1:name', 'hongten'
    ```

11. 查询table

    ```
    scan 't_student'
    ```

12. 把数据从内存写入到磁盘

    ```
    flush 't_student'
    ```

13. 