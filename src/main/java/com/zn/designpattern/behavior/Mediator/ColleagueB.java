package com.zn.designpattern.behavior.Mediator;

/**
 * Created by zhoun on 2018/7/8.
 **/
public class ColleagueB extends AbstractColleague {

    @Override
    public void setNumber(int num, AbstractColleague coll) {
        this.number = num;
        coll.setNumber(num / 100);
    }

    @Override
    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.BaffectA();
    }
}
