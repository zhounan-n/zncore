package com.zn.designpattern.structrue.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoun on 2018/7/7.
 **/
public class PayDemo {

    public abstract class Market {
        String name;

        public abstract void add(Market m);

        public abstract void remove(Market m);

        public abstract void payByCard();
    }

    //分店 下面可以有加盟店

    public class MarketBranch extends Market {
        /**
         * 加盟店列表
         */
        List<Market> list = new ArrayList<>();

        public MarketBranch(String s) {
            this.name = s;
        }


        @Override
        public void add(Market m) {
            list.add(m);
        }

        @Override
        public void remove(Market m) {
            list.remove(m);
        }

        @Override
        public void payByCard() {
            System.out.println(name + "已加入会员");
            for (Market m : list) {
                m.payByCard();
            }
        }

    }

    //加盟店 下面不再有加盟店和分店

    public class MarketJoin extends Market {
        public MarketJoin(String s) {
            this.name = s;
        }

        @Override
        public void add(Market m) {

        }

        @Override
        public void remove(Market m) {

        }

        @Override
        public void payByCard() {
            System.out.println(name + "消费，积分已加入会员卡");
        }
    }

    public static void main(String[] args) {
        PayDemo payDemo = new PayDemo();

        MarketBranch rootBranch = payDemo.new MarketBranch("总店");
        MarketBranch ahdBranch = payDemo.new MarketBranch("秦皇岛分店");

        MarketJoin hgqJoin = payDemo.new MarketJoin("海港区加盟");
        MarketJoin btlJoin = payDemo.new MarketJoin("白塔岭加盟");

        ahdBranch.add(hgqJoin);
        ahdBranch.add(btlJoin);

        rootBranch.add(ahdBranch);

        rootBranch.payByCard();

    }

}
