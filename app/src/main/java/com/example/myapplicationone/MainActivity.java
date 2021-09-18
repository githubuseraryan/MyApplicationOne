package com.example.myapplicationone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private CameraManager cameraManager;
    private String cameraId;
    private ShareActionProvider shareActionProvider;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("https://mega.nz/file/lzwmyAyI#j2T-zaIeqqqv55XYppckuCG5j9bpijDdOEt8kBRR-io");
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(i);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!isFlashAvailable) {
            noFlashAvailableError();
        }

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
        ToggleButton toggleDarkModeButton = (ToggleButton) findViewById(R.id.Button3);
        //attach listener with ToggleButton
        toggleDarkModeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RelativeLayout rootView = findViewById(R.id.mainLayout);
                TextView rootView2 = findViewById(R.id.textView5);
                TextView rootView3 = findViewById(R.id.textView3);
                TextView rootView4 = findViewById(R.id.textView4);
                TextView rootView5 = findViewById(R.id.textView6);
                TextView rootView6 = findViewById(R.id.textView7);
                TextView rootView7 = findViewById(R.id.textView8);
                if (isChecked) {
                    rootView.setBackgroundColor(0xFF363229);
                    rootView2.setBackgroundColor(0xFF363229);
                    rootView3.setBackgroundColor(0xFF363229);
                    rootView4.setBackgroundColor(0xFF363229);
                    rootView5.setBackgroundColor(0xFF363229);
                    rootView6.setBackgroundColor(0xFF363229);
                    rootView7.setBackgroundColor(0xFF363229);
                    rootView3.setTextColor(0xFF40E0D0);
                    rootView2.setTextColor(0xFF40E0D0);
                    rootView4.setTextColor(0xFF40E0D0);
                    rootView5.setTextColor(0xFF40E0D0);
                    rootView6.setTextColor(0xFF40E0D0);
                    rootView7.setTextColor(0xFF40E0D0);
                } else {
                    rootView.setBackgroundColor(0xFF40E0D0);
                    rootView2.setBackgroundColor(0xFFF9945E);
                    rootView3.setBackgroundColor(0xFF40E0D0);
                    rootView4.setBackgroundColor(0xFFF9945E);
                    rootView5.setBackgroundColor(0xFFF9945E);
                    rootView6.setBackgroundColor(0xFFF50B1A);
                    rootView7.setBackgroundColor(0xFFF50B1A);
                    rootView3.setTextColor(0xFF000204);
                    rootView2.setTextColor(0xFF741111);
                    rootView4.setTextColor(0xFF741111);
                    rootView5.setTextColor(0xFF741111);
                    rootView6.setTextColor(0xFFFFF7FB);
                    rootView7.setTextColor(0xFFFFF7FB);
                }

            }
        });

        // ACTIONS FOR BUTTON ID: Button4
        ToggleButton toggleBlinkBtn = findViewById(R.id.Button4);
        toggleBlinkBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    blinkFlash();
                } else {
                    try {
                        cameraManager.setTorchMode(cameraId, false);
                    } catch (CameraAccessException e) {
                    }
                    Toast.makeText(MainActivity.this, "Press the above button first.",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });
        // ACTIONS FOR BUTTON ID: Button5
        ToggleButton toggleSoSModeButton = (ToggleButton) findViewById(R.id.Button5);
        //attach listener with ToggleButton
        toggleSoSModeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RelativeLayout rootView = findViewById(R.id.mainLayout);
                TextView textView3 = findViewById(R.id.textView3);
                final AnimationDrawable drawable1 = new AnimationDrawable();
                final AnimationDrawable drawable2 = new AnimationDrawable();
                ScheduledExecutorService backgroundExecutor = Executors.newSingleThreadScheduledExecutor();

                if (isChecked) {
                    drawable1.addFrame(new ColorDrawable(Color.RED), 400);
                    drawable1.addFrame(new ColorDrawable(0xFF00FFFF), 400);
                    drawable1.setOneShot(false);

                    drawable2.addFrame(new ColorDrawable(Color.RED), 400);
                    drawable2.addFrame(new ColorDrawable(0xFF00FFFF), 400);
                    drawable2.setOneShot(false);

                    rootView.setBackground(drawable1);
                    textView3.setBackground(drawable2);
                    backgroundExecutor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            drawable1.start();
                            drawable2.start();
                        }
                    }, 100, TimeUnit.MILLISECONDS);

                } else {
                    ((AnimationDrawable) (rootView.getBackground())).stop();
                    rootView.setBackground(null);
                    rootView.setBackgroundColor(0xFF00FFFF);
                    ((AnimationDrawable) (textView3.getBackground())).stop();
                    textView3.setBackground(null);
                    textView3.setBackgroundColor(0xFF00FFFF);
                }
            }

        });
        //Action for Button 6
        ToggleButton toggleSoSModeButton2 = (ToggleButton) findViewById(R.id.Button6);
        toggleSoSModeButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    blinkSOSFlash();
                } else {
                    try {
                        cameraManager.setTorchMode(cameraId, false);
                    } catch (CameraAccessException e) {
                    }
                    Toast.makeText(MainActivity.this, "Press the above button first.",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });

    }


    private void noFlashAvailableError() {
        Toast.makeText(MainActivity.this, "Torch not Available", Toast.LENGTH_SHORT).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void blinkSOSFlash() {
        try {
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = cameraManager.getCameraIdList()[0];
            String myString = "101010000001010100000010101000000101000000101010000001010100000010101000000101010000001010100000010101000000101010000001010100000010101";
            long blinkDelay = 89; //Delay in ms
            for (int i = 0; i < myString.length(); i++) {
                if (myString.charAt(i) == '0') {
                    this.cameraManager.setTorchMode(cameraId, true);
                } else {
                    this.cameraManager.setTorchMode(cameraId, false);
                }
                try {
                    Thread.sleep(blinkDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void blinkFlash() {
        try {
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = cameraManager.getCameraIdList()[0];
            String myString = "010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010010101010101010101010101010101010101010101010101010101";
            long blinkDelay = 150; //Delay in ms
            for (int i = 0; i < myString.length(); i++) {
                if (myString.charAt(i) == '0') {
                    this.cameraManager.setTorchMode(cameraId, true);
                } else {
                    this.cameraManager.setTorchMode(cameraId, false);
                }
                try {
                    Thread.sleep(blinkDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}


