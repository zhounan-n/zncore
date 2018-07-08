package com.zn.designpattern.structrue.bridge;

/**
 * 桥接模式：
 *  将抽象部分与实现部分解耦，将继承转换成关联
 * Created by zhoun on 2018/7/7.
 **/
public class Client {

    public static void main(String[] args){
        Bridge bridge=new MyBridge();
        Driver driver=new MySqlDriver();
        bridge.setDriver(driver);
        bridge.connect();

        driver=new SqlServerDriver();
        bridge.setDriver(driver);
        driver.connect();
    }


}
