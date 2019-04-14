package com.ss.android.vesdk.runtime.cloudconfig;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import android.util.Log;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.ss.android.vesdk.runtime.VERuntime;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class PerformanceConfig {
    public static final String BASE_URL_CHINA = "https://effect.snssdk.com/devicehub/getSettings?";
    public static final String BASE_URL_SINGAPORE = "https://sg-effect.byteoversea.com/devicehub/getSetting?";
    public static final String BASE_URL_TEST = "https://effect.snssdk.com/devicehub/getSettings/?";
    public static final String BASE_URL_US = "https://va-effect.byteoversea.com/devicehub/getSettings?";
    public static final int DISABLE = 2;
    public static final int ENABLE = 1;
    public static final String KEY_I_EARPHONE_ECHO_AAUDIO = "earphone_echo_aaudio";
    public static final String KEY_I_EARPHONE_ECHO_HUAWEI = "earphone_echo_huawei";
    public static final String KEY_I_EARPHONE_ECHO_NORMAL = "earphone_echo_normal";
    public static final String KEY_I_IMPORT_ENCODE_MODE = "import_encode_mode";
    public static final String KEY_I_IMPORT_HW_BITRATE_MODE = "import_hw_bitrate_mode";
    public static final String KEY_I_IMPORT_HW_PROFILE = "import_hw_profile";
    public static final String KEY_I_IMPORT_SHORTER_PIXELS = "import_shorter_pixels";
    public static final String KEY_I_IMPORT_SW_BITRATE_MODE = "import_sw_bitrate_mode";
    public static final String KEY_I_IMPORT_VIDEO_HW_BITRATE = "import_video_hw_bitrate";
    public static final String KEY_I_IMPORT_VIDEO_SW_CRF = "import_video_sw_crf";
    public static final String KEY_I_IMPORT_VIDEO_SW_GOP = "import_video_sw_gop";
    public static final String KEY_I_IMPORT_VIDEO_SW_MAXRATE = "import_video_sw_maxrate";
    public static final String KEY_I_IMPORT_VIDEO_SW_PRESET = "import_video_sw_preset";
    public static final String KEY_I_IMPORT_VIDEO_SW_QP = "import_video_sw_qp";
    public static final String KEY_I_RECORD_CAMERA_COMPAT_LEVEL = "record_camera_compat_level";
    public static final String KEY_I_RECORD_CAMERA_TYPE = "record_camera_type";
    public static final String KEY_I_RECORD_ENCODE_MODE = "record_encode_mode";
    public static final String KEY_I_RECORD_HW_BITRATE_MODE = "record_hw_bitrate_mode";
    public static final String KEY_I_RECORD_HW_PROFILE = "record_hw_profile";
    public static final String KEY_I_RECORD_RESOLUTION_HEIGHT = "record_resolution_height";
    public static final String KEY_I_RECORD_RESOLUTION_WIDTH = "record_resolution_width";
    public static final String KEY_I_RECORD_SW_BITRATE_MODE = "record_sw_bitrate_mode";
    public static final String KEY_I_RECORD_VIDEO_HW_BITRATE = "record_video_hw_bitrate";
    public static final String KEY_I_RECORD_VIDEO_SW_CRF = "record_video_sw_crf";
    public static final String KEY_I_RECORD_VIDEO_SW_GOP = "record_video_sw_gop";
    public static final String KEY_I_RECORD_VIDEO_SW_MAXRATE = "record_video_sw_maxrate";
    public static final String KEY_I_RECORD_VIDEO_SW_PRESET = "record_video_sw_preset";
    public static final String KEY_I_RECORD_VIDEO_SW_QP = "record_video_sw_qp";
    public static final String KEY_I_SYNTHETIC_ENCODE_MODE = "synthetic_encode_mode";
    public static final String KEY_I_SYNTHETIC_HW_BITRATE_MODE = "synthetic_hw_bitrate_mode";
    public static final String KEY_I_SYNTHETIC_HW_PROFILE = "synthetic_hw_profile";
    public static final String KEY_I_SYNTHETIC_SW_BITRATE_MODE = "synthetic_sw_bitrate_mode";
    public static final String KEY_I_SYNTHETIC_VIDEO_HW_BITRATE = "synthetic_video_hw_bitrate";
    public static final String KEY_I_SYNTHETIC_VIDEO_SW_CRF = "synthetic_video_sw_crf";
    public static final String KEY_I_SYNTHETIC_VIDEO_SW_GOP = "synthetic_video_sw_gop";
    public static final String KEY_I_SYNTHETIC_VIDEO_SW_MAXRATE = "synthetic_video_sw_maxrate";
    public static final String KEY_I_SYNTHETIC_VIDEO_SW_PRESET = "synthetic_video_sw_preset";
    public static final String KEY_I_SYNTHETIC_VIDEO_SW_QP = "synthetic_video_sw_qp";
    private static final String TAG = "PerfConfig";
    public static final int UNDEFINED = 0;
    public static final List<Config> sConfigList = new ArrayList(20);
    private static final String sConfigs = "ShortVideoConfig";
    private static final String sPerfConfigPrefix = "PerfConfig_";
    public static final VECloudConfig sVECloudConfig = new VECloudConfig();

    private static class ConfigAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConfigAsyncTask() {
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Void... voidArr) {
            try {
                Context context = VERuntime.getInstance().getContext();
                Map toMap = DeviceInfoDetector.toMap();
                toMap.put("package_name", context.getPackageName());
                toMap.put("model", Build.MODEL);
                toMap.put("os_version", VERSION.RELEASE);
                toMap.put("local", Locale.getDefault().getCountry());
                toMap.put("platform", "android");
                String body = HttpRequest.get(PerformanceConfig.BASE_URL_CHINA, toMap, true).body();
                String str = PerformanceConfig.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("cloud config result = ");
                stringBuilder.append(body);
                Log.d(str, stringBuilder.toString());
                try {
                    JSONObject jSONObject = new JSONObject(body);
                    if (jSONObject.getInt("code") == 0) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                        HashMap hashMap = new HashMap();
                        for (Config config : PerformanceConfig.sConfigList) {
                            if (jSONObject2.has(config.key)) {
                                hashMap.put(config.key, PerformanceConfig.doubtingFilter(config, jSONObject2, true));
                            } else {
                                String str2 = PerformanceConfig.TAG;
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Configuration field not available for this config:");
                                stringBuilder2.append(config.key);
                                Log.e(str2, stringBuilder2.toString());
                            }
                        }
                        PerformanceConfig.setPerformanceConfig(VERuntime.getInstance().getContext(), hashMap);
                    } else {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Return code is ");
                        stringBuilder3.append(jSONObject.getInt("code"));
                        Log.e(PerformanceConfig.TAG, "Config return code not 0.", new IllegalStateException(stringBuilder3.toString()));
                    }
                } catch (JSONException e) {
                    Log.e(PerformanceConfig.TAG, "Parse json result failed! ", e);
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                Log.e(PerformanceConfig.TAG, "Fetch config failed! ", e2);
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Void voidR) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConfigKey {
    }

    static {
        sConfigList.add(new Config(KEY_I_RECORD_CAMERA_TYPE, 0, Integer.valueOf(1)));
        sConfigList.add(new Config(KEY_I_RECORD_VIDEO_SW_CRF, 0, Integer.valueOf(15)));
        sConfigList.add(new Config(KEY_I_RECORD_VIDEO_HW_BITRATE, 0, Integer.valueOf(4194304)));
        sConfigList.add(new Config(KEY_I_RECORD_ENCODE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_RECORD_RESOLUTION_WIDTH, 0, Integer.valueOf(576)));
        sConfigList.add(new Config(KEY_I_RECORD_RESOLUTION_HEIGHT, 0, Integer.valueOf(1024)));
        sConfigList.add(new Config(KEY_I_RECORD_CAMERA_COMPAT_LEVEL, 0, Integer.valueOf(1)));
        sConfigList.add(new Config(KEY_I_RECORD_VIDEO_SW_MAXRATE, 0, Integer.valueOf(5000000)));
        sConfigList.add(new Config(KEY_I_RECORD_VIDEO_SW_PRESET, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_RECORD_VIDEO_SW_GOP, 0, Integer.valueOf(35)));
        sConfigList.add(new Config(KEY_I_RECORD_VIDEO_SW_QP, 0, Integer.valueOf(2)));
        sConfigList.add(new Config(KEY_I_RECORD_SW_BITRATE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_RECORD_HW_BITRATE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_RECORD_HW_PROFILE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_IMPORT_ENCODE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_IMPORT_SHORTER_PIXELS, 0, Integer.valueOf(576)));
        sConfigList.add(new Config(KEY_I_IMPORT_VIDEO_SW_CRF, 0, Integer.valueOf(15)));
        sConfigList.add(new Config(KEY_I_IMPORT_VIDEO_HW_BITRATE, 0, Integer.valueOf(4194304)));
        sConfigList.add(new Config(KEY_I_IMPORT_VIDEO_SW_MAXRATE, 0, Integer.valueOf(5000000)));
        sConfigList.add(new Config(KEY_I_IMPORT_VIDEO_SW_PRESET, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_IMPORT_VIDEO_SW_GOP, 0, Integer.valueOf(35)));
        sConfigList.add(new Config(KEY_I_IMPORT_VIDEO_SW_QP, 0, Integer.valueOf(2)));
        sConfigList.add(new Config(KEY_I_IMPORT_SW_BITRATE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_IMPORT_HW_BITRATE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_IMPORT_HW_PROFILE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_ENCODE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_VIDEO_SW_CRF, 0, Integer.valueOf(15)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_VIDEO_HW_BITRATE, 0, Integer.valueOf(4194304)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_VIDEO_SW_MAXRATE, 0, Integer.valueOf(5000000)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_VIDEO_SW_PRESET, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_VIDEO_SW_GOP, 0, Integer.valueOf(35)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_VIDEO_SW_QP, 0, Integer.valueOf(2)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_SW_BITRATE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_HW_BITRATE_MODE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_SYNTHETIC_HW_PROFILE, 0, Integer.valueOf(0)));
        sConfigList.add(new Config(KEY_I_EARPHONE_ECHO_NORMAL, 0, Integer.valueOf(1)));
        sConfigList.add(new Config(KEY_I_EARPHONE_ECHO_AAUDIO, 0, Integer.valueOf(1)));
        sConfigList.add(new Config(KEY_I_EARPHONE_ECHO_HUAWEI, 0, Integer.valueOf(1)));
    }

    @RestrictTo({Scope.LIBRARY})
    public static void restoreFromCache() {
        if (VERuntime.getInstance().getContext() != null) {
            setConfigsFromMap(getPerformanceConfig(VERuntime.getInstance().getContext()));
            return;
        }
        throw new IllegalStateException("Must call VideoSdkCore.init() before.");
    }

    @RestrictTo({Scope.LIBRARY})
    public static void updateConfig(Map<String, String> map) {
        setConfigsFromMap(map);
        setPerformanceConfig(VERuntime.getInstance().getContext(), map);
    }

    private static void setConfigsFromMap(Map<String, String> map) {
        for (Config config : sConfigList) {
            String str = config.key;
            Object obj = -1;
            switch (str.hashCode()) {
                case -2004732869:
                    if (str.equals(KEY_I_SYNTHETIC_VIDEO_HW_BITRATE)) {
                        obj = 27;
                        break;
                    }
                    break;
                case -2003520120:
                    if (str.equals(KEY_I_EARPHONE_ECHO_AAUDIO)) {
                        obj = 36;
                        break;
                    }
                    break;
                case -1815767918:
                    if (str.equals(KEY_I_IMPORT_ENCODE_MODE)) {
                        obj = 14;
                        break;
                    }
                    break;
                case -1785223334:
                    if (str.equals(KEY_I_EARPHONE_ECHO_HUAWEI)) {
                        obj = 37;
                        break;
                    }
                    break;
                case -1694569666:
                    if (str.equals(KEY_I_RECORD_ENCODE_MODE)) {
                        obj = 3;
                        break;
                    }
                    break;
                case -1668300853:
                    if (str.equals(KEY_I_IMPORT_HW_BITRATE_MODE)) {
                        obj = 23;
                        break;
                    }
                    break;
                case -1645123140:
                    if (str.equals(KEY_I_IMPORT_VIDEO_SW_PRESET)) {
                        obj = 19;
                        break;
                    }
                    break;
                case -1618492838:
                    if (str.equals(KEY_I_EARPHONE_ECHO_NORMAL)) {
                        obj = 35;
                        break;
                    }
                    break;
                case -1558016730:
                    if (str.equals(KEY_I_RECORD_CAMERA_TYPE)) {
                        obj = null;
                        break;
                    }
                    break;
                case -1376521253:
                    if (str.equals(KEY_I_IMPORT_VIDEO_HW_BITRATE)) {
                        obj = 17;
                        break;
                    }
                    break;
                case -1303806036:
                    if (str.equals(KEY_I_RECORD_RESOLUTION_HEIGHT)) {
                        obj = 5;
                        break;
                    }
                    break;
                case -1248494809:
                    if (str.equals(KEY_I_RECORD_HW_PROFILE)) {
                        obj = 13;
                        break;
                    }
                    break;
                case -1236656006:
                    if (str.equals(KEY_I_SYNTHETIC_VIDEO_SW_CRF)) {
                        obj = 26;
                        break;
                    }
                    break;
                case -1236652245:
                    if (str.equals(KEY_I_SYNTHETIC_VIDEO_SW_GOP)) {
                        obj = 30;
                        break;
                    }
                    break;
                case -1136469343:
                    if (str.equals(KEY_I_RECORD_RESOLUTION_WIDTH)) {
                        obj = 4;
                        break;
                    }
                    break;
                case -532682277:
                    if (str.equals(KEY_I_RECORD_VIDEO_SW_MAXRATE)) {
                        obj = 7;
                        break;
                    }
                    break;
                case -457298194:
                    if (str.equals(KEY_I_RECORD_VIDEO_SW_CRF)) {
                        obj = 1;
                        break;
                    }
                    break;
                case -457294433:
                    if (str.equals(KEY_I_RECORD_VIDEO_SW_GOP)) {
                        obj = 9;
                        break;
                    }
                    break;
                case -144025773:
                    if (str.equals(KEY_I_IMPORT_HW_PROFILE)) {
                        obj = 24;
                        break;
                    }
                    break;
                case 18051394:
                    if (str.equals(KEY_I_RECORD_SW_BITRATE_MODE)) {
                        obj = 11;
                        break;
                    }
                    break;
                case 80523290:
                    if (str.equals(KEY_I_IMPORT_VIDEO_SW_CRF)) {
                        obj = 16;
                        break;
                    }
                    break;
                case 80527051:
                    if (str.equals(KEY_I_IMPORT_VIDEO_SW_GOP)) {
                        obj = 20;
                        break;
                    }
                    break;
                case 514297628:
                    if (str.equals(KEY_I_SYNTHETIC_VIDEO_SW_QP)) {
                        obj = 31;
                        break;
                    }
                    break;
                case 614850679:
                    if (str.equals(KEY_I_RECORD_HW_BITRATE_MODE)) {
                        obj = 12;
                        break;
                    }
                    break;
                case 638028392:
                    if (str.equals(KEY_I_RECORD_VIDEO_SW_PRESET)) {
                        obj = 8;
                        break;
                    }
                    break;
                case 681699503:
                    if (str.equals(KEY_I_RECORD_VIDEO_HW_BITRATE)) {
                        obj = 2;
                        break;
                    }
                    break;
                case 762676278:
                    if (str.equals(KEY_I_SYNTHETIC_SW_BITRATE_MODE)) {
                        obj = 32;
                        break;
                    }
                    break;
                case 1051236402:
                    if (str.equals(KEY_I_SYNTHETIC_ENCODE_MODE)) {
                        obj = 25;
                        break;
                    }
                    break;
                case 1068128883:
                    if (str.equals(KEY_I_RECORD_CAMERA_COMPAT_LEVEL)) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1075852647:
                    if (str.equals(KEY_I_SYNTHETIC_VIDEO_SW_MAXRATE)) {
                        obj = 28;
                        break;
                    }
                    break;
                case 1359475563:
                    if (str.equals(KEY_I_SYNTHETIC_HW_BITRATE_MODE)) {
                        obj = 33;
                        break;
                    }
                    break;
                case 1382653276:
                    if (str.equals(KEY_I_SYNTHETIC_VIDEO_SW_PRESET)) {
                        obj = 29;
                        break;
                    }
                    break;
                case 1399891293:
                    if (str.equals(KEY_I_IMPORT_SHORTER_PIXELS)) {
                        obj = 15;
                        break;
                    }
                    break;
                case 1704064263:
                    if (str.equals(KEY_I_IMPORT_VIDEO_SW_MAXRATE)) {
                        obj = 18;
                        break;
                    }
                    break;
                case 1749573555:
                    if (str.equals(KEY_I_SYNTHETIC_HW_PROFILE)) {
                        obj = 34;
                        break;
                    }
                    break;
                case 1942260604:
                    if (str.equals(KEY_I_IMPORT_VIDEO_SW_QP)) {
                        obj = 21;
                        break;
                    }
                    break;
                case 2029867158:
                    if (str.equals(KEY_I_IMPORT_SW_BITRATE_MODE)) {
                        obj = 22;
                        break;
                    }
                    break;
                case 2063458856:
                    if (str.equals(KEY_I_RECORD_VIDEO_SW_QP)) {
                        obj = 10;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    sVECloudConfig.mRecordCameraType = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 1:
                    sVECloudConfig.mRecordSWEncodeCRF = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 2:
                    sVECloudConfig.mRecordHWEncodeBPS = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 3:
                    sVECloudConfig.mRecordEncodeMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 4:
                    sVECloudConfig.mRecordResolutionWidth = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 5:
                    sVECloudConfig.mRecordResolutionHeight = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 6:
                    sVECloudConfig.mRecordCameraCompatLevel = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 7:
                    sVECloudConfig.mRecordVideoSWMaxrate = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 8:
                    sVECloudConfig.mRecordVideoSWPreset = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 9:
                    sVECloudConfig.mRecordVideoSWGop = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 10:
                    sVECloudConfig.mRecordVideoSWQP = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 11:
                    sVECloudConfig.mRecordSWBitrateMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 12:
                    sVECloudConfig.mRecordHwBitrateMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 13:
                    sVECloudConfig.mRecordHwProfile = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 14:
                    sVECloudConfig.mImportEncodeMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 15:
                    sVECloudConfig.mImportShortEdgeValue = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 16:
                    sVECloudConfig.mImportSWEncodeCRF = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 17:
                    sVECloudConfig.mImportHWEncodeBPS = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 18:
                    sVECloudConfig.mImportVideoSWMaxrate = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 19:
                    sVECloudConfig.mImportVideoSWPreset = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 20:
                    sVECloudConfig.mImportVideoSWGop = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 21:
                    sVECloudConfig.mImportVideoSWQP = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 22:
                    sVECloudConfig.mImportSWBitrateMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 23:
                    sVECloudConfig.mImportHwBitrateMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 24:
                    sVECloudConfig.mImportHwProfile = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 25:
                    sVECloudConfig.mCompileEncodeMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 26:
                    sVECloudConfig.mCompileEncodeSWCRF = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 27:
                    sVECloudConfig.mCompileEncodeHWBPS = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 28:
                    sVECloudConfig.mCompileEncodeSWMaxrate = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 29:
                    sVECloudConfig.mCompileEncodeSWCRFPreset = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 30:
                    sVECloudConfig.mCompileEncodeSWGOP = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 31:
                    sVECloudConfig.mCompileVideoSWQP = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 32:
                    sVECloudConfig.mCompileSWBitrateMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 33:
                    sVECloudConfig.mCompileHwBitrateMode = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 34:
                    sVECloudConfig.mCompileHwProfile = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 35:
                    sVECloudConfig.mEarphoneEchoNormal = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 36:
                    sVECloudConfig.mEarphoneEchoAAudio = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                case 37:
                    sVECloudConfig.mEarphoneEchoHuawei = getIntConfig(map, config.key, ((Integer) config.defaultValue).intValue());
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unexpected Config.key = ");
                    stringBuilder.append(config.key);
                    throw new IllegalStateException(stringBuilder.toString());
            }
        }
    }

    private static int getIntConfig(Map<String, String> map, String str, int i) {
        if (map == null) {
            return 0;
        }
        String str2 = (String) map.get(str);
        try {
            if (!TextUtils.isEmpty(str2)) {
                return Integer.parseInt(str2);
            }
        } catch (Exception e) {
            Log.e(TAG, "getIntConfig: error", e);
        }
        return i;
    }

    private static float getFloatConfig(Map<String, String> map, String str, float f) {
        if (map == null) {
            return PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO;
        }
        String str2 = (String) map.get(str);
        try {
            if (!TextUtils.isEmpty(str2)) {
                return Float.parseFloat(str2);
            }
        } catch (Exception e) {
            Log.e(TAG, "getIntConfig: error", e);
        }
        return f;
    }

    public static void fetch() {
        new ConfigAsyncTask().execute(new Void[0]);
    }

    private static int getDefaultInt(String str) {
        for (Config config : sConfigList) {
            if (config.key.equals(str)) {
                return ((Integer) config.defaultValue).intValue();
            }
        }
        return -1;
    }

    private static float getDefaultFloat(String str) {
        for (Config config : sConfigList) {
            if (config.key.equals(str)) {
                return ((Float) config.defaultValue).floatValue();
            }
        }
        return -1.0f;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:147:0x022b=Splitter:B:147:0x022b, B:224:0x02dc=Splitter:B:224:0x02dc, B:141:0x021d=Splitter:B:141:0x021d, B:135:0x020f=Splitter:B:135:0x020f} */
    /* JADX WARNING: Missing block: B:128:0x01f9, code skipped:
            r10 = -1;
     */
    /* JADX WARNING: Missing block: B:130:0x0200, code skipped:
            switch(r10) {
                case 0: goto L_0x02f9;
                case 1: goto L_0x02ec;
                case 2: goto L_0x02ec;
                case 3: goto L_0x02ec;
                case 4: goto L_0x02e1;
                case 5: goto L_0x02e1;
                case 6: goto L_0x02e1;
                case 7: goto L_0x02d4;
                case 8: goto L_0x02d4;
                case 9: goto L_0x02d4;
                case 10: goto L_0x02c1;
                case 11: goto L_0x02b0;
                case 12: goto L_0x029f;
                case 13: goto L_0x028b;
                case 14: goto L_0x028b;
                case 15: goto L_0x028b;
                case 16: goto L_0x027d;
                case 17: goto L_0x027d;
                case 18: goto L_0x027d;
                case 19: goto L_0x0271;
                case 20: goto L_0x0265;
                case 21: goto L_0x0265;
                case 22: goto L_0x0265;
                case 23: goto L_0x0259;
                case 24: goto L_0x0259;
                case 25: goto L_0x0259;
                case 26: goto L_0x024d;
                case 27: goto L_0x024d;
                case 28: goto L_0x024d;
                case 29: goto L_0x023f;
                case 30: goto L_0x023f;
                case 31: goto L_0x023f;
                case 32: goto L_0x0231;
                case 33: goto L_0x0231;
                case 34: goto L_0x0231;
                case 35: goto L_0x0223;
                case 36: goto L_0x0215;
                case 37: goto L_0x0207;
                default: goto L_0x0203;
            };
     */
    /* JADX WARNING: Missing block: B:132:0x0207, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:133:0x020b, code skipped:
            if (r10 == 0) goto L_0x020f;
     */
    /* JADX WARNING: Missing block: B:134:0x020d, code skipped:
            if (r10 != 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:136:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:138:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:139:0x0219, code skipped:
            if (r10 == 0) goto L_0x021d;
     */
    /* JADX WARNING: Missing block: B:140:0x021b, code skipped:
            if (r10 != 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:142:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:144:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:145:0x0227, code skipped:
            if (r10 == 0) goto L_0x022b;
     */
    /* JADX WARNING: Missing block: B:146:0x0229, code skipped:
            if (r10 != 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:148:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:150:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:151:0x0235, code skipped:
            if (r10 < 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:152:0x0237, code skipped:
            if (r10 > 2) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:154:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:156:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:157:0x0243, code skipped:
            if (r10 < 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:158:0x0245, code skipped:
            if (r10 > 50) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:160:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:162:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:163:0x0251, code skipped:
            if (r10 < 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:165:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:167:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:168:0x025d, code skipped:
            if (r10 < 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:170:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:172:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:173:0x0269, code skipped:
            if (r10 < 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:175:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:177:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:178:0x0275, code skipped:
            if (r10 < 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:180:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:182:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:183:0x0281, code skipped:
            if (r10 < 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:184:0x0283, code skipped:
            if (r10 > 9) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:186:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:188:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:190:0x0292, code skipped:
            if (r10 < 100000) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:192:0x0297, code skipped:
            if (r10 > 10000000) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:194:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:196:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:199:0x02a5, code skipped:
            if ((r10 % 16) != 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:200:0x02a7, code skipped:
            if (r10 < 160) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:201:0x02a9, code skipped:
            if (r10 > 5120) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:202:0x02ab, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:204:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:207:0x02b6, code skipped:
            if ((r10 % 16) != 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:208:0x02b8, code skipped:
            if (r10 < 160) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:209:0x02ba, code skipped:
            if (r10 > 5120) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:210:0x02bc, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:212:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:215:0x02c7, code skipped:
            if ((r10 % 16) != 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:216:0x02c9, code skipped:
            if (r10 < 160) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:217:0x02cb, code skipped:
            if (r10 > 5120) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:218:0x02cd, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:219:0x02d2, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:221:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:222:0x02d8, code skipped:
            if (r10 == 0) goto L_0x02dc;
     */
    /* JADX WARNING: Missing block: B:223:0x02da, code skipped:
            if (r10 != 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:225:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:227:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:228:0x02e5, code skipped:
            if (r10 <= 0) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:230:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:232:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:233:0x02f0, code skipped:
            if (r10 < 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:234:0x02f2, code skipped:
            if (r10 > 50) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:236:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:238:?, code skipped:
            r10 = r0.getInt(r3);
     */
    /* JADX WARNING: Missing block: B:239:0x02fd, code skipped:
            if (r10 < 1) goto L_0x0304;
     */
    /* JADX WARNING: Missing block: B:241:?, code skipped:
            r0 = java.lang.String.valueOf(r10);
     */
    /* JADX WARNING: Missing block: B:242:0x0304, code skipped:
            r0 = null;
     */
    /* JADX WARNING: Missing block: B:245:0x030b, code skipped:
            throw new java.lang.IllegalStateException("Shouldn't reach here. Unmatched config.key case");
     */
    public static java.lang.String doubtingFilter(com.ss.android.vesdk.runtime.cloudconfig.Config r16, org.json.JSONObject r17, boolean r18) {
        /*
        r1 = r16;
        r0 = r17;
        if (r0 == 0) goto L_0x038e;
    L_0x0006:
        r3 = r1.key;
        r4 = r0.has(r3);
        r5 = 0;
        if (r4 != 0) goto L_0x002f;
    L_0x000f:
        r0 = "PerfConfig";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = "fetched config do not contain config.key = ";
        r4.append(r6);
        r4.append(r3);
        r3 = r4.toString();
        android.util.Log.e(r0, r3);
        if (r18 == 0) goto L_0x002e;
    L_0x0027:
        r0 = r1.defaultValue;
        r0 = r0.toString();
        return r0;
    L_0x002e:
        return r5;
        r6 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
        r4 = -1;
        r8 = 0;
        r9 = 1;
        r10 = r3.hashCode();	 Catch:{ JSONException -> 0x030c }
        r11 = 2;
        r12 = 9;
        switch(r10) {
            case -2004732869: goto L_0x01ef;
            case -2003520120: goto L_0x01e4;
            case -1815767918: goto L_0x01d9;
            case -1785223334: goto L_0x01ce;
            case -1694569666: goto L_0x01c4;
            case -1668300853: goto L_0x01b9;
            case -1645123140: goto L_0x01ae;
            case -1618492838: goto L_0x01a3;
            case -1558016730: goto L_0x0199;
            case -1376521253: goto L_0x018e;
            case -1303806036: goto L_0x0182;
            case -1248494809: goto L_0x0176;
            case -1236656006: goto L_0x016b;
            case -1236652245: goto L_0x015f;
            case -1136469343: goto L_0x0153;
            case -532682277: goto L_0x0147;
            case -457298194: goto L_0x013c;
            case -457294433: goto L_0x0130;
            case -144025773: goto L_0x0124;
            case 18051394: goto L_0x0118;
            case 80523290: goto L_0x010d;
            case 80527051: goto L_0x0101;
            case 514297628: goto L_0x00f5;
            case 614850679: goto L_0x00e9;
            case 638028392: goto L_0x00dd;
            case 681699503: goto L_0x00d2;
            case 762676278: goto L_0x00c6;
            case 1051236402: goto L_0x00bb;
            case 1068128883: goto L_0x00af;
            case 1075852647: goto L_0x00a3;
            case 1359475563: goto L_0x0097;
            case 1382653276: goto L_0x008b;
            case 1399891293: goto L_0x007f;
            case 1704064263: goto L_0x0073;
            case 1749573555: goto L_0x0067;
            case 1942260604: goto L_0x005b;
            case 2029867158: goto L_0x004f;
            case 2063458856: goto L_0x0043;
            default: goto L_0x0041;
        };	 Catch:{ JSONException -> 0x030c }
    L_0x0041:
        goto L_0x01f9;
    L_0x0043:
        r10 = "record_video_sw_qp";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x004b:
        r10 = 29;
        goto L_0x01fa;
    L_0x004f:
        r10 = "import_sw_bitrate_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0057:
        r10 = 33;
        goto L_0x01fa;
    L_0x005b:
        r10 = "import_video_sw_qp";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0063:
        r10 = 30;
        goto L_0x01fa;
    L_0x0067:
        r10 = "synthetic_hw_profile";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x006f:
        r10 = 25;
        goto L_0x01fa;
    L_0x0073:
        r10 = "import_video_sw_maxrate";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x007b:
        r10 = 14;
        goto L_0x01fa;
    L_0x007f:
        r10 = "import_shorter_pixels";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0087:
        r10 = 12;
        goto L_0x01fa;
    L_0x008b:
        r10 = "synthetic_video_sw_preset";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0093:
        r10 = 16;
        goto L_0x01fa;
    L_0x0097:
        r10 = "synthetic_hw_bitrate_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x009f:
        r10 = 21;
        goto L_0x01fa;
    L_0x00a3:
        r10 = "synthetic_video_sw_maxrate";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00ab:
        r10 = 15;
        goto L_0x01fa;
    L_0x00af:
        r10 = "record_camera_compat_level";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00b7:
        r10 = 19;
        goto L_0x01fa;
    L_0x00bb:
        r10 = "synthetic_encode_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00c3:
        r10 = r12;
        goto L_0x01fa;
    L_0x00c6:
        r10 = "synthetic_sw_bitrate_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00ce:
        r10 = 34;
        goto L_0x01fa;
    L_0x00d2:
        r10 = "record_video_hw_bitrate";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00da:
        r10 = 4;
        goto L_0x01fa;
    L_0x00dd:
        r10 = "record_video_sw_preset";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00e5:
        r10 = 17;
        goto L_0x01fa;
    L_0x00e9:
        r10 = "record_hw_bitrate_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00f1:
        r10 = 20;
        goto L_0x01fa;
    L_0x00f5:
        r10 = "synthetic_video_sw_qp";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x00fd:
        r10 = 31;
        goto L_0x01fa;
    L_0x0101:
        r10 = "import_video_sw_gop";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0109:
        r10 = 27;
        goto L_0x01fa;
    L_0x010d:
        r10 = "import_video_sw_crf";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0115:
        r10 = r11;
        goto L_0x01fa;
    L_0x0118:
        r10 = "record_sw_bitrate_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0120:
        r10 = 32;
        goto L_0x01fa;
    L_0x0124:
        r10 = "import_hw_profile";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x012c:
        r10 = 24;
        goto L_0x01fa;
    L_0x0130:
        r10 = "record_video_sw_gop";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0138:
        r10 = 26;
        goto L_0x01fa;
    L_0x013c:
        r10 = "record_video_sw_crf";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0144:
        r10 = r9;
        goto L_0x01fa;
    L_0x0147:
        r10 = "record_video_sw_maxrate";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x014f:
        r10 = 13;
        goto L_0x01fa;
    L_0x0153:
        r10 = "record_resolution_width";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x015b:
        r10 = 10;
        goto L_0x01fa;
    L_0x015f:
        r10 = "synthetic_video_sw_gop";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0167:
        r10 = 28;
        goto L_0x01fa;
    L_0x016b:
        r10 = "synthetic_video_sw_crf";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0173:
        r10 = 3;
        goto L_0x01fa;
    L_0x0176:
        r10 = "record_hw_profile";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x017e:
        r10 = 23;
        goto L_0x01fa;
    L_0x0182:
        r10 = "record_resolution_height";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x018a:
        r10 = 11;
        goto L_0x01fa;
    L_0x018e:
        r10 = "import_video_hw_bitrate";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x0196:
        r10 = 5;
        goto L_0x01fa;
    L_0x0199:
        r10 = "record_camera_type";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01a1:
        r10 = r8;
        goto L_0x01fa;
    L_0x01a3:
        r10 = "earphone_echo_normal";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01ab:
        r10 = 35;
        goto L_0x01fa;
    L_0x01ae:
        r10 = "import_video_sw_preset";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01b6:
        r10 = 18;
        goto L_0x01fa;
    L_0x01b9:
        r10 = "import_hw_bitrate_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01c1:
        r10 = 22;
        goto L_0x01fa;
    L_0x01c4:
        r10 = "record_encode_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01cc:
        r10 = 7;
        goto L_0x01fa;
    L_0x01ce:
        r10 = "earphone_echo_huawei";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01d6:
        r10 = 37;
        goto L_0x01fa;
    L_0x01d9:
        r10 = "import_encode_mode";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01e1:
        r10 = 8;
        goto L_0x01fa;
    L_0x01e4:
        r10 = "earphone_echo_aaudio";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01ec:
        r10 = 36;
        goto L_0x01fa;
    L_0x01ef:
        r10 = "synthetic_video_hw_bitrate";
        r10 = r3.equals(r10);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x01f9;
    L_0x01f7:
        r10 = 6;
        goto L_0x01fa;
    L_0x01f9:
        r10 = r4;
    L_0x01fa:
        r13 = 50;
        r14 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r15 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        switch(r10) {
            case 0: goto L_0x02f9;
            case 1: goto L_0x02ec;
            case 2: goto L_0x02ec;
            case 3: goto L_0x02ec;
            case 4: goto L_0x02e1;
            case 5: goto L_0x02e1;
            case 6: goto L_0x02e1;
            case 7: goto L_0x02d4;
            case 8: goto L_0x02d4;
            case 9: goto L_0x02d4;
            case 10: goto L_0x02c1;
            case 11: goto L_0x02b0;
            case 12: goto L_0x029f;
            case 13: goto L_0x028b;
            case 14: goto L_0x028b;
            case 15: goto L_0x028b;
            case 16: goto L_0x027d;
            case 17: goto L_0x027d;
            case 18: goto L_0x027d;
            case 19: goto L_0x0271;
            case 20: goto L_0x0265;
            case 21: goto L_0x0265;
            case 22: goto L_0x0265;
            case 23: goto L_0x0259;
            case 24: goto L_0x0259;
            case 25: goto L_0x0259;
            case 26: goto L_0x024d;
            case 27: goto L_0x024d;
            case 28: goto L_0x024d;
            case 29: goto L_0x023f;
            case 30: goto L_0x023f;
            case 31: goto L_0x023f;
            case 32: goto L_0x0231;
            case 33: goto L_0x0231;
            case 34: goto L_0x0231;
            case 35: goto L_0x0223;
            case 36: goto L_0x0215;
            case 37: goto L_0x0207;
            default: goto L_0x0203;
        };	 Catch:{ JSONException -> 0x030c }
    L_0x0203:
        r0 = new java.lang.IllegalStateException;	 Catch:{ JSONException -> 0x030c }
        goto L_0x0306;
    L_0x0207:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x020f;
    L_0x020d:
        if (r10 != r9) goto L_0x0304;
    L_0x020f:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x0215:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x021d;
    L_0x021b:
        if (r10 != r9) goto L_0x0304;
    L_0x021d:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x0223:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x022b;
    L_0x0229:
        if (r10 != r9) goto L_0x0304;
    L_0x022b:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x0231:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < 0) goto L_0x0304;
    L_0x0237:
        if (r10 > r11) goto L_0x0304;
    L_0x0239:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x023f:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < r9) goto L_0x0304;
    L_0x0245:
        if (r10 > r13) goto L_0x0304;
    L_0x0247:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x024d:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < r9) goto L_0x0304;
    L_0x0253:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x0259:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < 0) goto L_0x0304;
    L_0x025f:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x0265:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < 0) goto L_0x0304;
    L_0x026b:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x0271:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < 0) goto L_0x0304;
    L_0x0277:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x027d:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < 0) goto L_0x0304;
    L_0x0283:
        if (r10 > r12) goto L_0x0304;
    L_0x0285:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x028b:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        r0 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
        if (r10 < r0) goto L_0x0304;
    L_0x0294:
        r0 = 10000000; // 0x989680 float:1.4012985E-38 double:4.9406565E-317;
        if (r10 > r0) goto L_0x0304;
    L_0x0299:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x029f:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        r0 = r10 % 16;
        if (r0 != 0) goto L_0x0304;
    L_0x02a7:
        if (r10 < r15) goto L_0x0304;
    L_0x02a9:
        if (r10 > r14) goto L_0x0304;
    L_0x02ab:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x02b0:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        r0 = r10 % 16;
        if (r0 != 0) goto L_0x0304;
    L_0x02b8:
        if (r10 < r15) goto L_0x0304;
    L_0x02ba:
        if (r10 > r14) goto L_0x0304;
    L_0x02bc:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x02c1:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        r0 = r10 % 16;
        if (r0 != 0) goto L_0x0304;
    L_0x02c9:
        if (r10 < r15) goto L_0x0304;
    L_0x02cb:
        if (r10 > r14) goto L_0x0304;
    L_0x02cd:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x02d2:
        r0 = move-exception;
        goto L_0x030e;
    L_0x02d4:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 == 0) goto L_0x02dc;
    L_0x02da:
        if (r10 != r9) goto L_0x0304;
    L_0x02dc:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x02e1:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 <= 0) goto L_0x0304;
    L_0x02e7:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x02ec:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < r9) goto L_0x0304;
    L_0x02f2:
        if (r10 > r13) goto L_0x0304;
    L_0x02f4:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x02f9:
        r10 = r0.getInt(r3);	 Catch:{ JSONException -> 0x030c }
        if (r10 < r9) goto L_0x0304;
    L_0x02ff:
        r0 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x02d2 }
        goto L_0x0305;
    L_0x0304:
        r0 = r5;
    L_0x0305:
        goto L_0x031b;
    L_0x0306:
        r8 = "Shouldn't reach here. Unmatched config.key case";
        r0.<init>(r8);	 Catch:{ JSONException -> 0x030c }
        throw r0;	 Catch:{ JSONException -> 0x030c }
    L_0x030c:
        r0 = move-exception;
        r10 = r4;
    L_0x030e:
        r4 = "PerfConfig";
        r8 = "Parse json result failed! ";
        android.util.Log.e(r4, r8, r0);
        r0.printStackTrace();
        r0 = r5;
        r8 = r9;
    L_0x031b:
        if (r8 != 0) goto L_0x0368;
    L_0x031d:
        if (r0 != 0) goto L_0x0368;
    L_0x031f:
        r4 = r1.type;
        if (r4 != 0) goto L_0x0344;
    L_0x0323:
        r4 = "doubtingFilter report: ";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "Checked config did not pass: key = ";
        r6.append(r7);
        r7 = r1.key;
        r6.append(r7);
        r7 = " value = ";
        r6.append(r7);
        r6.append(r10);
        r6 = r6.toString();
        android.util.Log.e(r4, r6);
        goto L_0x0368;
    L_0x0344:
        r4 = r1.type;
        if (r4 != r9) goto L_0x0368;
    L_0x0348:
        r4 = "doubtingFilter report: ";
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Checked config did not pass: key = ";
        r8.append(r9);
        r9 = r1.key;
        r8.append(r9);
        r9 = " value = ";
        r8.append(r9);
        r8.append(r6);
        r6 = r8.toString();
        android.util.Log.e(r4, r6);
    L_0x0368:
        if (r0 != 0) goto L_0x0374;
    L_0x036a:
        if (r18 == 0) goto L_0x0373;
    L_0x036c:
        r0 = r1.defaultValue;
        r5 = r0.toString();
    L_0x0373:
        return r5;
    L_0x0374:
        r1 = "doubtingFilter report: ";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r3);
        r3 = " == ";
        r2.append(r3);
        r2.append(r0);
        r2 = r2.toString();
        android.util.Log.d(r1, r2);
        return r0;
    L_0x038e:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "fetchedRawConfigs == null";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.vesdk.runtime.cloudconfig.PerformanceConfig.doubtingFilter(com.ss.android.vesdk.runtime.cloudconfig.Config, org.json.JSONObject, boolean):java.lang.String");
    }

    public static void setPerformanceConfig(Context context, Map<String, String> map) {
        if (map != null) {
            Set<Entry> entrySet = map.entrySet();
            Editor edit = context.getSharedPreferences(sConfigs, 0).edit();
            for (Entry entry : entrySet) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(sPerfConfigPrefix);
                stringBuilder.append((String) entry.getKey());
                edit.putString(stringBuilder.toString(), (String) entry.getValue());
            }
            edit.apply();
        }
    }

    public static Map<String, String> getPerformanceConfig(Context context) {
        HashMap hashMap = new HashMap();
        for (Entry entry : context.getSharedPreferences(sConfigs, 0).getAll().entrySet()) {
            if (((String) entry.getKey()).startsWith(sPerfConfigPrefix)) {
                hashMap.put(((String) entry.getKey()).substring(sPerfConfigPrefix.length()), (String) entry.getValue());
            }
        }
        return hashMap;
    }
}
