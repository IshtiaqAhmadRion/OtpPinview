package com.example.otppinview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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

    ListView listView;
    DrawerLayout drawerLayout;
    ImageButton nav_button;

    String options[];
    int[] icon ={R.drawable.ic_favorite_black_24dp,R.drawable.ic_settings_black_24dp,
                 R.drawable.ic_report_problem_black_24dp,R.drawable.ic_help_black_24dp,
                 R.drawable.log_out};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        listView = findViewById(R.id.list_view_option);

        options = getResources().getStringArray(R.array.options);


        CustomAdapter adapter = new CustomAdapter(this,options,icon);
        listView.setAdapter(adapter);

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



}
