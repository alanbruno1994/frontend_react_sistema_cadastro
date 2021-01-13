package com.example.mobilesystem.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilesystem.R;

public class MainActivity extends AppCompatActivity {

    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login=findViewById(R.id.buttonProdutos);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuSystem.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void permissoes() {
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET};
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
    }
}