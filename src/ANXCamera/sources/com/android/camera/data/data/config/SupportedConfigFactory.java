package com.android.camera.data.data.config;

import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.data.DataRepository;
import com.android.camera2.CameraCapabilities;
import com.mi.config.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class SupportedConfigFactory {
    public static final int AI_DETECT = 242;
    public static final int AI_SCENE = 201;
    public static final int AUTO_ZOOM = 253;
    public static final int BACKLIGHT = 249;
    public static final int BEAUTY = 239;
    public static final String CLOSE_BY_AI = "e";
    public static final String CLOSE_BY_BURST_SHOOT = "d";
    public static final String CLOSE_BY_FILTER = "k";
    public static final String CLOSE_BY_GROUP = "b";
    public static final String CLOSE_BY_HDR = "g";
    public static final String CLOSE_BY_HHT = "a";
    public static final String CLOSE_BY_SUPER_RESOLUTION = "c";
    public static final String CLOSE_BY_ULTRA_PIXEL = "j";
    public static final String CLOSE_BY_ULTRA_WIDE = "i";
    public static final String CLOSE_BY_VIDEO = "h";
    public static final int DUAL_WATER_MARK = 240;
    public static final int FAST = 233;
    public static final int FILTER = 196;
    public static final int FLASH = 193;
    public static final int FOCUS_PEAK = 199;
    public static final int GENDER_AGE = 238;
    public static final int GRADIENTER = 229;
    public static final int GROUP = 235;
    public static final int HAND_GESTURE = 252;
    public static final int HDR = 194;
    public static final int HFR = 202;
    public static final int HHT = 230;
    public static final int INVALID = 198;
    public static final int LIGHTING = 203;
    public static final int LIVE_MUSIC_SELECT = 245;
    public static final int LIVE_SHOT = 206;
    public static final int MAGIC_FOCUS = 231;
    public static final int MAGIC_MIRROR = 236;
    public static final int MENU_ULTRA_PIXEL_PHOTOGRAPHY = 250;
    public static final int MENU_ULTRA_PIXEL_PHOTOGRAPHY_FRONT = 251;
    public static final int MOON = 246;
    public static final int MORE = 197;
    public static final int[] MUTEX_MENU_CONFIGS = new int[]{229, 236, 235, 228, 230, 241, 234, 195, 238, 199, 203, 206, 250, 251};
    public static final int NEW_SLOW_MOTION = 204;
    public static final int NIGHT = 247;
    public static final int PORTRAIT = 195;
    public static final int RAW = 237;
    public static final int SCENE = 234;
    public static final int SETTING = 225;
    public static final int SILHOUETTE = 248;
    public static final int SLOW = 232;
    public static final int SUPER_RESOLUTION = 241;
    public static final int TILT = 228;
    public static final int TIMER = 226;
    public static final int ULTRA_PIXEL_PHOTOGRAPHY = 209;
    public static final int ULTRA_WIDE = 205;
    public static final int ULTRA_WIDE_BOKEH = 207;
    public static final int USER_DEFINE_WATER_MARK = 244;
    public static final int VIDEO_BOKEH = 243;
    public static int[] gRecordingMutexElements;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CloseElementTrigger {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConfigItem {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MutexElement {
    }

    public static boolean isMutexConfig(int i) {
        for (int i2 : MUTEX_MENU_CONFIGS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static String getConfigKey(int i) {
        if (i == 195) {
            return "pref_camera_portrait_mode_key";
        }
        if (i == 199) {
            return "pref_camera_peak_key";
        }
        if (i == 201) {
            return "pref_camera_ai_scene_mode_key";
        }
        if (i == 206) {
            return "pref_live_shot_enabled";
        }
        if (i == 238) {
            return "pref_camera_show_gender_age_key";
        }
        if (i == 241) {
            return "pref_camera_super_resolution_key";
        }
        switch (i) {
            case 228:
                return "pref_camera_tilt_shift_mode";
            case 229:
                return "pref_camera_gradienter_key";
            case 230:
                return "pref_camera_hand_night_key";
            default:
                switch (i) {
                    case 234:
                        return "pref_camera_scenemode_setting_key";
                    case 235:
                        return "pref_camera_groupshot_mode_key";
                    case 236:
                        return "pref_camera_magic_mirror_key";
                    default:
                        switch (i) {
                            case 250:
                                return "pref_menu_ultra_pixel_photography_rear";
                            case 251:
                                return "pref_menu_ultra_pixel_photography_front";
                            case 252:
                                return "pref_hand_gesture";
                            case 253:
                                return "pref_camera_auto_zoom";
                            default:
                                throw new RuntimeException("unknown config item");
                        }
                }
        }
    }

    public static final SupportedConfigs getSupportedTopConfigs(int i, DataItemConfig dataItemConfig, int i2, CameraCapabilities cameraCapabilities, boolean z) {
        dataItemConfig.reInitComponent(i, cameraCapabilities);
        List arrayList = new ArrayList();
        if (dataItemConfig.supportFlash()) {
            arrayList.add(createConfigItem(193));
        }
        boolean fE = DataRepository.dataItemFeature().fE();
        switch (i) {
            case 161:
                if (z && i2 != 0 && DataRepository.dataItemFeature().ff()) {
                    arrayList.add(createConfigItem(243));
                }
                if (i2 == 1) {
                    arrayList.add(createConfigItem(196, 17));
                } else {
                    arrayList.add(createConfigItem(196));
                }
                if (Util.UI_DEBUG() || i2 != 0 || !cameraCapabilities.isSupportVideoBeauty()) {
                    arrayList.add(createConfigItem(225));
                    break;
                }
                arrayList.add(createConfigItem(197));
                break;
                break;
            case 162:
            case 168:
            case 169:
            case 170:
                if (i2 != 0) {
                    if (z && i == 162 && DataRepository.dataItemFeature().ff()) {
                        arrayList.add(createConfigItem(243));
                    }
                    arrayList.add(createConfigItem(225));
                    break;
                }
                if (isSupportedAutoZoom()) {
                    arrayList.add(createConfigItem(253));
                }
                if (!z) {
                    arrayList.add(createConfigItem(225));
                    break;
                }
                if (dataItemConfig.supportHdr()) {
                    arrayList.add(createConfigItem(194));
                }
                if (!DataRepository.dataItemFeature().fv() && b.gC() && b.hU()) {
                    arrayList.add(createConfigItem(202));
                }
                arrayList.add(createConfigItem(197));
                break;
            case 165:
                if (dataItemConfig.supportHdr()) {
                    arrayList.add(createConfigItem(194));
                }
                if ((b.gL() && i2 == 0) || (DataRepository.dataItemFeature().fd() && i2 == 1)) {
                    arrayList.add(createConfigItem(201));
                }
                arrayList.add(createConfigItem(196));
                arrayList.add(createConfigItem(197));
                break;
            case 166:
                arrayList.clear();
                arrayList.add(createConfigItem(225));
                break;
            case 167:
                ComponentManuallyFocus manuallyFocus = dataItemConfig.getManuallyFocus();
                if (b.ha() && !manuallyFocus.getComponentValue(i).equals(manuallyFocus.getDefaultValue(i))) {
                    arrayList.add(createConfigItem(199));
                }
                if (CameraSettings.isSupportedUltraPixelPhotography(cameraCapabilities) && z) {
                    arrayList.add(createConfigItem(209));
                    DataRepository.dataItemConfig().getRearComponentConfigUltraPixel().initUltraPixelResource(DataRepository.dataItemFeature().fR());
                }
                arrayList.add(createConfigItem(196));
                arrayList.add(createConfigItem(197));
                break;
            case 171:
                if (!Util.UI_DEBUG() && z && cameraCapabilities.isSupportPortraitLighting()) {
                    if (i2 == 0) {
                        if (DataRepository.dataItemFeature().fa()) {
                            arrayList.add(createConfigItem(203));
                        }
                    } else if (i2 == 1 && DataRepository.dataItemFeature().fb()) {
                        arrayList.add(createConfigItem(203));
                    }
                }
                if (DataRepository.dataItemFeature().isSupportUltraWide() && i2 == 0 && z) {
                    arrayList.add(createConfigItem(207));
                }
                if (DataRepository.dataItemFeature().fe() && (i2 == 0 || (DataRepository.dataItemFeature().fd() && i2 == 1))) {
                    arrayList.add(createConfigItem(201));
                }
                arrayList.add(createConfigItem(196, 17));
                arrayList.add(createConfigItem(197));
                break;
            case 172:
                arrayList.add(createConfigItem(204));
                arrayList.add(createConfigItem(225));
                break;
            case 173:
                arrayList.add(createConfigItem(225));
                break;
            case 174:
                if (z && i2 != 0 && DataRepository.dataItemFeature().ff()) {
                    arrayList.add(createConfigItem(243));
                }
                arrayList.add(createConfigItem(245, 17));
                arrayList.add(createConfigItem(225));
                break;
            default:
                if (dataItemConfig.supportHdr()) {
                    arrayList.add(createConfigItem(194));
                }
                if ((b.gL() && i2 == 0) || (DataRepository.dataItemFeature().fd() && i2 == 1)) {
                    arrayList.add(createConfigItem(201));
                }
                if (fE && z) {
                    arrayList.add(createConfigItem(206));
                }
                arrayList.add(createConfigItem(196));
                arrayList.add(createConfigItem(197));
                break;
        }
        TopViewPositionArray.fillNotUseViewPosition(arrayList);
        SupportedConfigs supportedConfigs = new SupportedConfigs();
        supportedConfigs.add(arrayList);
        return supportedConfigs;
    }

    private static TopConfigItem createConfigItem(int i, int i2) {
        return new TopConfigItem(i, i2);
    }

    private static TopConfigItem createConfigItem(int i) {
        return new TopConfigItem(i);
    }

    private static boolean isSupportedAutoZoom() {
        return DataRepository.dataItemFeature().fC();
    }

    /* JADX WARNING: Removed duplicated region for block: B:89:0x015e  */
    public static final com.android.camera.data.data.config.SupportedConfigs getSupportedExtraConfigs(int r8, int r9, com.android.camera.data.cloud.DataCloud.CloudFeature r10, com.android.camera2.CameraCapabilities r11, boolean r12) {
        /*
        r0 = new com.android.camera.data.data.config.SupportedConfigs;
        r0.<init>();
        r1 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        r2 = 239; // 0xef float:3.35E-43 double:1.18E-321;
        r3 = 225; // 0xe1 float:3.15E-43 double:1.11E-321;
        if (r8 == r1) goto L_0x018c;
    L_0x000d:
        switch(r8) {
            case 161: goto L_0x018c;
            case 162: goto L_0x015b;
            default: goto L_0x0010;
        };
    L_0x0010:
        r1 = 252; // 0xfc float:3.53E-43 double:1.245E-321;
        r4 = 236; // 0xec float:3.31E-43 double:1.166E-321;
        r5 = 238; // 0xee float:3.34E-43 double:1.176E-321;
        r6 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        switch(r8) {
            case 167: goto L_0x0153;
            case 168: goto L_0x015b;
            case 169: goto L_0x015b;
            case 170: goto L_0x015b;
            case 171: goto L_0x0109;
            default: goto L_0x001b;
        };
    L_0x001b:
        r3 = r0.add(r3);
        r3.add(r6);
        r3 = 235; // 0xeb float:3.3E-43 double:1.16E-321;
        r6 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r7 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        switch(r9) {
            case 0: goto L_0x0085;
            case 1: goto L_0x002d;
            default: goto L_0x002b;
        };
    L_0x002b:
        goto L_0x0107;
    L_0x002d:
        if (r12 == 0) goto L_0x003a;
    L_0x002f:
        r9 = com.mi.config.b.hE();
        if (r9 == 0) goto L_0x003a;
    L_0x0035:
        if (r8 == r7) goto L_0x003a;
    L_0x0037:
        r0.add(r3);
    L_0x003a:
        if (r8 == r7) goto L_0x003e;
    L_0x003c:
        if (r8 != r6) goto L_0x0107;
    L_0x003e:
        r9 = com.mi.config.b.gn();
        if (r9 == 0) goto L_0x004d;
    L_0x0044:
        r9 = com.mi.config.b.gm();
        if (r9 == 0) goto L_0x004d;
    L_0x004a:
        r0.add(r5);
    L_0x004d:
        r9 = com.mi.config.b.hq();
        if (r9 == 0) goto L_0x0056;
    L_0x0053:
        r0.add(r4);
    L_0x0056:
        if (r8 != r6) goto L_0x0107;
    L_0x0058:
        r8 = com.android.camera.data.DataRepository.dataItemFeature();
        r8 = r8.fS();
        r9 = com.android.camera.CameraSettings.isFrontSupportedUltraPixelPhotography(r11);
        if (r9 == 0) goto L_0x007a;
    L_0x0066:
        if (r12 == 0) goto L_0x007a;
    L_0x0068:
        if (r8 <= 0) goto L_0x007a;
    L_0x006a:
        r9 = 251; // 0xfb float:3.52E-43 double:1.24E-321;
        r0.add(r9);
        r9 = com.android.camera.data.DataRepository.dataItemConfig();
        r9 = r9.getFrontComponentConfigUltraPixel();
        r9.initUltraPixelResource(r8);
    L_0x007a:
        r8 = com.mi.config.b.fT();
        if (r8 == 0) goto L_0x0107;
    L_0x0080:
        r0.add(r1);
        goto L_0x0107;
    L_0x0085:
        r9 = com.mi.config.b.hl();
        if (r9 == 0) goto L_0x0090;
    L_0x008b:
        r9 = 228; // 0xe4 float:3.2E-43 double:1.126E-321;
        r0.add(r9);
    L_0x0090:
        r9 = com.mi.config.b.he();
        if (r9 == 0) goto L_0x009b;
    L_0x0096:
        r9 = 229; // 0xe5 float:3.21E-43 double:1.13E-321;
        r0.add(r9);
    L_0x009b:
        r9 = com.mi.config.b.gK();
        if (r9 == 0) goto L_0x00a6;
    L_0x00a1:
        r9 = 234; // 0xea float:3.28E-43 double:1.156E-321;
        r0.add(r9);
    L_0x00a6:
        if (r8 == r7) goto L_0x00b3;
    L_0x00a8:
        if (r12 == 0) goto L_0x00b3;
    L_0x00aa:
        r9 = com.mi.config.b.hE();
        if (r9 == 0) goto L_0x00b3;
    L_0x00b0:
        r0.add(r3);
    L_0x00b3:
        r9 = com.android.camera.Util.UI_DEBUG();
        if (r9 != 0) goto L_0x00c6;
    L_0x00b9:
        if (r8 == r7) goto L_0x00bd;
    L_0x00bb:
        if (r8 != r6) goto L_0x00c6;
    L_0x00bd:
        r9 = com.mi.config.b.gm();
        if (r9 == 0) goto L_0x00c6;
    L_0x00c3:
        r0.add(r2);
    L_0x00c6:
        if (r12 == 0) goto L_0x00d7;
    L_0x00c8:
        r9 = com.android.camera.CameraAppImpl.getAndroidContext();
        r9 = com.android.camera.CameraSettings.checkLensAvailability(r9);
        if (r9 == 0) goto L_0x00d7;
    L_0x00d2:
        r9 = 242; // 0xf2 float:3.39E-43 double:1.196E-321;
        r0.add(r9);
    L_0x00d7:
        if (r8 == r6) goto L_0x00da;
    L_0x00d9:
        goto L_0x0106;
    L_0x00da:
        r8 = com.android.camera.data.DataRepository.dataItemFeature();
        r8 = r8.fR();
        r9 = com.android.camera.CameraSettings.isSupportedUltraPixelPhotography(r11);
        if (r9 == 0) goto L_0x0106;
    L_0x00e8:
        if (r12 == 0) goto L_0x0106;
    L_0x00ea:
        if (r8 <= 0) goto L_0x0106;
    L_0x00ec:
        r9 = com.android.camera.data.DataRepository.dataItemFeature();
        r9 = r9.fP();
        if (r9 != 0) goto L_0x0106;
    L_0x00f6:
        r9 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        r0.add(r9);
        r9 = com.android.camera.data.DataRepository.dataItemConfig();
        r9 = r9.getRearComponentConfigUltraPixel();
        r9.initUltraPixelResource(r8);
    L_0x0107:
        goto L_0x01a0;
    L_0x0109:
        r8 = r0.add(r3);
        r8.add(r6);
        r8 = 1;
        if (r9 != r8) goto L_0x013b;
    L_0x0113:
        r8 = com.mi.config.b.hz();
        if (r8 == 0) goto L_0x013b;
    L_0x0119:
        r8 = com.mi.config.b.gn();
        if (r8 == 0) goto L_0x0128;
    L_0x011f:
        r8 = com.mi.config.b.gm();
        if (r8 == 0) goto L_0x0128;
    L_0x0125:
        r0.add(r5);
    L_0x0128:
        r8 = com.mi.config.b.hq();
        if (r8 == 0) goto L_0x0131;
    L_0x012e:
        r0.add(r4);
    L_0x0131:
        r8 = com.mi.config.b.fT();
        if (r8 == 0) goto L_0x01a0;
    L_0x0137:
        r0.add(r1);
        goto L_0x01a0;
    L_0x013b:
        if (r9 != 0) goto L_0x01a0;
    L_0x013d:
        r8 = com.android.camera.Util.UI_DEBUG();
        if (r8 != 0) goto L_0x01a0;
    L_0x0143:
        r8 = com.android.camera.CameraSettings.isCameraPortraitWithFaceBeauty();
        if (r8 == 0) goto L_0x01a0;
    L_0x0149:
        r8 = com.mi.config.b.gm();
        if (r8 == 0) goto L_0x01a0;
    L_0x014f:
        r0.add(r2);
        goto L_0x01a0;
    L_0x0153:
        r8 = r0.add(r3);
        r8.add(r6);
        goto L_0x01a0;
    L_0x015b:
        if (r9 == 0) goto L_0x015e;
    L_0x015d:
        goto L_0x018b;
    L_0x015e:
        r8 = r0.add(r3);
        r9 = 233; // 0xe9 float:3.27E-43 double:1.15E-321;
        r8.add(r9);
        r8 = com.mi.config.b.gC();
        if (r8 == 0) goto L_0x017c;
    L_0x016d:
        r8 = com.android.camera.data.DataRepository.dataItemFeature();
        r8 = r8.fv();
        if (r8 != 0) goto L_0x017c;
    L_0x0177:
        r8 = 232; // 0xe8 float:3.25E-43 double:1.146E-321;
        r0.add(r8);
    L_0x017c:
        r8 = com.android.camera.Util.UI_DEBUG();
        if (r8 != 0) goto L_0x018b;
    L_0x0182:
        r8 = r11.isSupportVideoBeauty();
        if (r8 == 0) goto L_0x018b;
    L_0x0188:
        r0.add(r2);
    L_0x018b:
        goto L_0x01a0;
    L_0x018c:
        if (r9 != 0) goto L_0x01a0;
    L_0x018e:
        r8 = r11.isSupportVideoBeauty();
        if (r8 == 0) goto L_0x01a0;
    L_0x0194:
        r0.add(r3);
        r8 = com.android.camera.Util.UI_DEBUG();
        if (r8 != 0) goto L_0x01a0;
    L_0x019d:
        r0.add(r2);
    L_0x01a0:
        r8 = r10.filterFeature(r0);
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.data.data.config.SupportedConfigFactory.getSupportedExtraConfigs(int, int, com.android.camera.data.cloud.DataCloud$CloudFeature, com.android.camera2.CameraCapabilities, boolean):com.android.camera.data.data.config.SupportedConfigs");
    }

    public static int findViewPosition(int i) {
        return TopViewPositionArray.getCurrentUseViewPositionFromConfig(i);
    }
}
