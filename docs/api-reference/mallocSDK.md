# 🧠 MallocSDK Class Reference

The `MallocSDK` class is the main interface for interacting with the Malloc Security SDK. It provides a variety of methods to perform security and privacy scans, such as detecting root access, spyware, suspicious apps, and dangerous files.

---

## 🧩 Initialization

These methods are used to initialize the SDK. For full usage instructions, see the [Initialization & Authentication](./authentication.md) guide.

```java
public static void initializeAsync(Context context, String key, InitializationFinished callback)
public static synchronized Boolean initializeSync(Context context, String key)
```

---

## 🪓 Root Detection

Check whether the device is rooted. This includes various root indicators.

```java
public static void rootCheckAsync(@NonNull ScanFinishedCallback callback)
public static JSONObject rootCheckSync()
```

---

## 🌐 Domain Check

Validate and categorize a domain by contacting Malloc's backend service.

```java
public static void checkURLAsync(String domain, @NonNull ScanFinishedCallback callback)
public static JSONObject checkURLSync(String domain)
```

---

## 🕵️‍♀️ Spyware Detection

Scan the device for signs that may indicate it is compromised by spyware.

```java
public static void scanForDeviceSpywareAsync(@NonNull ScanFinishedCallback callback)
public static JSONObject scanForDeviceSpywareSync()
```

---

## 📱 App Scanning

Scan installed apps for spyware, dangerous permissions, and enabled accessibility services.

### 🔍 Per-App Scanning (with updates)
```java
public static void scanAppsPerAppAsync(
    @NonNull AppsScanningUpdatesCallback appsScanningUpdatesCallback,
    boolean include_system_apps,
    boolean include_apps_with_no_internet_access
)
```

### 🧾 Summary Scanning (final result only)
```java
public static void scanAppsAsync(
    @NonNull ScanFinishedCallback callback,
    boolean include_system_apps,
    boolean include_apps_with_no_internet_access
)
```

---

## 📂 File Scanning

Scan the device’s downloads folder for potentially malicious APKs.

### 🔍 With progress updates
```java
public static void scanDownloadedFilesAsync(@NonNull FilesScanningUpdatesCallback callback)
```

### ✅ Sync scan with progress callback
```java
public static JSONObject scanDownloadedFilesSync(FilesScanningUpdatesCallback callback)
```

---

## 🛠 Callback Interfaces

### ✅ InitializationFinished
```java
public interface InitializationFinished {
    void onInitializationFinished(Boolean access_granted);
}
```

### 🧪 ScanFinishedCallback
```java
public interface ScanFinishedCallback {
    void onScanFinished(JSONObject result);
}
```

### 📲 AppsScanningUpdatesCallback
```java
public interface AppsScanningUpdatesCallback {
    void onScanFinished(JSONObject result);
    void onScanningAppUpdate(ApplicationInfo now_scanning_application_info);
    void onAppsToScanListReady(List<ApplicationInfo> apps_to_scan);
    void onScanProgressUpdate(ApplicationInfo now_scanning_application_info, int total_apps_scanned, int total_apps_to_scan);
}
```

### 📁 FilesScanningUpdatesCallback
```java
public interface FilesScanningUpdatesCallback {
    void onScanFinished(JSONObject result);
    void onScanningFileUpdate(String file_name, String file_path);
    void onFilesListGenerated(List<FileToScan> files_to_scan);
    void onScanProgressUpdate(String file_name, String file_path, int now_scanning_index, int total_files_to_scan);
}
```

---

## 📌 Notes

- **Async methods**: Ideal for UI-safe operations. Use callbacks to handle results.
- **Sync methods**: Useful for background threads where immediate results are required. **Never use sync methods on the main/UI thread.**
- **Threading**: All async methods handle threading internally. For sync methods, you must manage threads yourself.

---

## ✅ Best Practices

- Always initialize the SDK before calling any functions.
- Avoid blocking the UI thread by using background threads or the async variants.
- Never hard-code API keys in production builds.
