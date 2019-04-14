package com.android.gallery3d.ui;

import android.graphics.Bitmap;

public class BitmapTexture extends UploadedTexture {
    protected Bitmap mContentBitmap;

    public BitmapTexture(Bitmap bitmap) {
        this(bitmap, false);
    }

    private BitmapTexture(Bitmap bitmap, boolean z) {
        super(z);
        z = (bitmap == null || bitmap.isRecycled()) ? false : true;
        Utils.assertTrue(z);
        this.mContentBitmap = bitmap;
    }

    /* Access modifiers changed, original: protected */
    public void onFreeBitmap(Bitmap bitmap) {
    }

    /* Access modifiers changed, original: protected */
    public Bitmap onGetBitmap() {
        return this.mContentBitmap;
    }

    public Bitmap getBitmap() {
        return this.mContentBitmap;
    }
}
