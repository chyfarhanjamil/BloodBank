package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signUpPhoneEditText, signUpPasswordEditText;
    private TextView signInTextView;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("SIGN UP");

        signUpPhoneEditText = findViewById(R.id.signUpPhoneEditTextId);
        signUpPasswordEditText = findViewById(R.id.signUpPasswordEditTextId);
        signUpButton = findViewById(R.id.signUpButtonId);
        signInTextView = findViewById(R.id.signInTextViewId);

        signInTextView.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUpButtonId:
                userRegister();
                break;
            case R.id.signInTextViewId:
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void userRegister() {
        String phoneNo = signUpPhoneEditText.getText().toString().trim();
        String password = signUpPasswordEditText.getText().toString();



        if(phoneNo.isEmpty())
        {
            signUpPhoneEditText.setError("Enter a mobile no.");
            signUpPhoneEditText.requestFocus();
            return;
        }

        if(phoneNo.length()!=11)
        {
            signUpPhoneEditText.setError("Enter a valid mobile no.");
            signUpPhoneEditText.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            signUpPasswordEditText.setError("Enter a password");
            signUpPasswordEditText.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            signUpPasswordEditText.setError("Minimum length of password is 6");
            signUpPasswordEditText.requestFocus();
            return;
        }
        Intent intent = new Intent(getApplicationContext(),VerifyPhoneActivity.class);
        intent.putExtra("phoneNo", phoneNo);
        startActivity(intent);


    }
}