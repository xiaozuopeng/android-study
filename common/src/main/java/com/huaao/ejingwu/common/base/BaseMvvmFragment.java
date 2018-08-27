package com.huaao.ejingwu.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: xzp
 * @date: 2018/6/27
 * @desc:
 */
public abstract class BaseMvvmFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment {

    protected B mBinding;
    protected VM mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutRes() == 0)
            return super.onCreateView(inflater, container, savedInstanceState);
        if (mBinding == null) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        }
//        mBinding.setVariable(initVariableId(), mViewModel = initViewModel());
        mViewModel = initViewModel();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.onAttach();

        init();
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

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
        mViewModel.onDetach();
        mViewModel = null;
    }
}