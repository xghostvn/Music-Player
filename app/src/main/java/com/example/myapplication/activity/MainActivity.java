package com.example.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragments.Fragment_Home;
import com.example.myapplication.fragments.Fragment_PlayerPanel;
import com.example.myapplication.service.ServiceMusic;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame,new Fragment_Home(),"main_content")
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main,new Fragment_PlayerPanel(),"main_player")
                .addToBackStack(null)
                .commit();
        checkPermission();

        Intent intent = new Intent(this, ServiceMusic.class);
        startService(intent);


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


}
