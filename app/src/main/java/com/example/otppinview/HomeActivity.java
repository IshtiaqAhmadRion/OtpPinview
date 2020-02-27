package com.example.otppinview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.PhoneAuthCredential;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton nav_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        nav_button=findViewById(R.id.navigation_image);

        nav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT))
                    drawerLayout.closeDrawer(Gravity.LEFT);

                else
                    drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


    }


    public void sendToFavoriteActivity(View view) {
        Intent intent = new Intent(this,FavoriteActivity.class);
        startActivity(intent);


    }

    public void sendToSettingsActivity(View view) {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    public void sendToReportActivity(View view) {
//        Intent intent = new Intent(this,ReportActivity.class);
//        startActivity(intent);
    }
}
