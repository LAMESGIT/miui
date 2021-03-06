package com.android.camera;

public class IntArray {
    private static final int INIT_CAPACITY = 8;
    private int[] mData = new int[8];
    private int mSize = 0;

    public void add(int i) {
        int[] iArr;
        if (this.mData.length == this.mSize) {
            iArr = new int[(this.mSize + this.mSize)];
            System.arraycopy(this.mData, 0, iArr, 0, this.mSize);
            this.mData = iArr;
        }
        iArr = this.mData;
        int i2 = this.mSize;
        this.mSize = i2 + 1;
        iArr[i2] = i;
    }

    public int size() {
        return this.mSize;
    }

    public int[] toArray(int[] iArr) {
        Object iArr2;
        if (iArr2 == null || iArr2.length < this.mSize) {
            iArr2 = new int[this.mSize];
        }
        System.arraycopy(this.mData, 0, iArr2, 0, this.mSize);
        return iArr2;
    }
}
