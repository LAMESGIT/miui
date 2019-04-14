package com.android.camera.data.data.config;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import com.android.camera.CameraSettings;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import java.util.ArrayList;
import java.util.List;

public class ComponentManuallyFocus extends ComponentData {
    public ComponentManuallyFocus(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
    }

    public int getDisplayTitleString() {
        return 2131296613;
    }

    public String getKey(int i) {
        return CameraSettings.KEY_QC_FOCUS_POSITION;
    }

    public String getDefaultValue(int i) {
        return "1000";
    }

    public List<ComponentDataItem> getItems() {
        if (this.mItems == null) {
            this.mItems = initItems();
        }
        return this.mItems;
    }

    private List<ComponentDataItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ComponentDataItem(-1, -1, 2131296340, "1000"));
        arrayList.add(new ComponentDataItem(-1, -1, 2131296340, "manual"));
        return arrayList;
    }

    @StringRes
    public int getValueDisplayString(int i) {
        if (Integer.valueOf(getComponentValue(i)).intValue() == 1000) {
            return 2131296340;
        }
        return -2;
    }

    @DrawableRes
    public int getValueSelectedDrawable(int i) {
        i = Integer.valueOf(getComponentValue(i)).intValue();
        if (i == 1000) {
            return -1;
        }
        double d = (double) i;
        if (d >= 600.0d) {
            return 2130837748;
        }
        if (d >= 200.0d) {
            return 2130837749;
        }
        return 2130837747;
    }
}
