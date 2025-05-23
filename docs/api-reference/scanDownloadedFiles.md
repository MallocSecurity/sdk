# 📂 File Scanning

The **Malloc Security SDK** provides a file scanning feature that inspects APK files located in the device’s **Downloads folder**. It identifies potentially malicious APKs by sending the package's signature to the Malloc backend threat intelligence service.


> ⚠️⚠️ This function will be updated with an extended knowledge source in version 0.2.X

### Permissions Required
Before scanning files, the appropriate permission must be granted based on the Android version. The method [requestFilesScannerPermission()](./mallocSDK.md#permissions-required) can be used to request the appropriate permissions.



> This operation is available in both **asynchronous** and **synchronous** versions.

---

## Asynchronous Usage (With Progress Updates)

```java
MallocSDK.scanDownloadedFilesAsync(new MallocSDK.FilesScanningUpdatesCallback() {
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

---

## Synchronous Usage

> Do **not** call this on the main/UI thread.

```java
executor.execute(new Runnable() {
    @Override
    public void run() {
        JSONObject scanDownloadedFilesResultsJson = MallocSDK.scanDownloadedFilesSync();
        // Use the scanDownloadedFilesResultsJson
    }
});
```

---

## 📦 Sample JSON Response

```json
{
  "status": "success",
  "details": {
    "downloadsScanResultsArray": [
      {
        "sha256": "b18af2a0e44d7634bbcdf93664d9c78a2695e050393fcfbb5e8b91f902d194a4",
        "file_name": "insecurebankv2.apk",
        "apk_name": "InsecureBankv2",
        "platform": "SI",
        "is_malicious": true,
        "image_url": "https://sdk.mallocsecurity.com/image-proxy/b3/64/b3645cf9986be0f8c9d400daab34e29dda877e971a131cee5c97be64c2c7947c",
        "tags": "malicious,reflection,clipboard,trojan,undetected,obfuscated",
        "detected_by": "[{\"antivirus\": \"Ikarus\", \"details\": \"Trojan-Spy.AndroidOS.Agent\"}]",
        "scan_time": "2025-05-08T08:16:23"
      }
    ]
  }
}
```

---

## Interpretation

* `downloadsScanResultsArray`: An array with the scanned apks.
* `sha256`: The SHA-256 hash of the APK file.
* `file_name`: The name of the file scanned.
* `apk_name`: Human-readable name if resolvable.
* `platform`: The platform or vendor the app is associated with.
* `is_malicious`: Boolean flag indicating if the APK is flagged as malicious.
* `image_url`: Optional image link representing the APK.
* `tags`: Descriptive tags like `obfuscated`, `reflection`, `contains-elf`, etc.
* `detected_by`: List of antivirus engines and detection info.
* `scan_time`: Time of scan, if available.

> If any of the fields above do not have a corresponding value for a scanned APK, the value will be an empty string "" in the result JSON.



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
