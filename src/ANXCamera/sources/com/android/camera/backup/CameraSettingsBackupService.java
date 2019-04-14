package com.android.camera.backup;

import com.xiaomi.settingsdk.backup.CloudBackupServiceBase;
import com.xiaomi.settingsdk.backup.ICloudBackup;

public class CameraSettingsBackupService extends CloudBackupServiceBase {
    /* Access modifiers changed, original: protected */
    public ICloudBackup getBackupImpl() {
        return new CameraSettingsBackupImpl();
    }
}
