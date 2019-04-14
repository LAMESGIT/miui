package com.android.camera;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.camera.ui.Rotatable;
import com.android.camera.ui.RotateLayout;

public class RotateDialogController implements Rotatable {
    private static final long ANIM_DURATION = 150;
    private static final String TAG = "RotateDialogController";
    private Activity mActivity;
    private View mDialogRootLayout;
    private Animation mFadeInAnim;
    private Animation mFadeOutAnim;
    private int mLayoutResourceID;
    private RotateLayout mRotateDialog;
    private TextView mRotateDialogButton1;
    private TextView mRotateDialogButton2;
    private View mRotateDialogButtonLayout;
    private ProgressBar mRotateDialogSpinner;
    private TextView mRotateDialogText;
    private TextView mRotateDialogTitle;
    private View mRotateDialogTitleLayout;

    public RotateDialogController(Activity activity, int i) {
        this.mActivity = activity;
        if (i == 0) {
            i = 2130968640;
        }
        this.mLayoutResourceID = i;
    }

    private void inflateDialogLayout() {
        if (this.mDialogRootLayout == null) {
            View inflate = this.mActivity.getLayoutInflater().inflate(this.mLayoutResourceID, (ViewGroup) this.mActivity.getWindow().getDecorView());
            this.mDialogRootLayout = inflate.findViewById(2131558613);
            this.mRotateDialog = (RotateLayout) inflate.findViewById(2131558614);
            this.mRotateDialogTitleLayout = inflate.findViewById(2131558615);
            this.mRotateDialogButtonLayout = inflate.findViewById(2131558619);
            this.mRotateDialogTitle = (TextView) inflate.findViewById(2131558616);
            this.mRotateDialogSpinner = (ProgressBar) inflate.findViewById(2131558617);
            this.mRotateDialogText = (TextView) inflate.findViewById(2131558618);
            this.mRotateDialogButton1 = (Button) inflate.findViewById(2131558621);
            this.mRotateDialogButton2 = (Button) inflate.findViewById(2131558620);
            this.mFadeInAnim = AnimationUtils.loadAnimation(this.mActivity, 17432576);
            this.mFadeOutAnim = AnimationUtils.loadAnimation(this.mActivity, 17432577);
            this.mFadeInAnim.setDuration(ANIM_DURATION);
            this.mFadeOutAnim.setDuration(ANIM_DURATION);
        }
    }

    public void setOrientation(int i, boolean z) {
        inflateDialogLayout();
        this.mRotateDialog.setOrientation(i, z);
    }

    public void resetRotateDialog() {
        inflateDialogLayout();
        this.mRotateDialogTitleLayout.setVisibility(8);
        this.mRotateDialogSpinner.setVisibility(8);
        this.mRotateDialogButton1.setVisibility(8);
        this.mRotateDialogButton2.setVisibility(8);
        this.mRotateDialogButtonLayout.setVisibility(8);
    }

    private void fadeOutDialog() {
        this.mDialogRootLayout.startAnimation(this.mFadeOutAnim);
        this.mDialogRootLayout.setVisibility(8);
    }

    private void fadeInDialog() {
        this.mDialogRootLayout.startAnimation(this.mFadeInAnim);
        this.mDialogRootLayout.setVisibility(0);
    }

    public void dismissDialog() {
        if (this.mDialogRootLayout != null && this.mDialogRootLayout.getVisibility() != 8) {
            fadeOutDialog();
        }
    }

    public void showAlertDialog(String str, String str2, String str3, final Runnable runnable, String str4, final Runnable runnable2) {
        resetRotateDialog();
        if (str != null) {
            this.mRotateDialogTitle.setText(str);
            this.mRotateDialogTitleLayout.setVisibility(0);
        }
        this.mRotateDialogText.setText(str2);
        if (str3 != null) {
            this.mRotateDialogButton1.setText(str3);
            this.mRotateDialogButton1.setContentDescription(str3);
            this.mRotateDialogButton1.setVisibility(0);
            this.mRotateDialogButton1.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (runnable != null) {
                        runnable.run();
                    }
                    RotateDialogController.this.dismissDialog();
                }
            });
            this.mRotateDialogButtonLayout.setVisibility(0);
        }
        if (str4 != null) {
            this.mRotateDialogButton2.setText(str4);
            this.mRotateDialogButton2.setContentDescription(str4);
            this.mRotateDialogButton2.setVisibility(0);
            this.mRotateDialogButton2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                    RotateDialogController.this.dismissDialog();
                }
            });
            this.mRotateDialogButtonLayout.setVisibility(0);
        }
        fadeInDialog();
    }

    public static AlertDialog showSystemAlertDialog(Context context, String str, String str2, String str3, final Runnable runnable, String str4, final Runnable runnable2) {
        Builder builder = new Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setCancelable(false);
        if (str3 != null) {
            builder.setPositiveButton(str3, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
        }
        if (str4 != null) {
            builder.setNegativeButton(str4, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            });
        }
        AlertDialog create = builder.create();
        create.show();
        return create;
    }

    public static AlertDialog showSystemChoiceDialog(final Context context, String str, String str2, String str3, String str4, final Runnable runnable, final Runnable runnable2) {
        AnonymousClass5 anonymousClass5 = new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 25 || i == 24;
            }
        };
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(2130968643, null);
        ((TextView) inflate.findViewById(2131558624)).setText(str2);
        str2 = context.getResources().getString(2131296786);
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                ActivityLauncher.launchPrivacyPolicyWebpage(context);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(context.getResources().getColor(17170432));
            }
        }, 0, str2.length(), 33);
        TextView textView = (TextView) inflate.findViewById(2131558625);
        textView.setClickable(true);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (Util.isAccessible()) {
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ActivityLauncher.launchPrivacyPolicyWebpage(context);
                }
            });
        }
        final CheckBox checkBox = (CheckBox) inflate.findViewById(2131558626);
        checkBox.setText(str3);
        builder.setOnKeyListener(anonymousClass5);
        builder.setTitle(str);
        builder.setCancelable(false);
        builder.setView(inflate);
        if (str4 != null) {
            builder.setPositiveButton(str4, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (checkBox.isChecked()) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    } else if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            });
        }
        AlertDialog create = builder.create();
        create.show();
        return create;
    }

    public void showWaitingDialog(String str) {
        resetRotateDialog();
        this.mRotateDialogText.setText(str);
        this.mRotateDialogSpinner.setVisibility(0);
        fadeInDialog();
    }
}
