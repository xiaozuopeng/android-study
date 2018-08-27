package com.huaao.ejingwu.standard.rebuild.ui.activity;

import com.huaao.ejingwu.common.base.BaseMvvmActivity;
import com.huaao.ejingwu.standard.rebuild.R;
import com.huaao.ejingwu.standard.rebuild.databinding.ActivityGuideBinding;
import com.huaao.ejingwu.standard.rebuild.ui.viewmodel.GuidViewModel;
import com.huaao.ejingwu.common.widget.GuideView;

public class GuideActivity extends BaseMvvmActivity<ActivityGuideBinding, GuidViewModel> implements GuideView.OnNextBtnClickLister {

    private int[] imgs = {R.drawable.guide_one, R.drawable.guide_two, R.drawable.guide_three, R.drawable.guide_four};

    @Override
    public GuidViewModel initViewModel() {
        return new GuidViewModel(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init() {
//        mBinding.btnGoToMain.setOnClickListener((View view) -> {
//            mViewModel.startActivity(MainActivity.class);
//            finish();
//            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//        });

        mBinding.guideView.setImages(imgs);
        mBinding.guideView.setOnNextBtnClickLister(this);
    }

    @Override
    public void onClickNext() {
        mViewModel.startActivity(MainActivity.class);
        finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
