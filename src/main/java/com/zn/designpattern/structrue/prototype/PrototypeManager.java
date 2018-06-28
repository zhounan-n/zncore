package com.zn.designpattern.structrue.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型管理器角色
 * 提供所有原型对象的登记，提供必要的接口供外界增加新的原型对象和取得已经登记过的对象
 * Created by zhoun on 2018/6/28.
 **/
public class PrototypeManager {

    /**
     *  用来记录原型的编号和原型实例的对应关系
     */
    private static Map<String, Prototype> map = new HashMap<>();

    /**
     * 私有化构造方法 避免外部意外创建实例
     */
    private PrototypeManager() {

    }

    /**
     * 向原型管理器添加某个原型或修改
     * @param propertyId
     * @param prototype
     */
    public synchronized static void setPoperty(String propertyId, Prototype prototype) {
        map.put(propertyId, prototype);
    }

    /**
     * 从原型管理器里面删除某个原型注册
     * @param prototypeId 原型编号
     */
    public synchronized static void removePrototype(String prototypeId){
        map.remove(prototypeId);
    }

    /**
     *  获取某个原型编号对应的原型实例
     * @param prototypeId
     * @return
     * @throws Exception
     */
    public synchronized static Prototype getPrototype(String prototypeId) throws Exception{
        Prototype prototype = map.get(prototypeId);
        if(prototype == null){
            throw new Exception("您希望获取的原型还没有注册或已被销毁");
        }
        return prototype;
    }
}
