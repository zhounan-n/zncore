---
title: 阿里云ECS服务器cenos7系统部署环境安装(tomcat,jdk,mysql等)
date: 2017-11-02 16:06:28
tags: 阿里云centos7 环境安装
categories: 阿里云部署环境
---


在阿里云买了三年的ecs服务器，想后续搭建自己的网站，这边记录一下部署环境的安装过程,如果命令不熟练，可以自己在本地安装虚拟机试一下(可以看图形界面)，都一样的，ecs也是虚拟机

#### jdk安装:
    yum install -y lrzsz
    rz
    执行上面的命令安装可进行本地传输文件，将本地下载好的jdk包传上去,解压
    tar-xzvf jdk-8u131-linux-x64.tar.gz 
    修改配置文件
    vi /etc/profile
    在文件末尾添加如下内容
    export JAVA_HOME=/usr/java/jdk1.8.0_45
    export PATH=$JAVA_HOME/bin:$PATH
    export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
    移除jdk安装包
    rm -rf jdk-8u131-linux-x64.tar.gz
    source /etc/profile
    使配置文件生效
    java -version查看 是否生效 
<!--more-->    

#### tomcat安装:
进入/usr/local目录,我这里安装的tomcat7版本

    cd /usr/local
    wget http://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-7/v7.0.82/bin/apache-tomcat-7.0.82.tar.gz (下载包)
    tar -zxf apache-tomcat-7.0.82.tar.gz (解压)
    mv apache-tomcat-7.0.82 tomcat (重命名)
    rm -rf apache-tomcat-7.0.82.tar.gz (移除安装包)
    
![图1](/images/ecs1.png  "图1")
    
    ls (查看当前目录下的东西)
    cd tomcat/bin
    ./catalina.sh (启动，这时候如果你没安装jdk什么的启动就会报错)
    
![图2](/images/ecs2.png  "图2")

    export JAVA_HOME=/usr/java/jdk1.8.0_45
    export CATALINA_HOME=/usr/local/apache-tomcat-8.0.22 export PATH=$JAVA_HOME/bin:$PATH:$CATALINA_HOME/lib
    export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$CATALINA_HOME/bin
    
    这样就可以启动了
![图3](/images/ecs3.png  "图3")

如果不能通过浏览器访问8080,则需要设置阿里云服务器的安全组规则

#### mysql安装():
    rpm qa|grep MYSQL是否已安装其他版本的mysql(有的话用命令删除)

    创建MariaDB.repo:
    sudo vi /etc/yum.repos.d/Mariadb.repo

    将以下文件中的字段添加到MariaDB.repo文件中:
    #MariaDB 10.1 CentOS repository list - created 2016-12-01 03:36 UTC
    #http://downloads.mariadb.org/mariadb/repositories/
    [mariadb]
    name = MariaDB
    baseurl = http://yum.mariadb.org/10.1/centos7-amd64
    gpgkey=https://yum.mariadb.org/RPM-GPG-KEY-MariaDB
    gpgcheck=1

    ##### yum安装MariaDB
    sudo yum -y install MariaDB-server MariaDB-client
    安装需要一段时间

    systemctl start mysql.service           /启动mariaDB服务

    配置MariaDB服务
    mysql_secure_installation
    图

    mysql -u root -p 登录
    exit; 可退出

    配置mysql:
    1:字符集设置
    vi /etc/my.cnf
    在文件末加入以下内容
    [mysql]
    default-character-set =utf8
    
    2:远程连接设置
    grant all privileges on *.* to root@'%'identified by 'password';(表权限赋予所有ip地址的root用户) 这样就可以远程连接了

    

     


     



