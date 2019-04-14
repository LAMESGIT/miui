package com.android.gallery3d.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;

abstract class CanvasTexture extends UploadedTexture {
    protected Canvas mCanvas;
    private final Config mConfig = Config.ARGB_8888;

    public abstract void onDraw(Canvas canvas, Bitmap bitmap);

    public CanvasTexture(int i, int i2) {
        setSize(i, i2);
        setOpaque(false);
    }

    /* Access modifiers changed, original: protected */
    public Bitmap onGetBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, this.mConfig);
        this.mCanvas = new Canvas(createBitmap);
        onDraw(this.mCanvas, createBitmap);
        return createBitmap;
    }

    /* Access modifiers changed, original: protected */
    public void onFreeBitmap(Bitmap bitmap) {
        if (!BasicTexture.inFinalizer()) {
            bitmap.recycle();
        }
    }
}
