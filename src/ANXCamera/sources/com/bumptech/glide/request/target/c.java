package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* compiled from: BitmapImageViewTarget */
public class c extends h<Bitmap> {
    public c(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public c(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: n */
    public void s(Bitmap bitmap) {
        ((ImageView) this.view).setImageBitmap(bitmap);
    }
}
