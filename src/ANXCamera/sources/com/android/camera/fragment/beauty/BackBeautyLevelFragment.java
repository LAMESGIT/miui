package com.android.camera.fragment.beauty;

import android.view.View;
import android.widget.AdapterView;
import com.android.camera.CameraSettings;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.beauty.SingleCheckAdapter.LevelItem;
import com.android.camera.module.ModuleManager;
import java.util.ArrayList;
import java.util.List;

public class BackBeautyLevelFragment extends BeautyLevelFragment {
    /* Access modifiers changed, original: protected */
    public List<LevelItem> initBeautyItems() {
        String[] stringArray = getResources().getStringArray(2131623978);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LevelItem(2130837714));
        arrayList.add(new LevelItem(stringArray[0]));
        arrayList.add(new LevelItem(stringArray[1]));
        arrayList.add(new LevelItem(stringArray[2]));
        arrayList.add(new LevelItem(stringArray[3]));
        arrayList.add(new LevelItem(stringArray[4]));
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void onLevelClick(AdapterView<?> adapterView, View view, int i, long j) {
        DataRepository.dataItemConfig().getComponentConfigBeauty().setComponentValue(ModuleManager.getActiveModuleIndex(), CameraSettings.beautyLevelToIntelligentValue(i));
        BeautyHelper.onBeautyChanged();
    }
}
