package com.shubham.p2p_location_tracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ViewPeers extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName;
    private EditText editTextLat;
    private EditText editTextLong;
    private Button btnPrev;
    private Button btnNext;
    private Button btnMap;

    private static final String SELECT_SQL = "SELECT * FROM location";

    private SQLiteDatabase db;

    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpeers);
        openDatabase();

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLat = (EditText) findViewById(R.id.editLat);
        editTextLong = (EditText) findViewById(R.id.editLong);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnMap = (Button) findViewById(R.id.btnMap);

        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnMap.setOnClickListener(this);

        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showRecords();
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("location_DB", Context.MODE_PRIVATE, null);
    }

    protected void showRecords() {
        String name = c.getString(1);
        String lat = c.getString(2);
        String lon = c.getString(3);
        editTextName.setText(name);
        editTextLat.setText(lat);
        editTextLong.setText(lon);
    }

    protected void moveNext() {
        if (!c.isLast())
            c.moveToNext();

        showRecords();
    }

    protected void movePrev() {
        if (!c.isFirst())
            c.moveToPrevious();

        showRecords();

    }




    private void openMap() {
        String lat = c.getString(2);
        String lon = c.getString(3);
        double dlat = Double.parseDouble(lat);
        double dlon = Double.parseDouble(lon);
        String uri = "http://maps.google.com/maps?saddr=" + 28.4569 + "," + 77.4981 + "&daddr=" + dlat + "," + dlon;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v == btnNext) {
            moveNext();
        }

        if (v == btnPrev) {
            movePrev();
        }



        if (v == btnMap) {
            openMap();
        }
    }

}