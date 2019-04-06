package com.example.checkselfpermission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    private Button btnCallYou, btnFollowingMap;
    private static final String TAG = "MainActivity";
    private static final String[] PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCallYou = findViewById(R.id.btn_call_phone);
        btnFollowingMap = findViewById(R.id.btn_open_map);
        btnCallYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "085200152331"));
                startActivity(intent);
            }
        });
        btnFollowingMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:-7.0138637,110.4105095"));
                startActivity(intent);
            }
        });
        verifyPermission();
    }
    private void verifyPermission(){
        if(!hasPermission(this, PERMISSION)){
            ActivityCompat.requestPermissions(this, PERMISSION, 1);
        }
    }

    public static boolean hasPermission(Context context, String...permissions){
        if(context != null && permissions != null){
            for(String permission : permissions ){
                if(ActivityCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }
}
