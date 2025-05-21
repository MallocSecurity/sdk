# 📱 App Scanning

The **Malloc Security SDK** includes a powerful App Scan feature to help detect potentially dangerous apps on the device. It performs:

* Spyware app detection
* Permission analysis (dangerous or privacy-invading permissions)
* Accessibility services usage detection

> The scan can include system apps and apps without internet access based on the provided flags.

---

## Execution Options

This operation is offered in three versions:

| Version                    | Callback Type                 | Notes                                           |
| -------------------------- | ----------------------------- | ----------------------------------------------- |
| `scanAppsPerAppAsync(...)` | `AppsScanningUpdatesCallback` | Provides granular scanning progress             |
| `scanAppsAsync(...)`       | `ScanFinishedCallback`        | Returns final result after all apps are scanned |
| `scanAppsPerScanSync(...)` | —                             | Synchronous, must run off main thread           |

---

## Parameters

Both async and sync versions accept two boolean flags:

* `include_system_apps`: whether to include system applications.
* `include_apps_with_no_internet_access`: whether to include apps without internet access.

---

## Option 1: Per-App Async Scan (With Updates)

Provides progress updates while scanning apps.

### Java

```java
MallocSDK.AppsScanningUpdatesCallback appsScanningUpdatesCallback = new MallocSDK.AppsScanningUpdatesCallback() {
    @Override
    public void onAppsToScanListReady(List<ApplicationInfo> apps_to_scan) {
        // The apps_to_scan list contains a list of the ApplicationInfo of the apps to be scanned
    }

    @Override
    public void onScanningAppUpdate(ApplicationInfo now_scanning_app) {
        // The now_scanning_app is the app currently in scan
    }

    @Override
    public void onScanProgressUpdate(ApplicationInfo now_scanning_app, int total_scanned, int total_to_scan) {
        // The now_scanning_app is the app currently in scan
        // The total_scanned is the total number of the scanned apps
        // The total_to_scan is the total number of apps to scan
    }

    @Override
    public void onScanFinished(JSONObject result) {
        // use the result
    }
};

MallocSDK.scanAppsPerAppAsync(appsScanningUpdatesCallback, false, false);
```

ApplicationInfo API reference: https://developer.android.com/reference/android/content/pm/ApplicationInfo


---

## Option 2: Async Scan (Final Result Only)

### Java

```java
MallocSDK.scanAppsAsync(new MallocSDK.ScanFinishedCallback() {
    @Override
    public void onScanFinished(JSONObject result) {
        // use the result
    }
}, false, false);
```

---

## Option 3: Synchronous Scan

> Do **not** call on the main thread.

### Java

```java
executor.execute(new Runnable() {
    @Override
    public void run() {
        JSONObject result = MallocSDK.scanAppsPerScanSync(false, false);
        // use the result
    }
});
```

---

## 📦 Sample JSON Response

```json
{
  "status": "success",
  "details": {
    "spyware_apps": [
      {
        "package_name": "com.example.spyware1",
        "spyware": true
      },
      {
        "package_name": "com.example.spyware2",
        "spyware": true
      }
    ],
    "app_permissions": [
      {
        "package_name": "com.google.android.apps.weather",
        "granted_permissions": "ACCESS_FINE_LOCATION, ACCESS_BACKGROUND_LOCATION, INTERNET, ACCESS_COARSE_LOCATION"
      },
      {
        "package_name": "com.google.android.googlequicksearchbox",
        "granted_permissions": "ACTIVITY_RECOGNITION, RECORD_AUDIO, READ_CALL_LOG, READ_CALENDAR, READ_SMS, ACCESS_BACKGROUND_LOCATION, SEND_SMS, CALL_PHONE, WRITE_CONTACTS, BLUETOOTH_CONNECT, INTERNET, GET_ACCOUNTS, CAMERA, ACCESS_COARSE_LOCATION, READ_PHONE_STATE, READ_CONTACTS, BLUETOOTH_SCAN, WRITE_CALENDAR"
      }
    ],
    "packages_with_enabled_accessibility_services": [
      "com.avast.android.mobilesecurity",
      "com.celzero.bravedns"
    ]
  }
}
```

---

## Interpretation

* `spyware_apps`: Identified packages flagged as known spyware.
* `app_permissions`: A breakdown of dangerous permissions granted per app.
* `packages_with_enabled_accessibility_services`: Apps using accessibility services, often abused by spyware or intrusive apps.

The `status` field in the returned JSON indicates the result of the operation. Possible values include:
* `success` – The operation completed successfully and the result contains valid data.
* `failed_sdk_not_initialized` – The SDK was not initialized before the function was called. Ensure MallocSDK has been initialised.
* `failed_access_forbidden` – The operation was blocked, possibly due to missing permissions or restricted access.
* `failed_no_internet_connection` – The device has no active internet connection, which is required to perform the scan.
* `error` – A general error occurred during the operation (e.g., unexpected exception or parsing failure).

> These status strings are defined as final constants in the [MallocSdkResponseJson](./mallocSdkResponseJson.md) class.
      
---

## ✅ Best Practices

* Run this scan after SDK initialization.
* Prefer the async versions for better performance and smoother UI experience.
* Use `scanAppsPerAppAsync` if you want fine-grained progress feedback.
* Always check the status field before relying on other content within the JSON.

  
