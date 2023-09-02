package com.example.dalannamabapadanputeradanrohkudusamin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class MonitoringActivity extends AppCompatActivity {

    private TextView value_moist, value_suhu, value_ph, value_humid;
    private LinearLayout layoutHumid;
    private Firebase mRef1, mRef2, mRef3, mRef4;
    private DatabaseReference saveHistoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        saveHistoryRef = FirebaseDatabase.getInstance().getReference("history");

        value_moist = (TextView) findViewById(R.id.value);
        value_suhu = (TextView)findViewById(R.id.value2);
        value_ph = (TextView)findViewById(R.id.value3);
        value_humid = (TextView)findViewById(R.id.value4);

        layoutHumid = findViewById(R.id.keempat);

        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mRef1 = new Firebase("https://dalamnamabapaputrarohkud-d470b-default-rtdb.asia-southeast1.firebasedatabase.app/moist1/moist1");

        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String moist1 = dataSnapshot.getValue(String.class);
                value_moist.setText(moist1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef2 = new Firebase("https://dalamnamabapaputrarohkud-d470b-default-rtdb.asia-southeast1.firebasedatabase.app/suhu/suhu");

        mRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String suhu = dataSnapshot.getValue(String.class);
                value_suhu.setText(suhu);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef3 = new Firebase("https://dalamnamabapaputrarohkud-d470b-default-rtdb.asia-southeast1.firebasedatabase.app/phtanah/phtanah");

        mRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ph = dataSnapshot.getValue(String.class);
                value_ph.setText(ph);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef4 = new Firebase("https://dalamnamabapaputrarohkud-d470b-default-rtdb.asia-southeast1.firebasedatabase.app/humid/humid");

        mRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String humid = dataSnapshot.getValue(String.class);
                value_humid.setText(humid);
//                DatabaseReference humidRef = saveHistoryRef.child("humid").child(Util.getCurrentDate());
//                String key = humidRef.push().getKey();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        layoutHumid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonitoringActivity.this, GraphActivity.class);
                intent.putExtra("type", "humid");
                startActivity(intent);
            }
        });
    }
}
