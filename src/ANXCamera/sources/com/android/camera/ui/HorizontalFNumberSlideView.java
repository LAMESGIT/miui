package com.android.camera.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;

public class HorizontalFNumberSlideView extends HorizontalSlideView {
    private int mLineColorSelected;
    private float mLineSelectedHalfHeight;
    private int mLineWidth;
    private Paint mPaint;

    /* Access modifiers changed, original: protected */
    public void init(Context context) {
        super.init(context);
        this.mLineColorSelected = context.getColor(2131427433);
        this.mLineSelectedHalfHeight = ((float) context.getResources().getDimensionPixelSize(2131362029)) / 2.0f;
        this.mLineWidth = context.getResources().getDimensionPixelSize(2131362030);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth((float) this.mLineWidth);
        this.mPaint.setColor(this.mLineColorSelected);
    }

    public HorizontalFNumberSlideView(Context context) {
        super(context);
        init(context);
    }

    public HorizontalFNumberSlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public HorizontalFNumberSlideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public boolean canPositionScroll() {
        CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
        if (cameraAction == null || !cameraAction.isDoingAction()) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.mOriginX;
        float height = ((float) getHeight()) / 2.0f;
        canvas.save();
        canvas.translate(f, height);
        canvas.drawLine(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, -this.mLineSelectedHalfHeight, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, this.mLineSelectedHalfHeight, this.mPaint);
        canvas.restore();
    }
}
