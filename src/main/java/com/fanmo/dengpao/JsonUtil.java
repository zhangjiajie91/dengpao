package com.fanmo.dengpao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author fanmo
 * @date 2019/04/21
 */
public class JsonUtil {

    public static boolean isJsonString(String jsonString) {
        return isJsonObject(jsonString) || isJsonArray(jsonString);
    }

    public static boolean isJsonObject(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        try {
            JSON.parseObject(jsonString);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static boolean isJsonArray(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        try {
            JSON.parseArray(jsonString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * plat fat json
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> jsonPat(String jsonString) {
        Map<String, Object> map = Maps.newHashMap();
        if (!isJsonString(jsonString)) {
            return map;
        }

        if (isJsonObject(jsonString)) {
            parseJsonObject(null, map, JSON.parseObject(jsonString));
        } else if (isJsonArray(jsonString)) {
            parseJsonArray(null, map, JSON.parseArray(jsonString));
        }
        return map;
    }

    /**
     * compare json is equal, <strong>true<strong/> if all null.ß
     *
     * @param jsonString1
     * @param jsonString2
     * @param strong      if json is a list, true means must keep the same seq
     * @return
     */
    public static boolean jsonEqual(String jsonString1, String jsonString2, boolean strong) {
        if (StringUtils.isBlank(jsonString1) && StringUtils.isBlank(jsonString2)) {
            return true;
        }
        if (StringUtils.isBlank(jsonString1) || StringUtils.isBlank(jsonString2)) {
            return false;
        }
        if (isJsonObject(jsonString1) && isJsonObject(jsonString2)) {
            return compareJsonObject(JSON.parseObject(jsonString1), JSON.parseObject(jsonString2), strong);
        } else if (isJsonArray(jsonString1) && isJsonArray(jsonString2)) {
            return compareJsonArray(JSON.parseArray(jsonString1), JSON.parseArray(jsonString2), strong);
        }
        return false;
    }

    public static boolean compareJsonObject(JSONObject jsonObject1, JSONObject jsonObject2, boolean strong) {
        if (null == jsonObject1 && null == jsonObject2) {
            return true;
        }
        if (null == jsonObject1 || null == jsonObject2) {
            return false;
        }
        if (jsonObject1.size() != jsonObject2.size()) {
            return false;
        }
        for (String key : jsonObject1.keySet()) {
            if (!jsonObject2.containsKey(key)) {
                return false;
            }
            if (!isEquals(jsonObject1.get(key), jsonObject2.get(key), strong)) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareJsonArray(JSONArray jsonArray1, JSONArray jsonArray2, boolean strong) {
        if (null == jsonArray1 && null == jsonArray2) {
            return true;
        }
        if (null == jsonArray1 || null == jsonArray2) {
            return false;
        }
        if (jsonArray1.size() != jsonArray2.size()) {
            return false;
        }
        for (int i = 0; i < jsonArray1.size(); i++) {
            Object value1 = jsonArray1.get(i);
            if (strong) {
                Object value2 = jsonArray2.get(i);
                if (!isEquals(value1, value2, strong)) {
                    return false;
                }
                continue;
            }
            boolean findOneEqual = false;
            for (Object value2 : jsonArray2) {
                if (isEquals(value1, value2, strong)) {
                    findOneEqual = true;
                    break;
                }
            }
            if (!findOneEqual) {
                return false;
            }
        }

        return true;
    }

    private static boolean isEquals(Object o1, Object o2, boolean strong) {
        if (null == o1 && null == o2) {
            return true;
        }
        if (null == o1 || null == o2) {
            return false;
        }
        if (!isTypeEqual(o1, o2)) {
            return false;
        }
        if (o1 instanceof JSONObject) {
            return compareJsonObject((JSONObject) o1, (JSONObject) o2, strong);
        }
        if (o1 instanceof JSONArray) {
            return compareJsonArray((JSONArray) o1, (JSONArray) o2, strong);
        }
        return o1.equals(o2);
    }

    private static void parseJsonObject(String path, Map<String, Object> jsonMap, JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            String nextPath = (null == path) ? key : path + "." + key;
            Object obj = jsonObject.get(key);
            if (obj instanceof JSONObject) {
                parseJsonObject(nextPath, jsonMap, jsonObject.getJSONObject(key));
            } else if (obj instanceof JSONArray) {
                parseJsonArray(nextPath, jsonMap, jsonObject.getJSONArray(key));
            } else {
                jsonMap.put(nextPath, obj);
            }
        }
    }

    private static void parseJsonArray(String path, Map<String, Object> jsonMap, JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            String nextPath = (null == path) ? "[" + i + "]" : path + "[" + i + "]";
            ;
            Object obj = jsonArray.get(i);
            if (obj instanceof JSONObject) {
                parseJsonObject(nextPath, jsonMap, (JSONObject) obj);
            } else if (obj instanceof JSONArray) {
                parseJsonArray(nextPath, jsonMap, (JSONArray) obj);
            } else {
                jsonMap.put(nextPath, obj);
            }
        }
    }

    private static boolean isTypeEqual(Object obj1, Object obj2) {
        if (null == obj1 && null == obj2) {
            return true;
        }
        if (null == obj1 || null == obj2) {
            return false;
        }

        return obj1.getClass().isInstance(obj2) || obj2.getClass().isInstance(obj1);
    }

}
