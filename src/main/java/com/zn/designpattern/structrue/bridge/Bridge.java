package com.zn.designpattern.structrue.bridge;

/**
 * 桥接类(抽象类)
 * Created by zhoun on 2018/7/8.
 **/
public abstract class Bridge {

    private Driver driver;

    public void connect() {
        driver.connect();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
