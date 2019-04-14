package com.android.camera.module.loader;

import android.text.TextUtils;
import com.android.camera.Camera;
import com.android.camera.constant.GlobalConstant;
import com.android.camera.data.data.config.ComponentConfigBeauty;
import com.android.camera.data.data.config.ComponentConfigBeautyBody;
import com.android.camera.data.data.config.ComponentConfigFlash;
import com.android.camera.data.data.config.ComponentConfigHdr;
import com.android.camera.data.data.config.ComponentConfigUltraWide;
import com.android.camera.data.provider.DataProvider.ProviderEditor;
import com.android.camera.log.Log;
import com.android.camera.module.BaseModule;
import com.android.camera.permission.PermissionManager;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

public class FunctionCameraPrepare extends Func1Base<Camera, BaseModule> {
    private static final String TAG = "FunctionCameraPrepare";
    private BaseModule baseModule;
    private boolean mNeedReConfigureData;
    private int mResetType;

    public FunctionCameraPrepare(int i, int i2, boolean z, BaseModule baseModule) {
        super(i);
        this.mResetType = i2;
        this.mNeedReConfigureData = z;
        this.baseModule = baseModule;
    }

    public Scheduler getWorkThread() {
        return GlobalConstant.sCameraSetupScheduler;
    }

    public NullHolder<BaseModule> apply(@NonNull NullHolder<Camera> nullHolder) throws Exception {
        if (!nullHolder.isPresent()) {
            return NullHolder.ofNullable(null, 234);
        }
        if (!PermissionManager.checkCameraLaunchPermissions()) {
            return NullHolder.ofNullable(null, 229);
        }
        Camera camera = (Camera) nullHolder.get();
        if (camera.isFinishing()) {
            Log.d(TAG, "activity is finishing, the content of BaseModuleHolder is set to null");
            return NullHolder.ofNullable(null, 235);
        }
        camera.changeRequestOrientation();
        if (this.baseModule.isDeparted()) {
            return NullHolder.ofNullable(this.baseModule, 225);
        }
        reconfigureData();
        return NullHolder.ofNullable(this.baseModule);
    }

    private void reconfigureData() {
        /*
        r10 = this;
        r0 = r10.mNeedReConfigureData;
        if (r0 != 0) goto L_0x0016;
    L_0x0004:
        r0 = com.android.camera.data.DataRepository.dataItemConfig();
        r0 = r0.editor();
        r1 = "pref_camera_zoom_key";
        r0 = r0.remove(r1);
        r0.apply();
        return;
    L_0x0016:
        com.android.camera.CameraSettings.upgradeGlobalPreferences();
        r0 = com.android.camera.data.DataRepository.dataItemGlobal();
        r1 = com.android.camera.data.DataRepository.dataItemRunning();
        r2 = com.android.camera.data.DataRepository.dataItemConfig();
        r3 = r0.getLastCameraId();
        r4 = r2.getComponentFlash();
        r5 = r2.editor();
        r6 = com.android.camera.data.DataRepository.getInstance();
        r6 = r6.backUp();
        r7 = "pref_camera_zoom_key";
        r7 = r5.remove(r7);
        r8 = "pref_camera_exposure_key";
        r7.remove(r8);
        r7 = r10.mTargetMode;
        r7 = r4.getPersistValue(r7);
        r8 = r4.isValidFlashValue(r7);
        if (r8 != 0) goto L_0x005a;
    L_0x0050:
        r7 = r10.mTargetMode;
        r7 = r4.getKey(r7);
        r5.remove(r7);
        goto L_0x0071;
    L_0x005a:
        r8 = "2";
        r7 = r7.equals(r8);
        if (r7 == 0) goto L_0x0071;
    L_0x0062:
        r7 = r10.mTargetMode;
        r7 = r4.getKey(r7);
        r8 = r10.mTargetMode;
        r8 = r4.getDefaultValue(r8);
        r5.putString(r7, r8);
    L_0x0071:
        r7 = r10.mTargetMode;
        r8 = 167; // 0xa7 float:2.34E-43 double:8.25E-322;
        if (r7 != r8) goto L_0x0096;
    L_0x0077:
        r7 = com.android.camera.CameraAppImpl.getAndroidContext();
        r9 = 2131296426; // 0x7f0900aa float:1.8210768E38 double:1.053000345E-314;
        r7 = r7.getString(r9);
        r9 = "pref_qc_camera_iso_key";
        r7 = r2.getString(r9, r7);
        r9 = 2131623962; // 0x7f0e001a float:1.887509E38 double:1.0531621695E-314;
        r7 = com.android.camera.Util.isStringValueContained(r7, r9);
        if (r7 != 0) goto L_0x0096;
    L_0x0091:
        r7 = "pref_qc_camera_iso_key";
        r5.remove(r7);
    L_0x0096:
        r7 = com.mi.config.b.gP();
        if (r7 != 0) goto L_0x00a7;
    L_0x009c:
        r7 = "pref_focus_position_key";
        r5.remove(r7);
        r7 = "pref_qc_camera_exposuretime_key";
        r5.remove(r7);
        goto L_0x00c1;
    L_0x00a7:
        r7 = r10.mTargetMode;
        if (r7 != r8) goto L_0x00c1;
    L_0x00ab:
        r7 = new com.android.camera.data.data.config.ComponentManuallyET;
        r7.<init>(r2);
        r8 = r10.mTargetMode;
        r8 = r7.getComponentValue(r8);
        r8 = r7.checkValueValid(r8);
        if (r8 != 0) goto L_0x00c1;
    L_0x00bc:
        r8 = r10.mTargetMode;
        r7.resetComponentValue(r8);
    L_0x00c1:
        r7 = com.android.camera.Util.isLabOptionsVisible();
        if (r7 != 0) goto L_0x00f8;
    L_0x00c7:
        r7 = r0.editor();
        r8 = "pref_camera_facedetection_key";
        r7 = r7.remove(r8);
        r8 = "pref_camera_portrait_with_facebeauty_key";
        r7 = r7.remove(r8);
        r8 = "pref_camera_facedetection_auto_hidden_key";
        r7 = r7.remove(r8);
        r8 = "pref_camera_dual_enable_key";
        r7 = r7.remove(r8);
        r8 = "pref_camera_dual_sat_enable_key";
        r7 = r7.remove(r8);
        r8 = "pref_camera_mfnr_sat_enable_key";
        r7 = r7.remove(r8);
        r8 = "pref_camera_sr_enable_key";
        r7 = r7.remove(r8);
        r7.apply();
    L_0x00f8:
        r7 = "pref_camera_antibanding_key";
        r8 = "1";
        r7 = r0.getString(r7, r8);
        r7 = com.android.camera.Util.isValidValue(r7);
        if (r7 != 0) goto L_0x0113;
    L_0x0106:
        r7 = r0.editor();
        r8 = "pref_camera_antibanding_key";
        r7 = r7.remove(r8);
        r7.apply();
    L_0x0113:
        r7 = r10.mResetType;
        r8 = 1;
        r9 = 0;
        switch(r7) {
            case 2: goto L_0x0236;
            case 3: goto L_0x0162;
            case 4: goto L_0x011c;
            case 5: goto L_0x011c;
            case 6: goto L_0x0162;
            default: goto L_0x011a;
        };
    L_0x011a:
        goto L_0x0260;
    L_0x011c:
        r2 = r10.mTargetMode;
        r4 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        if (r2 == r4) goto L_0x014f;
    L_0x0122:
        switch(r2) {
            case 161: goto L_0x014f;
            case 162: goto L_0x0145;
            default: goto L_0x0125;
        };
    L_0x0125:
        switch(r2) {
            case 166: goto L_0x0141;
            case 167: goto L_0x013f;
            case 168: goto L_0x013a;
            case 169: goto L_0x013a;
            case 170: goto L_0x013a;
            case 171: goto L_0x012d;
            default: goto L_0x0128;
        };
    L_0x0128:
        r2 = r0.getCurrentCameraId();
        goto L_0x0154;
    L_0x012d:
        r2 = com.mi.config.b.hz();
        if (r2 == 0) goto L_0x0138;
    L_0x0133:
        r2 = r0.getCurrentCameraId();
        goto L_0x0154;
        goto L_0x0143;
    L_0x013a:
        r2 = r0.getCurrentCameraId();
        goto L_0x0154;
        goto L_0x0143;
    L_0x0143:
        r2 = r9;
        goto L_0x0154;
    L_0x0145:
        r2 = r0.getCurrentCameraId();
        if (r2 != 0) goto L_0x0154;
    L_0x014b:
        r6.removeOtherVideoMode();
        goto L_0x0154;
    L_0x014f:
        r2 = r0.getCurrentCameraId();
    L_0x0154:
        r0.setCameraIdTransient(r2);
        r4 = r10.mTargetMode;
        r4 = r0.getDataBackUpKey(r4);
        r6.revertRunning(r1, r4, r2);
        goto L_0x0260;
    L_0x0162:
        r10.resetFlash(r4, r5);
        r4 = r2.getComponentHdr();
        r10.resetHdr(r4, r5);
        r4 = r2.getComponentConfigBeauty();
        r10.resetVideoBeauty(r4, r5);
        r4 = r2.getComponentConfigUltraWide();
        r10.resetUltraWide(r4, r5);
        r4 = "pref_eye_light_type_key";
        r5.remove(r4);
        r4 = r2.getComponentConfigSlowMotion();
        r7 = r10.mTargetMode;
        r4 = r4.getKey(r7);
        r5.remove(r4);
        r4 = r0.getCurrentCameraId();
        if (r4 != 0) goto L_0x01ad;
    L_0x0192:
        r4 = r2.getComponentConfigBeauty();
        r10.resetBeauty(r4, r5);
        r4 = r10.mTargetMode;
        r2 = r2.getComponentConfigBeautyBody();
        r10.resetBeautyBody(r4, r2, r5);
        r2 = com.android.camera.data.DataRepository.provider();
        r2 = r2.dataConfig(r8);
        r2 = (com.android.camera.data.data.config.DataItemConfig) r2;
        goto L_0x01b7;
    L_0x01ad:
        r2 = com.android.camera.data.DataRepository.provider();
        r2 = r2.dataConfig(r9);
        r2 = (com.android.camera.data.data.config.DataItemConfig) r2;
    L_0x01b7:
        r4 = r2.editor();
        r7 = r2.getComponentFlash();
        r10.resetFlash(r7, r4);
        r7 = r2.getComponentHdr();
        r10.resetHdr(r7, r4);
        r2 = r2.getComponentConfigBeauty();
        r10.resetVideoBeauty(r2, r4);
        r2 = "pref_eye_light_type_key";
        r4.remove(r2);
        r4.apply();
        r1.clearArrayMap();
        r6.clearBackUp();
        r1 = com.android.camera.data.DataRepository.dataItemFeature();
        r1 = r1.fH();
        if (r1 == 0) goto L_0x0260;
    L_0x01e8:
        r1 = com.android.camera.data.DataRepository.dataItemLive();
        r1 = r1.editor();
        r2 = "pref_live_music_path_key";
        r1 = r1.remove(r2);
        r2 = "pref_live_music_hint_key";
        r1 = r1.remove(r2);
        r2 = "pref_live_sticker_key";
        r1 = r1.remove(r2);
        r2 = "pref_live_sticker_name_key";
        r1 = r1.remove(r2);
        r2 = "pref_live_sticker_hint_key";
        r1 = r1.remove(r2);
        r2 = "pref_live_speed_key";
        r1 = r1.remove(r2);
        r2 = "key_live_filter";
        r1 = r1.remove(r2);
        r2 = "key_live_shrink_face_ratio";
        r1 = r1.remove(r2);
        r2 = "key_live_enlarge_eye_ratio";
        r1 = r1.remove(r2);
        r2 = "key_live_smooth_strength";
        r1 = r1.remove(r2);
        r2 = "pref_live_beauty_status";
        r1 = r1.remove(r2);
        r1.apply();
        goto L_0x0260;
    L_0x0236:
        r2 = r10.mTargetMode;
        r2 = r0.getDataBackUpKey(r2);
        r7 = r0.getCurrentCameraId();
        r6.revertRunning(r1, r2, r7);
        r1 = r10.mTargetMode;
        r1 = r4.getPersistValue(r1);
        r2 = "2";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0260;
    L_0x0251:
        r1 = r10.mTargetMode;
        r1 = r4.getKey(r1);
        r2 = r10.mTargetMode;
        r2 = r4.getDefaultValue(r2);
        r5.putString(r1, r2);
    L_0x0260:
        r1 = com.android.camera.data.DataRepository.dataItemFeature();
        r1 = r1.fJ();
        r2 = r10.mResetType;
        r4 = 4;
        if (r2 != r4) goto L_0x0275;
    L_0x026d:
        r0 = r0.getCurrentCameraId();
        if (r3 != r0) goto L_0x0275;
        r1 = r9;
    L_0x0275:
        if (r1 == 0) goto L_0x027c;
    L_0x0277:
        r0 = "pref_lens_dirty_detect_enabled_key";
        r5.putBoolean(r0, r8);
    L_0x027c:
        r5.apply();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.loader.FunctionCameraPrepare.reconfigureData():void");
    }

    private void resetBeautyBody(int i, ComponentConfigBeautyBody componentConfigBeautyBody, ProviderEditor providerEditor) {
        componentConfigBeautyBody.resetBeautyBody(i, providerEditor);
    }

    private void resetFlash(ComponentConfigFlash componentConfigFlash, ProviderEditor providerEditor) {
        if (!componentConfigFlash.getPersistValue(this.mTargetMode).equals("3")) {
            providerEditor.putString(componentConfigFlash.getKey(this.mTargetMode), componentConfigFlash.getDefaultValue(this.mTargetMode));
        }
    }

    private void resetHdr(ComponentConfigHdr componentConfigHdr, ProviderEditor providerEditor) {
        String persistValue = componentConfigHdr.getPersistValue(this.mTargetMode);
        if (!persistValue.equals("auto") && !persistValue.equals("off")) {
            providerEditor.putString(componentConfigHdr.getKey(this.mTargetMode), componentConfigHdr.getDefaultValue(this.mTargetMode));
        }
    }

    private void resetBeauty(ComponentConfigBeauty componentConfigBeauty, ProviderEditor providerEditor) {
        String persistValue = componentConfigBeauty.getPersistValue(this.mTargetMode);
        String defaultValue = componentConfigBeauty.getDefaultValue(this.mTargetMode);
        if (!TextUtils.equals(persistValue, defaultValue)) {
            providerEditor.putString(componentConfigBeauty.getKey(this.mTargetMode), defaultValue);
        }
    }

    private void resetVideoBeauty(ComponentConfigBeauty componentConfigBeauty, ProviderEditor providerEditor) {
        String persistValue = componentConfigBeauty.getPersistValue(162);
        String defaultValue = componentConfigBeauty.getDefaultValue(162);
        if (!TextUtils.equals(persistValue, defaultValue)) {
            providerEditor.putString(componentConfigBeauty.getKey(162), defaultValue);
        }
    }

    private void resetUltraWide(ComponentConfigUltraWide componentConfigUltraWide, ProviderEditor providerEditor) {
        if (componentConfigUltraWide != null) {
            componentConfigUltraWide.resetUltraWide(providerEditor);
        }
    }
}
