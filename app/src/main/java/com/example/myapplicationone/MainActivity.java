package com.example.myapplicationone;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    private CameraManager cameraManager;
    private String cameraId;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!isFlashAvailable)
            noFlashAvailableError();

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException q) {
            q.printStackTrace();
        }

        // ACTIONS FOR BUTTON ID: Button2

        ToggleButton toggleFlashBtn = findViewById(R.id.Button2);
        toggleFlashBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            try {
                cameraManager.setTorchMode(cameraId, isChecked);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            }
        });

        // ACTIONS FOR BUTTON ID: Button3

        ToggleButton toggleDarkModeButton = findViewById(R.id.Button3);
        toggleDarkModeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RelativeLayout rootView = findViewById(R.id.mainLayout);
                if(isChecked) {
                    rootView.setBackgroundColor(0xFF363229);
                } else {
                    rootView.setBackgroundColor(0xFFCDDC39);
                }
            }
        });
    }

    private void noFlashAvailableError() {
        Toast.makeText(MainActivity.this, "Torch not Available", Toast.LENGTH_SHORT).show();
    }
}
