package com.android.camera.fragment.live;

import android.content.Context;
import android.util.AttributeSet;
import com.android.camera.fragment.sticker.download.DownloadView;

public class LiveDownloadView extends DownloadView {
    public LiveDownloadView(Context context) {
        super(context);
    }

    public LiveDownloadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public int getStateImageResource(int i) {
        if (i == 0) {
            return 2130837892;
        }
        switch (i) {
            case 2:
                return 2130837894;
            case 3:
                return 2130837893;
            case 4:
                return 2130837892;
            default:
                return 0;
        }
    }

    /* Access modifiers changed, original: protected */
    public float getAlphaNormal() {
        return 0.9f;
    }
}
