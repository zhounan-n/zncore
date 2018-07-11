---
layout: posts
title: ELASTICSEARCH:项目整合连接报错receiveTransportException:fail to getnode解决
date: 2017-09-30 10:43:09
tags: elasticsearch
categories: elasticsearch
---

windows环境,之前项目连整合连接一直报这个错误，而且spring官方文档上也没说这个端口是什么端口，疑惑了很久，今天在大神的帮助下总算解决了(非常感谢大神)，记录一下

#### 错误信息截图:
![连接错误信息](/images/receivetimeoutexception.png  "连接错误信息")
<!--more-->
#### 解决:
        spring.data.elasticsearch.cluster-nodes=localhost:9300
        项目中上面这条配置的端口要与配置文件中的transport.tcp.port: 9501对应，
        配置这个表明是使用TransportClient来连接elasticsearch的。
        
最后再次感谢大神的帮助让我解决了疑惑好几天的问题。