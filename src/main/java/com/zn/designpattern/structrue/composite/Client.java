package com.zn.designpattern.structrue.composite;

/**
 * 组合模式
 *
 * 会员卡消费：
 * 总店 分店  加盟店  在总店刷卡后，就可以在所有店累积积分
 * 什么情况下使用组合模式：
 *      当发现需求中时体现部分与整体层次结构时，以及你希望用户可以忽略组合对象与单个对象的不同，统一的使用组合结构中的所有对象时，就应该考虑组合模式了
 * Created by zhoun on 2018/7/7.
 **/
public class Client {

    public static void main(String[] args) {
        PayDemo payDemo = new PayDemo();

        PayDemo.MarketBranch rootBranch = payDemo.new MarketBranch("总店");
        PayDemo.MarketBranch ahdBranch = payDemo.new MarketBranch("秦皇岛分店");

        PayDemo.MarketJoin hgqJoin = payDemo.new MarketJoin("海港区加盟");
        PayDemo.MarketJoin btlJoin = payDemo.new MarketJoin("白塔岭加盟");

        ahdBranch.add(hgqJoin);
        ahdBranch.add(btlJoin);

        rootBranch.add(ahdBranch);

        rootBranch.payByCard();

    }


}
