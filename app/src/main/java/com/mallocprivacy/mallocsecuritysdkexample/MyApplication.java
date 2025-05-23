package com.mallocprivacy.mallocsecuritysdkexample;

import android.app.Application;
import android.util.Log;

import com.mallocprivacy.mallocsecuritysdk.MallocSDK;

public class MyApplication extends Application {
    String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Malloc Security SDK

        String key = "your-secret-api-key"; // Replace with your actual API key
        MallocSDK.initializeAsync(getApplicationContext(), key, new MallocSDK.InitializationFinished() {
            @Override
            public void onInitializationFinished(Boolean success) {
                if (success) {
                    // SDK is ready
                    Log.d(TAG, "MallocSDK initialized successfully");
                } else {
                    // Handle invalid API key
                    Log.d(TAG, "MallocSDK initialization failed");
                    Log.e(TAG, "Invalid API key");
                }
            }
        });    }
}
