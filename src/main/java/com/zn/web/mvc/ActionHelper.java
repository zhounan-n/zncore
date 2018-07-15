package com.zn.web.mvc;

import com.zn.web.core.ClassHelper;
import com.zn.web.mvc.annotation.Action;
import com.zn.web.mvc.annotation.Request;
import com.zn.web.utils.ArrayUtil;
import com.zn.web.utils.CollectionUtil;
import com.zn.web.utils.StringUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化Action配置
 * Created by zhoun on 2018/7/15.
 **/
public class ActionHelper {

    /**
     * Action Map（HTTP 请求与 Action 方法的映射）
     */
    private static final Map<Requester, Handler> actionMap = new HashMap<Requester, Handler>();

    static {
        //获取所有Action类
        List<Class<?>> actionClassList = ClassHelper.getClassByAnnotation(Action.class);
        if (CollectionUtil.isNotEmpty(actionClassList)) {
            //定义两个ActionMap
            Map<Requester, Handler> commonActionMap = new HashMap<Requester, Handler>();
            Map<Requester, Handler> regexpActionMap = new HashMap<Requester, Handler>();
            //遍历Action类
            for (Class<?> actionClass : actionClassList) {
                //获取并遍历该Action中的所有方法
                Method[] actionMethods = actionClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(actionMethods)) {
                    for (Method actionMethod : actionMethods) {
                        handleActionMethod(actionClass, actionMethod, commonActionMap, regexpActionMap);
                    }
                }
            }
            actionMap.putAll(commonActionMap);
            actionMap.putAll(regexpActionMap);
        }
    }

    private static void handleActionMethod(Class<?> actionClass, Method actionMethod, Map<Requester, Handler> commonMap, Map<Requester, Handler> regexpMap) {
        //判断当前Action方法是否带有Request注解
        if (actionMethod.isAnnotationPresent(Request.Get.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Get.class).value();
            putActionMap("GET", requestPath, actionClass, actionMethod, commonMap, regexpMap);
        } else if (actionMethod.isAnnotationPresent(Request.Post.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Post.class).value();
            putActionMap("POST", requestPath, actionClass, actionMethod, commonMap, regexpMap);
        } else if (actionMethod.isAnnotationPresent(Request.Put.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Put.class).value();
            putActionMap("GET", requestPath, actionClass, actionMethod, commonMap, regexpMap);
        } else if (actionMethod.isAnnotationPresent(Request.Delete.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Delete.class).value();
            putActionMap("GET", requestPath, actionClass, actionMethod, commonMap, regexpMap);
        }
    }

    private static void putActionMap(String requestMethod, String requestPath, Class<?> actionClass, Method actionMethod, Map<Requester, Handler> commonMap, Map<Requester, Handler> regexpMap) {
        //判断request path中是否有占位符
        if (requestPath.matches(".+\\{\\w+\\}.*")) {
            // 将请求路径中的占位符 {\w+} 转换为正则表达式 (\\w+)
            requestPath = StringUtil.replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
            // 将 Requester 与 Handler 放入 Regexp Action Map 中
            regexpMap.put(new Requester(requestMethod, requestPath), new Handler(actionClass, actionMethod));
        } else {
            // 将 Requester 与 Handler 放入 Common Action Map 中
            commonMap.put(new Requester(requestMethod, requestPath), new Handler(actionClass, actionMethod));

        }
    }

    /**
     * 获取ActionMap
     */
    public static Map<Requester, Handler> getActionMap() {
        return actionMap;
    }


}
