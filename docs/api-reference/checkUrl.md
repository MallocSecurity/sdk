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

## Detected Categories

| Category      | Description                                |
|---------------|--------------------------------------------|
| `"other"`     | Uncategorized or unrecognized domain       |
| `"spyware"`   | Associated with spyware                    |
| `"porn"`      | Adult or pornographic content              |
| `"coinmining"`| Used for crypto mining activities          |
| `"phishing"`  | Known phishing or scam domains             |
| `"ads"`       | Used for ads or tracking                   |
| `"http"`      | Unencrypted (HTTP) domain                  |

---

## ✅ Best Practices

- Always perform URL checks after the SDK has been properly initialized.
- Use the asynchronous version in UI components to avoid blocking the main thread.
