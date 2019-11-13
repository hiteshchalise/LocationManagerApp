package com.utopia.locationmanagerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "LocationManagerApp";

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;

    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkPermissions();
    }

    private void checkPermissions() {

        List<String> missingPermissions = new ArrayList<>();

        for(String permission: REQUIRED_SDK_PERMISSIONS){
            int result = checkCallingOrSelfPermission(permission);
            if (result != PackageManager.PERMISSION_GRANTED){
                missingPermissions.add(permission);
            }
        }

        if(!missingPermissions.isEmpty()){
            String[] permissions = missingPermissions.toArray(new String[0]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        }else{
            int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS, grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean hasPermissionDenied = false;
        if (requestCode == 1) {
            for(int index = permissions.length-1; index >=0; index--){
                if(grantResults[index] != PackageManager.PERMISSION_GRANTED){
                    hasPermissionDenied = true;
                    Toast.makeText(this,
                            "Required Permission Denied " + permissions[index] + ", Exiting",
                            Toast.LENGTH_SHORT).show();
                }
            }
            if(hasPermissionDenied){
                finish();
                return;
            }
            initialize();
        }
    }

    private void initialize() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra("source", "SplashActivity");
        startActivity(intent);
        finish();
    }
}

