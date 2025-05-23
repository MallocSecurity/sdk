package com.mallocprivacy.mallocsecuritysdkexample;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.ChipGroup;
import com.mallocprivacy.mallocsecuritysdk.FileToScan;
import com.mallocprivacy.mallocsecuritysdk.MallocSDK;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    AppCompatActivity mActivity;
    ExecutorService executor;

    ChipGroup syncAsyncOptionChipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mActivity = this;

        executor = Executors.newSingleThreadExecutor();

        syncAsyncOptionChipGroup = findViewById(R.id.syncAsyncOptionChipGroup);
        if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
        {
            Toast.makeText(mActivity, "Async version selected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(mActivity, "Sync version selected", Toast.LENGTH_SHORT).show();
        }

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(listener -> {
            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected", Toast.LENGTH_SHORT).show();
            }
        });

        Button button1 = findViewById(R.id.request_permissions);
        button1.setOnClickListener(listener -> {
            MallocSDK.requestFilesScannerPermission(mActivity);
        });

        Button button2 = findViewById(R.id.check_url);
        button2.setOnClickListener(listener -> {

            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected. Check logs for output.", Toast.LENGTH_SHORT).show();
                String url = "pornhub.com";
                Log.d(TAG, "Now Calling checkURLAsync(" + url + ")");
                MallocSDK.checkURLAsync(url, new MallocSDK.ScanFinishedCallback() {
                    @Override
                    public void onScanFinished(JSONObject result) {
                        Log.d(TAG, "Url Category Check Results Async: " + result);
                    }
                });
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = "pornhub.com";
                        Log.d(TAG, "Now Calling checkURLSync(" + url + ")");
                        JSONObject urlCategory = MallocSDK.checkURLSync(url);   // check url category
                        Log.d(TAG, "Url Category Check Results Sync: " + urlCategory);
                    }
                });
            }
        });

        Button button3 = findViewById(R.id.root_check);
        button3.setOnClickListener(listener -> {
            Toast.makeText(mActivity, "Coming soon", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Root Check Coming Soon");
//            MallocSDK.rootCheckAsync(new MallocSDK.ScanFinishedCallback() {
//                @Override
//                public void onScanFinished(JSONObject rootCheckResults)
//                {
//                    Log.d(TAG, "Root Check Results Async: " + rootCheckResults);
//                }
//            });

//
//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d(TAG, "rootCheck");
//                        JSONObject rootCheckResults = MallocSDK.rootCheckSync();
//                        Log.d(TAG, "Root Check Results: " + rootCheckResults);
//                    }
//                });
        });

        Button button4 = findViewById(R.id.scan_for_spyware_indicators);
        button4.setOnClickListener(listener -> {

            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "Now Calling scanForDeviceSpywareAsync()");
                MallocSDK.scanForDeviceSpywareAsync(new MallocSDK.ScanFinishedCallback() {
                    @Override
                    public void onScanFinished(JSONObject results) {
                        Log.d(TAG, "Scan Device For Spyware Results Async: " + results);
                    }
                });
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Now Calling scanForDeviceSpywareSync()");
                        JSONObject scanForDeviceSpywaresResultsJson = MallocSDK.scanForDeviceSpywareSync();    // scan for device spyware indicators
                        Log.d(TAG, "Scan Device For Spyware Results Sync: " + scanForDeviceSpywaresResultsJson);
                    }
                });
            }
        });

        Button button5 = findViewById(R.id.scan_apps);
        button5.setOnClickListener(listener -> {
            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                MallocSDK.AppsScanningUpdatesCallback appsScanningUpdatesCallback = new MallocSDK.AppsScanningUpdatesCallback() {
                    @Override
                    public void onAppsToScanListReady(List<ApplicationInfo> apps_to_scan) {
                        Log.d(TAG, "ScanAppsCallback: App List To Scan Ready" + apps_to_scan);
                    }

                    @Override
                    public void onScanningAppUpdate(ApplicationInfo now_Scanning_application_info) {
                        Log.d(TAG, "ScanAppsCallback: Now Scanning App: " + now_Scanning_application_info.packageName);
                    }

                    @Override
                    public void onScanProgressUpdate(ApplicationInfo now_scanning_application_info, int total_apps_scanned, int total_apps_to_scan) {
                        Log.d(TAG, "ScanAppsCallback: Scan Progress Update: " + now_scanning_application_info.packageName + ", " + total_apps_scanned + ", " + total_apps_to_scan);
                    }

                    @Override
                    public void onScanFinished(JSONObject result) {
                        Log.d(TAG, "ScanAppsCallback: Scan Apps Results Async: " + result);
                    }

                };
                Log.d(TAG, "Now Calling scanAppsPerAppAsync()");
                MallocSDK.scanAppsPerAppAsync(appsScanningUpdatesCallback, false, false);


//                MallocSDK.scanAppsAsync(new MallocSDK.ScanFinishedCallback() {
//                    @Override
//                    public void onScanFinished(JSONObject result) {
//                        Log.d(TAG, "Scan Apps Results Async: " + result);
//                    }
//                }, false, false);
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Now Calling scanAppsPerScanSync()");
                        JSONObject scanAppsResultsJson = MallocSDK.scanAppsPerScanSync(false,  false);  // scan apps for spyware, permissions, accessibility services
                        Log.d(TAG, "Scan Apps Results Sync: " + scanAppsResultsJson);
                    }
                });
            }
        });

        Button button6 = findViewById(R.id.scan_downloads);
        button6.setOnClickListener(listener -> {
            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "Now Calling scanDownloadedFilesAsync()");
                MallocSDK.scanDownloadedFilesAsync(new MallocSDK.FilesScanningUpdatesCallback() {
                    @Override
                    public void onFilesListGenerated(List<FileToScan> files_to_scan) {
                        Log.d(TAG, "Scan Downloaded Files Callback - Files List Generated: " + files_to_scan);
                    }

                    @Override
                    public void onScanningFileUpdate(String file_name, String file_path) {
                        Log.d(TAG, "Scan Downloaded Files Callback - Now Scanning File: " + file_name + ", " + file_path);
                    }

                    @Override
                    public void onScanProgressUpdate(String file_name, String file_path, int total_files_scanned, int total_files_to_scan) {
                        Log.d(TAG, "Scan Downloaded Files Callback - Scan Progress Update: " + file_name + ", " + file_path + ", " + total_files_scanned + ", " + total_files_to_scan);
                    }

                    @Override
                    public void onScanFinished(JSONObject result) {
                        Log.d(TAG, "Scan Downloaded Files Callback - Results Async: " + result);
                    }
                });
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Now Calling scanDownloadedFilesSync()");
                        JSONObject scanDownloadedFilesResultsJsonArray = MallocSDK.scanDownloadedFilesSync();    // scan for downloaded apk files that are marked as malicious
                        Log.d(TAG, "Scan Downloaded Files Results Sync: " + scanDownloadedFilesResultsJsonArray);
                    }
                });
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}