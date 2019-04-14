package com.android.camera.watermark;

import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import java.util.List;

public class AgeGenderWaterMarkDrawable extends BaseWaterMarkDrawable {
    private static final int GENDER_FEMALE_RECT_COLOR = -1152383;
    private static final int GENDER_MALE_RECT_COLOR = -9455628;
    private Paint mFaceRectPaint;
    private Drawable mFemaleAgeInfoPop;
    private int mFemaleHonPadding;
    private Drawable mMaleAgeInfoPop;
    private int mMaleHonPadding;
    private Drawable mSexFemaleIc;
    private Drawable mSexMaleIc;

    public AgeGenderWaterMarkDrawable(List<WaterMarkData> list) {
        super(list);
    }

    /* Access modifiers changed, original: protected */
    public void initBeforeDraw() {
        this.mMaleAgeInfoPop = this.mContext.getResources().getDrawable(2130837918);
        this.mFemaleAgeInfoPop = this.mContext.getResources().getDrawable(2130837571);
        this.mSexMaleIc = this.mContext.getResources().getDrawable(2130837852);
        this.mSexFemaleIc = this.mContext.getResources().getDrawable(2130837851);
        this.mFaceRectPaint = new Paint();
        this.mFaceRectPaint.setAntiAlias(true);
        this.mFaceRectPaint.setStrokeWidth((float) this.mContext.getResources().getDimensionPixelSize(2131361871));
        this.mFaceRectPaint.setStyle(Style.STROKE);
        this.mFaceRectPaint.setColor(GENDER_MALE_RECT_COLOR);
        this.mMaleHonPadding = this.mContext.getResources().getDimensionPixelSize(2131361863);
        this.mFemaleHonPadding = this.mContext.getResources().getDimensionPixelSize(2131361864);
        this.mFacePopupBottom = (int) (((double) this.mMaleAgeInfoPop.getIntrinsicHeight()) * 0.12d);
    }

    /* Access modifiers changed, original: protected */
    public Paint getFaceRectPaint(WaterMarkData waterMarkData) {
        if (waterMarkData.isFemale()) {
            this.mFaceRectPaint.setColor(GENDER_FEMALE_RECT_COLOR);
        } else {
            this.mFaceRectPaint.setColor(GENDER_MALE_RECT_COLOR);
        }
        return this.mFaceRectPaint;
    }

    /* Access modifiers changed, original: protected */
    public int getHonPadding(WaterMarkData waterMarkData) {
        return waterMarkData.isFemale() ? this.mFemaleHonPadding : this.mMaleHonPadding;
    }

    /* Access modifiers changed, original: protected */
    public Drawable getTopBackgroundDrawable(WaterMarkData waterMarkData) {
        return waterMarkData.isFemale() ? this.mFemaleAgeInfoPop : this.mMaleAgeInfoPop;
    }

    /* Access modifiers changed, original: protected */
    public Drawable getTopIndicatorDrawable(WaterMarkData waterMarkData) {
        return waterMarkData.isFemale() ? this.mSexFemaleIc : this.mSexMaleIc;
    }
}
