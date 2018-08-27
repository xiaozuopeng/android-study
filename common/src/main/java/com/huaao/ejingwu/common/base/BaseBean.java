package com.huaao.ejingwu.common.base;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.huaao.ejingwu.common.utils.GsonUtils;

import java.lang.reflect.Array;

/**
 * @author: xzp
 * @date: 2018/8/2
 * @desc:
 */
public abstract class BaseBean extends BaseObservable implements Parcelable {

    public String toJSONString() {
        return GsonUtils.getInstance().toJson(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseBean other = (BaseBean) obj;
        String jsonString = toJSONString();
        String otherJsonString = other.toJSONString();
        if (jsonString == null) {
            if (otherJsonString != null) {
                return false;
            }
        } else if (!jsonString.equals(otherJsonString)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "=[jsonString=" + toJSONString() + "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toJSONString());
    }

    public static class Creator<E extends BaseBean> implements Parcelable.Creator<E> {
        private Class<E> clazz;

        public Creator(Class<E> clazz) {
            this.clazz = clazz;
        }

        @Override
        public E createFromParcel(Parcel source) {
            return readFromJSONString(source.readString(), clazz);
        }

        @SuppressWarnings("unchecked")
        @Override
        public E[] newArray(int size) {
            return (E[]) Array.newInstance(clazz, size);
        }
    }

    public static <E extends BaseBean> E readFromJSONString(String jsonString, Class<E> clazz) {
        try {
            return GsonUtils.getInstance().fromJson(jsonString, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
