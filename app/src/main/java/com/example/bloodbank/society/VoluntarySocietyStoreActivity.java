package com.example.bloodbank.society;

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


public class VoluntarySocietyStoreActivity extends AppCompatActivity {

    private Button saveSocietyDataButton;
    private EditText societyNameEditText, societyAreaEditText, societyPhoneNoEditText;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntary_society_store);
        this.setTitle("Voluntary Society Information");
        databaseReference = FirebaseDatabase.getInstance().getReference("society");

        saveSocietyDataButton = findViewById(R.id.saveSocietyDataButtonId);
        societyNameEditText = findViewById(R.id.societyNameId);
        societyAreaEditText = findViewById(R.id.societyAreaId);
        societyPhoneNoEditText = findViewById(R.id.societyPhoneNoId);

        saveSocietyDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSocietyData();
                Intent intent = new Intent(VoluntarySocietyStoreActivity.this, VoluntarySocietyActivity.class);
                startActivity(intent);
            }
        });
    }
    private void saveSocietyData() {
        String name = societyNameEditText.getText().toString().trim();
        String area = societyAreaEditText.getText().toString().trim();
        String phone = societyPhoneNoEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

        VoluntarySociety voluntarySociety = new VoluntarySociety(name, area, phone);

        databaseReference.child(key).setValue(voluntarySociety);
        Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();

        societyNameEditText.setText("");
        societyAreaEditText.setText("");
        societyPhoneNoEditText.setText("");
    }
}
