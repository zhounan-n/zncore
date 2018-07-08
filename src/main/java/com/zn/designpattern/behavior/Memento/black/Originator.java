package com.zn.designpattern.behavior.Memento.black;

/**
 * Created by zhoun on 2018/7/8.
 **/
public class Originator {

    private String state;


    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("赋值状态：" + state);
    }

    /**
     * 工厂方法,返回一个新的备忘录对象
     */
    public MementoIF createMemento() {
        return new Memento(state);
    }

    /**
     * 发起人恢复到备忘录记录的状态
     */
    public void restoreMememto(MementoIF mementoIF) {
        this.setState(((Memento) mementoIF).getState());
    }


    private class Memento implements MementoIF {

        private String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

}
