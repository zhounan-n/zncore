package com.zn.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json操作工具类
 * Created by zhoun on 2018/7/16.
 **/
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将java对象转换成json字符串
     */
    public static <T> String toJSON(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("Java 转 JSON 出错！", e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将 JSON 字符串转为 Java 对象
     */
    public static <T> T fromJSON(String json, Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(json, type);
        } catch (Exception e) {
            logger.error("JSON 转 Java 出错！", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

}
