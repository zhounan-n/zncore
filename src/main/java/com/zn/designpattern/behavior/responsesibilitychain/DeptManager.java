package com.zn.designpattern.behavior.responsesibilitychain;

/**
 * Created by zhoun on 2018/6/20.
 **/
public class DeptManager extends Handler{

    @Override
    public String handleRequest(String user, double fee) {
        String str = "";
        if (fee < 1000) {
            //测试 只同意张三的请求
            if (user.equals("张三")) {
                str = "成功：项目经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            } else {
                str = "失败：项目经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }
        } else {
            //传递给更高权限的人处理
            if (getSuccessor() != null) {
                return getSuccessor().handleRequest(user, fee);
            }
        }
        return str;
    }

}
