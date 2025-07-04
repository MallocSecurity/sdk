
# 📂 File Scanning

The **Malloc Security SDK** provides a file scanning feature that inspects files located on the device.  
It identifies potentially malicious files by sending the file signatures to the Malloc backend threat intelligence service.

Starting from **v0.2.5**, you can scan **any files** by providing their full paths using the new methods:  
✅ `scanFilesAsync` (asynchronous, with progress updates)  
✅ `scanFilesSync` (synchronous)  
Prior to v0.2.5, scanning only supported the **Downloads folder**.

---

## 📋 Permissions Required

Before scanning files, the appropriate permission must be granted based on the Android version.  
The method [requestFilesScannerPermission()](./mallocSDK.md#permissions-required) can be used to request the appropriate permissions.

---

# 🔍 Scan Specific File Paths (v0.2.5+)

Use when you want to scan arbitrary files by specifying their full paths.

> This operation is available in both **asynchronous** and **synchronous** versions.

## Asynchronous

> Preferred for better UI responsiveness.

```java
List<String> file_paths_to_scan = List.of(
    "/storage/emulated/0/Download/Tasker.6.3.0-beta.apk",
    "/storage/emulated/0/DCIM/Camera/PXL_20250721_110106038.jpg"
);

MallocSDK.scanFilesAsync(file_paths_to_scan, new MallocSDK.FilesScanningUpdatesCallback() {
    @Override
    public void onFilesListGenerated(List<FileToScan> files_to_scan) {
        // The files_to_scan list contains a list of the FileToScan of the apps to be scanned
    }

    @Override
    public void onScanningFileUpdate(String file_name, String file_path) {
        // The file_name is the file name of the file that is currenlty in scan
        // The file_path is the path of the file that is currenlty in scan
    }

    @Override
    public void onScanProgressUpdate(String file_name, String file_path, int total_files_scanned, int total_files_to_scan) {
        // The file_name is the file name of the file that is currenlty in scan
        // The file_path is the path of the file that is currenlty in scan
        // The total_files_scanned is the total number of the scanned files
        // The total_files_to_scan is the total number of files to scan
    }

    @Override
    public void onScanFinished(JSONObject result) {
        // Use the result
    }
});
```

The **FileToScan** Class represents a file that is queued for scanning and it contains the display name of the file and the Uri reference pointing to the file's location on the device. See more in [FilesToScan](./fileToScan.md).

## Synchronous

> Do **not** call this on the main/UI thread.

```java
executor.execute(new Runnable() {
    @Override
    public void run() {
        List<String> file_paths_to_scan = List.of(
            "/storage/emulated/0/Download/insecurebankv2__1_.apk",
            "/storage/emulated/0/DCIM/Camera/PXL_20250721_110106038.jpg"
        );
        JSONObject result = MallocSDK.scanFilesSync(file_paths_to_scan);
        // Use the result
    }
});
```

## 📦 Sample JSON Response for `scanFilesAsync` / `scanFilesSync`

```json
{
  "status": "success",
  "details": {
    "filesScanResultsArray": [
       {
            "sha256": "b18af2a0e44d7634bbcdf93664d9c78a2695e050393fcfbb5e8b91f902d194a4",
            "file_name": "insecurebankv2__1_.apk",
            "file_path": "/storage/emulated/0/Download/insecurebankv2__1_.apk",
            "apk_name": "InsecureBankv2.apk",
            "platform": "Android",
            "is_malicious": true,
            "image_url": "",
            "tags": "apk, signed",
            "detected_by": "[{antivirus: InQuest, details: UNKNOWN}]",
            "scan_time": "2025-05-21T16:57:38",
            "timestamp_u": 1753110792
        },
        {
            "sha256": "ff93b2707c47133bb35f6a3a10771e5af653e7b1db3f6cefad8a42d6b0d4a625",
            "file_name": "PXL_20250721_110106038.jpg",
            "file_path": "/storage/emulated/0/DCIM/Camera/PXL_20250721_110106038.jpg",
            "apk_name": "",
            "platform": "",
            "is_malicious": false,
            "image_url": "",
            "tags": "error",
            "detected_by": " ",
            "scan_time": "2025-05-21T16:57:38",
            "timestamp_u": 1753110792
        }
    ]
  }
}
```

---

# 📂 Scan Downloads Folder

Use when you want to scan only the **Downloads folder**.

> This operation is available in both **asynchronous** and **synchronous** versions.

## Asynchronous

```java
MallocSDK.scanDownloadedFilesAsync(new MallocSDK.FilesScanningUpdatesCallback() {
    @Override
    public void onFilesListGenerated(List<FileToScan> files_to_scan) {
        // The files_to_scan list contains a list of the FileToScan objects of the files to be scanned
    }

    @Override
    public void onScanningFileUpdate(String file_name, String file_path) {
        // The file_name is the file name of the file that is currenlty in scan
        // The file_path is the path of the file that is currenlty in scan
    }

    @Override
    public void onScanProgressUpdate(String file_name, String file_path, int total_files_scanned, int total_files_to_scan) {
        // The file_name is the file name of the file that is currenlty in scan
        // The file_path is the path of the file that is currenlty in scan
        // The total_files_scanned is the total number of the scanned files
        // The total_files_to_scan is the total number of files to scan
    }

    @Override
    public void onScanFinished(JSONObject result) {
        // Use the result
    }
});
```

The **FileToScan** Class represents a file that is queued for scanning and it contains the display name of the file and the Uri reference pointing to the file's location on the device. See more in [FilesToScan](./fileToScan.md).

## Synchronous

> Do **not** call this on the main/UI thread.

```java
executor.execute(new Runnable() {
    @Override
    public void run() {
        JSONObject result = MallocSDK.scanDownloadedFilesSync();
        // Use the result
    }
});
```

## 📦 Sample JSON Response for `scanDownloadedFilesAsync` / `scanDownloadedFilesSync`

```json
{
  "status": "success",
  "details": {
    "downloadsScanResultsArray": [
        {
            "sha256": "b18af2a0e44d7634bbcdf93664d9c78a2695e050393fcfbb5e8b91f902d194a4",
            "file_name": "insecurebankv2__1_.apk",
            "file_path": "/storage/emulated/0/Download/insecurebankv2__1_.apk",
            "apk_name": "InsecureBankv2.apk",
            "platform": "Android",
            "is_malicious": true,
            "image_url": "",
            "tags": "apk, signed",
            "detected_by": "[{antivirus: InQuest, details: UNKNOWN}]",
            "scan_time": "2025-05-21T16:57:38",
            "timestamp_u": 1753110792
        }
    ]
  }
}
```

---

## 📝 Interpretation

* `filesScanResultsArray` — for `scanFilesAsync` / `scanFilesSync`
* `downloadsScanResultsArray` — for `scanDownloadedFilesAsync` / `scanDownloadedFilesSync`
* `sha256`: SHA-256 hash of the file.
* `file_name`: Name of the scanned file.
* `file_path`: Full path of the scanned file (only in file path scan).
* `apk_name`: Human-readable name if resolvable.
* `platform`: Platform/vendor if applicable.
* `is_malicious`: Whether the file is flagged as malicious.
* `image_url`: Optional image link representing the file or APK.
* `tags`: Descriptive tags (e.g., `obfuscated`, `reflection`, etc.).
* `detected_by`: List of antivirus engines and detection info.
* `scan_time`: Time of scan, if available.
* `timestamp_u`: Unix timestamp.

The `status` field in the returned JSON indicates the result of the operation. Possible values include:
* `success` – The operation completed successfully and the result contains valid data.
* `failed_sdk_not_initialized` – The SDK was not initialized before the function was called. Ensure MallocSDK has been initialised.
* `failed_access_forbidden` – The operation was blocked, possibly due to missing permissions or restricted access.
* `failed_no_internet_connection` – The device has no active internet connection, which is required to perform the scan.
* `error` – A general error occurred during the operation (e.g., unexpected exception or parsing failure).

> These status strings are defined as final constants in the [MallocSdkResponseJson](./mallocSdkResponseJson.md) class.

---

## ✅ Best Practices

* Always initiate scanning **after SDK initialization** is complete.
* Use the asynchronous method for better UI responsiveness.
* Consider warning users or restricting APK installation if `is_malicious` is `true`.
* Always check the status field before relying on other content within the JSON.

