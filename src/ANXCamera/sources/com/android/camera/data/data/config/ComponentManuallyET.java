package com.android.camera.data.data.config;

import android.annotation.TargetApi;
import android.text.TextUtils;
import android.util.Range;
import com.android.camera.CameraSettings;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera.log.Log;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import com.android.camera2.CameraCapabilities;
import java.util.ArrayList;
import java.util.List;

public class ComponentManuallyET extends ComponentData {
    private static final String TAG = ComponentManuallyET.class.getSimpleName();
    private ComponentDataItem[] mFullItems;

    public ComponentManuallyET(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
    }

    public int getDisplayTitleString() {
        return 2131296565;
    }

    public String getKey(int i) {
        return CameraSettings.KEY_QC_EXPOSURETIME;
    }

    public String getDefaultValue(int i) {
        return "0";
    }

    public List<ComponentDataItem> getItems() {
        if (this.mItems == null) {
            this.mItems = initItems();
        }
        return this.mItems;
    }

    public boolean checkValueValid(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (ComponentDataItem componentDataItem : getFullItems()) {
                if (componentDataItem.mValue.equals(str)) {
                    return true;
                }
            }
        }
        Log.d(TAG, "checkValueValid: invalid value!");
        return false;
    }

    public void resetComponentValue(int i) {
        super.resetComponentValue(i);
        setComponentValue(i, getDefaultValue(i));
    }

    public int getValueDisplayString(int i) {
        String componentValue = getComponentValue(i);
        for (ComponentDataItem componentDataItem : getFullItems()) {
            if (componentDataItem.mValue.equals(componentValue)) {
                return componentDataItem.mDisplayNameRes;
            }
        }
        throw new IllegalArgumentException("invalid value");
    }

    public String getComponentValue(int i) {
        String componentValue = super.getComponentValue(i);
        List items = getItems();
        if (items.isEmpty()) {
            return componentValue;
        }
        String str = ((ComponentDataItem) items.get(items.size() - 1)).mValue;
        if (Long.parseLong(componentValue) <= Long.parseLong(str)) {
            return componentValue;
        }
        setComponentValue(i, str);
        return str;
    }

    @TargetApi(21)
    private List<ComponentDataItem> initItems() {
        ArrayList arrayList = new ArrayList();
        CameraCapabilities currentCameraCapabilities = Camera2DataContainer.getInstance().getCurrentCameraCapabilities();
        if (currentCameraCapabilities == null) {
            return arrayList;
        }
        Range exposureTimeRange = currentCameraCapabilities.getExposureTimeRange();
        ComponentDataItem[] fullItems = getFullItems();
        arrayList.add(fullItems[0]);
        if (exposureTimeRange != null) {
            long longValue = ((Long) exposureTimeRange.getLower()).longValue();
            long longValue2 = ((Long) exposureTimeRange.getUpper()).longValue();
            for (int i = 1; i < fullItems.length; i++) {
                ComponentDataItem componentDataItem = fullItems[i];
                long parseLong = Long.parseLong(componentDataItem.mValue);
                if (longValue <= parseLong && parseLong <= longValue2) {
                    arrayList.add(componentDataItem);
                }
            }
        }
        return arrayList;
    }

    private ComponentDataItem[] getFullItems() {
        if (this.mFullItems != null) {
            return this.mFullItems;
        }
        this.mFullItems = new ComponentDataItem[]{new ComponentDataItem(-1, -1, 2131296567, "0"), new ComponentDataItem(-1, -1, 2131296583, "1000000"), new ComponentDataItem(-1, -1, 2131296582, "2000000"), new ComponentDataItem(-1, -1, 2131296581, "4000000"), new ComponentDataItem(-1, -1, 2131296580, "8000000"), new ComponentDataItem(-1, -1, 2131296579, "16667000"), new ComponentDataItem(-1, -1, 2131296578, "33333000"), new ComponentDataItem(-1, -1, 2131296577, "66667000"), new ComponentDataItem(-1, -1, 2131296576, "125000000"), new ComponentDataItem(-1, -1, 2131296575, "250000000"), new ComponentDataItem(-1, -1, 2131296574, "500000000"), new ComponentDataItem(-1, -1, 2131296573, "1000000000"), new ComponentDataItem(-1, -1, 2131296572, "2000000000"), new ComponentDataItem(-1, -1, 2131296571, "4000000000"), new ComponentDataItem(-1, -1, 2131296570, "8000000000"), new ComponentDataItem(-1, -1, 2131296569, "16000000000"), new ComponentDataItem(-1, -1, 2131296568, "32000000000")};
        return this.mFullItems;
    }
}
