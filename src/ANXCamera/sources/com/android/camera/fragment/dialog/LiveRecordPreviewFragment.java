package com.android.camera.fragment.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import com.android.camera.Util;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.LiveVideoEditor;

public class LiveRecordPreviewFragment extends BaseFragment implements OnClickListener {
    public static final String TAG = "LivePreview";
    private ViewGroup mBottomLayout;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mBottomLayout = (RelativeLayout) view.findViewById(2131558501);
        ((MarginLayoutParams) this.mBottomLayout.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(2131361916) + Util.sNavigationBarHeight;
        ((MarginLayoutParams) this.mBottomLayout.getLayoutParams()).height = Util.getBottomHeight(getResources());
        view.findViewById(2131558497).setOnClickListener(this);
        this.mBottomLayout.findViewById(2131558502).setOnClickListener(this);
        this.mBottomLayout.findViewById(2131558504).setOnClickListener(this);
        this.mBottomLayout.findViewById(2131558506).setOnClickListener(this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968606;
    }

    public int getFragmentInto() {
        return 0;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131558497) {
            ((LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209)).startPlay();
        } else if (id == 2131558502) {
            BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        } else if (id == 2131558504) {
            ((LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209)).combineVideoAudio(null, null, null);
        }
    }
}
