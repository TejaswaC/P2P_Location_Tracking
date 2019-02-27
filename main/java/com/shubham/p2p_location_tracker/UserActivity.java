package com.shubham.p2p_location_tracker;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";

    private TextView greetingTextView;
    private Button btnDB;
    private Button btnList;
    private Button btnPortal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        greetingTextView = (TextView) findViewById(R.id.greeting_text_view);
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");
        btnDB = (Button) findViewById(R.id.logout_button);
        btnList = (Button) findViewById(R.id.peer);
        btnPortal = (Button) findViewById(R.id.logout);

        greetingTextView.setText("Hello "+ "User");
        // Progress dialog
        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FinalLoc.class);
                startActivity(i);
            }


        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(getApplicationContext(), LocList.class);
                startActivity(j);
            }


        });
        btnPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_MAIN);
                PackageManager managerclock = getPackageManager();
                i = managerclock.getLaunchIntentForPackage("appinventor.ai_tejasw22.TutorialGoogleMap");
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(i);
            }


        });
    }
}

