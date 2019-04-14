package com.android.camera.data.data.config;

import android.text.TextUtils;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.mi.config.b;
import java.util.ArrayList;
import java.util.List;

public class ComponentManuallyWB extends ComponentData {
    public static final String MANUAL_WHITEBALANCE_VALUE = "pref_qc_manual_whitebalance_k_value_key";

    public ComponentManuallyWB(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
    }

    public int getDisplayTitleString() {
        return 2131296357;
    }

    public String getKey(int i) {
        return CameraSettings.KEY_WHITE_BALANCE;
    }

    public String getDefaultValue(int i) {
        return "1";
    }

    /* Access modifiers changed, original: protected */
    public boolean checkValueValid(String str) {
        for (ComponentDataItem componentDataItem : getItems()) {
            if (TextUtils.equals(str, componentDataItem.mValue)) {
                return true;
            }
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void resetComponentValue(int i) {
        setComponentValue(i, getDefaultValue(i));
    }

    public List<ComponentDataItem> getItems() {
        if (this.mItems == null) {
            this.mItems = initItems();
        }
        return this.mItems;
    }

    private List<ComponentDataItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        arrayList.add(new ComponentDataItem(-1, -1, 2131296358, "1"));
        arrayList.add(new ComponentDataItem(2130837524, 2130837867, -2, "2"));
        arrayList.add(new ComponentDataItem(2130837526, 2130837873, -2, "5"));
        arrayList.add(new ComponentDataItem(2130837523, 2130837864, -2, "3"));
        arrayList.add(new ComponentDataItem(2130837522, 2130837861, -2, "6"));
        if (b.gP() && DataRepository.dataItemFeature().fA()) {
            arrayList.add(new ComponentDataItem(2130837525, 2130837870, -2, "manual"));
        }
        return arrayList;
    }

    public void setCustomWB(int i) {
        this.mParentDataItem.editor().putInt("pref_qc_manual_whitebalance_k_value_key", i).apply();
    }

    public int getCustomWB() {
        return this.mParentDataItem.getInt("pref_qc_manual_whitebalance_k_value_key", CameraAppImpl.getAndroidContext().getResources().getInteger(2131755018));
    }
}
