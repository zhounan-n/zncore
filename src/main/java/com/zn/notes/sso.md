---
layout: posts
title: java单点登录的实现
date: 2018-04-01 21:14:32
tags: sso
categories: sso
---

------

> * 什么单点登录
> * 单点登录的好处
> * 单点登录的实现


### 单点登录的概念

> 简单地说，单点登录就是面对集群多个server之间，有一个server登录了，其他server也一起登录了

<!--more-->

------

### 单点登录的好处

> 用户不用进行多次登陆，身份认证中间流转打通子系统，降低安全和管理的风险，应对面向服务的架构，分布式集群，不同步服务部署在不同的服务器上的情况

### 单点登录的实现
> 通过redis存储token来进行身份的认证

具体实现步骤：
1：登录接口，接受密码进行比对，是否存在该用户，密码是否正确，生成token(可以用jwt)，用redis将token信息作为key的一部分，value存放用户信息,redis设置过期时间。将token写入cookie(浏览器关闭自动退出),返回token

2: 拦截器接收cookie,根据token获取用户信息(没有token或者从redis中获取不到对应的信息则需要重新登录)，获取到用户则刷新redis失效时间

3：手动退出,清空redis

(认证作为单独的一个服务，其他服务接收到token用它调用认证服务器认证信息)