package com.android.camera.data.data.config;

import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera.data.data.runing.DataItemRunning;
import java.util.List;

public class ComponentManuallyDualZoom extends ComponentData {
    public ComponentManuallyDualZoom(DataItemRunning dataItemRunning) {
        super(dataItemRunning);
    }

    public int getDisplayTitleString() {
        return 2131296666;
    }

    public String getKey(int i) {
        return "pref_camera_zoom_dual_key";
    }

    public String getDefaultValue(int i) {
        return "0";
    }

    public List<ComponentDataItem> getItems() {
        return null;
    }
}
