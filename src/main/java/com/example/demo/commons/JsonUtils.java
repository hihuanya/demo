package com.example.demo.commons;

import com.example.demo.bean.Goods;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author chuliuhuan
 * @date 2022-11-13 14:37
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);


    public static Goods jsonToPojo(String json, Class<Goods> goodsClass) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            //字符串转Json对象
            return objectMapper.readValue(json, goodsClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            //Json对象转为String字符串
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
