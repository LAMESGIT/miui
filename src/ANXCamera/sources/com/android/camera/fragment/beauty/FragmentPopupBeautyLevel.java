package com.android.camera.fragment.beauty;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import com.android.camera.Util;
import com.android.camera.animation.FragmentAnimationFactory;
import com.android.camera.fragment.BaseFragment;

@Deprecated
public class FragmentPopupBeautyLevel extends BaseFragment {
    public static final int FRAGMENT_INFO = 4082;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        ((MarginLayoutParams) view.getLayoutParams()).bottomMargin = Util.getBottomHeight(getResources());
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968620;
    }

    public int getFragmentInto() {
        return 4082;
    }

    /* Access modifiers changed, original: protected */
    public Animation provideEnterAnimation(int i) {
        return FragmentAnimationFactory.wrapperAnimation(161);
    }

    /* Access modifiers changed, original: protected */
    public Animation provideExitAnimation() {
        return FragmentAnimationFactory.wrapperAnimation(162);
    }
}
