# MallocSDK Class Reference
---

Here we provide a quick overview of the methods.
You can find an extensive description and usage guide for each method in sub-guides provided.


Find an example how to use the Malloc SDK class and how to run the methods at: [Github](https://github.com/mallocsecurity/sdk/)


---

The `MallocSDK` class is the main interface for interacting with the Malloc Security SDK. It provides a variety of methods to perform security and privacy scans, such as detecting root access, spyware, suspicious apps, and dangerous files.

## 🧩 Initialization

These methods are used to initialize the SDK. For full usage instructions, see the [Initialization & Authentication](./authentication.md) guide.

> This operation is available in both **synchronous** and **asynchronous** versions (with callbacks).

```java
// Asynchronous
void initializeAsync(Context context, String key, InitializationFinished callback)

// Synchronous
Boolean initializeSync(Context context, String key)
```

---

## 🪓 Root Detection

Check whether the device is rooted. This includes various root indicators. See the [Root Check](./rootCheck.md) guide.

> This operation is available in both **synchronous** and **asynchronous** versions (with callbacks).

```java
// Asynchronous
void rootCheckAsync(@NonNull ScanFinishedCallback callback)

// Synchronous
static JSONObject rootCheckSync()
```

---

## 🌐 URL Threat Detection 

Validate and categorize a domain by contacting Malloc's backend service, see the [URL Category Check](./checkUrl.md) guide.

> This operation is available in both **synchronous** and **asynchronous** versions (with callbacks).

```java
// Asynchronous
void checkURLAsync(String domain, @NonNull ScanFinishedCallback callback)

// Synchronous
JSONObject checkURLSync(String domain)
```

---

## 🕵️ Spyware Indicators Check

Scan the device for signs that may indicate it is compromised by spyware, see the [Spyware Indicators Check](./scanForDeviceSpyware.md) guide.

> This operation is available in both **synchronous** and **asynchronous** versions (with callbacks).

```java
// Asynchronous
void scanForDeviceSpywareAsync(@NonNull ScanFinishedCallback callback)

// Synchronous
JSONObject scanForDeviceSpywareSync()
```

---

## 📱 App Scanning

Scan installed apps for spyware, dangerous permissions, and enabled accessibility services, see the [Scan Installed Apps](./scanApps.md) guide.

> This operation is available in both **synchronous** and **asynchronous** versions (with callbacks).

```java
// Per-App Scanning (Asynchronous with updates)
void scanAppsPerAppAsync(
    @NonNull AppsScanningUpdatesCallback appsScanningUpdatesCallback,
    boolean include_system_apps,
    boolean include_apps_with_no_internet_access,
    boolean scan_apps_sha
)

// Summary Scanning (Asynchronous with final result only)
void scanAppsAsync(
    @NonNull ScanFinishedCallback callback,
    boolean include_system_apps,
    boolean include_apps_with_no_internet_access,
    boolean scan_apps_sha
)

// Summary Scanning (Synchronous)
JSONObject scanAppsPerScanSync(
    boolean include_system_apps,
    boolean include_apps_with_no_internet_access,
    boolean scan_apps_sha
)
```

---


## 📂 File Scanning

Scan the device’s files for potentially malicious content. You can scan the **Downloads folder** or specify a list of file paths to scan.  
See the full [File Scanning Guide](./scanDownloadedFiles.md) for details.

> Both operations are available in **synchronous** and **asynchronous** versions (with callbacks).

```java
// Asynchronous with progress updates
void scanDownloadedFilesAsync(@NonNull FilesScanningUpdatesCallback callback)
void scanFilesAsync(List<String> file_paths_to_scan, @NonNull FilesScanningUpdatesCallback callback)

// Synchronous
JSONObject scanDownloadedFilesSync()
JSONObject scanFilesSync(List<String> file_paths_to_scan)
```

### Permissions Required

Before scanning files, the appropriate permission must be granted based on the Android version. The SDK provides methods to handle prompting the user to grant the required permissions. 


> The following method displays a customizable dialog to the user before requesting the required file scanning permission.
```java
void requestFilesScannerPermissionWithDialog(Activity activity, @Nullable String title, @Nullable String description)
```
- If `title` and `description` are not null, the dialog uses the provided strings.
- If either is null, the SDK uses the following default values:
    - **Title:** `"Permission Required"`
    - **Description:** `"This app needs access to all files on your device so it can scan for malicious files and help keep your device secure. Please grant this permission on the next screen."`
Use this method if you want to provide additional context to users before prompting for sensitive permissions.


> The following method does not show any additional dialogs or explanations before the system permission prompt.
```java
void requestFilesScannerPermission(Activity activity)
```

The above methods request the necessary file scanning permission from the user, automatically handling the correct permission based on the device's Android version.
* For devices running **Android R (API level 30)** and above, this prompts the user for `android.permission.MANAGE_EXTERNAL_STORAGE` permission.
* For devices running **below Android R**, it requests `android.permission.READ_EXTERNAL_STORAGE`.

> This permission is essential for enabling the **File Scanning** feature in the SDK.

---

## 🛠 Callback Interfaces

### InitializationFinished
```java
interface InitializationFinished {
    void onInitializationFinished(Boolean access_granted);
}
```

### ScanFinishedCallback
```java
interface ScanFinishedCallback {
    void onScanFinished(JSONObject result);
}
```

### AppsScanningUpdatesCallback
```java
interface AppsScanningUpdatesCallback {
    void onScanFinished(JSONObject result);
    void onScanningAppUpdate(ApplicationInfo now_scanning_application_info);
    void onAppsToScanListReady(List<ApplicationInfo> apps_to_scan);
    void onScanProgressUpdate(ApplicationInfo now_scanning_application_info, int total_apps_scanned, int total_apps_to_scan);
}
```

### FilesScanningUpdatesCallback
```java
interface FilesScanningUpdatesCallback {
    void onScanFinished(JSONObject result);
    void onScanningFileUpdate(String file_name, String file_path);
    void onFilesListGenerated(List<FileToScan> files_to_scan);
    void onScanProgressUpdate(String file_name, String file_path, int now_scanning_index, int total_files_to_scan);
}
```

---

## Notes

- **Async methods**: Ideal for UI-safe operations. Use callbacks to handle results.
- **Sync methods**: Useful for background threads where immediate results are required. **Never use sync methods on the main/UI thread.**
- **Threading**: All async methods handle threading internally. For sync methods, you must manage threads yourself.

---

## ✅ Best Practices

- Always initialize the SDK before calling any functions.
- Avoid blocking the UI thread by using background threads or the async variants.
- Never hard-code API keys in production builds.
