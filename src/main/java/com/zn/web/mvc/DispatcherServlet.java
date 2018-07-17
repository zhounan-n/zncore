package com.zn.web.mvc;

import com.zn.web.FrameworkConstant;
import com.zn.web.InstanceFactory;
import com.zn.web.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 核心转发类
 * Created by zhoun on 2018/7/16.
 **/
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private HandlerMapping handlerMapping = InstanceFactory.getHandlerMapping();
    private HandlerInvoker handlerInvoker = InstanceFactory.getHandlerInvoker();
    private HandlerExceptionResolver handlerExceptionResolver = InstanceFactory.getHandlerExceptionResolver();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码方式
        req.setCharacterEncoding(FrameworkConstant.UTF_8);
        //获取当前请求相关数据
        String currentRequestMethod = req.getMethod();
        String currentRequestPath = WebUtil.getRequestPath(req);
        logger.debug("[Smart] {}:{}", currentRequestMethod, currentRequestPath);
        //将"/"请求重定向到首页
        if (currentRequestPath.equals("/")) {
            WebUtil.redirectRequest(FrameworkConstant.HOME_PAGE, req, resp);
            return;
        }
        //去掉打那个钱请求路径的末尾的"/"
        if (currentRequestPath.endsWith("/")) {
            currentRequestPath = currentRequestPath.substring(0, currentRequestPath.length() - 1);
        }
        //获取Handler
        Handler handler = handlerMapping.getHandler(currentRequestMethod, currentRequestPath);
        //若未找到Action则跳到404页面
        if (null == handler) {
            WebUtil.sendError(HttpServletResponse.SC_NOT_FOUND, "", resp);
            return;
        }
        //初始化DataContext
        

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关配置
        ServletContext servletContext = servletConfig.getServletContext();
        UploadHelper.init(servletContext);
    }

}
