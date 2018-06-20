package com.zn.designpattern.behavior.responsesibilitychain;

/**
 * 责任链模式
 * 击鼓传花 责任的传递(第一个处理者对象的下家是第二个处理者对象，将请求传给第一个处理者，第一个处理者收到请求后传给第二个处理者)
 * <p>
 * 应用场景：
 * 聚餐费用的申请：项目经理-->部门经理-->总经理 处理链的传递 (多级处理)
 * 需要把每位领导的处理独立出来，形成单独的职责处理对象，为他们提供一个公共的抽象的父职责对象，这样就可以在客户端动态的组织职责连
 * 实现不同的功能要求
 * <p>
 * <p>
 * Created by zhoun on 2018/6/20.
 **/
public class Client {

    public static void main() {
        //先组装职责连
        Handler h1 = new GeneralManager();
        Handler h2 = new DeptManager();
        Handler h3 = new ProjectManager();

        h2.setSuccessor(h1);
        h3.setSuccessor(h2);

        //测试
        String test1 = h3.handleRequest("张三", 300);
        String test2 = h3.handleRequest("李四",300);



    }

}
