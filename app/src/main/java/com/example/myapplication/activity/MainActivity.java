package com.example.myapplication.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragments.Fragment_Home;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("abc", "onCreate: create main activity");

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment_Home(),"main_content").commit();



    }


    private void checkPermission(){
        if(Build.VERSION.SDK_INT >=23){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("permit", "onRequestPermissionsResult:  success");
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("abc", "onDestroy: destroy main activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("abc", "onPause: main activity pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("abc", "onResume: main activity running");
    }
}
