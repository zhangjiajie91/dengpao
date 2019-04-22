package com.fanmo.dengpao;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * @author fanmo
 * @date 2019/04/21
 */
public class ListUtil {

    public static <T> boolean isEmpty(List<T> list) {
        return (null == list) || list.isEmpty();
    }

    public static <T> List<T> subList(List<T> list, int size) {
        if (isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.subList(0, Math.min(size, list.size()));
    }

    public static <T> String toString(List<T> list) {
        if (isEmpty(list)) {
            return "";
        }
        return toString(list, list.size());
    }

    public static <T> String toString(List<T> list, int size) {
        if (isEmpty(list)) {
            return "";
        }
        return toString(list, size, "\n");
    }

    public static <T> String toString(List<T> list, String split) {
        if (isEmpty(list)) {
            return "";
        }
        return toString(list, list.size(), split);
    }

    public static <T> String toString(List<T> list, int size, String split) {
        List<T> subList = subList(list, size);
        if (subList.isEmpty()) {
            return "";
        }
        split = (null == split) ? "" : split;
        StringBuilder sb = new StringBuilder();
        for (T t : subList) {
            sb.append(t.toString()).append(split);
        }
        return sb.toString().substring(0, sb.length() - split.length());
    }

    public static <T> boolean equals(List<T> list1, List<T> list2, boolean ignoreSeq) {
        if (!ignoreSeq) {
            return Objects.equals(list1, list2);
        }
        if (isEmpty(list1) != isEmpty(list2)) {
            return false;
        }
        if (isEmpty(list1)) {
            return true;
        }
        return list1.size() == list2.size() && list1.containsAll(list2) && list2.containsAll(list1);
    }


}
