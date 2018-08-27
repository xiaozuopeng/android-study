package com.huaao.ejingwu.standard.rebuild.ui.activity;

import android.support.v4.view.ViewPager;

import com.huaao.ejingwu.common.base.BaseMvvmActivity;
import com.huaao.ejingwu.common.base.BaseViewModel;
import com.huaao.ejingwu.common.widget.indicator.DotsIndicator;
import com.huaao.ejingwu.common.widget.indicator.SpringDotsIndicator;
import com.huaao.ejingwu.common.widget.indicator.WormDotsIndicator;
import com.huaao.ejingwu.common.widget.indicator.ZoomOutPageTransformer;
import com.huaao.ejingwu.standard.rebuild.R;
import com.huaao.ejingwu.standard.rebuild.databinding.ActivityMapViewBinding;
import com.huaao.ejingwu.standard.rebuild.widget.DotIndicatorPagerAdapter;

/**
 * @author: xzp
 * @date: 2018/7/26
 * @desc:
 */
public class MapViewActivity extends BaseMvvmActivity<ActivityMapViewBinding, BaseViewModel> {

    private int[] imgs = {R.drawable.guide_one, R.drawable.guide_two, R.drawable.guide_three, R.drawable.guide_four};

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_map_view;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new BaseViewModel(this);
    }

    @Override
    protected void init() {
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
        SpringDotsIndicator springDotsIndicator = findViewById(R.id.spring_dots_indicator);
        WormDotsIndicator wormDotsIndicator = findViewById(R.id.worm_dots_indicator);

        ViewPager viewPager = findViewById(R.id.view_pager);
        DotIndicatorPagerAdapter adapter = new DotIndicatorPagerAdapter(imgs);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        dotsIndicator.setViewPager(viewPager);
        springDotsIndicator.setViewPager(viewPager);
        wormDotsIndicator.setViewPager(viewPager);
    }
}
