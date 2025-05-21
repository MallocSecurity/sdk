# 🌐 URL Category Check

The **Malloc Security SDK** provides a mechanism to check and categorize URLs by contacting the Malloc backend service. This can help your application detect and respond to potentially harmful domains.

> This operation is available in both synchronous and asynchronous versions (with callbacks).

---

## Option 1: Asynchronous Usage

#### Java
```java
String url = "domaintocheck.com";
MallocSDK.checkURLAsync(url, new MallocSDK.ScanFinishedCallback() {
    @Override
    public void onScanFinished(JSONObject result) {
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
        String url = "domaintocheck.com";
        JSONObject urlCategory = MallocSDK.checkURLSync(url);
        // Use the result
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
    "category": "spyware"
  }
}
```

---

## Interpretation
- `category`: The category of the URL. The possible categories are:

    | Category      | Description                                |
    |---------------|--------------------------------------------|
    | `"other"`     | Uncategorized or unrecognized domain       |
    | `"spyware"`   | Associated with spyware                    |
    | `"porn"`      | Adult or pornographic content              |
    | `"coinmining"`| Used for crypto mining activities          |
    | `"phishing"`  | Known phishing or scam domains             |
    | `"ads"`       | Used for ads or tracking                   |

    
The `status` field in the returned JSON indicates the result of the operation. Possible values include:
* "success" – The operation completed successfully and the result contains valid data.
* `failed_sdk_not_initialized` – The SDK was not initialized before the function was called. Ensure MallocSDK has been initialised.
* `failed_access_forbidden` – The operation was blocked, possibly due to missing permissions or restricted access.
* `failed_no_internet_connection` – The device has no active internet connection, which is required to perform the scan.
* `error` – A general error occurred during the operation (e.g., unexpected exception or parsing failure).

> These status strings are defined as final constants in the [MallocSdkResponseJson](./mallocSdkResponseJson.md) class.

---

## ✅ Best Practices

- Always perform URL checks after the SDK has been properly initialized.
- Use the asynchronous version in UI components to avoid blocking the main thread.
- Always check the status field before relying on other content within the JSON.

  
