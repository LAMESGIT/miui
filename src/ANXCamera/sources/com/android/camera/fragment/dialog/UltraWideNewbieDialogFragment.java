package com.android.camera.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UltraWideNewbieDialogFragment extends AiSceneNewbieDialogFragment {
    public static final String TAG = "UltraWideHint";

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968597, viewGroup, false);
        initViewOnTouchListener(inflate);
        adjustViewHeight(inflate.findViewById(2131558472));
        return inflate;
    }
}
