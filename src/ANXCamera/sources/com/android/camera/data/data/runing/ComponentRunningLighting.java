package com.android.camera.data.data.runing;

import com.android.camera.CameraSettings;
import com.android.camera.constant.LightingConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import java.util.ArrayList;
import java.util.List;

public class ComponentRunningLighting extends ComponentData {
    public ComponentRunningLighting(DataItemRunning dataItemRunning) {
        super(dataItemRunning);
    }

    public int getDisplayTitleString() {
        return 0;
    }

    public String getKey(int i) {
        return "pref_portrait_lighting";
    }

    public String getDefaultValue(int i) {
        return "0";
    }

    public List<ComponentDataItem> getItems() {
        if (this.mItems == null) {
            initItems();
        }
        return this.mItems;
    }

    public void initItems() {
        if (this.mItems == null) {
            this.mItems = new ArrayList();
        } else {
            this.mItems.clear();
        }
        this.mItems.add(new ComponentDataItem(2130837759, 2130837759, 2131296775, "0"));
        if (CameraSettings.isBackCamera() || Camera2DataContainer.getInstance().getBokehFrontCameraId() != -1) {
            this.mItems.add(new ComponentDataItem(2130837758, 2130837758, 2131296776, "1"));
        }
        this.mItems.add(new ComponentDataItem(2130837762, 2130837762, 2131296777, "2"));
        this.mItems.add(new ComponentDataItem(2130837757, 2130837757, 2131296778, "3"));
        this.mItems.add(new ComponentDataItem(2130837760, 2130837760, 2131296779, "4"));
        this.mItems.add(new ComponentDataItem(2130837761, 2130837761, 2131296782, "5"));
        this.mItems.add(new ComponentDataItem(2130837754, 2130837754, 2131296781, "6"));
        this.mItems.add(new ComponentDataItem(2130837756, 2130837756, 2131296780, LightingConstant.LIGHTING_LEAF));
        if (DataRepository.dataItemFeature().fc()) {
            this.mItems.add(new ComponentDataItem(2130837755, 2130837755, 2131296783, "8"));
        }
    }

    public boolean isSwitchOn(int i) {
        return getComponentValue(i).equals("0") ^ 1;
    }
}
