package com.android.camera.fragment.beauty;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import com.android.camera.Util;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;

public class FragmentPopuEyeLightTip extends BaseFragment {
    public static final int FRAGMENT_INFO = 4089;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        ((MarginLayoutParams) view.getLayoutParams()).bottomMargin = Util.getBottomHeight(getResources()) + getResources().getDimensionPixelSize(2131362005);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968582;
    }

    public int getFragmentInto() {
        return 4089;
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
    }
}
