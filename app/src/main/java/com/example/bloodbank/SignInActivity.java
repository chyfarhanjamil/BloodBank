package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signInPhoneEditText, signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.setTitle("LOG IN");

        signInPhoneEditText = findViewById(R.id.signInPhoneEditTextId);
        signInPasswordEditText = findViewById(R.id.signInPasswordEditTextId);
        signInButton = findViewById(R.id.signInButtonId);
        signUpTextView = findViewById(R.id.signUpTextViewId);

        signUpTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signInButtonId:
                userRegister();

                break;
            case R.id.signUpTextViewId:
                intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
        }

    }
    private void userRegister() {
        String phoneNo = signInPhoneEditText.getText().toString().trim();
        String password = signInPasswordEditText.getText().toString();


        if (phoneNo.isEmpty()) {
            signInPhoneEditText.setError("Enter a mobile no.");
            signInPhoneEditText.requestFocus();
            return;
        }

        if (phoneNo.length() != 11) {
            signInPhoneEditText.setError("Enter a valid mobile no.");
            signInPhoneEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            signInPasswordEditText.setError("Enter a password");
            signInPasswordEditText.requestFocus();
            return;
        }
        if (!"123456".equals(password)) {
            signInPasswordEditText.setError("Phone no. or Password does not match");
            signInPasswordEditText.requestFocus();
            return;
        }
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}