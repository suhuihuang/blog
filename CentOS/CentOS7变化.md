##  CentOS 7 变化

1. 设置主机名:

   - 使用命令修改，退出登录，重新登陆生效

     ```
     hostnamectl set-hostname node1
     ```

   - 修改配置文件，重启生效

     ```
     cat << EOF >  /etc/hostname
     > node2
     > EOF
     ```

2. 关闭防火墙

   - 关闭防火墙

     ```
      systemctl stop firewalld       # 关闭防火墙
      systemctl start firewalld      # 启动防火墙
      systemctl status firewalld     # 查看防火墙状态
     ```

   - 禁用防火墙

     ```
     systemctl disable firewalld  # 禁用防火墙
     systemctl enable  firewalld  # 启用防火墙
     ```

3. 重启启动网卡

   ```
   systemctl restart network
   ```

4. 

