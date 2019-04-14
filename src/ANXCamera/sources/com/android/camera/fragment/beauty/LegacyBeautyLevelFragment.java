package com.android.camera.fragment.beauty;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.fragment.beauty.SingleCheckAdapter.LevelItem;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class LegacyBeautyLevelFragment extends BeautyLevelFragment {
    /* Access modifiers changed, original: protected */
    public List<LevelItem> initBeautyItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LevelItem(2130837714));
        arrayList.add(new LevelItem(2130837566));
        arrayList.add(new LevelItem(2130837567));
        arrayList.add(new LevelItem(2130837568));
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public int provideItemHorizontalMargin() {
        return getResources().getDimensionPixelSize(2131361903);
    }

    /* Access modifiers changed, original: protected */
    public OnItemClickListener initOnItemClickListener() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LegacyBeautyLevelFragment.this.onLevelClick(adapterView, view, i, j);
            }
        };
    }

    /* Access modifiers changed, original: protected */
    public int beautyLevelToPosition(int i, int i2) {
        return Util.clamp(i, 0, i2);
    }

    /* Access modifiers changed, original: 0000 */
    public void onLevelClick(AdapterView<?> adapterView, View view, int i, long j) {
        CameraSettings.setFaceBeautyLevel(i);
        BeautyHelper.onBeautyChanged();
    }
}
