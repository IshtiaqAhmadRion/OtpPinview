package com.example.otppinview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DeleteActivity extends AppCompatActivity {
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        imageButton = findViewById(R.id.report_back_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }

    public void sendToHomeActivity(View view) {
        Intent intent = new Intent(DeleteActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
