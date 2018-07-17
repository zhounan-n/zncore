package com.zn.web.mvc;

import com.zn.web.utils.ArrayUtil;
import com.zn.web.utils.CastUtil;
import com.zn.web.utils.CodecUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据上下文
 * Created by zhoun on 2018/7/17.
 **/
public class DataContext {

    /**
     * 提供每个线程各自拥有的DataContext实例
     */
    private static final ThreadLocal<DataContext> dataContextContainer = new ThreadLocal<DataContext>();

    private HttpServletRequest servletRequest;

    private HttpServletResponse servletResponse;


    /**
     * 初始化
     */
    public static void init(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        DataContext dataContext = new DataContext();
        dataContext.servletRequest = servletRequest;
        dataContext.servletResponse = servletResponse;
        dataContextContainer.set(dataContext);
    }

    /**
     * 销毁
     */
    public static void destroy() {
        dataContextContainer.remove();
    }

    /**
     * 获取实例
     */
    public static DataContext getInstance() {
        return dataContextContainer.get();
    }

    /**
     * 获取Request对象
     */
    public static HttpServletRequest getRequest() {
        return getInstance().servletRequest;
    }

    /**
     * 获取Response对象
     */
    public static HttpServletResponse getResponse() {
        return getInstance().servletResponse;
    }

    /**
     * 获取session对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取servlet context对象
     */
    public static javax.servlet.ServletContext getServletContext() {
        return getRequest().getServletContext();
    }

    /**
     * 封装 Request 相关操作
     */
    public static class Request {
        /**
         * 将数据放入 Request 中
         */
        public static void put(String key, Object value) {
            getRequest().setAttribute(key, value);
        }

        /**
         * 从 Request 中获取数据
         */
        @SuppressWarnings("unchecked")
        public static <T> T get(String key) {
            return (T) getRequest().getAttribute(key);
        }

        /**
         * 移除 Request 中的数据
         */
        public static void remove(String key) {
            getRequest().removeAttribute(key);
        }

        /**
         * 从 Request 中获取所有数据
         */
        public static Map<String, Object> getAll() {
            Map<String, Object> map = new HashMap<String, Object>();
            Enumeration<String> names = getRequest().getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                map.put(name, getRequest().getAttribute(name));
            }
            return map;
        }

    }

    /**
     * 封装 Response 相关操作
     */
    public static class Response {

        /**
         * 将数据放入 Response 中
         */
        public static void put(String key, Object value) {
            getResponse().setHeader(key, CastUtil.castString(value));
        }

        /**
         * 从 Response 中获取数据
         */
        @SuppressWarnings("unchecked")
        public static <T> T get(String key) {
            return (T) getResponse().getHeader(key);
        }

        /**
         * 从 Response 中获取所有数据
         */
        public static Map<String, Object> getAll() {
            Map<String, Object> map = new HashMap<String, Object>();
            for (String name : getResponse().getHeaderNames()) {
                map.put(name, getResponse().getHeader(name));
            }
            return map;
        }
    }

    /**
     * 封装 Session 相关操作
     */
    public static class Session {

        /**
         * 将数据放入 Session 中
         */
        public static void put(String key, Object value) {
            getSession().setAttribute(key, value);
        }

        /**
         * 从 Session 中获取数据
         */
        @SuppressWarnings("unchecked")
        public static <T> T get(String key) {
            return (T) getSession().getAttribute(key);
        }

        /**
         * 移除 Session 中的数据
         */
        public static void remove(String key) {
            getSession().removeAttribute(key);
        }

        /**
         * 从 Session 中获取所有数据
         */
        public static Map<String, Object> getAll() {
            Map<String, Object> map = new HashMap<String, Object>();
            Enumeration<String> names = getSession().getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                map.put(name, getSession().getAttribute(name));
            }
            return map;
        }

        /**
         * 移除 Session 中所有的数据
         */
        public static void removeAll() {
            getSession().invalidate();
        }
    }

    /**
     * 封装 Cookie 相关操作
     */
    public static class Cookie {

        /**
         * 将数据放入 Cookie 中
         */
        public static void put(String key, Object value) {
            String strValue = CodecUtil.encodeURL(CastUtil.castString(value));
            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(key, strValue);
            getResponse().addCookie(cookie);
        }

        /**
         * 从 Cookie 中获取数据
         */
        @SuppressWarnings("unchecked")
        public static <T> T get(String key) {
            T value = null;
            javax.servlet.http.Cookie[] cookieArray = getRequest().getCookies();
            if (ArrayUtil.isNotEmpty(cookieArray)) {
                for (javax.servlet.http.Cookie cookie : cookieArray) {
                    if (key.equals(cookie.getName())) {
                        value = (T) CodecUtil.decodeURL(cookie.getValue());
                        break;
                    }
                }
            }
            return value;
        }

        /**
         * 从 Cookie 中获取所有数据
         */
        public static Map<String, Object> getAll() {
            Map<String, Object> map = new HashMap<String, Object>();
            javax.servlet.http.Cookie[] cookieArray = getRequest().getCookies();
            if (ArrayUtil.isNotEmpty(cookieArray)) {
                for (javax.servlet.http.Cookie cookie : cookieArray) {
                    map.put(cookie.getName(), cookie.getValue());
                }
            }
            return map;
        }
    }

    /**
     * 封装 ServletContext 相关操作
     */
    public static class ServletContext {

        /**
         * 将数据放入 ServletContext 中
         */
        public static void put(String key, Object value) {
            getServletContext().setAttribute(key, value);
        }

        /**
         * 从 ServletContext 中获取数据
         */
        @SuppressWarnings("unchecked")
        public static <T> T get(String key) {
            return (T) getServletContext().getAttribute(key);
        }

        /**
         * 移除 ServletContext 中的数据
         */
        public static void remove(String key) {
            getServletContext().removeAttribute(key);
        }

        /**
         * 从 ServletContext 中获取所有数据
         */
        public static Map<String, Object> getAll() {
            Map<String, Object> map = new HashMap<String, Object>();
            Enumeration<String> names = getServletContext().getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                map.put(name, getServletContext().getAttribute(name));
            }
            return map;
        }
    }


}
