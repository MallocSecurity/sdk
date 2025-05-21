# 🕵️ Spyware Indicators Check

The **Malloc Security SDK** provides a powerful feature to scan for device-level spyware indicators. This helps determine whether a device may be compromised by known spyware like **Pegasus NSO** or **Cytrox**.

> This operation is available in both synchronous and asynchronous versions (with callbacks).

---

## Option 1: Asynchronous Usage

#### Java

```java
MallocSDK.scanForDeviceSpywareAsync(new MallocSDK.ScanFinishedCallback() {
    @Override
    public void onScanFinished(JSONObject results) {
        // Use the result
    }
});
```

---

## Option 2: Synchronous Usage

> Do **not** call this on the main/UI thread.

#### Java

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
executor.execute(new Runnable() {
    @Override
    public void run() {
        JSONObject results = MallocSDK.scanForDeviceSpywareSync();
        // Use the result
    }
});
```

---

## 📦 Sample JSON Response

```json
{
  "status": "success",
  "details": {
    "spywareIndicatorsArray": [
      {
        "spyware_name": "Cytrox",
        "detections": [
          {
            "type": "DIRECTORY_CHECK",
            "description": "/private/var/tmp/hooker"
          },
          {
            "type": "DIRECTORY_CHECK",
            "description": "/data/local/tmp/wd"
          }
        ]
      },
      {
        "spyware_name": "Nso",
        "detections": [
          {
            "type": "FILE_CHECK",
            "description": "roleaccountd.plist"
          }
        ]
      }
    ]
  }
}
```

---

## Interpretation

* `spyware_name`: The name of the detected spyware family (e.g., NSO, Cytrox).
* `detections`: A list of suspicious indicators including files and directories linked to spyware presence.
* `type`: Type of detection (`FILE_CHECK`, `DIRECTORY_CHECK`, `SERVICE_CHECK`).
* `description`: A human-readable path or artifact associated with the spyware.

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
* Use the asynchronous version to avoid blocking the UI.
* Always check the status field before relying on other content within the JSON.
