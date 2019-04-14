package com.android.camera.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.camera.Util;
import com.android.camera.data.DataRepository;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BackStack;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;

public class LensDirtyDetectDialogFragment extends DialogFragment implements OnKeyListener, OnClickListener, HandleBackTrace {
    public static final String TAG = "LensDirtyDetectDialog";

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int i;
        boolean z = false;
        View inflate = layoutInflater.inflate(2130968592, viewGroup, false);
        inflate.setOnClickListener(this);
        TextView textView = (TextView) inflate.findViewById(2131558465);
        if (DataRepository.dataItemGlobal().getCurrentCameraId() == 1) {
            z = true;
        }
        if (z) {
            i = 2131296768;
        } else {
            i = 2131296767;
        }
        textView.setText(i);
        adjustView(inflate.findViewById(2131558464));
        return inflate;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().setGravity(48);
        return onCreateDialog;
    }

    public final boolean canProvide() {
        return isAdded();
    }

    private void adjustView(View view) {
        Rect displayRect = Util.getDisplayRect(getContext());
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = Util.getDialogTopMargin(displayRect.top);
        layoutParams.width = displayRect.width();
        layoutParams.height = (displayRect.width() * 4) / 3;
    }

    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnKeyListener(this);
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        BackStack backStack = (BackStack) ModeCoordinatorImpl.getInstance().getAttachProtocol(171);
        if (backStack != null) {
            backStack.addInBackStack(this);
        }
    }

    public void onDestroyView() {
        BackStack backStack = (BackStack) ModeCoordinatorImpl.getInstance().getAttachProtocol(171);
        if (backStack != null) {
            backStack.removeBackStack(this);
        }
        super.onDestroyView();
    }

    public void onClick(View view) {
        onBackEvent(5);
    }

    public boolean onBackEvent(int i) {
        dismissAllowingStateLoss();
        switch (i) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        onBackEvent(5);
        return true;
    }
}
