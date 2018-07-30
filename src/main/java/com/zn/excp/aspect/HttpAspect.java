package com.zn.excp.aspect;

import com.zn.excp.ExceptionHandle;
import com.zn.excp.entities.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhounan
 * created on 2018/7/30
 */
@Aspect
@Component
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private ExceptionHandle exceptionHandle;

    @Pointcut("execution(public * com.zn.excp.web..*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        //url
        logger.info("url:{}", httpServletRequest.getRequestURL());
        //method
        logger.info("method:{}", httpServletRequest.getMethod());
        //id
        logger.info("id:{}", httpServletRequest.getRemoteAddr());
        //class_method
        logger.info("class_method:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        //args
        logger.info("args:{}", joinPoint.getArgs());

    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Result result = null;
        try {

        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
        if (result == null) {
            return proceedingJoinPoint.proceed();
        } else {
            return result;
        }

    }


    @AfterReturning(pointcut = "log()", returning = "object")
    public void doAfterReturning(Object object) {
        logger.info("response:{}", object.toString());
    }

}
