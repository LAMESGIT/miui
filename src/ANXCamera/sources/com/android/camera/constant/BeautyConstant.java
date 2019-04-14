package com.android.camera.constant;

import android.content.res.Resources;
import com.android.camera.CameraAppImpl;

public interface BeautyConstant {
    public static final String LEVEL_ADVANCED = res.getString(2131296594);
    public static final String LEVEL_CLOSE = res.getString(2131296587);
    public static final String LEVEL_HIGH = res.getString(2131296590);
    public static final String LEVEL_LOW = res.getString(2131296588);
    public static final String LEVEL_MEDIUM = res.getString(2131296589);
    public static final String LEVEL_XHIGH = res.getString(2131296591);
    public static final String LEVEL_XXHIGH = res.getString(2131296592);
    public static final String LEVEL_XXXHIGH = res.getString(2131296593);
    public static final Resources res = CameraAppImpl.getAndroidContext().getResources();
}
