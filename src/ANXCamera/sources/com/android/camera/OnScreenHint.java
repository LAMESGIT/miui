package com.android.camera;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.TextView;

public class OnScreenHint {
    private ViewGroup mHintView;

    public OnScreenHint(ViewGroup viewGroup) {
        this.mHintView = viewGroup;
    }

    public void show() {
        Util.fadeIn(this.mHintView);
    }

    public int getHintViewVisibility() {
        return this.mHintView.getVisibility();
    }

    public void cancel() {
        Util.fadeOut(this.mHintView);
    }

    public static OnScreenHint makeText(Activity activity, CharSequence charSequence) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(2131558600);
        OnScreenHint onScreenHint = new OnScreenHint(viewGroup);
        ((TextView) viewGroup.findViewById(2131558601)).setText(charSequence);
        return onScreenHint;
    }

    public void setText(CharSequence charSequence) {
        if (this.mHintView != null) {
            TextView textView = (TextView) this.mHintView.findViewById(2131558601);
            if (textView != null) {
                textView.setText(charSequence);
                return;
            }
            throw new RuntimeException("This OnScreenHint was not created with OnScreenHint.makeText()");
        }
        throw new RuntimeException("This OnScreenHint was not created with OnScreenHint.makeText()");
    }
}
