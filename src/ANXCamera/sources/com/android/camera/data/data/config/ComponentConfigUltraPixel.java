package com.android.camera.data.data.config;

import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import com.android.camera.CameraAppImpl;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera.log.Log;
import com.android.camera2.CameraCapabilities;
import java.util.List;

public class ComponentConfigUltraPixel extends ComponentData {
    private static final String TAG = ComponentConfigUltraPixel.class.getSimpleName();
    private String mCloseTipString = null;
    @DrawableRes
    private int mMenuDrawable = -1;
    private String mMenuString = null;
    private String mOpenTipString = null;

    public ComponentConfigUltraPixel(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
    }

    public int getDisplayTitleString() {
        return 0;
    }

    public String getKey(int i) {
        return null;
    }

    @NonNull
    public String getDefaultValue(int i) {
        return null;
    }

    public List<ComponentDataItem> getItems() {
        return null;
    }

    public void initUltraPixelResource(int i) {
        Resources resources = CameraAppImpl.getAndroidContext().getResources();
        if (i == CameraCapabilities.ULTRA_PIXEL_32M) {
            this.mMenuDrawable = 2130837798;
            this.mOpenTipString = resources.getString(2131296891);
            this.mCloseTipString = resources.getString(2131296892);
            this.mMenuString = resources.getString(2131296890);
        } else if (i != CameraCapabilities.ULTRA_PIXEL_48M) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown ultra pixel size: ");
            stringBuilder.append(i);
            Log.d(str, stringBuilder.toString());
        } else {
            this.mMenuDrawable = 2130837799;
            this.mOpenTipString = resources.getString(2131296852);
            this.mCloseTipString = resources.getString(2131296853);
            this.mMenuString = resources.getString(2131296869);
        }
    }

    public String getUltraPixelOpenTip() {
        return this.mOpenTipString;
    }

    public String getUltraPixelCloseTip() {
        return this.mCloseTipString;
    }

    @DrawableRes
    public int getMenuDrawable() {
        return this.mMenuDrawable;
    }

    public String getMenuString() {
        return this.mMenuString;
    }
}
