# 🪓 Root Check

The **Malloc Security SDK** offers a comprehensive root detection feature that checks for common indicators of a rooted device. This includes known rooting binaries, packages, superuser access, and modified security properties.

> ⚠️⚠️ This function is not yet available in version 0.1.X
---

## Option 1: Asynchronous Usage

####  Java
```java
MallocSDK.rootCheckAsync(new MallocSDK.ScanFinishedCallback() {
    @Override
    public void onScanFinished(JSONObject rootCheckResults) {
        // Use the rootCheckResults
    }
});
```

---

## Option 2: Synchronous Usage

> Do **not** call this on the main/UI thread.

####  Java
```java
ExecutorService executor = Executors.newSingleThreadExecutor();
executor.execute(new Runnable() {
    @Override
    public void run() {
        JSONObject rootCheckResults = MallocSDK.rootCheckSync();
        // Use the rootCheckResults
    }
});
executor.shutdown();
```

---

## 📦 Sample JSON Response

```json
{
    "status": "success",
    "details": {
        "rooted_flag": true,
        "root_check_results": [
            {
                "issue_found": false,
                "check_description": "Root Management Apps"
            },
            {
                "issue_found": false,
                "check_description": "Potentially Dangerous Apps"
            },
            {
                "issue_found": false,
                "check_description": "Root Cloaking Apps"
            },
            {
                "issue_found": false,
                "check_description": "Test Keys"
            },
            {
                "issue_found": false,
                "check_description": "BusyBox Binary"
            },
            {
                "issue_found": false,
                "check_description": "SU Binary"
            },
            {
                "issue_found": false,
                "check_description": "2nd SU Binary check"
            },
            {
                "issue_found": false,
                "check_description": "RW Paths"
            },
            {
                "issue_found": true,
                "check_description": "Dangerous Props"
            },
            {
                "issue_found": false,
                "check_description": "Root via native check"
            },
            {
                "issue_found": false,
                "check_description": "Magisk specific checks"
            }
        ]
    }
}
```

---

## Interpretation

- `rooted_flag`: A high-level boolean flag indicating if the device appears to be rooted.
- `root_check_results`: A list of all individual root checks performed, with their respective results and descriptions.
- `issue_found`: A boolean indicating whether the check flagged something (true = suspicious, false = clean)
- `check_description`: A short, readable explanation of what that check was about.

The `status` field in the returned JSON indicates the result of the operation. Possible values include:
* `success` – The operation completed successfully and the result contains valid data.
* `failed_sdk_not_initialized` – The SDK was not initialized before the function was called. Ensure MallocSDK has been initialised.
* `failed_access_forbidden` – The operation was blocked, possibly due to missing permissions or restricted access.
* `failed_no_internet_connection` – The device has no active internet connection, which is required to perform the scan.
* `error` – A general error occurred during the operation (e.g., unexpected exception or parsing failure).

> These status strings are defined as final constants in the [MallocSdkResponseJson](./mallocSdkResponseJson.md) class.
      
---

## ✅ Best Practices

- Always perform root checks **after SDK initialization** is completed.
- Use the **asynchronous** version for better UI performance.
- Always check the status field before relying on other content within the JSON.
