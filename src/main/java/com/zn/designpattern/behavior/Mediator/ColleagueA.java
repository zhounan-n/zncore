package com.zn.designpattern.behavior.Mediator;

/**
 * ColleagueA与ColleagueB为同事类关系
 * Created by zhoun on 2018/7/8.
 **/
public class ColleagueA extends AbstractColleague {

    @Override
    public void setNumber(int num, AbstractColleague coll) {
        this.number = num;
        coll.setNumber(num * 100);
    }

    @Override
    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.AaffectB();
    }
}
