package com.android.camera.fragment.beauty;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.camera.fragment.beauty.BeautyParameters.Type;
import com.android.camera.fragment.beauty.MakeupSingleCheckAdapter.MakeupItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.MakeupProtocol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemodelingParamsFragment extends MakeupParamsFragment {
    private static final HashMap<Type, MakeupItem> MAKEUP_ITEM_MAP = new HashMap();
    private List<Type> mBeautyTypes;

    static {
        MAKEUP_ITEM_MAP.put(Type.SHRINK_FACE_RATIO, new MakeupItem(2130837887, 2131296728, CameraBeautyParameterType.FACE_3D_SHRINK_FACE_RATIO));
        MAKEUP_ITEM_MAP.put(Type.ENLARGE_EYE_RATIO, new MakeupItem(2130837884, 2131296727, CameraBeautyParameterType.FACE_3D_ENLARGE_EYE_RATIO));
        MAKEUP_ITEM_MAP.put(Type.NOSE_RATIO, new MakeupItem(2130837896, 2131296729, CameraBeautyParameterType.FACE_3D_NOSE_RATIO));
        MAKEUP_ITEM_MAP.put(Type.RISORIUS_RATIO, new MakeupItem(2130837898, 2131296730, CameraBeautyParameterType.FACE_3D_RISORIUS_RATIO));
        MAKEUP_ITEM_MAP.put(Type.LIPS_RATIO, new MakeupItem(2130837891, 2131296731, CameraBeautyParameterType.FACE_3D_LIPS_RATIO));
        MAKEUP_ITEM_MAP.put(Type.CHIN_RATIO, new MakeupItem(2130837881, 2131296732, CameraBeautyParameterType.FACE_3D_CHIN_RATIO));
        MAKEUP_ITEM_MAP.put(Type.NECK_RATIO, new MakeupItem(2130837895, 2131296733, CameraBeautyParameterType.FACE_3D_NECK_RATIO));
        MAKEUP_ITEM_MAP.put(Type.SMILE_RATIO, new MakeupItem(2130837900, 2131296734, CameraBeautyParameterType.FACE_3D_SMILE_RATIO));
        MAKEUP_ITEM_MAP.put(Type.SLIM_NOSE_RATIO, new MakeupItem(2130837901, 2131296735, CameraBeautyParameterType.FACE_3D_SLIM_NOSE_RATIO));
    }

    /* Access modifiers changed, original: protected */
    public List<MakeupItem> initItems() {
        this.mBeautyTypes = new ArrayList();
        ArrayList arrayList = new ArrayList();
        if (BeautyHelper.getBeautyItems() == null || BeautyHelper.getBeautyItems().isEmpty()) {
            return arrayList;
        }
        for (Type type : BeautyHelper.getBeautyItems()) {
            if (MAKEUP_ITEM_MAP.containsKey(type)) {
                arrayList.add((MakeupItem) MAKEUP_ITEM_MAP.get(type));
                this.mBeautyTypes.add(type);
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public OnItemClickListener initOnItemClickListener() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                RemodelingParamsFragment.this.mSelectedParam = i;
                Type type = (Type) RemodelingParamsFragment.this.mBeautyTypes.get(i);
                BeautyHelper.setType(type);
                BeautyHelper.setCurrentBeautyParameterType(((MakeupItem) RemodelingParamsFragment.MAKEUP_ITEM_MAP.get(type)).getCameraBeautyParameterType());
                MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
                if (makeupProtocol != null) {
                    makeupProtocol.onMakeupItemSelected();
                }
            }
        };
    }

    /* Access modifiers changed, original: protected */
    public void reSelectItem() {
        if (this.mItemList != null && this.mSelectedParam < this.mItemList.size()) {
            BeautyHelper.setType(((MakeupItem) this.mItemList.get(this.mSelectedParam)).getCameraBeautyParameterType().beautyParamType);
        }
    }
}
