package com.zn.paramcheck;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.sun.rmi.rmid.ExecPermission;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoun on 2018/7/5.
 **/
@Aspect
@Component
public class RequiredParam {

    public RequiredParam() {
        System.out.println("初始化参数校验类");
    }

    @Pointcut("@annotation(com.zn.paramcheck.CheckParam)")
    public void controllerRequired() {

    }

    @Around("controllerRequired()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            //获取注解的方法
            MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint) proceedingJoinPoint;
            MethodSignature methodSignature = (MethodSignature) methodInvocationProceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            //获取方法上的注解
            CheckParam checkParam = method.getAnnotation(CheckParam.class);
            //放行
            if (!checkParam.require()) {
                proceedingJoinPoint.proceed();
            }
            String result = requesParameter();
            Gson gson = new Gson();
            List<Map> param = gson.fromJson(result, ArrayList.class);
            System.out.println(String.format("Request Param;{%s}", param));
            Preconditions.checkNotNull(param, "request param is null");
            //获取方法上的校验字段
            String fieldNames = checkParam.fieldNames().replace("，", ",");
            for (String fieldName : fieldNames.split(",")) {
                Preconditions.checkNotNull(param.get(0).get(fieldName), fieldName + "not found");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return proceedingJoinPoint.proceed();
    }

    /**
     * 获取请求中的数据
     */
    public static String requesParameter() throws Exception {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = sra.getRequest();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), "UTF-8"));
        String jsonStr;
        StringBuilder result = new StringBuilder();
        while ((jsonStr = bufferedReader.readLine()) != null) {
            result.append(jsonStr);
        }
        if (StringUtils.isEmpty(result.toString())) {
            return ERROR_MESSAGE;
        }
        return result.toString();
    }

    private static final String ERROR_MESSAGE = "缺少必要参数";
}
