package com.android.camera.fragment.beauty;

import android.util.SparseArray;

public class BeautyFragmentManager {
    private SparseArray<IBeautyFragmentBusiness> mBeautyBusinessArray = new SparseArray();

    public IBeautyFragmentBusiness getBeautyFragmentBusiness(int i) {
        IBeautyFragmentBusiness iBeautyFragmentBusiness = (IBeautyFragmentBusiness) this.mBeautyBusinessArray.get(i);
        if (iBeautyFragmentBusiness != null) {
            return iBeautyFragmentBusiness;
        }
        Object frontFragmentBusiness;
        switch (i) {
            case 161:
                frontFragmentBusiness = new FrontFragmentBusiness();
                break;
            case 162:
                frontFragmentBusiness = new BackMainFragmentBusiness();
                break;
            case 163:
                frontFragmentBusiness = new LiveBeautyFragmentBusiness();
                break;
            default:
                frontFragmentBusiness = new FrontFragmentBusiness();
                break;
        }
        this.mBeautyBusinessArray.put(i, frontFragmentBusiness);
        return frontFragmentBusiness;
    }
}
