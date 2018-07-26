package com.huaao.ejingwu.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Set;

/**
 * @author: xzp
 * @date: 2018/6/29
 * @desc: SharedPreferences工具类
 */
public class SPUtils {
    /**
     * 万能的put方法
     *
     * @param key   键
     * @param value 值
     */
    public static void put(@NonNull final String key, @NonNull final Object value) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor edit = sp.edit();

        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (int) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (long) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (float) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (boolean) value);
        } else {
            throw new ClassCastException("please input right class type");
        }

        edit.apply();
    }

    /**
     * SP中写入String集合
     *
     * @param key    键
     * @param values 值
     */
    public void putStringSet(@NonNull final String key, @NonNull final Set<String> values) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putStringSet(key, values);
        edit.apply();
    }

    /**
     * SP中读取StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code Collections.<String>emptySet()}
     */
    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP中读取StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(@NonNull final String key, @NonNull final Set<String> defaultValue) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * 获取字符串
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值
     */
    public static String getString(@NonNull final String key) {
        return getString(key, "");
    }

    /**
     * 获取字符串
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值
     */
    public static String getString(@NonNull final String key, final String defaultValue) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * 获取整数
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值
     */
    public static int getInt(@NonNull final String key) {
        return getInt(key, 0);
    }

    /**
     * 获取整数
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值
     */
    public static int getInt(@NonNull final String key, final int defaultValue) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    /**
     * 获取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值
     */
    public static float getFloat(@NonNull final String key) {
        return getFloat(key, 0);
    }

    /**
     * 获取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值
     */
    public static float getFloat(@NonNull final String key, final float defaultValue) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    /**
     * 获取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值
     */
    public static Long getLong(@NonNull final String key) {
        return getLong(key, 0);
    }

    /**
     * 获取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值
     */
    public static Long getLong(@NonNull final String key, final long defaultValue) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    /**
     * 获取boolean值
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值
     */
    public static boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取boolean值
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值
     */
    public static boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return 是否存在
     */
    public boolean contains(@NonNull final String key) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public void remove(@NonNull final String key) {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().remove(key).apply();
    }

    /**
     * SP中清除所有数据
     */
    public void clear() {
        SharedPreferences sp = UtilsManager.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}
