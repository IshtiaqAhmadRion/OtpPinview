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
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText phoneNumber;

    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submit_button);
        phoneNumber =findViewById(R.id.number_edit_text);

        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneNumber);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = ccp.getFullNumberWithPlus();
                String num = phoneNumber.getText().toString();

                if(TextUtils.isEmpty(num)){
                    Toast.makeText(MainActivity.this, "Please enter a phone number first", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this,PinViewActivity.class);
                    intent.putExtra("number",number);
                    intent.putExtra("num", num);
                    startActivity(intent);
                }

            }
        });

    }

}
