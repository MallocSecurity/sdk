# 🧩 Initialization & Authentication

The Malloc SDK handles authentication internally and does not require client-side token management. To start using its features, simply initialize the SDK once in your application's `Application` class.

## 🔧 Initialization Setup

To begin using `MallocSecuritySDK`, you must initialize it once in your `Application` class. This setup connects the SDK with your API key and prepares it for future security and privacy operations.

In your `Application` class (`MyApplication.java` or `MyApplication.kt`), override the `onCreate()` method and initialize the SDK as shown:

### 🚀 Java
```java
import android.app.Application;
import com.mallocprivacy.mallocsecuritysdk.MallocSDK;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ExecutorService mallocSDKExecutorService = Executors.newSingleThreadExecutor();
        mallocSDKExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                String key = "your-secret-api-key"; // Replace with your actual API key
                MallocSDK.initialize(getApplicationContext(), key);
            }
        });
    }
}
```

### 🚀 Kotlin
```kotlin
import android.app.Application
import com.mallocprivacy.mallocsecuritysdk.MallocSDK
import java.util.concurrent.Executors

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val mallocSDKExecutorService = Executors.newSingleThreadExecutor()
        mallocSDKExecutorService.execute {
            val key = "your-secret-api-key" // Replace with your actual API key
            MallocSDK.initialize(applicationContext, key)
        }
    }
}

```
#### 💡 Why use ExecutorService?
SDK initialization is performed off the main thread to avoid blocking the UI during app startup. This pattern ensures smooth launch performance without impacting user experience.

#### ✅ Tip: Avoid hardcoding your API key in production builds. Consider securing it using remote configuration or encrypted storage.
