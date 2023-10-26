package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * @author 86186
 */
public class MainActivity extends AppCompatActivity {

//    private static final int REQUEST_CODE_ACCESSIBILITY_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);

    }

    private BroadcastReceiver accessibilityStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                if (intent.getAction().equals(Intent.ACTION_PACKAGE_CHANGED) ||
                        intent.getAction().equals(Intent.ACTION_MY_PACKAGE_REPLACED)) {
                    // 在辅助功能开启时启动 MyAccessibilityService
                    if (isAccessibilityServiceEnabled(context, MyAccessibilityService.class)) {
                        startService(new Intent(MainActivity.this, MyAccessibilityService.class));
                    }
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_MY_PACKAGE_REPLACED);
        registerReceiver(accessibilityStateReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 取消注册广播接收器
        unregisterReceiver(accessibilityStateReceiver);
    }

    // 检查辅助功能服务是否已启用
    private boolean isAccessibilityServiceEnabled(Context context, Class<?> serviceClass) {
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> enabledServices = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);

        for (AccessibilityServiceInfo service : enabledServices) {
            if (service.getResolveInfo().serviceInfo.packageName.equals(context.getPackageName()) &&
                    service.getResolveInfo().serviceInfo.name.equals(serviceClass.getName())) {
                return true;
            }
        }
        return false;
    }
}