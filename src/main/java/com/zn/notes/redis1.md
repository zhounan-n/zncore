---
layout: posts
title: redis集群搭建
date: 2018-03-26 21:24:01
tags: redis 集群
categories: redis
---
redis集群搭建过程记录：

> * 安装ruby
> * 安装redis
> * 单机模拟集群
> * 启动


### ruby安装

在centos7系统上用yum安装的ruby默认是2.0版本的，需要升级。
升级方式：
gpg --keyserver hkp://keys.gnupg.net --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3 7D2BAF1CF37B13E2069D6956105BD0E739499BDB

<!--more-->
\curl -sSL https://get.rvm.io | bash -s stable

source  /etc/profile.d/rvm.sh

rvm list known(可以查看版本)

rvm install 2.4.1

ruby -v
gem -v(查看安装的版本)

gem install redis(安装redis接口)

------

### redis安装

上传对应的redis包到相应的目录中(如/opt/redis/)
tar -zxvf (解压包)
cd redis(进入解压的目录)
make --prefix /usr/local/redis (安装到对应的目录)
make install

### 集群模拟 

cd /opt
mkdir redis-cluster
cd redis-cluster
mkdir -p 9001/data 9002/data 9003/data 9004/data 9005/data 9006/data
(9001-9006六个端口 模拟集群 主备 根据节点选取失败规则，最少3个，所有最少6个节点)

将redis安装目录bin下的文件拷贝到900X文件下(上面是安装到/usr/local/redis的目录中了)
将redis源码目录src下的redis-trib.rb拷贝到redis-cluster目录下
再将redis的配置文件复制到900X目录下

### 集群启动
进入到900x对应的目录下执行 ./redis-server ./redis.conf
启动遇到的错误：
1：WARNING overcommit_memory is set to 0! Background save may fail under low memory condition. To fix this issue add 'vm.overcommit_memory = 1' to /etc/sysctl.conf and then reboot or run the command 'sysctl vm.overcommit_memory=1' for this to take effect.
13891:M 26 Mar 15:41:00.353 # WARNING you have Transparent Huge Pages (THP) support enabled in your kernel. This will create latency and memory usage issues with Redis. To fix this issue run the command 'echo never > /sys/kernel/mm/transparent_hugepage/enabled' as root, and add it to your /etc/rc.local in order to retain the setting after a reboot. Redis must be restarted after THP is disabled.

2:WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.

以上两个错误需要修改/etc/sysctl,加上以下两个东西。
vm.overcommit_memory = 1
net.core.somaxconn= 1024
启动就不会报这个错误了

启动6个节点后再设置集群：
进入到redis源码src目录下：
./redis-trib.rb create --replicas 1 192.168.101.3:7001 192.168.101.3:7002 192.168.101.3:7003 192.168.101.3:7004 192.168.101.3:7005  192.168.101.3:7006
执行以上命令设置集群
