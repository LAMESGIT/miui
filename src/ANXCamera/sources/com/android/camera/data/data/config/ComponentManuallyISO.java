package com.android.camera.data.data.config;

import android.annotation.TargetApi;
import android.util.Range;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import com.android.camera2.CameraCapabilities;
import java.util.ArrayList;
import java.util.List;

@TargetApi(21)
public class ComponentManuallyISO extends ComponentData {
    private ComponentDataItem[] mFullItems;

    public ComponentManuallyISO(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
    }

    public int getDisplayTitleString() {
        return 2131296427;
    }

    public String getKey(int i) {
        return CameraSettings.KEY_QC_ISO;
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

    /* Access modifiers changed, original: protected */
    public boolean checkValueValid(String str) {
        return Util.isStringValueContained((Object) str, 2131623962);
    }

    /* Access modifiers changed, original: protected */
    public void resetComponentValue(int i) {
        setComponentValue(i, getDefaultValue(i));
    }

    public String getComponentValue(int i) {
        String componentValue = super.getComponentValue(i);
        List items = getItems();
        if (items.isEmpty()) {
            return componentValue;
        }
        String str = ((ComponentDataItem) items.get(items.size() - 1)).mValue;
        if (Integer.parseInt(componentValue) > Integer.parseInt(str)) {
            return str;
        }
        return componentValue;
    }

    private List<ComponentDataItem> initItems() {
        CameraCapabilities currentCameraCapabilities = Camera2DataContainer.getInstance().getCurrentCameraCapabilities();
        ArrayList arrayList = new ArrayList();
        ComponentDataItem[] fullItems = getFullItems();
        arrayList.add(fullItems[0]);
        Range isoRange = currentCameraCapabilities.getIsoRange();
        if (isoRange != null) {
            int intValue = ((Integer) isoRange.getLower()).intValue();
            int intValue2 = ((Integer) isoRange.getUpper()).intValue();
            for (int i = 1; i < fullItems.length; i++) {
                ComponentDataItem componentDataItem = fullItems[i];
                int parseInt = Integer.parseInt(componentDataItem.mValue);
                if (i == 0 || (intValue <= parseInt && parseInt <= intValue2)) {
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
        this.mFullItems = new ComponentDataItem[]{new ComponentDataItem(-1, -1, 2131296428, "0"), new ComponentDataItem(-1, -1, 2131296429, "100"), new ComponentDataItem(-1, -1, 2131296430, "200"), new ComponentDataItem(-1, -1, 2131296431, "400"), new ComponentDataItem(-1, -1, 2131296432, "800"), new ComponentDataItem(-1, -1, 2131296433, "1600"), new ComponentDataItem(-1, -1, 2131296434, "3200")};
        return this.mFullItems;
    }
}
