package com.zn.designpattern.behavior.Mediator;

/**
 * Created by zhoun on 2018/7/8.
 **/
public abstract class AbstractMediator {

    protected AbstractColleague a;
    protected AbstractColleague b;

    public AbstractMediator(AbstractColleague a, AbstractColleague b) {
        this.a = a;
        this.b = b;
    }

    public abstract void AaffectB();

    public abstract void BaffectA();

}
