package com.android.camera.data.data.config;

import android.annotation.TargetApi;
import android.text.TextUtils;
import android.util.SparseArray;
import com.android.camera.CameraSettings;
import com.android.camera.ThermalDetector;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera.data.data.runing.DataItemRunning;
import com.android.camera2.CameraCapabilities;
import com.mi.config.b;
import java.util.ArrayList;
import java.util.List;

@TargetApi(21)
public class ComponentConfigFlash extends ComponentData {
    public static final String FLASH_VALUE_AUTO = "3";
    public static final String FLASH_VALUE_OFF = "0";
    public static final String FLASH_VALUE_ON = "1";
    public static final String FLASH_VALUE_SCREEN_LIGHT_AUTO = "103";
    public static final String FLASH_VALUE_SCREEN_LIGHT_ON = "101";
    public static final String FLASH_VALUE_TORCH = "2";
    private SparseArray<String> mFlashValuesForSceneMode;
    private boolean mIsClosed;
    private boolean mIsHardwareSupported;

    public boolean isClosed() {
        return this.mIsClosed;
    }

    public void setClosed(boolean z) {
        this.mIsClosed = z;
    }

    public ComponentConfigFlash(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
        this.mFlashValuesForSceneMode = new SparseArray();
        this.mItems = new ArrayList();
        this.mItems.add(new ComponentDataItem(getFlashOffRes(), getFlashOffRes(), 2131296354, "0"));
    }

    public int getDisplayTitleString() {
        return 2131296351;
    }

    public String getKey(int i) {
        if (!(i == 172 || i == 174)) {
            switch (i) {
                case 160:
                    throw new RuntimeException("unspecified flash");
                case 161:
                case 162:
                    break;
                default:
                    switch (i) {
                        case 168:
                        case 169:
                        case 170:
                            break;
                        default:
                            return CameraSettings.KEY_FLASH_MODE;
                    }
            }
        }
        return CameraSettings.KEY_VIDEOCAMERA_FLASH_MODE;
    }

    public String getDefaultValue(int i) {
        return "0";
    }

    public String getComponentValue(int i) {
        if (isClosed()) {
            return "0";
        }
        if (isEmpty()) {
            return "0";
        }
        return getComponentValueInternal(i);
    }

    public boolean disableUpdate() {
        return ThermalDetector.getInstance().thermalConstrained();
    }

    public int getDisableReasonString() {
        if (CameraSettings.isFrontCamera()) {
            return 2131296807;
        }
        return 2131296808;
    }

    private String getComponentValueInternal(int i) {
        DataItemRunning dataItemRunning = DataRepository.dataItemRunning();
        if (dataItemRunning.isSwitchOn("pref_camera_scenemode_setting_key")) {
            String flashModeByScene = CameraSettings.getFlashModeByScene(dataItemRunning.getComponentRunningSceneValue().getComponentValue(i));
            if (!TextUtils.isEmpty(flashModeByScene)) {
                return flashModeByScene;
            }
        }
        return super.getComponentValue(i);
    }

    public void setSceneModeFlashValue(int i, String str) {
        this.mFlashValuesForSceneMode.put(i, str);
    }

    public String getPersistValue(int i) {
        return super.getComponentValue(i);
    }

    public void setComponentValue(int i, String str) {
        setClosed(false);
        super.setComponentValue(i, str);
    }

    public List<ComponentDataItem> getItems() {
        return this.mItems;
    }

    public List<ComponentDataItem> reInit(int i, int i2, CameraCapabilities cameraCapabilities, ComponentConfigUltraWide componentConfigUltraWide) {
        if (this.mItems == null) {
            this.mItems = new ArrayList();
        } else {
            this.mItems.clear();
        }
        if ((i == 166 || i == 171 || i == 173) && i2 == 0) {
            return this.mItems;
        }
        this.mIsHardwareSupported = cameraCapabilities.isFlashSupported();
        if (this.mIsHardwareSupported) {
            this.mItems.add(new ComponentDataItem(getFlashOffRes(), getFlashOffRes(), 2131296354, "0"));
            if (!(i == 172 || i == 174)) {
                switch (i) {
                    case 161:
                    case 162:
                        break;
                    default:
                        switch (i) {
                            case 168:
                            case 169:
                            case 170:
                                break;
                            default:
                                this.mItems.add(new ComponentDataItem(getFlashAutoRes(), getFlashAutoRes(), 2131296352, "3"));
                                if (CameraSettings.isBackCamera()) {
                                    this.mItems.add(new ComponentDataItem(getFlashOnRes(), getFlashOnRes(), 2131296353, "1"));
                                }
                                if (!CameraSettings.isFrontCamera() || !b.hL()) {
                                    if (b.gT()) {
                                        this.mItems.add(new ComponentDataItem(getFlashTorchRes(), getFlashTorchRes(), 2131296355, "2"));
                                        break;
                                    }
                                }
                                this.mItems.add(new ComponentDataItem(getFlashOnRes(), getFlashOnRes(), 2131296353, "2"));
                                break;
                                break;
                        }
                }
            }
            this.mItems.add(new ComponentDataItem(getFlashTorchRes(), getFlashTorchRes(), 2131296355, "2"));
            return this.mItems;
        }
        if (i2 == 1 && b.hR() && (i == 163 || i == 165 || i == 171)) {
            this.mItems.add(new ComponentDataItem(getFlashOffRes(), getFlashOffRes(), 2131296354, "0"));
            this.mItems.add(new ComponentDataItem(getFlashAutoRes(), getFlashAutoRes(), 2131296352, FLASH_VALUE_SCREEN_LIGHT_AUTO));
            this.mItems.add(new ComponentDataItem(getFlashOnRes(), getFlashOnRes(), 2131296353, FLASH_VALUE_SCREEN_LIGHT_ON));
        }
        return this.mItems;
    }

    public int getValueSelectedDrawableIgnoreClose(int i) {
        String componentValue = getComponentValue(i);
        if ("1".equals(componentValue)) {
            return getFlashOnRes();
        }
        if ("3".equals(componentValue)) {
            return getFlashAutoRes();
        }
        if ("0".equals(componentValue)) {
            return getFlashOffRes();
        }
        if ("2".equals(componentValue)) {
            return CameraSettings.isFrontCamera() ? getFlashOnRes() : getFlashTorchRes();
        } else if (FLASH_VALUE_SCREEN_LIGHT_AUTO.equals(componentValue)) {
            return getFlashAutoRes();
        } else {
            if (FLASH_VALUE_SCREEN_LIGHT_ON.equals(componentValue)) {
                return getFlashOnRes();
            }
            return -1;
        }
    }

    private int getFlashOnRes() {
        return 2130837808;
    }

    private int getFlashOffRes() {
        return 2130837807;
    }

    private int getFlashAutoRes() {
        return 2130837806;
    }

    private int getFlashTorchRes() {
        return 2130837809;
    }

    public void clearClosed() {
        this.mIsClosed = false;
    }

    public int getValueSelectedStringIdIgnoreClose(int i) {
        String componentValue = getComponentValue(i);
        int i2 = 2131296477;
        if ("1".equals(componentValue)) {
            return 2131296477;
        }
        if ("3".equals(componentValue)) {
            return 2131296478;
        }
        if ("0".equals(componentValue)) {
            return 2131296479;
        }
        if ("2".equals(componentValue)) {
            if (!CameraSettings.isFrontCamera()) {
                i2 = 2131296480;
            }
            return i2;
        } else if (FLASH_VALUE_SCREEN_LIGHT_AUTO.equals(componentValue)) {
            return 2131296478;
        } else {
            if (FLASH_VALUE_SCREEN_LIGHT_ON.equals(componentValue)) {
                return 2131296477;
            }
            return -1;
        }
    }

    public boolean isValidFlashValue(String str) {
        return str.matches("^[0-9]+$");
    }

    public boolean isHardwareSupported() {
        return this.mIsHardwareSupported;
    }
}
