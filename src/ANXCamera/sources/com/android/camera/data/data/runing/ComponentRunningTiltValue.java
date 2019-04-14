package com.android.camera.data.data.runing;

import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import java.util.ArrayList;
import java.util.List;

public class ComponentRunningTiltValue extends ComponentData {
    public static final String TILT_CIRCLE = "circle";
    public static final String TILT_PARALLEL = "parallel";

    public ComponentRunningTiltValue(DataItemRunning dataItemRunning) {
        super(dataItemRunning);
    }

    public int getDisplayTitleString() {
        return 2131296654;
    }

    public String getKey(int i) {
        return "pref_camera_tilt_shift_key";
    }

    public String getDefaultValue(int i) {
        return TILT_CIRCLE;
    }

    public List<ComponentDataItem> getItems() {
        if (this.mItems == null) {
            this.mItems = initItems();
        }
        return this.mItems;
    }

    private List<ComponentDataItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ComponentDataItem(2130837727, 2130837727, 2131296596, TILT_CIRCLE));
        arrayList.add(new ComponentDataItem(2130837728, 2130837728, 2131296597, TILT_PARALLEL));
        return arrayList;
    }
}
