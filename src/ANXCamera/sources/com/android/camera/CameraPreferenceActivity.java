package com.android.camera;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.widget.Toast;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.PreviewListPreference;
import com.android.camera2.DetachableClickListener;
import com.mi.config.b;

public class CameraPreferenceActivity extends BasePreferenceActivity {
    public static final String IS_IMAGE_CAPTURE_INTENT = "IsCaptureIntent";
    private AlertDialog mDoubleConfirmActionChooseDialog = null;

    /* Access modifiers changed, original: protected */
    public int getPreferenceXml() {
        return 2131165184;
    }

    /* Access modifiers changed, original: protected */
    public void onSettingChanged(int i) {
        CameraSettings.sCameraChangeManager.request(i);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!preference.getKey().equals(CameraSettings.KEY_CAMERA_SNAP) || obj == null) {
            return super.onPreferenceChange(preference, obj);
        }
        if (System.getInt(getContentResolver(), "volumekey_wake_screen", 0) == 1) {
            Toast.makeText(this, 2131296653, 0).show();
            return false;
        } else if ((obj.equals(getString(2131296650)) || obj.equals(getString(2131296651))) && "public_transportation_shortcuts".equals(Secure.getString(getContentResolver(), "key_long_press_volume_down"))) {
            bringUpDoubleConfirmDlg((PreviewListPreference) preference, (String) obj);
            return false;
        } else {
            Secure.putString(getContentResolver(), "key_long_press_volume_down", CameraSettings.getMiuiSettingsKeyForStreetSnap((String) obj));
            CameraStatUtil.trackPreferenceChange(CameraSettings.KEY_CAMERA_SNAP, obj);
            return true;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getCharSequenceExtra(":miui:starting_window_label") != null) {
            getActionBar().setTitle(2131296325);
        }
        changeRequestOrientation();
    }

    public void changeRequestOrientation() {
        if (b.hI()) {
            if (CameraSettings.isFrontCamera()) {
                setRequestedOrientation(7);
            } else {
                setRequestedOrientation(1);
            }
        }
    }

    private void bringUpDoubleConfirmDlg(final PreviewListPreference previewListPreference, final String str) {
        if (this.mDoubleConfirmActionChooseDialog == null) {
            DetachableClickListener wrap = DetachableClickListener.wrap(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == -1) {
                        CameraPreferenceActivity.this.mDoubleConfirmActionChooseDialog = null;
                        CameraStatUtil.trackPreferenceChange(CameraSettings.KEY_CAMERA_SNAP, str);
                        previewListPreference.setValue(str);
                        Secure.putString(CameraPreferenceActivity.this.getContentResolver(), "key_long_press_volume_down", CameraSettings.getMiuiSettingsKeyForStreetSnap(str));
                    } else if (i == -2) {
                        CameraPreferenceActivity.this.mDoubleConfirmActionChooseDialog.dismiss();
                        CameraPreferenceActivity.this.mDoubleConfirmActionChooseDialog = null;
                    }
                }
            });
            this.mDoubleConfirmActionChooseDialog = new Builder(this).setTitle(2131296689).setMessage(2131296690).setPositiveButton(2131296691, wrap).setNegativeButton(2131296692, wrap).setCancelable(false).create();
            wrap.clearOnDetach(this.mDoubleConfirmActionChooseDialog);
            this.mDoubleConfirmActionChooseDialog.show();
        }
    }
}
