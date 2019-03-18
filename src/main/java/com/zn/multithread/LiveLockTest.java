package com.zn.multithread;

/**
 * 活锁测试类
 * 创建一个勺子类，有且只有一个
 * 丈夫和妻子用餐时，需要使用勺子，这时只能一个人拥有，同一时刻只能有同一个人进餐
 * 但是丈夫和妻子互相谦让，都想让对方先吃，所以勺子一直传递来传递去，谁都没法用餐。
 * Created by zhoun on 2019/3/18.
 **/
public class LiveLockTest {

    //定义一个勺子，owner表示这个勺子的拥有者
    static class Spoon {
        Dinner owner;//勺子的拥有者

        public Spoon(Dinner dinner) {
            this.owner = dinner;
        }

        //获取拥有者
        public String getOwnerName() {
            return owner.getName();
        }

        //设置拥有者
        public void setOwner(Dinner dinner) {
            this.owner = dinner;
        }

        //表示正在用餐
        public void use() {
            System.out.println(owner.getName() + "use this spoon and eat");
        }
    }

    //定义一个晚餐类
    static class Dinner {

        public Dinner(boolean isHungrey, String name) {
            this.isHungrey = isHungrey;
            this.name = name;
        }

        public boolean isHungrey;//是否饿了
        private String name;//当前用餐者的名字

        public boolean isHungrey() {
            return isHungrey;
        }

        public void setHungrey(boolean hungrey) {
            isHungrey = hungrey;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        //和某人吃饭
        public void eatWith(Dinner spouse, Spoon sharedSpoon) {
            try {

                synchronized (sharedSpoon) {
                    while (isHungrey) {
                        //当前用餐者和勺子拥有者不是同一个人则进行等待
                        while (!sharedSpoon.getOwnerName().equals(name)) {
                            sharedSpoon.wait();
                        }
                        //spouse此时是饿了，把勺子分给他
                        if (spouse.isHungrey) {
                            System.out.println("I am " + name + ", and my " + spouse.getName() + " is hungry, I should give it to him(her).\n");
                            sharedSpoon.setOwner(spouse);
                            sharedSpoon.notifyAll();

                        } else {
                            //用餐
                            sharedSpoon.use();
                            sharedSpoon.setOwner(spouse);
                            isHungrey = false;
                        }
                        Thread.sleep(500);
                    }

                }


            } catch (Exception e) {
                System.out.println(name + " is interrupted.");
            }
        }


    }


    public static void main(String[] args) {

        //创建一个丈夫用餐类
        Dinner husband = new Dinner(true, "husband");
        //创建一个妻子用餐类
        Dinner wife = new Dinner(true, "wife");
        final Spoon sharedSpoon = new Spoon(wife); //创建一个勺子  初始状态由妻子拥有

        //创建一个线程  由丈夫用餐
        Thread h = new Thread(new Runnable() {
            @Override
            public void run() {
                //和妻子用餐  判断妻子是否饿了  如果是 把勺子分给妻子 并通知他
                husband.eatWith(wife, sharedSpoon);
            }
        });
        h.start();

        //创建一个线程  由妻子用餐
        Thread w = new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(husband, sharedSpoon);
            }
        });

        w.start();

        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }

        h.interrupt();
        w.interrupt();

        try {
            h.join();
            w.join();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
