package com.shubham.p2p_location_tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LocListTest extends AppCompatActivity {
    private Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loclist);


        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "http://maps.google.com/maps?saddr=" + 28.4569 + "," + 77.4981 + "&daddr=" + 28.4500 + "," + 77.4900;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "http://maps.google.com/maps?saddr=" + 28.4569 + "," + 77.4981 + "&daddr=" + 12.2958 + "," + 76.6394;
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "http://maps.google.com/maps?saddr=" + 28.4569 + "," + 77.4981 + "&daddr=" + 28.5708 + "," + 77.3261 ;
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent3);
            }
        });



    }


}