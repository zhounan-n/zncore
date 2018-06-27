package com.zn.designpattern.behavior.Interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境类定义出变量值到布尔值的一个映射
 * Created by zhoun on 2018/6/27.
 **/
public class Context {
    private Map<Varible, Boolean> map = new HashMap<>();

    public void assign(Varible var, boolean value) {
        map.put(var, new Boolean(value));
    }

    public boolean lookup(Varible var) throws IllegalArgumentException{
        Boolean value = map.get(var);
        if(value == null){
            throw new IllegalArgumentException();
        }
        return value.booleanValue();
    }

}
