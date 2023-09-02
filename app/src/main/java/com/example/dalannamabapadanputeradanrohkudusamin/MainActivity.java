package com.example.dalannamabapadanputeradanrohkudusamin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageDeteksi, imageMonitoring, imageControlling, imageCatalog;
    TextView judulMonitoring, judulDeteksi, judulControlling, judulCatalog, deskMonitoring,
            deskDeteksi, deskControlling, deskCatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        judulMonitoring = findViewById(R.id.monitoring);
        judulDeteksi = findViewById(R.id.detection);
        judulControlling = findViewById(R.id.controlling);
        judulCatalog = findViewById(R.id.catalog);

        deskMonitoring = findViewById(R.id.deskripsi_monitoring);
        deskDeteksi = findViewById(R.id.deskripsi_deteksi);
        deskControlling = findViewById(R.id.deskripsi_controlling);
        deskCatalog = findViewById(R.id.deskripsi_catalog);

        imageDeteksi = findViewById(R.id.logo_detection);
        imageMonitoring = findViewById(R.id.logo_monitoring);
        imageControlling = findViewById(R.id.logo_controlling);
        imageCatalog = findViewById(R.id.logo_catalog);


        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        imageDeteksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetectionActivity.class));
            }
        });

        judulDeteksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetectionActivity.class));
            }
        });

        deskDeteksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetectionActivity.class));
            }
        });

        imageMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MonitoringActivity.class));
            }
        });

        judulMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MonitoringActivity.class));
            }
        });

        deskMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MonitoringActivity.class));
            }
        });

        imageControlling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ControllingActivity.class));
            }
        });

        judulControlling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ControllingActivity.class));
            }
        });

        deskControlling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ControllingActivity.class));
            }
        });

        imageCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CatalogActivity.class));
            }
        });

        judulCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CatalogActivity.class));
            }
        });

        deskCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CatalogActivity.class));
            }
        });
    }
}