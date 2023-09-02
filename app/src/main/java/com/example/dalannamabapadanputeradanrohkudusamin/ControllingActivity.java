package com.example.dalannamabapadanputeradanrohkudusamin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.google.firebase.database.FirebaseDatabase;

public class ControllingActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton siram, pupuk, pestisida, kipas, siramauto, pupukauto, kipasauto;

    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlling);

        mDatabase = FirebaseDatabase.getInstance();

        siram = findViewById(R.id.siram);
        pupuk = findViewById(R.id.pupuk);
        pestisida = findViewById(R.id.pestisida);
        kipas = findViewById(R.id.kipas);
        siramauto = findViewById(R.id.siramauto);
        pupukauto = findViewById(R.id.pupukauto);
        kipasauto = findViewById(R.id.kipasauto);

        siram.setOnClickListener(this);
        pupuk.setOnClickListener(this);
        pestisida.setOnClickListener(this);
        kipas.setOnClickListener(this);
        siramauto.setOnClickListener(this);
        pupukauto.setOnClickListener(this);
        kipasauto.setOnClickListener(this);

        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.siram:
                pompaSiram();
                break;
        }
        switch (view.getId()){
            case R.id.pupuk:
                pompaPupuk();
                break;
        }
        switch (view.getId()){
            case R.id.pestisida:
                pompaPestisida();
                break;
        }
        switch (view.getId()){
            case R.id.kipas:
                tombolKipas();
                break;
        }
        switch (view.getId()){
            case R.id.siramauto:
                pompaSiramAuto();
                break;
        }
        switch (view.getId()){
            case R.id.pupukauto:
                pompaPupukAuto();
                break;
        }
        switch (view.getId()){
            case R.id.kipasauto:
                tombolKipasAuto();
                break;
        }
    }

    void pompaSiram(){
        if (siram.isChecked()){
            turnOnRelaySiram();
        }else {
            turnOffRelaySiram();
        }
    }

    void pompaPupuk(){
        if (pupuk.isChecked()){
            turnOnRelayPupuk();
        }else {
            turnOffRelayPupuk();
        }
    }

    void pompaPestisida(){
        if (pestisida.isChecked()){
            turnOnRelayPestisida();
        }else {
            turnOffRelayPestisida();
        }
    }

    void tombolKipas(){
        if (kipas.isChecked()){
            turnOnRelayKipas();
        }else {
            turnOffRelayKipas();
        }
    }

    void pompaSiramAuto(){
        if (siramauto.isChecked()){
            siramauto.setVisibility(View.VISIBLE);
            turnOnRelaySiramAuto();
        }else {
            turnOffRelaySiramAuto();
        }
    }

    void pompaPupukAuto(){
        if (pupukauto.isChecked()){
            pupukauto.setVisibility(View.VISIBLE);
            turnOnRelayPupukAuto();
        }else {
            turnOffRelayPupukAuto();
        }
    }

    void tombolKipasAuto(){
        if (kipasauto.isChecked()){
            kipasauto.setVisibility(View.VISIBLE);
            turnOnRelayKipasAuto();
        }else {
            turnOffRelayKipasAuto();
        }
    }

    void turnOnRelaySiram(){
        siram.setEnabled(false);
        mDatabase.getReference("siram").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siram.setChecked(true);
            }
            siram.setEnabled(true);
        });
        mDatabase.getReference("s_siram").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siram.setChecked(true);
            }
            siram.setEnabled(true);
        });
    }

    void turnOffRelaySiram(){
        siram.setEnabled(false);
        mDatabase.getReference("siram").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siram.setChecked(false);

            }
            siram.setEnabled(true);
        });
        mDatabase.getReference("s_siram").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siram.setChecked(false);

            }
            siram.setEnabled(true);
        });
    }

    void turnOnRelayPupuk(){
        pupuk.setEnabled(false);
        mDatabase.getReference("pupuk").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupuk.setChecked(true);
            }
            pupuk.setEnabled(true);
        });
        mDatabase.getReference("s_pupuk").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupuk.setChecked(true);
            }
            pupuk.setEnabled(true);
        });
    }

    void turnOffRelayPupuk(){
        pupuk.setEnabled(false);
        mDatabase.getReference("pupuk").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupuk.setChecked(false);

            }
            pupuk.setEnabled(true);
        });
        mDatabase.getReference("s_pupuk").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupuk.setChecked(false);

            }
            pupuk.setEnabled(true);
        });
    }

    void turnOnRelayPestisida(){
        pestisida.setEnabled(false);
        mDatabase.getReference("pestisida").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pestisida.setChecked(true);
            }
            pestisida.setEnabled(true);
        });
        mDatabase.getReference("s_pestisida").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pestisida.setChecked(true);
            }
            pestisida.setEnabled(true);
        });
    }

    void turnOffRelayPestisida(){
        pestisida.setEnabled(false);
        mDatabase.getReference("pestisida").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pestisida.setChecked(false);
            }
            pestisida.setEnabled(true);
        });
        mDatabase.getReference("s_pestisida").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pestisida.setChecked(false);

            }
            pestisida.setEnabled(true);
        });
    }

    void turnOnRelayKipas(){
        kipas.setEnabled(false);
        mDatabase.getReference("kipas").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                kipas.setChecked(true);
            }
            kipas.setEnabled(true);
        });
    }

    void turnOffRelayKipas(){
        kipas.setEnabled(false);
        mDatabase.getReference("kipas").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                kipas.setChecked(false);
            }
            kipas.setEnabled(true);
        });
    }

    void turnOnRelaySiramAuto(){
        siramauto.setEnabled(false);
        mDatabase.getReference("siramauto").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siramauto.setChecked(true);
            }
            siramauto.setEnabled(true);
        });
        mDatabase.getReference("s_siramauto").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siramauto.setChecked(true);
            }
            siramauto.setEnabled(true);
        });
    }

    void turnOffRelaySiramAuto(){
        siramauto.setEnabled(false);
        mDatabase.getReference("siramauto").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siramauto.setChecked(false);
            }
            siramauto.setEnabled(true);
        });
        mDatabase.getReference("s_siramauto").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                siramauto.setChecked(false);
            }
            siramauto.setEnabled(true);
        });
    }

    void turnOnRelayPupukAuto(){
        pupukauto.setEnabled(false);
        mDatabase.getReference("pupukauto").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupukauto.setChecked(true);
            }
            pupukauto.setEnabled(true);
        });
        mDatabase.getReference("s_pupukauto").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupukauto.setChecked(true);
            }
            pupukauto.setEnabled(true);
        });
    }

    void turnOffRelayPupukAuto(){
        pupukauto.setEnabled(false);
        mDatabase.getReference("pupukauto").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupukauto.setChecked(false);
            }
            pupukauto.setEnabled(true);
        });
        mDatabase.getReference("s_pupukauto").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                pupukauto.setChecked(false);
            }
            pupukauto.setEnabled(true);
        });
    }

    void turnOnRelayKipasAuto(){
        kipasauto.setEnabled(false);
        mDatabase.getReference("kipasauto").setValue(1).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                kipasauto.setChecked(true);
            }
            kipasauto.setEnabled(true);
        });
    }

    void turnOffRelayKipasAuto(){
        kipasauto.setEnabled(false);
        mDatabase.getReference("kipasauto").setValue(0).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                kipasauto.setChecked(false);
            }
            kipasauto.setEnabled(true);
        });
    }
}
