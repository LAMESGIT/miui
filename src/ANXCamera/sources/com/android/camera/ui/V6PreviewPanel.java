package com.android.camera.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.android.camera.ActivityBase;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.PlayVideoProtocol;
import com.android.camera2.autozoom.AutoZoomView;

public class V6PreviewPanel extends V6RelativeLayout implements OnClickListener {
    public AutoZoomView mAutoZoomView;
    public V6EffectCropView mCropView;
    public FaceView mFaceView;
    public FocusView mFocusView;
    private Runnable mHidePreviewCover = new Runnable() {
        public void run() {
            V6PreviewPanel.this.mPreviewCover.setVisibility(8);
        }
    };
    public ObjectView mObjectView;
    private View mPreviewCover;
    public V6PreviewFrame mPreviewFrame;
    public ImageView mVideoReviewImage;
    public ImageView mVideoReviewPlay;

    public V6PreviewPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mFaceView = (FaceView) findChildrenById(2131558633);
        this.mObjectView = (ObjectView) findChildrenById(2131558634);
        this.mVideoReviewPlay = (ImageView) findChildrenById(2131558640);
        this.mFocusView = (FocusView) findChildrenById(2131558635);
        this.mPreviewFrame = (V6PreviewFrame) findChildrenById(2131558631);
        this.mCropView = (V6EffectCropView) findChildrenById(2131558641);
        this.mVideoReviewImage = (ImageView) findViewById(2131558639);
        this.mPreviewCover = findViewById(2131558630);
        this.mAutoZoomView = (AutoZoomView) findChildrenById(2131558636);
        this.mVideoReviewImage.setBackgroundColor(-16777216);
        this.mVideoReviewPlay.setOnClickListener(this);
    }

    public void onCameraOpen() {
        super.onCameraOpen();
    }

    public void onPause() {
        super.onPause();
        this.mFaceView.clear();
        this.mObjectView.clear();
        this.mAutoZoomView.clear(0);
        removeCallbacks(this.mHidePreviewCover);
        this.mPreviewCover.setVisibility(8);
    }

    public void onResume() {
        super.onResume();
        this.mFaceView.setVisibility(8);
        this.mObjectView.setVisibility(8);
        this.mVideoReviewImage.setVisibility(8);
        this.mVideoReviewPlay.setVisibility(8);
        if (((ActivityBase) getContext()).getCameraIntentManager().isScanQRCodeIntent()) {
            setVisibility(4);
        } else {
            setVisibility(0);
        }
    }

    public void onClick(View view) {
        if (view.getId() == 2131558640) {
            ((PlayVideoProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(167)).playVideo();
        }
    }

    public void setOrientation(int i, boolean z) {
        super.setOrientation(i, z);
    }

    public void onCapture() {
        this.mPreviewCover.setBackgroundResource(2131427402);
        this.mPreviewCover.setVisibility(0);
        removeCallbacks(this.mHidePreviewCover);
        postDelayed(this.mHidePreviewCover, 120);
    }
}
