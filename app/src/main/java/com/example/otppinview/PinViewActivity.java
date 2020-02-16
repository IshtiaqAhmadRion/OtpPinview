package com.example.otppinview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class PinViewActivity extends AppCompatActivity {

    Pinview pin;
    Button verifyButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_view);

        verifyButton = findViewById(R.id.verify_button);
        pin = (Pinview) findViewById(R.id.pinview);

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
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(MainActivity.mVerificationId,VerificationCode);
                    MainActivity.signInWithPhoneAuthCredential(credential);


                }
            }
        });


    }



}
