package com.zn.web.mvc.bean;

import com.zn.web.core.bean.BaseBean;
import com.zn.web.utils.CastUtil;

import java.util.Map;

/**
 * 封装请求参数
 * Created by zhoun on 2018/7/16.
 **/
public class Params extends BaseBean {

    private final Map<String, Object> fieldMap;

    public Params(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public String getString(String name) {
        return CastUtil.castString(get(name));
    }

    public Object get(String name) {
        return fieldMap.get(name);
    }

    public double getDouble(String name) {
        return CastUtil.castDouble(get(name));
    }

    public long getLong(String name) {
        return CastUtil.castLong(get(name));
    }

    public int getInt(String name) {
        return CastUtil.castInt(get(name));
    }
}
