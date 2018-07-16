package com.zn.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhoun on 2018/7/16.
 **/
public interface ViewResolver {

    void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult);
}
