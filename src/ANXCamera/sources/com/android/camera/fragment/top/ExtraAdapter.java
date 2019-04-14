package com.android.camera.fragment.top;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.camera.constant.ColorConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.config.DataItemConfig;
import com.android.camera.data.data.config.SupportedConfigs;
import com.android.camera.data.data.runing.DataItemRunning;
import com.android.camera.fragment.CommonRecyclerViewHolder;

public class ExtraAdapter extends Adapter<CommonRecyclerViewHolder> {
    private DataItemConfig mDataItemConfig;
    private DataItemRunning mDataItemRunning;
    private int mDegree;
    private int mImageNormalColor;
    private OnClickListener mOnClickListener;
    private int mSelectedColor;
    private SupportedConfigs mSupportedConfigs;
    private int mTAG = -1;
    private int mTextNormalColor;

    public ExtraAdapter(SupportedConfigs supportedConfigs, OnClickListener onClickListener) {
        this.mSupportedConfigs = supportedConfigs;
        this.mOnClickListener = onClickListener;
        this.mDataItemRunning = DataRepository.dataItemRunning();
        this.mDataItemConfig = DataRepository.dataItemConfig();
        this.mTextNormalColor = ColorConstant.COLOR_COMMON_NORMAL;
        this.mImageNormalColor = -1315861;
        this.mSelectedColor = -15101209;
    }

    public void setNewDegree(int i) {
        this.mDegree = i;
    }

    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2130968628, viewGroup, false);
        if (this.mDegree != 0) {
            ViewCompat.setRotation(inflate, (float) this.mDegree);
        }
        return new CommonRecyclerViewHolder(inflate);
    }

    /* JADX WARNING: Missing block: B:32:0x018c, code skipped:
            r1 = r0;
            r0 = r5;
     */
    /* JADX WARNING: Missing block: B:34:0x0196, code skipped:
            r7 = r4;
            r4 = false;
            r0 = r1;
            r1 = r7;
     */
    /* JADX WARNING: Missing block: B:35:0x019a, code skipped:
            r5 = (android.widget.TextView) r9.getView(2131558536);
            r6 = (com.android.camera.ui.ColorImageView) r9.getView(2131558535);
     */
    /* JADX WARNING: Missing block: B:36:0x01ac, code skipped:
            if (r1 == -1) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:37:0x01ae, code skipped:
            r5.setText(r1);
     */
    /* JADX WARNING: Missing block: B:38:0x01b2, code skipped:
            r5.setText(r3);
     */
    /* JADX WARNING: Missing block: B:39:0x01b5, code skipped:
            if (r4 == false) goto L_0x01ba;
     */
    /* JADX WARNING: Missing block: B:40:0x01b7, code skipped:
            r1 = r8.mSelectedColor;
     */
    /* JADX WARNING: Missing block: B:41:0x01ba, code skipped:
            r1 = r8.mTextNormalColor;
     */
    /* JADX WARNING: Missing block: B:42:0x01bc, code skipped:
            if (r4 == false) goto L_0x01c1;
     */
    /* JADX WARNING: Missing block: B:43:0x01be, code skipped:
            r2 = r8.mSelectedColor;
     */
    /* JADX WARNING: Missing block: B:44:0x01c1, code skipped:
            r2 = r8.mImageNormalColor;
     */
    /* JADX WARNING: Missing block: B:45:0x01c3, code skipped:
            r5.setTextColor(r1);
            r6.setColor(r2);
            r6.setImageResource(r0);
     */
    /* JADX WARNING: Missing block: B:46:0x01d0, code skipped:
            if (com.android.camera.Util.isAccessible() != false) goto L_0x01d8;
     */
    /* JADX WARNING: Missing block: B:48:0x01d6, code skipped:
            if (com.android.camera.Util.isSetContentDesc() == false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:50:0x01da, code skipped:
            if (r8.mTAG != r10) goto L_?;
     */
    /* JADX WARNING: Missing block: B:51:0x01dc, code skipped:
            r10 = new java.lang.StringBuilder();
            r10.append(r5.getText());
     */
    /* JADX WARNING: Missing block: B:52:0x01e8, code skipped:
            if (r4 == false) goto L_0x01fb;
     */
    /* JADX WARNING: Missing block: B:53:0x01ea, code skipped:
            r10.append(r9.itemView.getResources().getString(2131296495));
     */
    /* JADX WARNING: Missing block: B:54:0x01fb, code skipped:
            r10.append(r9.itemView.getResources().getString(2131296496));
     */
    /* JADX WARNING: Missing block: B:55:0x020b, code skipped:
            r9.itemView.setContentDescription(r10);
            r9.itemView.postDelayed(new com.android.camera.fragment.top.ExtraAdapter.AnonymousClass1(r8), 100);
     */
    /* JADX WARNING: Missing block: B:56:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:57:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:58:?, code skipped:
            return;
     */
    public void onBindViewHolder(final com.android.camera.fragment.CommonRecyclerViewHolder r9, int r10) {
        /*
        r8 = this;
        r0 = r8.mSupportedConfigs;
        r10 = r0.getConfig(r10);
        r0 = r9.itemView;
        r1 = java.lang.Integer.valueOf(r10);
        r0.setTag(r1);
        r0 = r9.itemView;
        r1 = r8.mOnClickListener;
        r0.setOnClickListener(r1);
        r0 = 0;
        r1 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r2 = -1;
        r3 = 0;
        switch(r10) {
            case 225: goto L_0x018f;
            case 226: goto L_0x0177;
            default: goto L_0x0022;
        };
    L_0x0022:
        switch(r10) {
            case 228: goto L_0x0150;
            case 229: goto L_0x0141;
            case 230: goto L_0x0132;
            case 231: goto L_0x0122;
            case 232: goto L_0x0116;
            case 233: goto L_0x010a;
            case 234: goto L_0x00fa;
            case 235: goto L_0x00ea;
            case 236: goto L_0x00da;
            case 237: goto L_0x00d2;
            case 238: goto L_0x00c2;
            case 239: goto L_0x00a6;
            case 240: goto L_0x009a;
            case 241: goto L_0x008a;
            case 242: goto L_0x0073;
            default: goto L_0x0025;
        };
    L_0x0025:
        switch(r10) {
            case 250: goto L_0x0056;
            case 251: goto L_0x003c;
            case 252: goto L_0x002c;
            default: goto L_0x0028;
        };
    L_0x0028:
        r4 = r0;
    L_0x0029:
        r1 = r2;
        goto L_0x019a;
    L_0x002c:
        r0 = 2130837717; // 0x7f0200d5 float:1.7280396E38 double:1.052773713E-314;
        r1 = 2131296900; // 0x7f090284 float:1.821173E38 double:1.0530005794E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_hand_gesture";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x003c:
        r0 = com.android.camera.data.DataRepository.dataItemConfig();
        r0 = r0.getFrontComponentConfigUltraPixel();
        r1 = r0.getMenuDrawable();
        r3 = r0.getMenuString();
        r0 = r8.mDataItemRunning;
        r4 = "pref_menu_ultra_pixel_photography_front";
        r0 = r0.isSwitchOn(r4);
        goto L_0x0070;
    L_0x0056:
        r0 = com.android.camera.data.DataRepository.dataItemConfig();
        r0 = r0.getRearComponentConfigUltraPixel();
        r1 = r0.getMenuDrawable();
        r3 = r0.getMenuString();
        r0 = r8.mDataItemRunning;
        r4 = "pref_menu_ultra_pixel_photography_rear";
        r0 = r0.isSwitchOn(r4);
    L_0x0070:
        r4 = r0;
        r0 = r1;
        goto L_0x0029;
        r1 = com.android.camera.Util.isGlobalVersion();
        if (r1 == 0) goto L_0x0082;
    L_0x007a:
        r1 = 2130837707; // 0x7f0200cb float:1.7280376E38 double:1.052773708E-314;
        r4 = 2131296634; // 0x7f09017a float:1.821119E38 double:1.053000448E-314;
        goto L_0x0196;
    L_0x0082:
        r1 = 2130837706; // 0x7f0200ca float:1.7280374E38 double:1.0527737074E-314;
        r4 = 2131296633; // 0x7f090179 float:1.8211188E38 double:1.0530004475E-314;
        goto L_0x0196;
    L_0x008a:
        r0 = 2130837725; // 0x7f0200dd float:1.7280412E38 double:1.052773717E-314;
        r1 = 2131296720; // 0x7f0901d0 float:1.8211365E38 double:1.0530004904E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_super_resolution_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x009a:
        r0 = 2130837711; // 0x7f0200cf float:1.7280384E38 double:1.05277371E-314;
        r1 = 2131296553; // 0x7f090129 float:1.8211026E38 double:1.053000408E-314;
        r4 = com.android.camera.CameraSettings.isDualCameraWaterMarkOpen();
        goto L_0x019a;
    L_0x00a6:
        r0 = com.android.camera.data.DataRepository.dataItemGlobal();
        r0 = r0.getCurrentMode();
        r1 = r8.mDataItemConfig;
        r1 = r1.getComponentConfigBeauty();
        r4 = r1.isSwitchOn(r0);
        r5 = r1.getValueSelectedDrawable(r0);
        r0 = r1.getValueDisplayString(r0);
        goto L_0x018c;
    L_0x00c2:
        r0 = 2130837715; // 0x7f0200d3 float:1.7280392E38 double:1.052773712E-314;
        r1 = 2131296586; // 0x7f09014a float:1.8211093E38 double:1.053000424E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_show_gender_age_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x00d2:
        r1 = 2130837721; // 0x7f0200d9 float:1.7280404E38 double:1.052773715E-314;
        r4 = 2131296335; // 0x7f09004f float:1.8210584E38 double:1.0530003E-314;
        goto L_0x0196;
    L_0x00da:
        r0 = 2130837720; // 0x7f0200d8 float:1.7280402E38 double:1.0527737143E-314;
        r1 = 2131296655; // 0x7f09018f float:1.8211233E38 double:1.0530004583E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_magic_mirror_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x00ea:
        r0 = 2130837716; // 0x7f0200d4 float:1.7280394E38 double:1.0527737123E-314;
        r1 = 2131296716; // 0x7f0901cc float:1.8211357E38 double:1.0530004885E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_groupshot_mode_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x00fa:
        r0 = 2130837722; // 0x7f0200da float:1.7280406E38 double:1.0527737153E-314;
        r1 = 2131296718; // 0x7f0901ce float:1.821136E38 double:1.0530004895E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_scenemode_setting_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x010a:
        r0 = 2130837713; // 0x7f0200d1 float:1.7280388E38 double:1.052773711E-314;
        r1 = 2131296600; // 0x7f090158 float:1.8211121E38 double:1.053000431E-314;
        r4 = com.android.camera.module.ModuleManager.isFastMotionModule();
        goto L_0x019a;
    L_0x0116:
        r0 = 2130837723; // 0x7f0200db float:1.7280408E38 double:1.052773716E-314;
        r1 = 2131296601; // 0x7f090159 float:1.8211123E38 double:1.0530004317E-314;
        r4 = com.android.camera.module.ModuleManager.isSlowMotionModule();
        goto L_0x019a;
    L_0x0122:
        r0 = 2130837719; // 0x7f0200d7 float:1.72804E38 double:1.052773714E-314;
        r1 = 2131296719; // 0x7f0901cf float:1.8211363E38 double:1.05300049E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_ubifocus_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x0132:
        r0 = 2130837718; // 0x7f0200d6 float:1.7280398E38 double:1.0527737133E-314;
        r1 = 2131296715; // 0x7f0901cb float:1.8211355E38 double:1.053000488E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_hand_night_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x0141:
        r0 = 2130837724; // 0x7f0200dc float:1.728041E38 double:1.0527737163E-314;
        r1 = 2131296714; // 0x7f0901ca float:1.8211352E38 double:1.0530004875E-314;
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_gradienter_key";
        r4 = r4.isSwitchOn(r5);
        goto L_0x019a;
    L_0x0150:
        r0 = r8.mDataItemRunning;
        r0 = r0.getComponentRunningTiltValue();
        r4 = r8.mDataItemRunning;
        r5 = "pref_camera_tilt_shift_mode";
        r4 = r4.isSwitchOn(r5);
        if (r4 == 0) goto L_0x016c;
        r5 = r0.getValueDisplayString(r1);
        r0 = r0.getValueSelectedDrawable(r1);
        r1 = r5;
        goto L_0x019a;
    L_0x016c:
        r0 = 2131296717; // 0x7f0901cd float:1.8211359E38 double:1.053000489E-314;
        r1 = 2130837726; // 0x7f0200de float:1.7280414E38 double:1.0527737173E-314;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x019a;
    L_0x0177:
        r0 = r8.mDataItemRunning;
        r0 = r0.getComponentRunningTimer();
        r4 = r0.isSwitchOn();
        r5 = r0.getValueSelectedDrawable(r1);
        r0 = r0.getValueDisplayString(r1);
    L_0x018c:
        r1 = r0;
        r0 = r5;
        goto L_0x019a;
    L_0x018f:
        r1 = 2130837712; // 0x7f0200d0 float:1.7280386E38 double:1.0527737104E-314;
        r4 = 2131296713; // 0x7f0901c9 float:1.821135E38 double:1.053000487E-314;
    L_0x0196:
        r7 = r4;
        r4 = r0;
        r0 = r1;
        r1 = r7;
    L_0x019a:
        r5 = 2131558536; // 0x7f0d0088 float:1.874239E38 double:1.0531298447E-314;
        r5 = r9.getView(r5);
        r5 = (android.widget.TextView) r5;
        r6 = 2131558535; // 0x7f0d0087 float:1.8742389E38 double:1.053129844E-314;
        r6 = r9.getView(r6);
        r6 = (com.android.camera.ui.ColorImageView) r6;
        if (r1 == r2) goto L_0x01b2;
    L_0x01ae:
        r5.setText(r1);
        goto L_0x01b5;
    L_0x01b2:
        r5.setText(r3);
    L_0x01b5:
        if (r4 == 0) goto L_0x01ba;
    L_0x01b7:
        r1 = r8.mSelectedColor;
        goto L_0x01bc;
    L_0x01ba:
        r1 = r8.mTextNormalColor;
    L_0x01bc:
        if (r4 == 0) goto L_0x01c1;
    L_0x01be:
        r2 = r8.mSelectedColor;
        goto L_0x01c3;
    L_0x01c1:
        r2 = r8.mImageNormalColor;
    L_0x01c3:
        r5.setTextColor(r1);
        r6.setColor(r2);
        r6.setImageResource(r0);
        r0 = com.android.camera.Util.isAccessible();
        if (r0 != 0) goto L_0x01d8;
    L_0x01d2:
        r0 = com.android.camera.Util.isSetContentDesc();
        if (r0 == 0) goto L_0x021c;
    L_0x01d8:
        r0 = r8.mTAG;
        if (r0 != r10) goto L_0x021c;
    L_0x01dc:
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = r5.getText();
        r10.append(r0);
        if (r4 == 0) goto L_0x01fb;
    L_0x01ea:
        r0 = r9.itemView;
        r0 = r0.getResources();
        r1 = 2131296495; // 0x7f0900ef float:1.8210908E38 double:1.0530003793E-314;
        r0 = r0.getString(r1);
        r10.append(r0);
        goto L_0x020b;
    L_0x01fb:
        r0 = r9.itemView;
        r0 = r0.getResources();
        r1 = 2131296496; // 0x7f0900f0 float:1.821091E38 double:1.05300038E-314;
        r0 = r0.getString(r1);
        r10.append(r0);
    L_0x020b:
        r0 = r9.itemView;
        r0.setContentDescription(r10);
        r10 = r9.itemView;
        r0 = new com.android.camera.fragment.top.ExtraAdapter$1;
        r0.<init>(r9);
        r1 = 100;
        r10.postDelayed(r0, r1);
    L_0x021c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.top.ExtraAdapter.onBindViewHolder(com.android.camera.fragment.CommonRecyclerViewHolder, int):void");
    }

    public int getItemCount() {
        return this.mSupportedConfigs.getLength();
    }

    public void setOnClictTag(int i) {
        this.mTAG = i;
    }
}
