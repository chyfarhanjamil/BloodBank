package com.example.bloodbank.ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AmbulanceStoreActivity extends AppCompatActivity {

    private Button saveAmbulanceDataButton;
    private EditText ambulanceNameEditText, ambulanceAreaEditText, ambulancePhoneNoEditText;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_store);
        this.setTitle("Ambulance Information");

        databaseReference = FirebaseDatabase.getInstance().getReference("ambulance");

        saveAmbulanceDataButton = findViewById(R.id.saveAmbulanceDataButtonId);
        ambulanceNameEditText = findViewById(R.id.ambulanceNameId);
        ambulanceAreaEditText = findViewById(R.id.ambulanceAreaId);
        ambulancePhoneNoEditText = findViewById(R.id.ambulancePhoneNoId);

        saveAmbulanceDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAmbulanceData();
                Intent intent = new Intent(AmbulanceStoreActivity.this, AmbulanceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveAmbulanceData() {
        String name = ambulanceNameEditText.getText().toString().trim();
        String area = ambulanceAreaEditText.getText().toString().trim();
        String phone = ambulancePhoneNoEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

        Ambulance ambulance = new Ambulance(name, area, phone);

        databaseReference.child(key).setValue(ambulance);
        Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();

        ambulanceNameEditText.setText("");
        ambulanceAreaEditText.setText("");
        ambulancePhoneNoEditText.setText("");
    }
}