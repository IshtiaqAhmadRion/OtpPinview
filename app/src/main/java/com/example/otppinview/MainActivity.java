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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText phoneNumber;
    CountryCodePicker ccp;

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        RootRef = FirebaseDatabase.getInstance().getReference();

        submit = findViewById(R.id.submit_button);
        phoneNumber =findViewById(R.id.number_edit_text);

        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneNumber);



    }

    @Override
    protected void onStart() {
        super.onStart();

        if(currentUser == null){
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

        else{
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }

    //    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if(currentUser != null){
//
//            verifyUser();
//
//        }
//    }

//    private void verifyUser() {
//        String currentUserId = mAuth.getCurrentUser().getUid();
//        RootRef.child("User").child(currentUserId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


}
