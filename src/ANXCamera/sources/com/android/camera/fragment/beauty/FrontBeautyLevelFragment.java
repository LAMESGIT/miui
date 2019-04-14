package com.android.camera.fragment.beauty;

import android.view.View;
import android.widget.AdapterView;
import com.android.camera.CameraSettings;
import com.android.camera.fragment.beauty.SingleCheckAdapter.LevelItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BottomMenuProtocol;
import com.mi.config.b;
import java.util.ArrayList;
import java.util.List;

public class FrontBeautyLevelFragment extends BeautyLevelFragment {
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
        setBeautyLevel(i);
    }

    private void setBeautyLevel(int i) {
        if (!b.hM()) {
            boolean isFaceBeautyOn = BeautyParameters.isFaceBeautyOn();
            BottomMenuProtocol bottomMenuProtocol = (BottomMenuProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(197);
            if (bottomMenuProtocol != null) {
                if (!isFaceBeautyOn && i > 0) {
                    bottomMenuProtocol.onBottomMenuAnimate(1, 160);
                } else if (isFaceBeautyOn && i == 0) {
                    bottomMenuProtocol.onBottomMenuAnimate(1, 161);
                }
            }
        }
        CameraSettings.setFaceBeautyLevel(i);
        BeautyHelper.onBeautyChanged();
    }
}
