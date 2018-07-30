package com.zn.excp.utils;

import com.zn.excp.entities.Result;
import com.zn.excp.enums.ExceptionEnum;

/**
 * @author zhounan
 * created on 2018/7/30
 */
public class ResultUtils {

    public static Result success(Object object) {
        Result result = new Result();
        result.setStatus(1);
        result.setCode("SUCCESS");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer errorStatus, String errorMsg) {
        Result result = new Result();
        result.setStatus(errorStatus);
        result.setCode(errorMsg);
        return result;
    }

    public static Result error(ExceptionEnum exceptionEnum) {
        Result result = new Result();
        result.setStatus(exceptionEnum.getStatue());
        result.setCode(exceptionEnum.getDesc());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(0);
        result.setCode("FAILED");
        return result;
    }


}
