package com.wufanbao.api.clientservice.common;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Json
 */
public class JsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            //gson = new Gson();
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                        @Override
                        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                            if (src == src.longValue())
                                return new JsonPrimitive(src.longValue());
                            return new JsonPrimitive(src);
                        }
                    })
                    .create();
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

    public static void main(String[] args) {
        String sp="/Upload/MachineIco/eb3b1781a05e47979b059dbd84820e5b.jpg;/Upload/MachineIco/d6d7441164c945d7856167169adad9be.jpg;;";
        String[] seekPhotos = null;
        if(!StringUtils.isNullOrEmpty(sp)){
            String[] strs = sp.split(";");
            int j = 0;
            for (int i = 0; i < strs.length; i++) {
                if (!StringUtils.isNullOrEmpty(strs[i])) {
                    j++;
                }
            }
            seekPhotos = new String[j];
            int t=0;
            for (int i = 0; i <strs.length; i++) {
                if(!StringUtils.isNullOrEmpty(strs[i])){
                    if (StringUtils.isNullOrEmpty(strs[i])) {
                        seekPhotos[t]= "";
                    }
                    if (strs[i].contains("http")) {
                        seekPhotos[t]= strs[i];
                    }
                    seekPhotos[t]="https://source.wufanbao.cn"+strs[i];
                    t++;
                }
            }
            System.out.println(Arrays.toString(seekPhotos));

        }
    }


}