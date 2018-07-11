---
layout: posts
title: java简单实现商品的秒杀(大概思路记录)
date: 2018-04-07 23:41:18
tags: 商品秒杀 java
categories: java基础
---

之前在面试的时候遇到过面试官问商品秒杀的问题，因为目前还没有在实际项目用到过，所以从网上找了视频学了下，用简单的demo练习了一下,这篇算是记录学习的一些概要。
参考：慕课网java秒杀api视频


秒杀实际上就是对于库存的操作。
秒杀的数据库设计：
秒杀表记录商品、库存、秒杀开始结束时间，秒杀成功记录秒杀明细用秒杀明细表。
同一用户只能对同一商品进行秒杀一次，所以秒杀商品和用户确定秒杀的唯一性，用联合主键
<!--more-->
create table seckill(
 'seckill_id' bigint not null auto_increment comment '',
 'name' varchar(120) not null comment '库存名字',
 'number' int not null comment '库存数量',
 'start_time' TIMESTAMP  not null comment '秒杀开始时间',
 'end_time' TIMESTAMP  not null comment '秒杀结束时间',
 'create_time' TIMESTAMP not null comment '创建时间',
 PRIMARY key (seckill_id),
 key idx_start_time(start_time),
 key idx_end_time(end_time),
 key idx_reate_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 comment '秒杀库存表';

-- 秒杀成功明细表
create table success_killd(
  'seckill_id' bigint not null comment '秒杀商品id',
  'user_phone' bigint not null comment '用户手机号',
  'state' tinyint not null default -1 comment '状态: -1表示无效 '
  'create_time' TIMESTAMP not null comment '创建时间',
  PRIMARY key(seckill_id,user_phone),/*联合主键 同一用户同一产品只能秒杀一次*/
  KEY idx_create_time(create_time)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 comment '秒杀成功明细表';

后台接口：
秒杀列表
秒杀商品详情
秒杀操作
暴露秒杀接口

秒杀逻辑：
   1：用户信息的判断(要登录)
   2：减库存+购买明细
    1：判断明细是否重复插入，即判断是否重复购买
    2：减库存(秒杀是否结束)
    3：秒杀成功增加购买明细
  秒杀为成功则事务回滚(并不减少库存)
  
sql控制部分：
        减库存(在对应时间内减库存)：
        UPDATE seckill
        SET number = number - 1
        WHERE seckill_id = #{seckillId}
        AND start_time <![CDATA[
        <=
        ]]> #{killTime}
        AND end_time >= #{killTime}
        AND number > 0
        
        <!--添加主键冲突时忽略错误返回0-->
        INSERT IGNORE INTO success_killed (seckill_id, user_phone, state)
        VALUES (#{seckillId}, #{userPhone}, 0)
        
    (后续补充)
    行级锁：排它锁 共享锁 (针对索引)
    共享锁: 只读
    排它锁：只能一个事务进行操作