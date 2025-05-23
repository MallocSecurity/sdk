# Malloc Security SDK - Sample Android App

This is a sample Android project that demonstrates the usage of the **[Malloc Security SDK](https://mallocsecurity.github.io/sdk/intro)**. It provides a working implementation showcasing how to import, initialize, and use the features of the SDK.

## 🔧 Features Demonstrated

- SDK initialization
- Permissions request
- URL Threat Detection (sync & async)
- Root check (⚠️  Coming soon) 
- Scan for device spyware indicators (sync & async)
- App scanning (sync & async)
- Scan for malicious downloads (sync & async)
- Toggle between synchronous and asynchronous SDK functions via UI

---

## 📸 Screenshots
| Main UI | 
|---------|
| ![Main UI](main_ui_screenshot.png)|

---

## 🚀 Getting Started

### 1. SDK Integration

#### Modify `settings.gradle`:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url "https://jitpack.io"
            credentials { username mallocSecuritySdkAuthToken }
        }
    }
}
```

#### Add dependency in `build.gradle (app)`:

```groovy
dependencies {
    implementation 'com.gitlab.devmalloc:malloc-security-sdk-android:0.1.0'
}
```

### 2. SDK Initialization

In your `Application` class:

```java
String key = "your-secret-api-key";
MallocSDK.initializeAsync(getApplicationContext(), key, new MallocSDK.InitializationFinished() {
    @Override
    public void onInitializationFinished(Boolean success) {
        if (success) {
            Log.d(TAG, "MallocSDK initialized successfully");
        } else {
            Log.e(TAG, "MallocSDK initialization failed");
        }
    }
});
```

---

## 🧪 Functionality Overview

- Choose between sync or async versions using the chip selector
- Use buttons to trigger specific SDK features
- Logs output printed to **Logcat**
  
---

## 📚 Documentation

Full SDK documentation is available here:  
**[Malloc Security SDK Documentation](https://mallocsecurity.github.io/sdk/intro)**

---

## 🧩 Coming Soon

- Root check functionality

---
