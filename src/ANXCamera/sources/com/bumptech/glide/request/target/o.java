package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

/* compiled from: ThumbnailImageViewTarget */
public abstract class o<T> extends h<T> {
    public abstract Drawable t(T t);

    public o(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public o(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    /* Access modifiers changed, original: protected */
    public void s(@Nullable T t) {
        LayoutParams layoutParams = ((ImageView) this.view).getLayoutParams();
        Drawable t2 = t(t);
        if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
            t2 = new g(t2, layoutParams.width, layoutParams.height);
        }
        ((ImageView) this.view).setImageDrawable(t2);
    }
}
