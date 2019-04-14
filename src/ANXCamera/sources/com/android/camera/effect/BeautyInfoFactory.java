package com.android.camera.effect;

import com.miui.filtersdk.filter.helper.FilterFactory;
import com.miui.filtersdk.filter.helper.FilterFactory.FilterScene;
import com.miui.filtersdk.filter.helper.FilterType;
import java.util.ArrayList;
import java.util.Collections;

public class BeautyInfoFactory {
    public static ArrayList<FilterInfo> initBeautyFilterInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FilterInfo(FilterInfo.FILTER_ID_NONE, 2131296361, 2130837933, 0));
        FilterType[] filtersByScene = FilterFactory.getFiltersByScene(FilterScene.BEAUTY);
        int length = filtersByScene.length;
        int i = 0;
        int i2 = i;
        int i3 = i2;
        int i4 = i3;
        while (i < length) {
            int i5;
            int i6;
            FilterType filterType = filtersByScene[i];
            switch (filterType) {
                case B_NATURE:
                    i2 = 10;
                    i3 = 2131296381;
                    i4 = 2130837932;
                    break;
                case B_JAPANESE:
                    i2 = 20;
                    i3 = 2131296384;
                    i4 = 2130837924;
                    break;
                case B_PINK:
                    i2 = 30;
                    i3 = 2131296383;
                    i4 = 2130837934;
                    break;
                case B_STORY:
                    i2 = 40;
                    i3 = 2131296390;
                    i4 = 2130837937;
                    break;
                case B_FAIRYTALE:
                    i2 = 50;
                    i3 = 2131296382;
                    i4 = 2130837923;
                    break;
                case B_MAZE:
                    i2 = 80;
                    i3 = 2131296387;
                    i4 = 2130837929;
                    break;
                case B_RIDDLE:
                    i2 = 100;
                    i3 = 2131296391;
                    i4 = 2130837935;
                    break;
                case B_MOVIE:
                    i2 = 110;
                    i3 = 2131296392;
                    i4 = 2130837931;
                    break;
                case B_M_TEA:
                    i2 = 120;
                    i3 = 2131296393;
                    i4 = 2130837928;
                    break;
                case B_M_LILT:
                    i2 = 130;
                    i3 = 2131296394;
                    i4 = 2130837926;
                    break;
                case B_M_SEPIA:
                    i2 = 140;
                    i3 = 2131296395;
                    i4 = 2130837927;
                    break;
                case B_M_WHITEANDBLACK:
                    i2 = 150;
                    i3 = 2131296389;
                    i4 = 2130837925;
                    break;
                default:
                    i5 = i2;
                    i6 = i3;
                    i2 = i4;
                    break;
            }
            i5 = i3;
            i6 = i4;
            if (i5 == 0 || i6 == 0) {
                i3 = i6;
            } else {
                arrayList.add(new FilterInfo(2, filterType.ordinal(), i5, i6, i2));
                i3 = 0;
                i5 = i3;
            }
            i++;
            i4 = i2;
            i2 = i5;
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static ArrayList<FilterInfo> initIndiaBeautyFilterInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FilterInfo(FilterInfo.FILTER_ID_NONE, 2131296361, 2130837946, 0));
        FilterType[] filtersByScene = FilterFactory.getFiltersByScene(FilterScene.BEAUTY_INDIA);
        int length = filtersByScene.length;
        int i = 0;
        int i2 = i;
        int i3 = i2;
        int i4 = i3;
        while (i < length) {
            int i5;
            int i6;
            FilterType filterType = filtersByScene[i];
            switch (filterType) {
                case BI_SUNNY:
                    i2 = 10;
                    i3 = 2131296913;
                    i4 = 2130837952;
                    break;
                case BI_PINK:
                    i2 = 20;
                    i3 = 2131296914;
                    i4 = 2130837947;
                    break;
                case BI_MEMORY:
                    i2 = 30;
                    i3 = 2131296915;
                    i4 = 2130837944;
                    break;
                case BI_STRONG:
                    i2 = 40;
                    i3 = 2131296916;
                    i4 = 2130837951;
                    break;
                case BI_WARM:
                    i2 = 50;
                    i3 = 2131296917;
                    i4 = 2130837954;
                    break;
                case BI_RETRO:
                    i2 = 80;
                    i3 = 2131296920;
                    i4 = 2130837949;
                    break;
                case BI_ROMANTIC:
                    i2 = 100;
                    i3 = 2131296922;
                    i4 = 2130837950;
                    break;
                case BI_M_DUSK:
                    i2 = 120;
                    i3 = 2131296396;
                    i4 = 2130837940;
                    break;
                case BI_M_LILT:
                    i2 = 130;
                    i3 = 2131296394;
                    i4 = 2130837941;
                    break;
                case BI_M_TEA:
                    i2 = 140;
                    i3 = 2131296393;
                    i4 = 2130837943;
                    break;
                case BI_M_SEPIA:
                    i2 = 150;
                    i3 = 2131296395;
                    i4 = 2130837942;
                    break;
                case BI_M_WHITEANDBLACK:
                    i2 = 160;
                    i3 = 2131296389;
                    i4 = 2130837939;
                    break;
                default:
                    i5 = i2;
                    i6 = i3;
                    i2 = i4;
                    break;
            }
            i5 = i3;
            i6 = i4;
            if (i5 == 0 || i6 == 0) {
                i3 = i6;
            } else {
                arrayList.add(new FilterInfo(2, filterType.ordinal(), i5, i6, i2));
                i3 = 0;
                i5 = i3;
            }
            i++;
            i4 = i2;
            i2 = i5;
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static int getFilterDegree(FilterType filterType) {
        int i = AnonymousClass1.$SwitchMap$com$miui$filtersdk$filter$helper$FilterType[filterType.ordinal()];
        if (i == 1) {
            return 70;
        }
        switch (i) {
            case 25:
                return 60;
            case 26:
                return 70;
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                return 80;
            default:
                return 100;
        }
    }

    public static int getIndiaFilterDegree(FilterType filterType) {
        int i = AnonymousClass1.$SwitchMap$com$miui$filtersdk$filter$helper$FilterType[filterType.ordinal()];
        if (i == 13 || i == 16) {
            return 80;
        }
        switch (i) {
            case 25:
                return 60;
            case 26:
                return 70;
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                return 80;
            case 33:
                return 70;
            default:
                return 100;
        }
    }
}
