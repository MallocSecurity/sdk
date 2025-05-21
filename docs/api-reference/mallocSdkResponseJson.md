# MallocSdkResponseJson Class

The `MallocSdkResponseJson` class is a utility class that extends the standard `JSONObject` and provides a consistent structure for responses returned by the **Malloc Security SDK** operations.

It standardizes the use of a `status` field to describe the outcome of an operation, enabling the developer to reliably determine whether the SDK function completed successfully or encountered an issue.

---

## Class Definition

```java
public class MallocSdkResponseJson extends JSONObject {
    public static final String success = "success";
    public static final String failed_sdk_not_initialized = "failed_sdk_not_initialized";
    public static final String failed_access_forbidden = "failed_access_forbidden";
    public static final String failed_no_internet_connection = "failed_no_internet_connection";
    public static final String error_occured = "error";

    // put success
    public void putStatus(String status) {
        try {
            put("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## ✅ Status Constants

These predefined `String` constants represent all possible outcomes of an SDK operation:

| Constant                        | Description                                                                                       |
| ------------------------------- | ------------------------------------------------------------------------------------------------- |
| `success`                       | The operation completed successfully, and the result contains valid data.                         |
| `failed_sdk_not_initialized`    | The SDK was not initialized before the method was called. Ensure `MallocSDK.init()` is invoked.   |
| `failed_access_forbidden`       | The operation was blocked, potentially due to missing permissions or security restrictions.       |
| `failed_no_internet_connection` | No internet connection was detected on the device, which is required for the operation.           |
| `error_occured`                 | A general, catch-all error occurred during execution (e.g., parsing error, unexpected exception). |

---

## ✅ Best Practices

* Always check the `status` field before relying on other content within the JSON.
