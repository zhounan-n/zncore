package com.zn.excp;

import com.zn.excp.entities.Result;
import com.zn.excp.enums.ExceptionEnum;
import com.zn.excp.exception.MyException;
import com.zn.excp.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhounan
 * created on 2018/7/30
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtils.error(myException.getCode(), myException.getMessage());
        }
        logger.error("系统异常:{}", e.getMessage());
        return ResultUtils.error(ExceptionEnum.UNKNOWN_ERROR);
    }


}
