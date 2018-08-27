package com.huaao.ejingwu.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.huaao.ejingwu.common.R;
import com.huaao.ejingwu.common.utils.DimensionUtils;
import com.huaao.ejingwu.common.widget.indicator.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xzp
 * @date: 2018/7/2
 * @desc: 自定义引导页
 */
public class GuideView extends RelativeLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private Drawable aboveDotDrawable;
    private Drawable bottomDotsDrawable;
    private boolean isHideDots;
    private boolean isShowAnimation;
    private Context mContext;
    private ViewPager mGuideViewpager;
    private Button mBtnNext;
    private LinearLayout mLlBottomDots;
    private ImageView mIvAboveDot;
    private List<ImageView> mImageList = new ArrayList<>();
    private GuideAdapter guideAdapter;
    private int mDistance;
    private OnNextBtnClickLister lister;

    public void setOnNextBtnClickLister(OnNextBtnClickLister lister) {
        this.lister = lister;
    }

    public GuideView(Context context) {
        this(context, null);
    }

    public GuideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        if (attrs != null) {
            TypedArray attrsTypedArray = context.obtainStyledAttributes(attrs, R.styleable.GuideView);
            if (attrsTypedArray != null) {
                aboveDotDrawable = attrsTypedArray.getDrawable(R.styleable.GuideView_aboveDotDrawable);
                bottomDotsDrawable = attrsTypedArray.getDrawable(R.styleable.GuideView_bottomDotsDrawable);
                isHideDots = attrsTypedArray.getBoolean(R.styleable.GuideView_isHideDots, false);
                isShowAnimation = attrsTypedArray.getBoolean(R.styleable.GuideView_isShowAnimation, false);
                attrsTypedArray.recycle();
            }
        }

        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_guide, this);
        mGuideViewpager = view.findViewById(R.id.guide_view_pager);
        if (isShowAnimation) {
            mGuideViewpager.setPageTransformer(true, new DepthPageTransformer());
        }
        guideAdapter = new GuideAdapter();
        mGuideViewpager.setAdapter(guideAdapter);
        mGuideViewpager.addOnPageChangeListener(this);

        RelativeLayout mRlDotsContainer = view.findViewById(R.id.rl_dots_container);
        if (isHideDots) {
            mRlDotsContainer.setVisibility(GONE);
        }
        mBtnNext = view.findViewById(R.id.btn_next);
        mLlBottomDots = view.findViewById(R.id.ll_bottom_dots);
        mIvAboveDot = view.findViewById(R.id.iv_above_dot);
        if (aboveDotDrawable != null) {
            mIvAboveDot.setImageDrawable(aboveDotDrawable);
        } else {
            mIvAboveDot.setImageResource(R.drawable.widget_above_dot);
        }

        mBtnNext.setOnClickListener((v) -> {
            if (lister != null) {
                lister.onClickNext();
            }
        });
    }

    public void setImages(int[] imgs) {
        initImages(imgs);
        initDots();
        moveDots();
    }

    private void moveDots() {
        mIvAboveDot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获得两个圆点之间的距离
                mDistance = mLlBottomDots.getChildAt(1).getLeft() - mLlBottomDots.getChildAt(0).getLeft();
                mIvAboveDot.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    private void initDots() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, DimensionUtils.dp2px(16), 0);
        for (int i = 0; i < mImageList.size(); i++) {
            ImageView mDotView = new ImageView(mContext);
            if (bottomDotsDrawable != null) {
                mDotView.setImageDrawable(bottomDotsDrawable);
            } else {
                mDotView.setImageResource(R.drawable.widget_bottom_dot);
            }
            mDotView.setId(i);
            mDotView.setOnClickListener(this);

            mLlBottomDots.addView(mDotView, layoutParams);
        }
    }

    private void initImages(@NonNull int[] imgs) {
        for (int img : imgs) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(img);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageList.add(imageView);
        }
        guideAdapter.setImageList(mImageList);
    }

    @Override
    public void onClick(View v) {
        mGuideViewpager.setCurrentItem(v.getId());
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //页面滚动时小白点移动的距离，并通过setLayoutParams(params)不断更新其位置
        float leftMargin = mDistance * (position + positionOffset);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mIvAboveDot.getLayoutParams();
        params.leftMargin = (int) leftMargin;
        mIvAboveDot.setLayoutParams(params);
        if (position == mImageList.size() - 1) {
            mBtnNext.setVisibility(View.VISIBLE);
        }
        if (position != mImageList.size() - 1 && mBtnNext.getVisibility() == View.VISIBLE) {
            mBtnNext.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnNextBtnClickLister {
        void onClickNext();
    }

    private class GuideAdapter extends PagerAdapter {

        private List<ImageView> mImageList;

        private void setImageList(List<ImageView> mImageList) {
            this.mImageList = mImageList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mImageList == null ? 0 : mImageList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mImageList.get(position));
            return mImageList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mImageList.get(position));
        }
    }
}
