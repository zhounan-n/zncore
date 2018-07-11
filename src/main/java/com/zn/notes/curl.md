---
layout: posts
title: WINDOWS环境下安装curl
date: 2017-09-26 16:24:12
tags: elasticsearch
categories: elasticsearch
---

因为学习elasticsearch需要使用curl命令，因为电脑是windows系统，配置原因使用虚拟机会卡爆，所以需要在windows环境下安装以供之后的学习使用,需要的朋友可以mark

#### curl下载地址:
     https://curl.haxx.se/download.html
     
这里我下载的版本是7.53.1，如图
![版本](/images/bb.png  "版本")

<!--more-->
下载后解压到指定目录

#### 环境变量配置
    如图：
    
![环境变量配置1](/images/hjbl1.png  "环境变量配置1")
![环境变量配置2](/images/hjbl2.png  "环境变量配置2")

配置好环境变量后重启，打开命令窗口用curl命令进行验证
![安装成功](/images/hjbl2.png  "安装成功")    

其实我发现安装curl之前git客户端是可以使用curl命令的。。
