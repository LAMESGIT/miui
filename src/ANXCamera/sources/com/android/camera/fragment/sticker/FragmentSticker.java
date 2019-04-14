package com.android.camera.fragment.sticker;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.android.camera.animation.FragmentAnimationFactory;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.CtaNoticeFragment;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.ui.NoScrollViewPager;
import io.reactivex.Completable;
import java.util.ArrayList;
import java.util.List;

public class FragmentSticker extends BaseFragment implements HandleBackTrace {
    public static final int FRAGMENT_INFO = 255;
    private ImageView mBackButton;
    private OnRadioButtonChangedListener mOnRadioButtonChangedListener;
    private RadioGroup mRadioGroup;
    private NoScrollViewPager mViewPager;

    public interface OnRadioButtonChangedListener {
        void onChanged();
    }

    private class StickerPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList;

        public StickerPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
            super(fragmentManager);
            this.mFragmentList = list;
        }

        public Fragment getItem(int i) {
            return (Fragment) this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList == null ? 0 : this.mFragmentList.size();
        }
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        view.getLayoutParams().height = (int) (((float) getResources().getDisplayMetrics().heightPixels) - (((float) getResources().getDisplayMetrics().widthPixels) / 0.75f));
        this.mViewPager = (NoScrollViewPager) view.findViewById(2131558421);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FragmentStickerPager());
        arrayList.add(new FragmentStickerPager());
        this.mViewPager.setAdapter(new StickerPagerAdapter(getChildFragmentManager(), arrayList));
        this.mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        FragmentSticker.this.mRadioGroup.check(2131558552);
                        return;
                    case 1:
                        FragmentSticker.this.mRadioGroup.check(2131558553);
                        return;
                    default:
                        return;
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.mRadioGroup = (RadioGroup) view.findViewById(2131558551);
        this.mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case 2131558552:
                        FragmentSticker.this.mViewPager.setCurrentItem(0, false);
                        break;
                    case 2131558553:
                        FragmentSticker.this.mViewPager.setCurrentItem(1, false);
                        break;
                }
                if (FragmentSticker.this.mOnRadioButtonChangedListener != null) {
                    FragmentSticker.this.mOnRadioButtonChangedListener.onChanged();
                }
            }
        });
        this.mRadioGroup.check(2131558552);
        this.mBackButton = (ImageView) view.findViewById(2131558554);
        this.mBackButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160)).delegateEvent(4);
                if (FragmentSticker.this.mCurrentMode == 161) {
                    ((BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175)).reInitTipImage();
                }
            }
        });
        CtaNoticeFragment.checkCta(getActivity().getFragmentManager());
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        registerBackStack(modeCoordinator, this);
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        unRegisterBackStack(modeCoordinator, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968622;
    }

    public int getFragmentInto() {
        return 255;
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        super.provideAnimateElement(i, list, i2);
    }

    public void setOnRadioButtonChangedListener(OnRadioButtonChangedListener onRadioButtonChangedListener) {
        this.mOnRadioButtonChangedListener = onRadioButtonChangedListener;
    }

    /* Access modifiers changed, original: protected */
    public Animation provideEnterAnimation(int i) {
        return FragmentAnimationFactory.wrapperAnimation(167, 161);
    }

    /* Access modifiers changed, original: protected */
    public Animation provideExitAnimation() {
        return FragmentAnimationFactory.wrapperAnimation(168, 162);
    }

    public boolean onBackEvent(int i) {
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        if (baseDelegate == null || baseDelegate.getActiveFragment(2131558427) != 255) {
            return false;
        }
        baseDelegate.delegateEvent(4);
        return true;
    }
}
