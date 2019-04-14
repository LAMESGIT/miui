package com.android.camera.data.data.runing;

import com.android.camera.constant.CameraScene;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import java.util.ArrayList;
import java.util.List;

public class ComponentRunningSceneValue extends ComponentData {
    public ComponentRunningSceneValue(DataItemRunning dataItemRunning) {
        super(dataItemRunning);
    }

    public int getDisplayTitleString() {
        return 2131296412;
    }

    public String getKey(int i) {
        return "pref_camera_scenemode_key";
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

    private List<ComponentDataItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ComponentDataItem(2130837838, 2130837838, 2131296413, "0"));
        arrayList.add(new ComponentDataItem(2130837846, 2130837846, 2131296414, "3"));
        arrayList.add(new ComponentDataItem(2130837843, 2130837843, 2131296415, "4"));
        arrayList.add(new ComponentDataItem(2130837848, 2130837848, 2131296422, CameraScene.SPORTS));
        arrayList.add(new ComponentDataItem(2130837844, 2130837844, 2131296416, "5"));
        arrayList.add(new ComponentDataItem(2130837845, 2130837845, 2131296417, "6"));
        arrayList.add(new ComponentDataItem(2130837840, 2130837840, 2131296418, "8"));
        arrayList.add(new ComponentDataItem(2130837847, 2130837847, 2131296419, CameraScene.SNOW));
        arrayList.add(new ComponentDataItem(2130837849, 2130837849, 2131296420, CameraScene.SUNSET));
        arrayList.add(new ComponentDataItem(2130837841, 2130837841, 2131296421, CameraScene.FIREWORKS));
        return arrayList;
    }
}
