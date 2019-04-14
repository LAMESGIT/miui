package com.android.camera.fragment.beauty;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.camera.fragment.beauty.MakeupSingleCheckAdapter.MakeupItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.MakeupProtocol;
import com.miui.filtersdk.beauty.BeautyParameterType;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class LegacyMakeupParamsFragment extends MakeupParamsFragment {
    /* Access modifiers changed, original: protected */
    public List<MakeupItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MakeupItem(2130837887, 2131296728));
        arrayList.add(new MakeupItem(2130837900, 2131296725));
        arrayList.add(new MakeupItem(2130837902, 2131296726));
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public OnItemClickListener initOnItemClickListener() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BeautyParameterType beautyParameterType;
                LegacyMakeupParamsFragment.this.mSelectedParam = i;
                switch (i) {
                    case 0:
                        beautyParameterType = BeautyParameterType.SHRINK_FACE_RATIO;
                        break;
                    case 1:
                        beautyParameterType = BeautyParameterType.WHITEN_STRENGTH;
                        break;
                    case 2:
                        beautyParameterType = BeautyParameterType.SMOOTH_STRENGTH;
                        break;
                    default:
                        beautyParameterType = BeautyParameterType.WHITEN_STRENGTH;
                        break;
                }
                BeautyHelper.setType(beautyParameterType);
                MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
                if (makeupProtocol != null) {
                    makeupProtocol.onMakeupItemSelected();
                }
            }
        };
    }
}
