---
title: fastdfs单机安装部署全过程(阿里云centos环境)
date: 2018-02-08 18:50:53
tags: fastdfs安装
categories: fastdfs
---


本篇总结一下fastdfs在阿里云centos7下的安装。
网上有很多安装的教程，但都千篇一律又不完整，按其安装的最终都不能通过浏览器访问到文件，最近搞了几天弄成功了所以总结下，方便自己忘记了也可以回头看看。下面开始

注意：阿里云centos7的防火墙是firewalld,网上都是iptables的，可以安装iptables，但可以直接用firewalld 相关命令开启防火墙并添加端口，阿里云放行还需添加安全组规则。

## 依赖环境的安装
    yum install make cmake gcc gcc-c++
   #### 安装libfatscommon (目录可自行选择创建)
    yum install -y unzip zip
    unzip libfastcommon-master.zip    
    cd libfastcommon-master   
    
## 创建数据存储目录
   mkdir -p /export/fastdfs/{storage,tracker}
   创建后的目录为/export/fastdfs/storage,/export/fastdfs/tracker
   (上传文件后可到此查看)
<!--more-->
## fastdfs安装配置
#### 下载
    wget http://sourceforge.net/projects/fastdfs/files/FastDFS%20Server%20Source%20Code/FastDFS%20Server%20with%20PHP%20Extension%20Source%20Code%20V5.05/FastDFS_v5.05.tar.gz/download
#### 解压
    tar zxf download(download是wget下来后的文件名，解压后变成FastDfs)
#### 安装
    cd FastDfs
    ./make && ./make install
    cp -a conf/*.conf /etc/fdfs/ (复制配置文件到对应目录,后续配置的目录)
    cd /etc/fdfs/
    rm -rf *.sample
    chown -R fastdfs: /export/fastdfs
#### 配置tracker
        vim /etc/fdfs/tracker.conf (修改配置文件)
        disabled=false  
        port=22122                   （默认端口）
        base_path=/export/fastdfs/tracker (自己创建的用于存放tracker的目录)
（其余默认）
        开启防火墙22122端口并加入安全组
        systemctl start firewalld (开启防火墙)
        firewall-cmd --add-port=22122/tcp --permanent (添加端口)
        firewall-cmd --reload (更新防火墙)
        firewall-cmd --list-all (查看目前开放的端口)
        systemctl status firewalld (查看防火墙是否开启)
        
        启动tracker
        fdfs_tracker /etc/fdfs/trcker.conf start
#### 配置storage (ip改成自己的)
        vim /etc/fdfs/storage.conf
        disabled=false                      # 启用配置文件
        port=23000     
        group_name=group1
        base_path=/export/fastdfs/storage
        store_path0=/export/fastdfs/storage
        tracker_server=ip:22122
        http.server_port=80 (浏览器访问文件的端口)

        开启防火墙23000,80端口并加入安全组
        启动storage
        fdfs_storage /etc/fdfs/storage.conf start
        查看监听状态
        netstat -unltp | grep storage (LISTENING为成功)
#### 配置client
        vim /etc/fdfs/client.conf
        base_path=/export/fastdfs/tracker
        tracker_server=ip:22122

## fastdfs-nginx-module安装配置
        wget http://sourceforge.net/projects/fastdfs/files/FastDFS%20Nginx%20Module%20Source%20Code/fastdfs-nginx-module_v1.16.tar.gz/download
        tar zxf download(download是你wget下来的文件名)
        cd fastdfs-nginx-module/src
        vim conf   #更改如下， 去掉local，并指定lib64（64系统）
        CORE_INCS="$CORE_INCS /usr/include/fastdfs /usr/include/fastcommon/"
        CORE_LIBS="$CORE_LIBS -L/usr/lib64 -lfastcommon -lfdfsclient"

        复制mod_fastdfs.conf文件到上面配置的/etc/fdfs目录下
        
        cp mod_fastdfs.conf /etc/fdfs/
        vim mod_fastdfs.conf (修改配置)
        group_name=group1
        base_path=/export/fastdfs/storage
        store_path0=/export/fastdfs/storage
        tracker_server=ip:22122  (ip是你本机的ip)
        url_have_group_name = true  (group前缀)

## nginx安装配置(关联fastdfs-nginx-module)
#### nginx依赖安装
    yum install gcc gcc-c++ make automake autoconf libtool pcre* zlib openssl openssl-devel
#### nginx安装    
        wget http://nginx.org/download/nginx-1.8.0.tar.gz
        tar zxf nginx-1.8.0.tar.gz
        cd nginx-1.8.0
        ./configure --prefix=/opt/nginx --add-module=/usr/local/src/fastdfs-nginx-module/src (添加fastdfs-nginx-module模块，prefix路径可自行配置)
        make && make install

        配置nginx.conf
        cd /opt/nginx/conf (之前prefix指向的路径)
         location /group1/M00 {
            root /export/fastdfs/storage/data; (文件存储路径)
            ngx_fastdfs_module;
         }
![nginx配置界面](/images/fastdfs1.png "nginx跳转配置")
        
        重启nginx(重启后配置生效)
        ./nginx -s reload

## 文件上传，浏览器访问测试
fdfs_test /etc/fdfs/client.conf upload test.txt(文件名)
上传成功返回路径
![文件上传](/images/fastdfs2.png "文件上传")

浏览器进行访问

 