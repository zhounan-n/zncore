package com.zn.web.mvc.impl;

import com.zn.web.FrameworkConstant;
import com.zn.web.mvc.HandlerExceptionResolver;
import com.zn.web.mvc.fault.AuthcException;
import com.zn.web.mvc.fault.AuthzException;
import com.zn.web.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhoun on 2018/7/17.
 **/
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver{

    private static final Logger logger = LoggerFactory.getLogger(DefaultHandlerExceptionResolver.class);

    @Override
    public void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        // 判断异常原因
        Throwable cause = e.getCause();
        if (cause == null) {
            logger.error(e.getMessage(), e);
            return;
        }
        if (cause instanceof AuthcException) {
            // 分两种情况进行处理
            if (WebUtil.isAJAX(request)) {
                // 跳转到 403 页面
                WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
            } else {
                // 重定向到首页
                WebUtil.redirectRequest(FrameworkConstant.HOME_PAGE, request, response);
            }
        } else if (cause instanceof AuthzException) {
            // 跳转到 403 页面
            WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
        } else {
            // 跳转到 500 页面
            WebUtil.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cause.getMessage(), response);
        }
    }

}
