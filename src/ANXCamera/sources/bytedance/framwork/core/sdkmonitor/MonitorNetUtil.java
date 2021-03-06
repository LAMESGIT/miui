package bytedance.framwork.core.sdkmonitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.frameworks.core.encrypt.RequestEncryptUtils;
import com.bytedance.frameworks.core.encrypt.TTEncryptUtils;
import com.ss.android.vesdk.runtime.cloudconfig.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MonitorNetUtil {
    private static final boolean DEBUG_MOBILE = false;
    private static IRequestIntercept sRequestIntercept;

    public enum CompressType {
        NONE(0),
        GZIP(1),
        DEFLATER(2);
        
        final int nativeInt;

        private CompressType(int i) {
            this.nativeInt = i;
        }
    }

    public interface IRequestIntercept {
        String addRequestVerifyParams(String str, byte[] bArr);
    }

    public enum NetworkType {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);
        
        final int nativeInt;

        private NetworkType(int i) {
            this.nativeInt = i;
        }

        public int getValue() {
            return this.nativeInt;
        }
    }

    public static boolean isWifi(Context context) {
        boolean z = false;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    if (1 == activeNetworkInfo.getType()) {
                        z = true;
                    }
                    return z;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static NetworkType getNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    int type = activeNetworkInfo.getType();
                    if (1 == type) {
                        return NetworkType.WIFI;
                    }
                    if (type != 0) {
                        return NetworkType.MOBILE;
                    }
                    switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return NetworkType.MOBILE_3G;
                        case 13:
                            return NetworkType.MOBILE_4G;
                        default:
                            return NetworkType.MOBILE;
                    }
                }
            }
            return NetworkType.NONE;
        } catch (Throwable th) {
            return NetworkType.MOBILE;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getNetWorkType(Context context) {
        if (context == null) {
            return null;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    return activeNetworkInfo.getTypeName();
                }
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    public static void setRequestIntercept(IRequestIntercept iRequestIntercept) {
        sRequestIntercept = iRequestIntercept;
    }

    public static byte[] getRequest(String str, String str2, boolean z) throws Throwable {
        if (TextUtils.isDigitsOnly(str)) {
            return null;
        }
        return excuteRequest(str, null, str2, null, "GET", false, z);
    }

    public static byte[] excuteRequest(String str, byte[] bArr, String str2, String str3, String str4, boolean z, boolean z2) throws Throwable {
        URL url;
        if (sRequestIntercept != null) {
            str = sRequestIntercept.addRequestVerifyParams(str, bArr);
        }
        LinkedList<Pair> linkedList = new LinkedList();
        if (z2) {
            url = new URL(RequestEncryptUtils.tryEncryptRequest(str, linkedList));
        } else {
            url = new URL(str);
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (z2 && !linkedList.isEmpty()) {
            for (Pair pair : linkedList) {
                if (pair != null) {
                    httpURLConnection.setRequestProperty((String) pair.first, (String) pair.second);
                }
            }
        }
        if (z) {
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setDoOutput(false);
        }
        if (str2 != null) {
            httpURLConnection.setRequestProperty("Content-Type", str2);
        }
        if (str3 != null) {
            httpURLConnection.setRequestProperty("Content-Encoding", str3);
        }
        httpURLConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, HttpRequest.ENCODING_GZIP);
        if (str4 != null) {
            httpURLConnection.setRequestMethod(str4);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                byte[] toByteArray;
                InputStream inputStream = httpURLConnection.getInputStream();
                str = httpURLConnection.getContentEncoding();
                if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase(HttpRequest.ENCODING_GZIP)) {
                    toByteArray = toByteArray(inputStream);
                } else {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
                    toByteArray = toByteArray(gZIPInputStream);
                    gZIPInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return toByteArray;
            }
            throw new HttpResponseException(responseCode, httpURLConnection.getResponseMessage());
        }
        throw new IllegalArgumentException("request method is not null");
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] excutePost(long j, String str, byte[] bArr, CompressType compressType, String str2, boolean z) throws Throwable {
        String str3 = null;
        if (str == null) {
            return null;
        }
        if (bArr == null) {
            bArr = new byte[0];
        }
        int length = bArr.length;
        if (CompressType.GZIP == compressType && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                bArr = byteArrayOutputStream.toByteArray();
                str3 = HttpRequest.ENCODING_GZIP;
            } catch (Throwable th) {
                byteArrayOutputStream = th;
                return null;
            } finally {
                gZIPOutputStream.close();
            }
        } else if (CompressType.DEFLATER == compressType && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(8192);
            Deflater deflater = new Deflater();
            deflater.setInput(bArr);
            deflater.finish();
            bArr = new byte[8192];
            while (!deflater.finished()) {
                byteArrayOutputStream2.write(bArr, 0, deflater.deflate(bArr));
            }
            deflater.end();
            bArr = byteArrayOutputStream2.toByteArray();
            str3 = "deflate";
        }
        byte[] bArr2 = bArr;
        String str4 = str3;
        if (z) {
            byte[] bArr3;
            byte[] encrypt = TTEncryptUtils.encrypt(bArr2, bArr2.length);
            if (encrypt != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("&tt_data=a");
                str = stringBuilder.toString();
                bArr3 = encrypt;
                str3 = str;
                str = "application/octet-stream;tt-data=a";
            } else {
                str3 = str;
                str = str2;
                bArr3 = bArr2;
            }
            return excuteRequest(str3, bArr3, str, str4, "POST", true, true);
        }
        return excuteRequest(str, bArr2, str2, str4, "POST", true, false);
    }

    public static String uploadFile(String str, File file, Map<String, String> map, String str2, boolean z) throws Throwable {
        Throwable th;
        if (TextUtils.isEmpty(str) || file == null || !file.exists()) {
            throw new IllegalArgumentException("url and file not be null ");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tt_file_upload");
        stringBuilder.append(UUID.randomUUID().toString());
        String stringBuilder2 = stringBuilder.toString();
        String str3 = "--";
        String str4 = "\r\n";
        String str5 = "multipart/form-data";
        File file2 = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", str2);
            httpURLConnection.setRequestProperty("connection", "keep-alive");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str5);
            stringBuilder3.append(";boundary=");
            stringBuilder3.append(stringBuilder2);
            httpURLConnection.setRequestProperty("Content-Type", stringBuilder3.toString());
            if (z) {
                httpURLConnection.setRequestProperty("Content-Encoding", HttpRequest.ENCODING_GZIP);
            }
            if (!(map == null || map.isEmpty())) {
                for (Entry entry : map.entrySet()) {
                    String str6 = (String) entry.getKey();
                    str5 = (String) entry.getValue();
                    if (!TextUtils.isEmpty(str6)) {
                        if (!TextUtils.isEmpty(str5)) {
                            httpURLConnection.setRequestProperty(str6, str5);
                        }
                    }
                }
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            if (file == null) {
                return null;
            }
            StringBuilder stringBuilder4;
            InputStream fileInputStream;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str3);
            stringBuffer.append(stringBuilder2);
            stringBuffer.append(str4);
            if (z) {
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("Content-Disposition: form-data; name=\"file\"; filename=\"");
                stringBuilder4.append(file.getName());
                stringBuilder4.append(".zip\"");
                stringBuilder4.append(str4);
                stringBuffer.append(stringBuilder4.toString());
            }
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Content-Type: application/octet-stream; charset=");
            stringBuilder4.append(str2);
            stringBuilder4.append(str4);
            stringBuffer.append(stringBuilder4.toString());
            stringBuffer.append(str4);
            dataOutputStream.write(stringBuffer.toString().getBytes());
            if (z) {
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append(file.getAbsolutePath());
                stringBuilder5.append("_tmp.zip");
                File file3 = new File(stringBuilder5.toString());
                try {
                    if (file3.exists()) {
                        file3.delete();
                    }
                    zipFile(file, file3);
                    fileInputStream = new FileInputStream(file3);
                    file2 = file3;
                } catch (Throwable th2) {
                    th = th2;
                    file2 = file3;
                    if (file2 != null && file2.exists()) {
                        file2.delete();
                    }
                    throw th;
                }
            }
            fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                dataOutputStream.write(bArr, 0, read);
            }
            fileInputStream.close();
            dataOutputStream.write(str4.getBytes());
            StringBuilder stringBuilder6 = new StringBuilder();
            stringBuilder6.append(str3);
            stringBuilder6.append(stringBuilder2);
            stringBuilder6.append(str3);
            stringBuilder6.append(str4);
            dataOutputStream.write(stringBuilder6.toString().getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                byte[] toByteArray;
                fileInputStream = httpURLConnection.getInputStream();
                str = httpURLConnection.getContentEncoding();
                if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase(HttpRequest.ENCODING_GZIP)) {
                    toByteArray = toByteArray(fileInputStream);
                } else {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(fileInputStream);
                    toByteArray = toByteArray(gZIPInputStream);
                    gZIPInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                str = new String(toByteArray);
                if (file2 != null && file2.exists()) {
                    file2.delete();
                }
                return str;
            }
            throw new HttpResponseException(responseCode, httpURLConnection.getResponseMessage());
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004d  */
    public static void zipFile(java.io.File r5, java.io.File r6) throws java.io.IOException {
        /*
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ all -> 0x003e }
        r1.<init>(r5);	 Catch:{ all -> 0x003e }
        r5 = new java.io.FileOutputStream;	 Catch:{ all -> 0x003b }
        r5.<init>(r6);	 Catch:{ all -> 0x003b }
        r6 = new java.util.zip.GZIPOutputStream;	 Catch:{ all -> 0x0036 }
        r6.<init>(r5);	 Catch:{ all -> 0x0036 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ all -> 0x0030 }
    L_0x0017:
        r2 = r1.read(r0);	 Catch:{ all -> 0x0030 }
        r3 = -1;
        if (r2 == r3) goto L_0x0023;
    L_0x001e:
        r3 = 0;
        r6.write(r0, r3, r2);	 Catch:{ all -> 0x0030 }
        goto L_0x0017;
        r6.close();
        r5.close();
        r1.close();
        return;
    L_0x0030:
        r0 = move-exception;
        r4 = r6;
        r6 = r5;
        r5 = r0;
        r0 = r4;
        goto L_0x0041;
    L_0x0036:
        r6 = move-exception;
        r4 = r6;
        r6 = r5;
        r5 = r4;
        goto L_0x0041;
    L_0x003b:
        r5 = move-exception;
        r6 = r0;
        goto L_0x0041;
    L_0x003e:
        r5 = move-exception;
        r6 = r0;
        r1 = r6;
    L_0x0041:
        if (r0 == 0) goto L_0x0046;
    L_0x0043:
        r0.close();
    L_0x0046:
        if (r6 == 0) goto L_0x004b;
    L_0x0048:
        r6.close();
    L_0x004b:
        if (r1 == 0) goto L_0x0050;
    L_0x004d:
        r1.close();
    L_0x0050:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: bytedance.framwork.core.sdkmonitor.MonitorNetUtil.zipFile(java.io.File, java.io.File):void");
    }
}
