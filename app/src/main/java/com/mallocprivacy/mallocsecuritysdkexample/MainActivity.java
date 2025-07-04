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

import java.util.ArrayList;
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

        Button request_permissions_button = findViewById(R.id.request_permissions);
        request_permissions_button.setOnClickListener(listener -> {
            // Option 1: Show dilog with default wording
            MallocSDK.requestFilesScannerPermissionWithDialog(mActivity, null, null);

            // Option 2: Show dialog with custom wording
            // MallocSDK.requestFilesScannerPermissionWithDialog(mActivity, "TEST TITLE", "TEST DESCRIPTION");

            // Option 3: Directly redirect the user to the app settings
            //MallocSDK.requestFilesScannerPermission(mActivity);
        });

        Button check_url_button = findViewById(R.id.check_url);
        check_url_button.setOnClickListener(listener -> {

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

        Button root_check_button = findViewById(R.id.root_check);
        root_check_button.setOnClickListener(listener -> {
            Toast.makeText(mActivity, "Coming soon", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Root Check Coming Soon");

            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected. Check logs for output.", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Now Calling rootCheckAsync()");
                MallocSDK.rootCheckAsync(new MallocSDK.ScanFinishedCallback() {
                    @Override
                    public void onScanFinished(JSONObject rootCheckResults)
                    {
                        Log.d(TAG, "Root Check Results Async: " + rootCheckResults);
                    }
                });
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Now Calling rootCheckSync()");
                        JSONObject rootCheckResults = MallocSDK.rootCheckSync();
                        Log.d(TAG, "Root Check Results Sync: " + rootCheckResults);
                    }
                });
            }
        });

        Button scan_for_spyware_indicators_button = findViewById(R.id.scan_for_spyware_indicators);
        scan_for_spyware_indicators_button.setOnClickListener(listener -> {

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

        Button scan_apps_button = findViewById(R.id.scan_apps);
        scan_apps_button.setOnClickListener(listener -> {
            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                long start_timestamp = System.currentTimeMillis() / 1000L;

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
                        Log.d(TAG, "Scan Apps Time: " + (System.currentTimeMillis() / 1000L - start_timestamp));
                    }

                };
                Log.d(TAG, "Now Calling scanAppsPerAppAsync()");
                MallocSDK.scanAppsPerAppAsync(appsScanningUpdatesCallback, true, true, true); // scan apps for spyware, permissions, accessibility services and malicious apks

//                MallocSDK.scanAppsAsync(new MallocSDK.ScanFinishedCallback() {
//                    @Override
//                    public void onScanFinished(JSONObject result) {
//                        Log.d(TAG, "Scan Apps Results Async: " + result);
//                    }
//                }, false, false, true);
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Now Calling scanAppsPerScanSync()");
                        JSONObject scanAppsResultsJson = MallocSDK.scanAppsPerScanSync(true,  true, true);  // scan apps for spyware, permissions, accessibility services and malicious apks
                        Log.d(TAG, "Scan Apps Results Sync: " + scanAppsResultsJson);
                    }
                });
            }
        });

        Button scan_app_list_button = findViewById(R.id.scan_app_list);
        scan_app_list_button.setOnClickListener(listener -> {
            List<String> packages_to_scan1 = List.of("com.eset.ems2.gp", "com.alibaba.aliexpresshd", "com.google.android.youtube", "com.android.providers.telephony");

            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                long start_timestamp = System.currentTimeMillis() / 1000L;

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
                        Log.d(TAG, "Scan Apps Time: " + (System.currentTimeMillis() / 1000L - start_timestamp));
                    }

                };
                Log.d(TAG, "Now Calling scanAppsPerAppAsync()");
                MallocSDK.scanAppsPerAppAsync(appsScanningUpdatesCallback, packages_to_scan1); // scan apps for spyware, permissions, accessibility services and malicious apks
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Now Calling scanAppsPerScanSync()");
                        JSONObject scanAppsResultsJson1 = MallocSDK.scanAppsPerScanSync(packages_to_scan1, true); // scan apps for spyware, permissions, accessibility services and malicious apks
                        Log.d(TAG, "Scan Apps Results Sync: " + scanAppsResultsJson1);
                    }
                });
            }
        });

        Button scan_downloads_button = findViewById(R.id.scan_downloads);
        scan_downloads_button.setOnClickListener(listener -> {
            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                MallocSDK.FilesScanningUpdatesCallback filesScanningUpdatesCallback = new MallocSDK.FilesScanningUpdatesCallback() {
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
                };

                Log.d(TAG, "Now Calling scanDownloadedFilesAsync()");
                MallocSDK.scanDownloadedFilesAsync(filesScanningUpdatesCallback);
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

        Button scan_file_list_button = findViewById(R.id.scan_file_list);
        scan_file_list_button.setOnClickListener(listener -> {
            List<String> file_paths_to_scan = List.of(
                    "/storage/emulated/0/Download/Tasker.6.3.0-beta.apk",
                    "/storage/emulated/0/DCIM/Camera/PXL_20250721_110106038.jpg",
                    "/storage/self/primary/DCIM/Camera/IMG_20240911_130259.jpg",
                    "/sdcard/DCIM/Camera/IMG_20240911_130259.jpg",
                    "/storage/self/primary/Download/insecurebankv2__1_ (1).apk",
                    "/storage/self/primary/Download/SpinDeals app_2.70_APKPure (1).apk",
                    "/storage/self/primary/Download/the_times_of_india.apk",
                    "/storage/emulated/0/Download/aba17776b98b8660b50d2a4ef9aa0a79e17f22a47a76b7658b3981f7bddeadd6-1 (1).apk");

            if (syncAsyncOptionChipGroup.getCheckedChipId() == R.id.async_chip)
            {
                Toast.makeText(mActivity, "Async version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                MallocSDK.FilesScanningUpdatesCallback filesScanningUpdatesCallback = new MallocSDK.FilesScanningUpdatesCallback() {
                    @Override
                    public void onFilesListGenerated(List<FileToScan> files_to_scan) {
                        Log.d(TAG, "Scan Files Callback - Files List Generated: " + files_to_scan);
                    }

                    @Override
                    public void onScanningFileUpdate(String file_name, String file_path) {
                        Log.d(TAG, "Scan Files Callback - Now Scanning File: " + file_name + ", " + file_path);
                    }

                    @Override
                    public void onScanProgressUpdate(String file_name, String file_path, int total_files_scanned, int total_files_to_scan) {
                        Log.d(TAG, "Scan Files Callback - Scan Progress Update: " + file_name + ", " + file_path + ", " + total_files_scanned + ", " + total_files_to_scan);
                    }

                    @Override
                    public void onScanFinished(JSONObject result) {

                        Log.d(TAG, "Scan Files Callback - Results Async: " + result);
                    }
                };

                Log.d(TAG, "Now Calling scanFilesAsync()");
                MallocSDK.scanFilesAsync(file_paths_to_scan, filesScanningUpdatesCallback);
            }
            else
            {
                Toast.makeText(mActivity, "Sync version selected. Check logs for output.", Toast.LENGTH_SHORT).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Now Calling scanFilesSync()");
                        JSONObject scanDownloadedFilesResultsJsonArray = MallocSDK.scanFilesSync(file_paths_to_scan);
                        Log.d(TAG, "Scan Files Results Sync: " + scanDownloadedFilesResultsJsonArray);
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