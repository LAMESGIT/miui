package com.android.camera.watermark;

import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import com.android.camera.CameraAppImpl;
import java.util.List;

public class MagicMirrorWaterMarkDrawable extends BaseWaterMarkDrawable {
    private static final int MAGIC_MIRROR_RECT_COLOR = -18377;
    private Drawable mBeautyScoreIc;
    private Paint mFaceRectPaint;
    private int mHonPadding;
    private Drawable mMagicMirrorInfoPop;

    public MagicMirrorWaterMarkDrawable(List<WaterMarkData> list) {
        super(list);
    }

    /* Access modifiers changed, original: protected */
    public void initBeforeDraw() {
        this.mMagicMirrorInfoPop = this.mContext.getResources().getDrawable(2130837916);
        this.mBeautyScoreIc = this.mContext.getResources().getDrawable(2130837697);
        this.mFaceRectPaint = new Paint();
        this.mFaceRectPaint.setAntiAlias(true);
        this.mFaceRectPaint.setStrokeWidth((float) CameraAppImpl.getAndroidContext().getResources().getDimensionPixelSize(2131361871));
        this.mFaceRectPaint.setStyle(Style.STROKE);
        this.mFaceRectPaint.setColor(MAGIC_MIRROR_RECT_COLOR);
        this.mFacePopupBottom = (int) (((double) this.mMagicMirrorInfoPop.getIntrinsicHeight()) * 0.12d);
        this.mHonPadding = this.mContext.getResources().getDimensionPixelSize(2131361864);
        this.mVerPadding = this.mContext.getResources().getDimensionPixelSize(2131361865);
    }

    /* Access modifiers changed, original: protected */
    public Paint getFaceRectPaint(WaterMarkData waterMarkData) {
        return this.mFaceRectPaint;
    }

    /* Access modifiers changed, original: protected */
    public int getHonPadding(WaterMarkData waterMarkData) {
        return this.mHonPadding;
    }

    /* Access modifiers changed, original: protected */
    public Drawable getTopBackgroundDrawable(WaterMarkData waterMarkData) {
        return this.mMagicMirrorInfoPop;
    }

    /* Access modifiers changed, original: protected */
    public Drawable getTopIndicatorDrawable(WaterMarkData waterMarkData) {
        return this.mBeautyScoreIc;
    }
}
