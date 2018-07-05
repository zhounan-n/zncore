package com.zn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhoun
 * @since 2017/3/29.
 */
@Component
public class CookieUtil {
    private final static String cookieDomainName = "userInfo";
    private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;


    public static void saveCookie(HttpServletResponse response, Object user) {
        //cookie有效期
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 5000);
        String passWord = "";
        String cookieValue = "";
        Cookie cookie = new Cookie(cookieDomainName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void clearCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieDomainName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public boolean readCookieAndLogOn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(cookieDomainName)) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        if (cookieValue == null) {
            return false;
        }
        String[] cookieValues = cookieValue.split(",");
        if (cookieValues.length != 3) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("您正在用非正常的方式进入本站");
            out.close();
            return false;
        }
        //cookie是否有效
        long validateTime = new Long(cookieValues[2]);
        if (validateTime < System.currentTimeMillis()) {
            clearCookie(response);
            return false;
        }
        String userName = cookieValues[0];
        Object user =new Object();
        //User user = userService.getUser(userName);
       /* if (user != null) {
            String pwd = cookieValues[1];
            String userPwd=CryptUtil.encryptPwd(user.getPwd(),user.getMob());
            if(StringUtils.equals(pwd, userPwd)){
                HttpSession session=request.getSession(true);
                session.setAttribute(UserConstant.USER_INFO,user);
                return true;
            }
        }else{
            clearCookie(response);//用户记录被删除
        }*/
        return false;
    }


}
