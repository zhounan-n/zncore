package com.zn.designpattern.creation.prototype;

/**
 * 如果需要创建原型对象数目较少且相对固定的话，可以用简单模式
 * 如果需要创建的原型对象数目不确定的话，可以采取原型登记的原型模式
 *
 * java中的克隆方法 Object clone方法
 * Java语言提供的Cloneable接口只起一个作用，就是在运行时期通知Java虚拟机可以安全地在这个类上使用clone()方法。通过调用这个clone()方法可以得到一个对象的复制。由于Object类本身并不实现Cloneable接口，因此如果所考虑的类没有实现Cloneable接口时，调用clone()方法会抛出CloneNotSupportedException异常。
 * Created by zhoun on 2018/6/28.
 **/
public class Client {

    public static void main(String[] args) {
        try {

            Prototype p1 = new ConcretePrototype1();
            PrototypeManager.setPoperty("p1", p1);
            //获取原型来创建对象
            Prototype p3 = PrototypeManager.getPrototype("p1").clone();
            p3.setName("张三");
            System.out.println("第一个实例p3:" + p3);
            //有人动态的切换了实现
            Prototype p2 = new ConcretePrototype2();
            PrototypeManager.setPoperty("p1", p2);
            //重新获取原型创建对象
            Prototype p4 = PrototypeManager.getPrototype("p1").clone();
            p4.setName("李四");
            System.out.println("第二个实例：" + p4);
            //有人注销了这个原型
            PrototypeManager.removePrototype("p1");
            //再次获取原型来创建对象
            Prototype p5 = PrototypeManager.getPrototype("p1");
            p5.setName("王五");
            System.out.println("第三个实例：" + p5);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
