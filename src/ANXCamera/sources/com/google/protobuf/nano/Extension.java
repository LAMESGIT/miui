package com.google.protobuf.nano;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Extension<M extends ExtendableMessageNano<M>, T> {
    public static final int TYPE_BOOL = 8;
    public static final int TYPE_BYTES = 12;
    public static final int TYPE_DOUBLE = 1;
    public static final int TYPE_ENUM = 14;
    public static final int TYPE_FIXED32 = 7;
    public static final int TYPE_FIXED64 = 6;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_GROUP = 10;
    public static final int TYPE_INT32 = 5;
    public static final int TYPE_INT64 = 3;
    public static final int TYPE_MESSAGE = 11;
    public static final int TYPE_SFIXED32 = 15;
    public static final int TYPE_SFIXED64 = 16;
    public static final int TYPE_SINT32 = 17;
    public static final int TYPE_SINT64 = 18;
    public static final int TYPE_STRING = 9;
    public static final int TYPE_UINT32 = 13;
    public static final int TYPE_UINT64 = 4;
    protected final Class<T> clazz;
    protected final GeneratedMessageLite<?, ?> defaultInstance;
    protected final boolean repeated;
    public final int tag;
    protected final int type;

    private static class PrimitiveExtension<M extends ExtendableMessageNano<M>, T> extends Extension<M, T> {
        private final int nonPackedTag;
        private final int packedTag;

        public PrimitiveExtension(int i, Class<T> cls, int i2, boolean z, int i3, int i4) {
            super(i, (Class) cls, i2, z, null);
            this.nonPackedTag = i3;
            this.packedTag = i4;
        }

        /* Access modifiers changed, original: protected */
        public Object readData(CodedInputByteBufferNano codedInputByteBufferNano) {
            try {
                switch (this.type) {
                    case 1:
                        return Double.valueOf(codedInputByteBufferNano.readDouble());
                    case 2:
                        return Float.valueOf(codedInputByteBufferNano.readFloat());
                    case 3:
                        return Long.valueOf(codedInputByteBufferNano.readInt64());
                    case 4:
                        return Long.valueOf(codedInputByteBufferNano.readUInt64());
                    case 5:
                        return Integer.valueOf(codedInputByteBufferNano.readInt32());
                    case 6:
                        return Long.valueOf(codedInputByteBufferNano.readFixed64());
                    case 7:
                        return Integer.valueOf(codedInputByteBufferNano.readFixed32());
                    case 8:
                        return Boolean.valueOf(codedInputByteBufferNano.readBool());
                    case 9:
                        return codedInputByteBufferNano.readString();
                    case 12:
                        return codedInputByteBufferNano.readBytes();
                    case 13:
                        return Integer.valueOf(codedInputByteBufferNano.readUInt32());
                    case 14:
                        return Integer.valueOf(codedInputByteBufferNano.readEnum());
                    case 15:
                        return Integer.valueOf(codedInputByteBufferNano.readSFixed32());
                    case 16:
                        return Long.valueOf(codedInputByteBufferNano.readSFixed64());
                    case 17:
                        return Integer.valueOf(codedInputByteBufferNano.readSInt32());
                    case 18:
                        return Long.valueOf(codedInputByteBufferNano.readSInt64());
                    default:
                        int i = this.type;
                        StringBuilder stringBuilder = new StringBuilder(24);
                        stringBuilder.append("Unknown type ");
                        stringBuilder.append(i);
                        throw new IllegalArgumentException(stringBuilder.toString());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Error reading extension field", e);
            }
        }

        /* Access modifiers changed, original: protected */
        public void readDataInto(UnknownFieldData unknownFieldData, List<Object> list) {
            if (unknownFieldData.tag == this.nonPackedTag) {
                list.add(readData(CodedInputByteBufferNano.newInstance(unknownFieldData.bytes)));
                return;
            }
            CodedInputByteBufferNano newInstance = CodedInputByteBufferNano.newInstance(unknownFieldData.bytes);
            try {
                newInstance.pushLimit(newInstance.readRawVarint32());
                while (!newInstance.isAtEnd()) {
                    list.add(readData(newInstance));
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Error reading extension field", e);
            }
        }

        /* Access modifiers changed, original: protected|final */
        public final void writeSingularData(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) {
            try {
                codedOutputByteBufferNano.writeRawVarint32(this.tag);
                switch (this.type) {
                    case 1:
                        codedOutputByteBufferNano.writeDoubleNoTag(((Double) obj).doubleValue());
                        break;
                    case 2:
                        codedOutputByteBufferNano.writeFloatNoTag(((Float) obj).floatValue());
                        break;
                    case 3:
                        codedOutputByteBufferNano.writeInt64NoTag(((Long) obj).longValue());
                        break;
                    case 4:
                        codedOutputByteBufferNano.writeUInt64NoTag(((Long) obj).longValue());
                        break;
                    case 5:
                        codedOutputByteBufferNano.writeInt32NoTag(((Integer) obj).intValue());
                        break;
                    case 6:
                        codedOutputByteBufferNano.writeFixed64NoTag(((Long) obj).longValue());
                        break;
                    case 7:
                        codedOutputByteBufferNano.writeFixed32NoTag(((Integer) obj).intValue());
                        break;
                    case 8:
                        codedOutputByteBufferNano.writeBoolNoTag(((Boolean) obj).booleanValue());
                        break;
                    case 9:
                        codedOutputByteBufferNano.writeStringNoTag((String) obj);
                        break;
                    case 12:
                        codedOutputByteBufferNano.writeBytesNoTag((byte[]) obj);
                        break;
                    case 13:
                        codedOutputByteBufferNano.writeUInt32NoTag(((Integer) obj).intValue());
                        break;
                    case 14:
                        codedOutputByteBufferNano.writeEnumNoTag(((Integer) obj).intValue());
                        break;
                    case 15:
                        codedOutputByteBufferNano.writeSFixed32NoTag(((Integer) obj).intValue());
                        break;
                    case 16:
                        codedOutputByteBufferNano.writeSFixed64NoTag(((Long) obj).longValue());
                        break;
                    case 17:
                        codedOutputByteBufferNano.writeSInt32NoTag(((Integer) obj).intValue());
                        break;
                    case 18:
                        codedOutputByteBufferNano.writeSInt64NoTag(((Long) obj).longValue());
                        break;
                    default:
                        int i = this.type;
                        StringBuilder stringBuilder = new StringBuilder(24);
                        stringBuilder.append("Unknown type ");
                        stringBuilder.append(i);
                        throw new IllegalArgumentException(stringBuilder.toString());
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        /* Access modifiers changed, original: protected */
        /* JADX WARNING: Missing block: B:12:0x0045, code skipped:
            if (r2 >= r0) goto L_0x0051;
     */
        /* JADX WARNING: Missing block: B:13:0x0047, code skipped:
            r7.writeSInt64NoTag(java.lang.reflect.Array.getLong(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:15:0x0053, code skipped:
            if (r2 >= r0) goto L_0x005f;
     */
        /* JADX WARNING: Missing block: B:16:0x0055, code skipped:
            r7.writeSInt32NoTag(java.lang.reflect.Array.getInt(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:18:0x0061, code skipped:
            if (r2 >= r0) goto L_0x006d;
     */
        /* JADX WARNING: Missing block: B:19:0x0063, code skipped:
            r7.writeSFixed64NoTag(java.lang.reflect.Array.getLong(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:21:0x006f, code skipped:
            if (r2 >= r0) goto L_0x007b;
     */
        /* JADX WARNING: Missing block: B:22:0x0071, code skipped:
            r7.writeSFixed32NoTag(java.lang.reflect.Array.getInt(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:24:0x007d, code skipped:
            if (r2 >= r0) goto L_0x0089;
     */
        /* JADX WARNING: Missing block: B:25:0x007f, code skipped:
            r7.writeEnumNoTag(java.lang.reflect.Array.getInt(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:27:0x008b, code skipped:
            if (r2 >= r0) goto L_0x0097;
     */
        /* JADX WARNING: Missing block: B:28:0x008d, code skipped:
            r7.writeUInt32NoTag(java.lang.reflect.Array.getInt(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:30:0x0099, code skipped:
            if (r2 >= r0) goto L_0x00a5;
     */
        /* JADX WARNING: Missing block: B:31:0x009b, code skipped:
            r7.writeBoolNoTag(java.lang.reflect.Array.getBoolean(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:33:0x00a6, code skipped:
            if (r2 >= r0) goto L_0x00b2;
     */
        /* JADX WARNING: Missing block: B:34:0x00a8, code skipped:
            r7.writeFixed32NoTag(java.lang.reflect.Array.getInt(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:36:0x00b3, code skipped:
            if (r2 >= r0) goto L_0x00bf;
     */
        /* JADX WARNING: Missing block: B:37:0x00b5, code skipped:
            r7.writeFixed64NoTag(java.lang.reflect.Array.getLong(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:39:0x00c0, code skipped:
            if (r2 >= r0) goto L_0x00cc;
     */
        /* JADX WARNING: Missing block: B:40:0x00c2, code skipped:
            r7.writeInt32NoTag(java.lang.reflect.Array.getInt(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:42:0x00cd, code skipped:
            if (r2 >= r0) goto L_0x00d9;
     */
        /* JADX WARNING: Missing block: B:43:0x00cf, code skipped:
            r7.writeUInt64NoTag(java.lang.reflect.Array.getLong(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:45:0x00da, code skipped:
            if (r2 >= r0) goto L_0x00e6;
     */
        /* JADX WARNING: Missing block: B:46:0x00dc, code skipped:
            r7.writeInt64NoTag(java.lang.reflect.Array.getLong(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:48:0x00e7, code skipped:
            if (r2 >= r0) goto L_0x00f3;
     */
        /* JADX WARNING: Missing block: B:49:0x00e9, code skipped:
            r7.writeFloatNoTag(java.lang.reflect.Array.getFloat(r6, r2));
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:51:0x00f4, code skipped:
            if (r2 >= r0) goto L_0x0103;
     */
        /* JADX WARNING: Missing block: B:52:0x00f6, code skipped:
            r7.writeDoubleNoTag(java.lang.reflect.Array.getDouble(r6, r2));
     */
        /* JADX WARNING: Missing block: B:53:0x00fd, code skipped:
            r2 = r2 + 1;
     */
        /* JADX WARNING: Missing block: B:74:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:75:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:76:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:77:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:78:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:79:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:80:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:81:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:82:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:83:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:84:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:85:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:86:?, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:87:?, code skipped:
            return;
     */
        public void writeRepeatedData(java.lang.Object r6, com.google.protobuf.nano.CodedOutputByteBufferNano r7) {
            /*
            r5 = this;
            r0 = r5.tag;
            r1 = r5.nonPackedTag;
            if (r0 != r1) goto L_0x000b;
        L_0x0006:
            super.writeRepeatedData(r6, r7);
            goto L_0x0103;
        L_0x000b:
            r0 = r5.tag;
            r1 = r5.packedTag;
            if (r0 != r1) goto L_0x010b;
        L_0x0011:
            r0 = java.lang.reflect.Array.getLength(r6);
            r1 = r5.computePackedDataSize(r6);
            r2 = r5.tag;	 Catch:{ IOException -> 0x0104 }
            r7.writeRawVarint32(r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeRawVarint32(r1);	 Catch:{ IOException -> 0x0104 }
            r1 = r5.type;	 Catch:{ IOException -> 0x0104 }
            r2 = 0;
            switch(r1) {
                case 1: goto L_0x00f4;
                case 2: goto L_0x00e7;
                case 3: goto L_0x00da;
                case 4: goto L_0x00cd;
                case 5: goto L_0x00c0;
                case 6: goto L_0x00b3;
                case 7: goto L_0x00a6;
                case 8: goto L_0x0099;
                default: goto L_0x0027;
            };	 Catch:{ IOException -> 0x0104 }
        L_0x0027:
            switch(r1) {
                case 13: goto L_0x008b;
                case 14: goto L_0x007d;
                case 15: goto L_0x006f;
                case 16: goto L_0x0061;
                case 17: goto L_0x0053;
                case 18: goto L_0x0045;
                default: goto L_0x002a;
            };	 Catch:{ IOException -> 0x0104 }
        L_0x002a:
            r6 = new java.lang.IllegalArgumentException;	 Catch:{ IOException -> 0x0104 }
            r7 = r5.type;	 Catch:{ IOException -> 0x0104 }
            r0 = 27;
            r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0104 }
            r1.<init>(r0);	 Catch:{ IOException -> 0x0104 }
            r0 = "Unpackable type ";
            r1.append(r0);	 Catch:{ IOException -> 0x0104 }
            r1.append(r7);	 Catch:{ IOException -> 0x0104 }
            r7 = r1.toString();	 Catch:{ IOException -> 0x0104 }
            r6.<init>(r7);	 Catch:{ IOException -> 0x0104 }
            throw r6;	 Catch:{ IOException -> 0x0104 }
        L_0x0045:
            if (r2 >= r0) goto L_0x0051;
        L_0x0047:
            r3 = java.lang.reflect.Array.getLong(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeSInt64NoTag(r3);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x0045;
        L_0x0051:
            goto L_0x0101;
        L_0x0053:
            if (r2 >= r0) goto L_0x005f;
        L_0x0055:
            r1 = java.lang.reflect.Array.getInt(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeSInt32NoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x0053;
        L_0x005f:
            goto L_0x0101;
        L_0x0061:
            if (r2 >= r0) goto L_0x006d;
        L_0x0063:
            r3 = java.lang.reflect.Array.getLong(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeSFixed64NoTag(r3);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x0061;
        L_0x006d:
            goto L_0x0101;
        L_0x006f:
            if (r2 >= r0) goto L_0x007b;
        L_0x0071:
            r1 = java.lang.reflect.Array.getInt(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeSFixed32NoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x006f;
        L_0x007b:
            goto L_0x0101;
        L_0x007d:
            if (r2 >= r0) goto L_0x0089;
        L_0x007f:
            r1 = java.lang.reflect.Array.getInt(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeEnumNoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x007d;
        L_0x0089:
            goto L_0x0101;
        L_0x008b:
            if (r2 >= r0) goto L_0x0097;
        L_0x008d:
            r1 = java.lang.reflect.Array.getInt(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeUInt32NoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x008b;
        L_0x0097:
            goto L_0x0101;
        L_0x0099:
            if (r2 >= r0) goto L_0x00a5;
        L_0x009b:
            r1 = java.lang.reflect.Array.getBoolean(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeBoolNoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x0099;
        L_0x00a5:
            goto L_0x0101;
        L_0x00a6:
            if (r2 >= r0) goto L_0x00b2;
        L_0x00a8:
            r1 = java.lang.reflect.Array.getInt(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeFixed32NoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x00a6;
        L_0x00b2:
            goto L_0x0101;
        L_0x00b3:
            if (r2 >= r0) goto L_0x00bf;
        L_0x00b5:
            r3 = java.lang.reflect.Array.getLong(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeFixed64NoTag(r3);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x00b3;
        L_0x00bf:
            goto L_0x0101;
        L_0x00c0:
            if (r2 >= r0) goto L_0x00cc;
        L_0x00c2:
            r1 = java.lang.reflect.Array.getInt(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeInt32NoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x00c0;
        L_0x00cc:
            goto L_0x0101;
        L_0x00cd:
            if (r2 >= r0) goto L_0x00d9;
        L_0x00cf:
            r3 = java.lang.reflect.Array.getLong(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeUInt64NoTag(r3);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x00cd;
        L_0x00d9:
            goto L_0x0101;
        L_0x00da:
            if (r2 >= r0) goto L_0x00e6;
        L_0x00dc:
            r3 = java.lang.reflect.Array.getLong(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeInt64NoTag(r3);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x00da;
        L_0x00e6:
            goto L_0x0101;
        L_0x00e7:
            if (r2 >= r0) goto L_0x00f3;
        L_0x00e9:
            r1 = java.lang.reflect.Array.getFloat(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeFloatNoTag(r1);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x00e7;
        L_0x00f3:
            goto L_0x0101;
        L_0x00f4:
            if (r2 >= r0) goto L_0x0100;
        L_0x00f6:
            r3 = java.lang.reflect.Array.getDouble(r6, r2);	 Catch:{ IOException -> 0x0104 }
            r7.writeDoubleNoTag(r3);	 Catch:{ IOException -> 0x0104 }
            r2 = r2 + 1;
            goto L_0x00f4;
        L_0x0103:
            return;
        L_0x0104:
            r6 = move-exception;
            r7 = new java.lang.IllegalStateException;
            r7.<init>(r6);
            throw r7;
        L_0x010b:
            r6 = new java.lang.IllegalArgumentException;
            r7 = r5.tag;
            r0 = r5.nonPackedTag;
            r1 = r5.packedTag;
            r2 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
            r3 = new java.lang.StringBuilder;
            r3.<init>(r2);
            r2 = "Unexpected repeated extension tag ";
            r3.append(r2);
            r3.append(r7);
            r7 = ", unequal to both non-packed variant ";
            r3.append(r7);
            r3.append(r0);
            r7 = " and packed variant ";
            r3.append(r7);
            r3.append(r1);
            r7 = r3.toString();
            r6.<init>(r7);
            throw r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.nano.Extension$PrimitiveExtension.writeRepeatedData(java.lang.Object, com.google.protobuf.nano.CodedOutputByteBufferNano):void");
        }

        /* JADX WARNING: Missing block: B:26:0x009d, code skipped:
            r0 = r0 * 4;
     */
        /* JADX WARNING: Missing block: B:27:0x00a0, code skipped:
            r0 = r0 * 8;
     */
        /* JADX WARNING: Missing block: B:36:?, code skipped:
            return r0;
     */
        private int computePackedDataSize(java.lang.Object r6) {
            /*
            r5 = this;
            r0 = java.lang.reflect.Array.getLength(r6);
            r1 = r5.type;
            r2 = 0;
            switch(r1) {
                case 1: goto L_0x00a0;
                case 2: goto L_0x009d;
                case 3: goto L_0x008d;
                case 4: goto L_0x007d;
                case 5: goto L_0x006d;
                case 6: goto L_0x00a0;
                case 7: goto L_0x009d;
                case 8: goto L_0x006b;
                default: goto L_0x000b;
            };
        L_0x000b:
            switch(r1) {
                case 13: goto L_0x005b;
                case 14: goto L_0x004b;
                case 15: goto L_0x009d;
                case 16: goto L_0x00a0;
                case 17: goto L_0x003a;
                case 18: goto L_0x0029;
                default: goto L_0x000e;
            };
        L_0x000e:
            r6 = new java.lang.IllegalArgumentException;
            r0 = r5.type;
            r1 = 40;
            r2 = new java.lang.StringBuilder;
            r2.<init>(r1);
            r1 = "Unexpected non-packable type ";
            r2.append(r1);
            r2.append(r0);
            r0 = r2.toString();
            r6.<init>(r0);
            throw r6;
        L_0x0029:
            r1 = r2;
        L_0x002a:
            if (r2 >= r0) goto L_0x0038;
        L_0x002c:
            r3 = java.lang.reflect.Array.getLong(r6, r2);
            r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeSInt64SizeNoTag(r3);
            r1 = r1 + r3;
            r2 = r2 + 1;
            goto L_0x002a;
        L_0x0038:
            goto L_0x00a4;
        L_0x003a:
            r1 = r2;
        L_0x003b:
            if (r2 >= r0) goto L_0x0049;
        L_0x003d:
            r3 = java.lang.reflect.Array.getInt(r6, r2);
            r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeSInt32SizeNoTag(r3);
            r1 = r1 + r3;
            r2 = r2 + 1;
            goto L_0x003b;
        L_0x0049:
            goto L_0x00a4;
        L_0x004b:
            r1 = r2;
        L_0x004c:
            if (r2 >= r0) goto L_0x005a;
        L_0x004e:
            r3 = java.lang.reflect.Array.getInt(r6, r2);
            r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeEnumSizeNoTag(r3);
            r1 = r1 + r3;
            r2 = r2 + 1;
            goto L_0x004c;
        L_0x005a:
            goto L_0x00a4;
        L_0x005b:
            r1 = r2;
        L_0x005c:
            if (r2 >= r0) goto L_0x006a;
        L_0x005e:
            r3 = java.lang.reflect.Array.getInt(r6, r2);
            r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeUInt32SizeNoTag(r3);
            r1 = r1 + r3;
            r2 = r2 + 1;
            goto L_0x005c;
        L_0x006a:
            goto L_0x00a4;
            goto L_0x00a3;
        L_0x006d:
            r1 = r2;
        L_0x006e:
            if (r2 >= r0) goto L_0x007c;
        L_0x0070:
            r3 = java.lang.reflect.Array.getInt(r6, r2);
            r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt32SizeNoTag(r3);
            r1 = r1 + r3;
            r2 = r2 + 1;
            goto L_0x006e;
        L_0x007c:
            goto L_0x00a4;
        L_0x007d:
            r1 = r2;
        L_0x007e:
            if (r2 >= r0) goto L_0x008c;
        L_0x0080:
            r3 = java.lang.reflect.Array.getLong(r6, r2);
            r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeUInt64SizeNoTag(r3);
            r1 = r1 + r3;
            r2 = r2 + 1;
            goto L_0x007e;
        L_0x008c:
            goto L_0x00a4;
        L_0x008d:
            r1 = r2;
        L_0x008e:
            if (r2 >= r0) goto L_0x009c;
        L_0x0090:
            r3 = java.lang.reflect.Array.getLong(r6, r2);
            r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt64SizeNoTag(r3);
            r1 = r1 + r3;
            r2 = r2 + 1;
            goto L_0x008e;
        L_0x009c:
            goto L_0x00a4;
        L_0x009d:
            r0 = r0 * 4;
            goto L_0x00a3;
        L_0x00a0:
            r0 = r0 * 8;
        L_0x00a3:
            r1 = r0;
        L_0x00a4:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.nano.Extension$PrimitiveExtension.computePackedDataSize(java.lang.Object):int");
        }

        /* Access modifiers changed, original: protected */
        public int computeRepeatedSerializedSize(Object obj) {
            if (this.tag == this.nonPackedTag) {
                return super.computeRepeatedSerializedSize(obj);
            }
            if (this.tag == this.packedTag) {
                int computePackedDataSize = computePackedDataSize(obj);
                return (computePackedDataSize + CodedOutputByteBufferNano.computeRawVarint32Size(computePackedDataSize)) + CodedOutputByteBufferNano.computeRawVarint32Size(this.tag);
            }
            int i = this.tag;
            int i2 = this.nonPackedTag;
            int i3 = this.packedTag;
            StringBuilder stringBuilder = new StringBuilder(124);
            stringBuilder.append("Unexpected repeated extension tag ");
            stringBuilder.append(i);
            stringBuilder.append(", unequal to both non-packed variant ");
            stringBuilder.append(i2);
            stringBuilder.append(" and packed variant ");
            stringBuilder.append(i3);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        /* Access modifiers changed, original: protected|final */
        public final int computeSingularSerializedSize(Object obj) {
            int tagFieldNumber = WireFormatNano.getTagFieldNumber(this.tag);
            switch (this.type) {
                case 1:
                    return CodedOutputByteBufferNano.computeDoubleSize(tagFieldNumber, ((Double) obj).doubleValue());
                case 2:
                    return CodedOutputByteBufferNano.computeFloatSize(tagFieldNumber, ((Float) obj).floatValue());
                case 3:
                    return CodedOutputByteBufferNano.computeInt64Size(tagFieldNumber, ((Long) obj).longValue());
                case 4:
                    return CodedOutputByteBufferNano.computeUInt64Size(tagFieldNumber, ((Long) obj).longValue());
                case 5:
                    return CodedOutputByteBufferNano.computeInt32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 6:
                    return CodedOutputByteBufferNano.computeFixed64Size(tagFieldNumber, ((Long) obj).longValue());
                case 7:
                    return CodedOutputByteBufferNano.computeFixed32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 8:
                    return CodedOutputByteBufferNano.computeBoolSize(tagFieldNumber, ((Boolean) obj).booleanValue());
                case 9:
                    return CodedOutputByteBufferNano.computeStringSize(tagFieldNumber, (String) obj);
                case 12:
                    return CodedOutputByteBufferNano.computeBytesSize(tagFieldNumber, (byte[]) obj);
                case 13:
                    return CodedOutputByteBufferNano.computeUInt32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 14:
                    return CodedOutputByteBufferNano.computeEnumSize(tagFieldNumber, ((Integer) obj).intValue());
                case 15:
                    return CodedOutputByteBufferNano.computeSFixed32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 16:
                    return CodedOutputByteBufferNano.computeSFixed64Size(tagFieldNumber, ((Long) obj).longValue());
                case 17:
                    return CodedOutputByteBufferNano.computeSInt32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 18:
                    return CodedOutputByteBufferNano.computeSInt64Size(tagFieldNumber, ((Long) obj).longValue());
                default:
                    tagFieldNumber = this.type;
                    StringBuilder stringBuilder = new StringBuilder(24);
                    stringBuilder.append("Unknown type ");
                    stringBuilder.append(tagFieldNumber);
                    throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
    }

    @Deprecated
    public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T> createMessageTyped(int i, Class<T> cls, int i2) {
        return new Extension(i, cls, i2, false);
    }

    public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T> createMessageTyped(int i, Class<T> cls, long j) {
        return new Extension(i, cls, (int) j, false);
    }

    public static <M extends ExtendableMessageNano<M>, T extends GeneratedMessageLite<?, ?>> Extension<M, T> createMessageLiteTyped(int i, Class<T> cls, T t, long j) {
        return new Extension(i, (Class) cls, (GeneratedMessageLite) t, (int) j, false);
    }

    public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T[]> createRepeatedMessageTyped(int i, Class<T[]> cls, long j) {
        return new Extension(i, cls, (int) j, true);
    }

    public static <M extends ExtendableMessageNano<M>, T extends GeneratedMessageLite<?, ?>> Extension<M, T[]> createRepeatedMessageLiteTyped(int i, Class<T[]> cls, T t, long j) {
        return new Extension(i, (Class) cls, (GeneratedMessageLite) t, (int) j, true);
    }

    public static <M extends ExtendableMessageNano<M>, T> Extension<M, T> createPrimitiveTyped(int i, Class<T> cls, long j) {
        return new PrimitiveExtension(i, cls, (int) j, false, 0, 0);
    }

    public static <M extends ExtendableMessageNano<M>, T> Extension<M, T> createRepeatedPrimitiveTyped(int i, Class<T> cls, long j, long j2, long j3) {
        return new PrimitiveExtension(i, cls, (int) j, true, (int) j2, (int) j3);
    }

    private Extension(int i, Class<T> cls, int i2, boolean z) {
        this(i, (Class) cls, null, i2, z);
    }

    private Extension(int i, Class<T> cls, GeneratedMessageLite<?, ?> generatedMessageLite, int i2, boolean z) {
        this.type = i;
        this.clazz = cls;
        this.tag = i2;
        this.repeated = z;
        this.defaultInstance = generatedMessageLite;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Extension)) {
            return false;
        }
        Extension extension = (Extension) obj;
        if (!(this.type == extension.type && this.clazz == extension.clazz && this.tag == extension.tag && this.repeated == extension.repeated)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * (((((1147 + this.type) * 31) + this.clazz.hashCode()) * 31) + this.tag)) + this.repeated;
    }

    /* Access modifiers changed, original: final */
    public final T getValueFrom(List<UnknownFieldData> list) {
        if (list == null) {
            return null;
        }
        return this.repeated ? getRepeatedValueFrom(list) : getSingularValueFrom(list);
    }

    private T getRepeatedValueFrom(List<UnknownFieldData> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            UnknownFieldData unknownFieldData = (UnknownFieldData) list.get(i2);
            if (unknownFieldData.bytes.length != 0) {
                readDataInto(unknownFieldData, arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        Object cast = this.clazz.cast(Array.newInstance(this.clazz.getComponentType(), size));
        while (i < size) {
            Array.set(cast, i, arrayList.get(i));
            i++;
        }
        return cast;
    }

    private T getSingularValueFrom(List<UnknownFieldData> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.clazz.cast(readData(CodedInputByteBufferNano.newInstance(((UnknownFieldData) list.get(list.size() - 1)).bytes)));
    }

    /* Access modifiers changed, original: protected */
    public Object readData(CodedInputByteBufferNano codedInputByteBufferNano) {
        String valueOf;
        StringBuilder stringBuilder;
        Class componentType = this.repeated ? this.clazz.getComponentType() : this.clazz;
        try {
            MessageNano messageNano;
            switch (this.type) {
                case 10:
                    messageNano = (MessageNano) componentType.newInstance();
                    codedInputByteBufferNano.readGroup(messageNano, WireFormatNano.getTagFieldNumber(this.tag));
                    return messageNano;
                case 11:
                    if (this.defaultInstance != null) {
                        return codedInputByteBufferNano.readMessageLite(this.defaultInstance.getParserForType());
                    }
                    messageNano = (MessageNano) componentType.newInstance();
                    codedInputByteBufferNano.readMessage(messageNano);
                    return messageNano;
                default:
                    int i = this.type;
                    StringBuilder stringBuilder2 = new StringBuilder(24);
                    stringBuilder2.append("Unknown type ");
                    stringBuilder2.append(i);
                    throw new IllegalArgumentException(stringBuilder2.toString());
            }
        } catch (InstantiationException e) {
            valueOf = String.valueOf(componentType);
            stringBuilder = new StringBuilder(33 + String.valueOf(valueOf).length());
            stringBuilder.append("Error creating instance of class ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString(), e);
        } catch (IllegalAccessException e2) {
            valueOf = String.valueOf(componentType);
            stringBuilder = new StringBuilder(33 + String.valueOf(valueOf).length());
            stringBuilder.append("Error creating instance of class ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* Access modifiers changed, original: protected */
    public void readDataInto(UnknownFieldData unknownFieldData, List<Object> list) {
        list.add(readData(CodedInputByteBufferNano.newInstance(unknownFieldData.bytes)));
    }

    /* Access modifiers changed, original: 0000 */
    public void writeTo(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if (this.repeated) {
            writeRepeatedData(obj, codedOutputByteBufferNano);
        } else {
            writeSingularData(obj, codedOutputByteBufferNano);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void writeAsMessageSetTo(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if (this.repeated) {
            writeRepeatedDataAsMessageSet(obj, codedOutputByteBufferNano);
        } else {
            writeSingularDataAsMessageSet(obj, codedOutputByteBufferNano);
        }
    }

    /* Access modifiers changed, original: protected */
    public void writeSingularData(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) {
        try {
            codedOutputByteBufferNano.writeRawVarint32(this.tag);
            switch (this.type) {
                case 10:
                    int tagFieldNumber = WireFormatNano.getTagFieldNumber(this.tag);
                    if (this.defaultInstance == null) {
                        codedOutputByteBufferNano.writeGroupNoTag((MessageNano) obj);
                    } else {
                        codedOutputByteBufferNano.writeGroupNoTag((MessageLite) obj);
                    }
                    codedOutputByteBufferNano.writeTag(tagFieldNumber, 4);
                    break;
                case 11:
                    if (this.defaultInstance != null) {
                        codedOutputByteBufferNano.writeMessageNoTag((MessageLite) obj);
                        break;
                    } else {
                        codedOutputByteBufferNano.writeMessageNoTag((MessageNano) obj);
                        break;
                    }
                default:
                    int i = this.type;
                    StringBuilder stringBuilder = new StringBuilder(24);
                    stringBuilder.append("Unknown type ");
                    stringBuilder.append(i);
                    throw new IllegalArgumentException(stringBuilder.toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void writeSingularDataAsMessageSet(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        codedOutputByteBufferNano.writeMessageSetExtension(WireFormatNano.getTagFieldNumber(this.tag), (MessageNano) obj);
    }

    /* Access modifiers changed, original: protected */
    public void writeRepeatedData(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                writeSingularData(obj2, codedOutputByteBufferNano);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void writeRepeatedDataAsMessageSet(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                writeSingularDataAsMessageSet(obj2, codedOutputByteBufferNano);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int computeSerializedSize(Object obj) {
        if (this.repeated) {
            return computeRepeatedSerializedSize(obj);
        }
        return computeSingularSerializedSize(obj);
    }

    /* Access modifiers changed, original: 0000 */
    public int computeSerializedSizeAsMessageSet(Object obj) {
        if (this.repeated) {
            return computeRepeatedSerializedSizeAsMessageSet(obj);
        }
        return computeSingularSerializedSizeAsMessageSet(obj);
    }

    /* Access modifiers changed, original: protected */
    public int computeRepeatedSerializedSize(Object obj) {
        int length = Array.getLength(obj);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (Array.get(obj, i) != null) {
                i2 += computeSingularSerializedSize(Array.get(obj, i));
            }
            i++;
        }
        return i2;
    }

    /* Access modifiers changed, original: protected */
    public int computeSingularSerializedSize(Object obj) {
        int tagFieldNumber = WireFormatNano.getTagFieldNumber(this.tag);
        switch (this.type) {
            case 10:
                if (this.defaultInstance == null) {
                    return CodedOutputByteBufferNano.computeGroupSize(tagFieldNumber, (MessageNano) obj);
                }
                return CodedOutputStream.computeGroupSize(tagFieldNumber, (MessageLite) obj);
            case 11:
                if (this.defaultInstance == null) {
                    return CodedOutputByteBufferNano.computeMessageSize(tagFieldNumber, (MessageNano) obj);
                }
                return CodedOutputStream.computeMessageSize(tagFieldNumber, (MessageLite) obj);
            default:
                tagFieldNumber = this.type;
                StringBuilder stringBuilder = new StringBuilder(24);
                stringBuilder.append("Unknown type ");
                stringBuilder.append(tagFieldNumber);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public int computeRepeatedSerializedSizeAsMessageSet(Object obj) {
        int length = Array.getLength(obj);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (Array.get(obj, i) != null) {
                i2 += computeSingularSerializedSizeAsMessageSet(Array.get(obj, i));
            }
            i++;
        }
        return i2;
    }

    /* Access modifiers changed, original: protected */
    public int computeSingularSerializedSizeAsMessageSet(Object obj) {
        return CodedOutputByteBufferNano.computeMessageSetExtensionSize(WireFormatNano.getTagFieldNumber(this.tag), (MessageNano) obj);
    }
}
