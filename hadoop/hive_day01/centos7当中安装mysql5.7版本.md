

# 一、课前准备

准备一台centos7的服务器，并启动

# 二、课堂主题

如何在centos7当中安装mysql数据库，并开启mysql数据库的远程连接

# 三、课堂目标

1. 熟练在centos7当中安装mysql5.7数据库

# 四、知识要点

## 1.开启centos7服务器，并切换到root用户

 在CentOS7中默认安装有MariaDB，这个是MySQL的分支，但为了需要，还是要在系统中安装MySQL，而且安装完成之后可以直接覆盖掉MariaDB。

将我们的centos7切换到root用户方便我们的mysql的安装

## 2.下载并安装官方的mysql的yum源

使用root用户在centos7服务器的/kkb/soft路径下执行以下命令

```shell
cd /kkb/soft/
yum -y install wget
wget -i -c http://dev.mysql.com/get/mysql57-community-release-el7-10.noarch.rpm
yum -y install mysql57-community-release-el7-10.noarch.rpm
yum -y install mysql-community-server
# 这步可能会花些时间，安装完成后就会覆盖掉之前的mariadb
```

## 3.mysql数据库的设置

首先启动MySQL

```shell
执行以下命令启动mysql服务

systemctl start mysqld.service

查看mysql启动状态

systemctl status mysqld.service
```

此时MySQL已经开始正常运行，不过要想进入MySQL还得先找出此时root用户的密码，通过如下命令可以在日志文件中找出密码

```shell
grep "password" /var/log/mysqld.log
```

可以查看到我的临时密码为

   ==!ta2M9e7_B.&==

![1568183752297](centos7%E5%BD%93%E4%B8%AD%E5%AE%89%E8%A3%85mysql5.7%E7%89%88%E6%9C%AC.assets/1568183752297.png)

使用临时密码，进入mysql客户端，然后更改密码

```
 mysql -uroot -p
 set global validate_password_policy=LOW;
 set global validate_password_length=6;
 ALTER USER 'root'@'localhost' IDENTIFIED BY '123456';
 #开启mysql的远程连接权限
grant all privileges  on  *.* to 'root'@'%' identified by '123456' with grant option;
flush privileges;

```

## 4.mysql的卸载

上面我们在centos7当中已经安装好了5.7版本的mysql服务，如果以后我们不需要mysql了，或者mysql安装失败了需要重新安装，那么我们就可以来将mysql给卸载掉了

第一步：停止mysql服务并卸载rpm的包

```
systemctl stop mysqld.service
rpm -qa | grep -i mysql 
yum list install mysql*
yum remove mysql mysql-server mysql-libs compat-mysql51
yum remove mysql-community-release
rpm -e --nodeps mysql57-community-release-el7-10.noarch mysql-community-common-5.7.27-1.el7.x86_64 
```

第二步：删除mysql残留文件夹

```
whereis mysql 
rm -rf /usr/share/mysql/
find / -name mysql
rm -rf /var/lib/mysql/
rm -rf /root/.mysql_history
```





