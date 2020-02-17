package com.example.otppinview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PinViewActivity<mCallbacks> extends AppCompatActivity {

    Pinview pin;
    Button verifyButton;

    //PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    public static String mVerificationId;
    public PhoneAuthProvider.ForceResendingToken mResendToken;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_view);

        verifyButton = findViewById(R.id.verify_button);
        pin = (Pinview) findViewById(R.id.pinview);
        mAuth = FirebaseAuth.getInstance();



        String num = getIntent().getStringExtra("num");


        sendVerificationCode();

        pin.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {

                Toast.makeText(PinViewActivity.this, "code enter successfully", Toast.LENGTH_SHORT).show();
            }
        });



        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VerificationCode = pin.getValue().toString();
                if(TextUtils.isEmpty(VerificationCode)){
                    Toast.makeText(PinViewActivity.this, "Please enter code first ...", Toast.LENGTH_SHORT).show();
                }
                else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,VerificationCode);
                    signInWithPhoneAuthCredential(credential);


                }
            }
        });


    }

    private void sendVerificationCode() {


        String number = getIntent().getStringExtra("number");

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                PinViewActivity.this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //String code = phoneAuthCredential.getSmsCode();


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Toast.makeText(PinViewActivity.this, "Something wrong..", Toast.LENGTH_SHORT).show();

        }

        @Override
          public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                mVerificationId = verificationId;
                mResendToken = token;



            }



    } ;

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PinViewActivity.this, "Congratulations, You're logged in successfully...", Toast.LENGTH_SHORT).show();

                            sendUserToHomeActivity();



                        } else {
                            String message =task.getException().toString();
                            Toast.makeText(PinViewActivity.this, "Please Enter valid code", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void sendUserToHomeActivity() {
        Intent pinviewIntent = new Intent(PinViewActivity.this,HomeActivity.class);
        startActivity(pinviewIntent);
    }



}
