package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {
    //Button verify_button;
    EditText OTP_editText;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    String verificationCodeBySystem;
    public String fakeEmail;
    String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);


        //verify_button = findViewById(R.id.verifyPhoneButtonId);
        OTP_editText = findViewById(R.id.OTPEditTextId);
        progressBar = findViewById(R.id.progressbarId);

        phoneNo = getIntent().getStringExtra("phoneNo");
        sendVerificationCodeToUser(phoneNo);

    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88" + phoneNo,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
        );


    }




    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!= null){
                OTP_editText.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };
    private void verifyCode(String codeByUser){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    /*fakeEmail= phoneNo+"@gmail.com";
                    AuthCredential credential = EmailAuthProvider.getCredentialWithLink(fakeEmail, fakeEmail);
                    firebaseAuth.getCurrentUser().linkWithCredential(credential);*/
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(VerifyPhoneActivity.this, "Verified", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                    Toast.makeText(VerifyPhoneActivity.this, "Verification not completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void callNextScreenFromOTP(View view) {
        String code = OTP_editText.getText().toString();
        if(!code.isEmpty())
        {
            verifyCode(code);
        }

    }
}