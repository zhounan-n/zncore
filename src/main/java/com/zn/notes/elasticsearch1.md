---
title: elasticsearch及head插件在windows环境下的安装
date: 2017-09-20 17:57:28
tags: elasticsearch
categories: elasticsearch
---


最近在学习使用elasticsearch,但是没有linux环境，所以在网上搜集了各种资料后成功在本地windows系统下安装了，简单总结下。

## elasticsearch的安装

  - elasticsearch的安装比较简单，直接去官网下载就可以，解压。执行bin文件下的bat文件即可启动，附上elasticsearch启动成功的界面。
  ![启动成功界面](/images/1.png "elasticsearch成功启动界面")
 
## head插件的安装
<!--more-->
   - 这里要用到node.js,下面需要用npm指令安装插件，百度一下安装即可。
   - 安装grunt,head插件是通过grunt启动的。
   路径切换到安装的node.js目录下，如：D:\nodejs,执行以下命令(如果你安装了git,可以直接在目录下右键git bash打开窗口,比cmd窗口要美观一些)
   npm install -g grunt-cli
   安装完成后用grunt -verion检查是否安装成功
   ![grunt安装界面](/images/2.png "grunt安装界面")
   - 用git下载head插件
   git clone git://github.com/mobz/elasticsearch-head.git
   #### 修改head源码：
       -- 因为head插件的版本是2.6的，直接执行有很多限制，需要修改接个地方
       目录：head/Gruntfile.js：
 connect: {
    &emsp;&emsp;server: {
        &emsp;&emsp;options: {
            &emsp;&emsp; port: 9100,
            &emsp;&emsp;hostname: '*',
           &emsp;&emsp; base: '.',
            &emsp;&emsp;keepalive: true
        &emsp;&emsp;}
    &emsp;&emsp;}
}
增加hostname属性，设置为\*
   #### 修改链接地址：
        目录：head/_site/app.js
        this.base_uri = this.config.base_uri || this.prefs.get("app-base_uri") ||"http://localhost:9200";
        把localhost修改成你es的服务器地址，如：
        this.base_uri = this.config.base_uri || this.prefs.get("app-base_uri") ||"http://10.10.10.10:9200";

#### 运行head
     修改elasticsearch的配置文件 config/elasticsearch.yml(参数后面要有空格)：
     
     换个集群的名字，免得跟别人的集群混在一起
     cluster.name: es-5.0-test

     换个节点名字
     node.name: node-101

     修改一下ES的监听地址，这样别的机器也可以访问
     network.host: 0.0.0.0

     端口默认的就好
     http.port: 9200

     增加新的参数，这样head插件可以访问es
     http.cors.enabled: true
     http.cors.allow-origin: "*"
     
### 启动elasticsearch
### 然后在head源码目录中，执行npm install 下载的包：
    npm install (这个命令执行可能要等一会儿，运行报错就重新执行一遍直到完成)
### 启动head插件
    localhost:9100
    这样就可以访问head插件了
![head插件启动成功界面](/images/3.png  "head插件启动界面")

     
