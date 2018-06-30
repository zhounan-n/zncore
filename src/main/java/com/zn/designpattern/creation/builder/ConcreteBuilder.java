package com.zn.designpattern.creation.builder;

/**
 * 具体建造者
 * Created by zhoun on 2018/6/30.
 **/
public class ConcreteBuilder implements Builder {

    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("编号:9527");
    }

    @Override
    public void buildPart2() {
        product.setPart2("名称：xxx");
    }

    /**
     * 产品返还方法
     *
     * @return
     */
    @Override
    public Product retrieveResult() {
        return product;
    }
}
