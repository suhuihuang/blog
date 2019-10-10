# HDFS用户及权限配置

使用linux用户bruce，格式化hadoop的namenode，那么bruce成为hdfs的超级用户
在bruce用户下运行命令：
&#35; 创建/user/hadoop目录
**hadoop fs -mkdir -p /user/hadoop**

&#35;  修改/user/hadoop的所有者
**hadoop fs -chown hadoop:hadoop /user/hadoop**
&#35;  修改hdfs用户hadoop对hfds的/user/hadoop目录的最大磁盘占用量
**hfds dfsadmin -setSpaceQuota 10g /user/hadoop**
测试权限是否设置成功
bruce下向/user/hadoop上传文件，成功（因为bruce是hdfs超级用户）
[bruce@node-01 backup]$ hadoop fs -ls /user/hadoop
分别创建linux用户hadoop、kkb，并设置密码，并修改用户主目录下的.bash_profile文件，增加JAVA_HOME及HADOOP_HOME的相关环境变量
以hadoop为例：
[bruce@node-01 backup]$ su root
Password:
[root@node-01 backup]# useradd hadoop
[root@node-01 backup]# passwd hadoop
Changing password for user hadoop.
New password: 
BAD PASSWORD: The password is shorter than 8 characters
Retype new password: 
passwd: all authentication tokens updated successfully.
[hadoop@node-01 ~]$ vim .bash_profile
增加如下内容：

```shell
export JAVA_HOME=/usr/java/jdk1.8.0_211-amd64/
export PATH=$JAVA_HOME/bin:$PATH

#export HADOOP_HOME=/home/bruce/hadoop-3.2.0/
export HADOOP_HOME=/opt/bigdata/hadoop-2.7.3
export PATH=$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH
```

[hadoop@node-01 ~]$ source .bash_profile
[hadoop@node-01 ~]$ hadoop fs -put hadoop.txt /user/hadoop
&#35;  切换到kkb用户
[hadoop@node-01 ~]$ su - kkb
&#35;  kkb用户没有向/user/hadoop目录上传文件的权限
[kkb@node-01 ~]$ hadoop fs -put kkb.txt /user/hadoop
**put: Permission denied: user=kkb, access=WRITE, inode="/user/hadoop/kkb.txt._COPYING_":hadoop:hadoop:drwxr-xr-x**