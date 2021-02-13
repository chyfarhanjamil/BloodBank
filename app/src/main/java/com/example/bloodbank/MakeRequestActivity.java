package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.society.VoluntarySocietyActivity;
import com.example.bloodbank.society.VoluntarySocietyStoreActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MakeRequestActivity extends AppCompatActivity {

    String[] bloodGrpArray;
    private Spinner recipientBloodGrpSpinner;
    private Button saveRecipientDataButton;
    private EditText recipientNameEditText, recipientAreaEditText, recipientPhoneNoEditText,
            recipientDetailsEditText, recipientAmountBloodEditText;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);
        this.setTitle("Make Request");
        databaseReference = FirebaseDatabase.getInstance().getReference("request");

        bloodGrpArray = getResources().getStringArray(R.array.blood_grp);

        recipientBloodGrpSpinner = findViewById(R.id.recipientBloodGrpSpinnerId);

        saveRecipientDataButton = findViewById(R.id.saveRecipientDataButtonId);

        recipientNameEditText = findViewById(R.id.recipientNameId);
        recipientAreaEditText = findViewById(R.id.recipientAreaId);
        recipientAmountBloodEditText=findViewById(R.id.recipientAmountBloodId);
        recipientPhoneNoEditText = findViewById(R.id.recipientPhoneNoId);
        recipientDetailsEditText = findViewById(R.id.recipientDetailsId);

        ArrayAdapter<String> bloodGrpAdapter = new ArrayAdapter<>(this, R.layout.sample_bloodgrp_spinner_layout,
                R.id.textViewBloodGrpSampleId, bloodGrpArray);
        recipientBloodGrpSpinner.setAdapter(bloodGrpAdapter);

        saveRecipientDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecipientData();
                Intent intent = new Intent(MakeRequestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveRecipientData(){
        String name = recipientNameEditText.getText().toString().trim();
        String area = recipientAreaEditText.getText().toString().trim();
        String bloodgrp = recipientBloodGrpSpinner.getSelectedItem().toString();
        String amount = recipientAmountBloodEditText.getText().toString().trim();
        String phone = recipientPhoneNoEditText.getText().toString().trim();
        String details = recipientDetailsEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

        MakeRequest makeRequest = new MakeRequest(name, area, bloodgrp, amount, phone, details);
        databaseReference.child(key).setValue(makeRequest);
        Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();

        recipientNameEditText.setText("");
        recipientAreaEditText.setText("");
        recipientAmountBloodEditText.setText("");
        recipientPhoneNoEditText.setText("");
        recipientDetailsEditText.setText("");
    }
}