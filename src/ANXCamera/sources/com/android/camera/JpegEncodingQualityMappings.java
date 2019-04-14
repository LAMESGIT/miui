package com.android.camera;

import java.util.HashMap;

public class JpegEncodingQualityMappings {
    public static final int DEFAULT_QUALITY = 87;
    public static final String HIGH = "high";
    public static final String LOW = "low";
    public static final String NORMAL = "normal";
    private static HashMap<String, Integer> sHashMap = new HashMap();

    static {
        sHashMap.put(LOW, Integer.valueOf(67));
        sHashMap.put("normal", Integer.valueOf(87));
        sHashMap.put(HIGH, Integer.valueOf(CameraAppImpl.getAndroidContext().getResources().getInteger(2131755010)));
    }

    public static int getQualityNumber(String str) {
        Integer num = (Integer) sHashMap.get(str);
        if (num == null) {
            return 87;
        }
        return num.intValue();
    }
}