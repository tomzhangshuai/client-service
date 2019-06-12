package com.wufanbao.api.oldclientservice.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Json
 */
public class JsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }


    /**
     * 对象转化为json 数据
     *
     * @param object 需要转化的对象
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * json 数据转化为实体类对象
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Type cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> T GsonToBean(String gsonString) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, new TypeToken<T>() {
            }.getType());
        }
        return t;
    }

    /**
     * json 数据转化为实体类对象
     *
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * Json 数据转化为List集合--集合中为实体类
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;

    }

    /**
     * 将数据转化成List集合--集合中为map
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 将json数据转化成map
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}