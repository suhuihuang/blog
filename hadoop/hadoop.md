# hadoop 安装

3. hadoop 下载

   [镜像源](https://www-eu.apache.org/dist/hadoop/common/)

4. 解压安装
   ```
   tar xfzv hadoop-3.2.0.tar.gz  -C /app/deploy
   ln -sf /app/deploy/hadoop-3.2.0 /app/deploy/hadoop
   ```
   
4. 配置环境变量

   > vim /etc/profile

   ```shell
   # jdk
   export JAVA_HOME=/opt/jdk1.8
   
   # zookeeper
   export ZK_HOME=/opt/zk
   
   # hbase 
   export HBASE_HOME=/opt/hbase
   
   # janusgraph
   export JANUSGRAPH_HOME=/opt/janusgraph
   
   # hadoop
   export HADOOP_HOME=/app/deploy/hadoop
   export YARN_HOME=$HADOOP_HOME
   export YARN_HDFS_HOME=$HADOOP_HOME
   #export YARN_CONF_DIR=$HADOOP_HOME/etc/hadoop
   export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
   export HADOOP_COMMON_HOME=$HADOOP_HOME
   export HADOOP_HDFS_HOME=$HADOOP_HOME
   export HADOOP_MAPRED_HOME=$HADOOP_HOME
   #export HADOOP_YARN_HOME=$HADOOP_HOME
   export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib/native"
   export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
   export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH:$HADOOP_HOME/bin:/bin:/usr/bin:/usr/local/bin:$ZK_HOME/bin:$HADOOP_HOME/sbin:/opt/hbase/bin:$JANUSGRAPH_HOME/bin
   export CLASSPATH=.$CLASSPATH:$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$JAVA_HOME/lib/tools.jar
   ```

   > 生效

   `source /etc/profile`  或  ` . /etc/profile`

5. 修改配置文件(`/app/deploy/hadoop/etc/hadoop`)

   - `vim  hadoop-env.sh`

     ```shell
     export JAVA_HOME=/opt/jdk1.8
     ```

     > 添加 jdk  路径

   - 修改 `修改core-site.xml`

     ```shell
       <property>
         <name>fs.defaultFS</name>
         <value>hdfs://jp-node1:9000</value>
       </property>
       <property>
         <name>hadoop.tmp.dir</name>
         <value>/app/deploy/hadoop/tmp</value>
       </property>
       <property>
         <name>ha.zookeeper.quorum</name>
         <value>jp-node1:2181,jp-node2:2181,jp-node3:2181</value>
     ```

   - 修改 `hdfs-site.xml`

     ```shell
       <property>
         <name>dfs.namenode.http-address</name>
         <value>jp-node1:50070</value>
       </property>
       <property>
         <name>dfs.replication</name>
         <value>3</value>
       </property>
     ```

   - 修改 ` yarn-site.xml `

     ```shell
       <property>
         <name>yarn.resourcemanager.hostname</name>
         <value>jp-node1</value>
       </property>
       <property>
         <name>yarn.nodemanageer.aux-services</name>
         <value>mapreduce_shuffle</value>
       </property>
       <property>
         <name>yarn.nodemanager.vmem-check-enabled</name>
         <value>false</value>
       </property>
     ```

   - 修改 `mapred-site.xml`

     ```shell
       <property>
         <name>mapreduce.framework.name</name>
         <value>yarn</value>
       </property>
       <property>
         <name>yarn.app.mapreduce.am.env</name>
         <value>HADOOP_MAPRED_HOME=/app/deploy/hadoop</value>
       </property>
     ```

   - 修改 workers

     ```shell
     jp-node1
     jp-node2
     jp-node3
     ```

6. 分发安装节点( 回到`/app/deploy/`)

   ```shell
   scp -r hadoop node2:/app/deploy
   scp -r hadoop node3:/app/deploy
   ```

7. 格式化命名空间和 `zookeeper`(在主节点上)

   ```shell
   hdfs  namenode  -formate
   hdfs zkfc -formatZK
   ```

8. 启动 hadoop

   `start-all.sh`

9. 查看是否成功

   访问： (http://node1:50070)

   ```
   [hadoop@jp-node1 ~]$ jps
   3153 NodeManager
   3026 ResourceManager
   2770 SecondaryNameNode
   3701 HMaster
   3879 HRegionServer
   2443 NameNode
   3933 Jps
   2573 DataNode
   ```

10. 测试

   

