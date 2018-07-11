---
layout: posts
title: WINDOWS环境下安装elasticsearch集群
date: 2017-09-26 18:02:29
tags: elasticsearch
categories: elasticsearch
---

限于硬件条件，在windows安装集群，是为了学习elastisearch
以下都在本机试过了并且记录了会遇到的问题

#### step1:
     再复制一份elasticsearch文件并且重命名。
     
#### step2:
     修改elasticsearch配置文件
     cluster.name: elasticsearch
     node.name: node-1
     network.host: 192.168.3.50
     http.port: 9201 (同一机器不能一样)
     network.publish_host: 192.168.3.50(本机地址)
     discovery.zen.ping.unicast.hosts: ["192.168.3.50:9500"] (通信地址写当前的)
     transport.tcp.port: 9501 (通信端口同一机器不能一样)
<!--more-->     
     cluster.name: elasticsearch
     node.name: node-2
     network.host: 192.168.3.50
     http.port: 9201 (同一机器不能一样)
	 network.publish_host: 192.168.3.50(本机地址)
     discovery.zen.ping.unicast.hosts: ["192.168.3.50:9500","192.168.3.50:9501"] (通信地址添加前面的节点)
     transport.tcp.port: 9501 (通信端口同一机器不能一样)
     
#### step3:集群搭建成功
    启动节点1
    启动节点2成功后界面会显示节点added的信息，如下图
![节点1](/images/jd1.png  "节点1")
![节点2](/images/jd2.png  "节点2")
    
#### 可能会遇到的问题：
     如果启动后cmd窗口自动关闭了，可能是端口问题，修改一下通信端口，如:9500
     