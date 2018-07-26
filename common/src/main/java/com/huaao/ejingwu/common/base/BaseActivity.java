package com.huaao.ejingwu.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author: xzp
 * @date: 2018/6/27
 * @desc:
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected B mBinding;
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewDataBinding();

        mViewModel.onAttach();

        init();

    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getView(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        mBinding = DataBindingUtil.setContentView(this, getLayoutRes());
//        mBinding.setVariable(initVariableId(), mViewModel = initViewModel());
        mViewModel = initViewModel();
    }

//    /**
//     * 初始化ViewModel的id
//     *
//     * @return BR的id
//     */
//    public abstract int initVariableId();

    /**
     * 传入布局文件
     *
     * @return 基类会自动生成对应的DataBinding供导出类使用
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public abstract VM initViewModel();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
        mViewModel.onDetach();
        mViewModel = null;
    }
}
