# 🧩 Initialization & Authentication

The Malloc SDK handles authentication internally and does not require client-side token management. To start using its features, simply initialize the SDK once in your application's Application class.

## 🔧 Initialization Setup

To begin using `MallocSDK`, you must initialize it once in your `Application` class. This setup connects the SDK with your API key and prepares it for future security and privacy operations.

In your `Application` class (`MyApplication.java` or `MyApplication.kt`), add the SDK initialization in the `onCreate()` method as shown below:

---

### Option 1: Asynchronous Initialization

Use `MallocSDK.initializeAsync()` to initialize the SDK in a background thread and receive a callback when the process completes. This is the recommended method, especially when API key verification is important before continuing execution.

####  Java
```java
import android.app.Application;
import com.mallocprivacy.mallocsecuritysdk.MallocSDK;
import com.mallocprivacy.mallocsecuritysdk.InitializationFinished;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        String key = "your-secret-api-key"; // Replace with your actual API key
        MallocSDK.initializeAsync(getApplicationContext(), key, new InitializationFinished() {
            @Override
            public void onInitializationFinished(Boolean success) {
                if (success) {
                    // SDK is ready
                } else {
                    // Handle invalid API key
                }
            }
        });
    }
}
```

####  Kotlin
```kotlin
import android.app.Application
import com.mallocprivacy.mallocsecuritysdk.MallocSDK
import com.mallocprivacy.mallocsecuritysdk.InitializationFinished

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val key = "your-secret-api-key"
        MallocSDK.initializeAsync(applicationContext, key, object : InitializationFinished {
            override fun onInitializationFinished(success: Boolean) {
                if (success) {
                    // SDK is ready
                } else {
                    // Handle invalid API key
                }
            }
        })
    }
}
```

---

### Option 2: Synchronous Initialization

Use `initializeSync()` if you need a simple and immediate boolean response to indicate whether initialization succeeded. This method blocks the thread, so **do not call it on the main/UI thread**.

#### Java
```java
ExecutorService executorService = Executors.newSingleThreadExecutor();
executorService.execute(() -> {
    boolean success = MallocSDK.initializeSync(getApplicationContext(), "your-secret-api-key");
    if (success) {
        // SDK is ready
    } else {
        // Handle invalid API key
    }
});
executorService.shutdown();
```

####  Kotlin
```kotlin
val executorService = Executors.newSingleThreadExecutor()
executorService.execute {
    val success = MallocSDK.initializeSync(applicationContext, "your-secret-api-key")
    if (success) {
        // SDK is ready
    } else {
        // Handle invalid API key
    }
}
executorService.shutdown()
```

#### Why use ExecutorService?
SDK initialization is performed off the main thread to avoid blocking the UI during app startup. This pattern ensures smooth launch performance without impacting user experience.

---

### Which one should I use?

| Method              | Thread-safe | Returns success? | Recommended for |
|---------------------|-------------|------------------|------------------|
| `initializeAsync()` | ✅ Yes       | ✅ via callback  | Production apps (non-blocking) |
| `initializeSync()`  | ❗ Not for main thread | ✅ Immediately     | background services |


#### ✅ Tip: Avoid hardcoding your API key in production builds. Consider securing it using remote configuration or encrypted storage.
