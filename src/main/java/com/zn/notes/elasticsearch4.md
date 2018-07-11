---
layout: posts
title: elasticsearch2.3.2启动报错：ReceiveTimeOutTransportException解决
date: 2017-09-26 10:58:29
tags: elasticsearch
categories: elasticsearch
---

这几天一直在搞elasticsearch安装连接的各种问题，各种坑，使用要及其注意版本问题，版本不一样出现的问题不一样，这个错误在5.x版本中就不会出现

![启动报错界面](/images/cc.png  "启动报错界面")

#### 解决:
     修改配置文件，显示指定network.publish_host(ip根据自己的来)：
     #network.publish_host: 192.168.3.50
     #discovery.zen.ping.unicast.hosts: ["hosts"]
     
修改后就可以启动成功了
<!--more-->