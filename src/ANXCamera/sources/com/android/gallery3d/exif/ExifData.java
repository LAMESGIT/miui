package com.android.gallery3d.exif;

import com.android.camera.log.Log;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ExifData {
    private static final String TAG = "ExifData";
    private static final byte[] USER_COMMENT_ASCII = new byte[]{(byte) 65, (byte) 83, (byte) 67, (byte) 73, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] USER_COMMENT_JIS = new byte[]{(byte) 74, (byte) 73, (byte) 83, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] USER_COMMENT_UNICODE = new byte[]{(byte) 85, (byte) 78, (byte) 73, (byte) 67, (byte) 79, (byte) 68, (byte) 69, (byte) 0};
    private final ByteOrder mByteOrder;
    private final IfdData[] mIfdDatas = new IfdData[5];
    private ArrayList<byte[]> mStripBytes = new ArrayList();
    private byte[] mThumbnail;

    ExifData(ByteOrder byteOrder) {
        this.mByteOrder = byteOrder;
    }

    /* Access modifiers changed, original: protected */
    public byte[] getCompressedThumbnail() {
        return this.mThumbnail;
    }

    /* Access modifiers changed, original: protected */
    public void setCompressedThumbnail(byte[] bArr) {
        this.mThumbnail = bArr;
    }

    /* Access modifiers changed, original: protected */
    public boolean hasCompressedThumbnail() {
        return this.mThumbnail != null;
    }

    /* Access modifiers changed, original: protected */
    public void setStripBytes(int i, byte[] bArr) {
        if (i < this.mStripBytes.size()) {
            this.mStripBytes.set(i, bArr);
            return;
        }
        for (int size = this.mStripBytes.size(); size < i; size++) {
            this.mStripBytes.add(null);
        }
        this.mStripBytes.add(bArr);
    }

    /* Access modifiers changed, original: protected */
    public int getStripCount() {
        return this.mStripBytes.size();
    }

    /* Access modifiers changed, original: protected */
    public byte[] getStrip(int i) {
        return (byte[]) this.mStripBytes.get(i);
    }

    /* Access modifiers changed, original: protected */
    public boolean hasUncompressedStrip() {
        return this.mStripBytes.size() != 0;
    }

    /* Access modifiers changed, original: protected */
    public ByteOrder getByteOrder() {
        return this.mByteOrder;
    }

    /* Access modifiers changed, original: protected */
    public IfdData getIfdData(int i) {
        if (ExifTag.isValidIfd(i)) {
            return this.mIfdDatas[i];
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void addIfdData(IfdData ifdData) {
        this.mIfdDatas[ifdData.getId()] = ifdData;
    }

    /* Access modifiers changed, original: protected */
    public IfdData getOrCreateIfdData(int i) {
        IfdData ifdData = this.mIfdDatas[i];
        if (ifdData != null) {
            return ifdData;
        }
        ifdData = new IfdData(i);
        this.mIfdDatas[i] = ifdData;
        return ifdData;
    }

    /* Access modifiers changed, original: protected */
    public ExifTag getTag(short s, int i) {
        IfdData ifdData = this.mIfdDatas[i];
        return ifdData == null ? null : ifdData.getTag(s);
    }

    /* Access modifiers changed, original: protected */
    public ExifTag addTag(ExifTag exifTag) {
        if (exifTag != null) {
            return addTag(exifTag, exifTag.getIfd());
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public ExifTag addTag(ExifTag exifTag, int i) {
        if (exifTag == null || !ExifTag.isValidIfd(i)) {
            return null;
        }
        return getOrCreateIfdData(i).setTag(exifTag);
    }

    /* Access modifiers changed, original: protected */
    public void clearThumbnailAndStrips() {
        this.mThumbnail = null;
        this.mStripBytes.clear();
    }

    /* Access modifiers changed, original: protected */
    public void removeThumbnailData() {
        clearThumbnailAndStrips();
        this.mIfdDatas[1] = null;
    }

    /* Access modifiers changed, original: protected */
    public void removeTag(short s, int i) {
        IfdData ifdData = this.mIfdDatas[i];
        if (ifdData != null) {
            ifdData.removeTag(s);
        }
    }

    /* Access modifiers changed, original: protected */
    public String getUserComment() {
        IfdData ifdData = this.mIfdDatas[0];
        if (ifdData == null) {
            return null;
        }
        ExifTag tag = ifdData.getTag(ExifInterface.getTrueTagKey(ExifInterface.TAG_USER_COMMENT));
        if (tag == null || tag.getComponentCount() < 8) {
            return null;
        }
        byte[] bArr = new byte[tag.getComponentCount()];
        tag.getBytes(bArr);
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArr, 0, bArr2, 0, 8);
        try {
            if (Arrays.equals(bArr2, USER_COMMENT_ASCII)) {
                return new String(bArr, 8, bArr.length - 8, "US-ASCII");
            }
            if (Arrays.equals(bArr2, USER_COMMENT_JIS)) {
                return new String(bArr, 8, bArr.length - 8, "EUC-JP");
            }
            if (Arrays.equals(bArr2, USER_COMMENT_UNICODE)) {
                return new String(bArr, 8, bArr.length - 8, "UTF-16");
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            Log.w(TAG, "Failed to decode the user comment");
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public String getXiaomiComment() {
        IfdData ifdData = this.mIfdDatas[2];
        if (ifdData == null) {
            return null;
        }
        ExifTag tag = ifdData.getTag(ExifInterface.getTrueTagKey(ExifInterface.TAG_XIAOMI_COMMENT));
        if (tag == null || tag.getComponentCount() < 1) {
            return null;
        }
        byte[] stringByte = tag.getStringByte();
        try {
            if (stringByte[stringByte.length - 1] == (byte) 0) {
                return new String(stringByte, 0, stringByte.length - 1, "US-ASCII");
            }
            return new String(stringByte, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            Log.w(TAG, "Failed to decode the xiaomicomment");
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public List<ExifTag> getAllTags() {
        ArrayList arrayList = new ArrayList();
        for (IfdData ifdData : this.mIfdDatas) {
            if (ifdData != null) {
                ExifTag[] allTags = ifdData.getAllTags();
                if (allTags != null) {
                    Collections.addAll(arrayList, allTags);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public List<ExifTag> getAllTagsForIfd(int i) {
        IfdData ifdData = this.mIfdDatas[i];
        if (ifdData == null) {
            return null;
        }
        ExifTag[] allTags = ifdData.getAllTags();
        if (allTags == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(allTags.length);
        Collections.addAll(arrayList, allTags);
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public List<ExifTag> getAllTagsForTagId(short s) {
        ArrayList arrayList = new ArrayList();
        for (IfdData ifdData : this.mIfdDatas) {
            if (ifdData != null) {
                ExifTag tag = ifdData.getTag(s);
                if (tag != null) {
                    arrayList.add(tag);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExifData)) {
            return false;
        }
        ExifData exifData = (ExifData) obj;
        if (exifData.mByteOrder != this.mByteOrder || exifData.mStripBytes.size() != this.mStripBytes.size() || !Arrays.equals(exifData.mThumbnail, this.mThumbnail)) {
            return false;
        }
        int i;
        for (i = 0; i < this.mStripBytes.size(); i++) {
            if (!Arrays.equals((byte[]) exifData.mStripBytes.get(i), (byte[]) this.mStripBytes.get(i))) {
                return false;
            }
        }
        for (i = 0; i < 5; i++) {
            IfdData ifdData = exifData.getIfdData(i);
            IfdData ifdData2 = getIfdData(i);
            if (ifdData != ifdData2 && ifdData != null && !ifdData.equals(ifdData2)) {
                return false;
            }
        }
        return true;
    }
}
