---
layout: posts
title: windows下将elasticsearch安装为系统服务
date: 2017-09-27 14:25:42
tags: elasticsearch
categories: elasticsearch
---
如何将elasticsearch安装为系统服务，受限于条件需要在本地windows环境学习elasticsearch(linux下的安装可以自行百度)

#### step1:
    cmd到bin目录下,执行 service.bat install
    执行成功后就可以在系统服务里找到elasticsearch的选项了，如下图
![系统服务界面](/images/es.png  "系统服务界面")
<!--more-->
    在同目录下执行service.bat 可启动服务
     