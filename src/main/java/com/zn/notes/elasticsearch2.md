---
title: elasticsearch在linux环境中安装遇到的问题及解决方法
date: 2017-09-21 16:29:13
tags: elasticsearch
categories: elasticsearch
---

昨天写了再windows环境中安装elasticsearh的文章，在使用中可能依然会遇到一些问题。今天这篇记录一下在linux环境中安装启动遇到的一些错误。我的系统是centos7
#### 错误1:
     root用户启动报错，切换成非root用户启动

#### 错误2:
     Could not register mbeans java.security.AccessControlException
     解决方法：切换之后要把elasticsearch文件的所有权改成当前用户，命令如下(zhoun为当前用户名)：
     chown -R zhoun 目录名/
<!--more-->
#### 错误3:
     BindTransportException[Failed to bind to [9300-9400]]
     解决犯法：修改/ect/elasticsearch/elasticsearch.yml配置文件，例如:127.0.0.1
	 
#### 错误4:
     max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
     解决方法:切换到root用户执行如下命令：
     echo "vm.max_map_count=262144" >>/etc/sysctl.conf
	 修改后需重启才会生效
	 
#### 错误5：
     bootstrap checks failed 
     max file descriptors [65535] for elasticsearch process likely too low, increase to at least [65536]
     解决方法：切换到root用户，修改limits.conf配置文件，保存后记得重启(这个错误试了好久)
     vi /etc/security/limits.conf 
     添加如下内容:
     * soft nofile 65536
     * hard nofile 131072
     * soft nproc 2048
     * hard nproc 4096
     
###### 解决了上面几个错误后elasticsearch成功在虚拟机中启动了(可能环境不一样遇到的错误也不一样，我这里只把我遇到的记录整理了)