package com.example.otppinview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText phoneNumber;
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    public static String mVerificationId;
    public PhoneAuthProvider.ForceResendingToken mResendToken;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = findViewById(R.id.submit_button);


        mAuth = FirebaseAuth.getInstance();

        phoneNumber =findViewById(R.id.number_edit_text);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNumber.getText().toString();

                if(TextUtils.isEmpty(number)){
                    Toast.makeText(MainActivity.this, "Please enter a phone number first", Toast.LENGTH_SHORT).show();
                }
                else {

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            number,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            MainActivity.this,               // Activity (for callback binding)
                            mCallbacks);        // OnVerificationStateChangedCallbacks

                    Intent intent = new Intent(MainActivity.this,PinViewActivity.class);


                    startActivity(intent);

                }

            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {


                if (e instanceof FirebaseAuthInvalidCredentialsException) {


                } else if (e instanceof FirebaseTooManyRequestsException) {


                }


            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                mVerificationId = verificationId;
                mResendToken = token;

                Log.e("code","sent");


            }
        };

    }
    public static void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        Log.e("verification","successful");

    }



}
