package com.huaao.ejingwu.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author: xzp
 * @date: 2018/6/28
 * @desc:
 */
public abstract class LazyLoadMvvmFragment extends BaseMvvmFragment {

    /**
     * 是否允许加载数据
     */
    private boolean isEnableLoad = false;
    /**
     * Fragment是否可见
     */
    private boolean isVisible = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // View创建完毕后才允许加载数据，否则数据绑定到View时会出现NullPointerException
        isEnableLoad = true;
        // 保证数据被加载一次
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        // View销毁后，不允许加载数据
        isEnableLoad = false;
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // isVisibleToUser表示该Fragment的UI用户是否可见
        if (isVisibleToUser) {
            isVisible = true;
            // 保证数据被加载一次
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    private void lazyLoad() {
        // 这里进行双重标记判断，是因为setUserVisibleHint会多次回调，并且会在onCreateView执行前回调；
        // 必须确保onCreateView加载完毕且页面可见才加载数据。
        if (isEnableLoad && isVisible) {
            loadData();
            // 数据加载完毕，恢复标记，防止重复加载
            isEnableLoad = false;
        }
    }

    protected abstract void loadData();

}
