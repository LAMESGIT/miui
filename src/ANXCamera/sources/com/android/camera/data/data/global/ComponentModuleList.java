package com.android.camera.data.data.global;

import com.android.camera.CameraSettings;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.mi.config.b;
import java.util.ArrayList;
import java.util.List;

public class ComponentModuleList extends ComponentData {
    private int mIntentType;

    public ComponentModuleList(DataItemGlobal dataItemGlobal) {
        super(dataItemGlobal);
    }

    public int getDisplayTitleString() {
        return 0;
    }

    public String getKey(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DataItemGlobal.DATA_COMMON_CURRENT_MODE);
        stringBuilder.append(this.mIntentType);
        return stringBuilder.toString();
    }

    public String getDefaultValue(int i) {
        return null;
    }

    public List<ComponentDataItem> getItems() {
        if (this.mItems == null) {
            this.mItems = initItems();
        }
        return this.mItems;
    }

    public void reInit() {
    }

    private List<ComponentDataItem> initItems() {
        if (this.mIntentType != -1) {
            ArrayList arrayList = new ArrayList();
            if (!DataRepository.dataItemFeature().fH() && this.mIntentType == 0) {
                arrayList.add(new ComponentDataItem(-1, -1, 2131296704, String.valueOf(161)));
            }
            if (this.mIntentType == 0 && DataRepository.dataItemFeature().fv()) {
                arrayList.add(new ComponentDataItem(-1, -1, 2131296705, String.valueOf(172)));
            }
            if (DataRepository.dataItemFeature().fH() && this.mIntentType == 0) {
                arrayList.add(new ComponentDataItem(-1, -1, 2131296704, String.valueOf(174)));
            }
            if (this.mIntentType == 2 || this.mIntentType == 0) {
                arrayList.add(new ComponentDataItem(-1, -1, 2131296706, String.valueOf(162)));
            }
            if (this.mIntentType == 3) {
                arrayList.add(new ComponentDataItem(-1, -1, 2131296707, String.valueOf(163)));
            } else if (this.mIntentType == 1 || this.mIntentType == 0) {
                arrayList.add(new ComponentDataItem(-1, -1, 2131296707, String.valueOf(163)));
                if (b.isSupportedPortrait()) {
                    arrayList.add(new ComponentDataItem(-1, -1, 2131296712, String.valueOf(171)));
                }
                if (DataRepository.dataItemFeature().fk() && this.mIntentType == 0) {
                    arrayList.add(new ComponentDataItem(-1, -1, 2131296416, String.valueOf(173)));
                }
                arrayList.add(new ComponentDataItem(-1, -1, 2131296709, String.valueOf(165)));
                if (DataRepository.dataItemFeature().fW() && this.mIntentType == 0) {
                    arrayList.add(new ComponentDataItem(-1, -1, 2131296710, String.valueOf(166)));
                }
                arrayList.add(new ComponentDataItem(-1, -1, 2131296856, String.valueOf(167)));
            }
            return arrayList;
        }
        throw new RuntimeException("parse intent first!");
    }

    public int getMode(int i) {
        return Integer.valueOf(((ComponentDataItem) this.mItems.get(i)).mValue).intValue();
    }

    public void setIntentType(int i) {
        this.mIntentType = i;
        if (this.mItems != null) {
            this.mItems.clear();
        }
        this.mItems = initItems();
    }

    public boolean needShowLiveRedDot() {
        return CameraSettings.isLiveModuleClicked() ^ 1;
    }
}
