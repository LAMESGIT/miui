package com.android.camera.fragment.sticker;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.android.camera.fragment.sticker.BaseSelectAdapter.BaseSelectHolder;
import com.android.camera.fragment.sticker.download.DownloadView;
import com.android.camera.network.imageloader.ImageLoaderManager;
import com.android.camera.sticker.StickerInfo;

public class StickerAdapter extends BaseSelectAdapter<StickerInfo> {

    public class StickerHolder extends BaseSelectHolder {
        public DownloadView mDownloadView;
        private ImageView mImageView;

        public StickerHolder(View view) {
            super(view);
            this.mImageView = (ImageView) view.findViewById(2131558556);
            this.mDownloadView = (DownloadView) view.findViewById(2131558520);
        }

        public void bindView(int i) {
            StickerInfo stickerInfo = (StickerInfo) StickerAdapter.this.getItemData(i);
            if (stickerInfo.isLocal) {
                this.mImageView.setImageResource(stickerInfo.imageId);
            } else {
                ImageLoaderManager.getInstance().loadImage(this.mImageView, stickerInfo.icon);
            }
            int downloadState = stickerInfo.getDownloadState();
            this.mDownloadView.setStateImage(downloadState);
            if (downloadState == 3) {
                stickerInfo.downloadState = 1;
            }
        }
    }

    public StickerAdapter(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return 2130968623;
    }

    /* Access modifiers changed, original: protected */
    public BaseSelectHolder getHolder(View view) {
        return new StickerHolder(view);
    }
}
