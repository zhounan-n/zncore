---
layout: posts
title: elasticsearch2.3.2在windows环境中安装head插件
date: 2017-09-25 15:25:45
tags: elasticsearch
categories: elasticsearch
---


这几天本地项目连接elasticsearch一直连不上有点烦躁啊。head插件安装之前那篇文章也有记录，但这个不太一样(和版本也有关系)，之前百度的一些都是说plugin -install,没有这个命令，plugin install才对。
#### 安装:
     进入到elasticsearch安装目录，进入bin目录执行安装命令即可：
     plugin install mobz/elasticsearch-head 
     
![head插件启动成功界面](/images/4.png  "head插件安装界面")
<!--more-->


     