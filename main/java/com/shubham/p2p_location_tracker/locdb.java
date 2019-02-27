package com.shubham.p2p_location_tracker;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class locdb extends AppCompatActivity {

    private static final String URL = "http://localhost/location_database/register.php";
    private static final String TAG = "AddLocation";

    ProgressDialog progressDialog;

    private EditText e1;
    private TextView t1,t2;
    private Button b1;
    private double lat,lon;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locdb);
        Bundle bundle = getIntent().getExtras();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);


       final double  lat =  bundle.getDouble("latitude");
       final  double lon =  bundle.getDouble("longitude");
t1 = (TextView) findViewById(R.id.textView9);
        t2 = (TextView) findViewById(R.id.textView7);
        e1 = (EditText) findViewById(R.id.editText);
        b1 = (Button) findViewById(R.id.button2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }});}

            private void submitForm() {
                registerLocation(e1.getText().toString(), lat, lon);
            }
            private void registerLocation(final String name, final double latitude, final double longitude )
            {
                String cancel_req_tag = "register";
                progressDialog.setMessage("Adding location ...");
                showDialog();

                StringRequest strReq = new StringRequest(Request.Method.POST,
                        URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                String user = jObj.getJSONObject("user").getString("name");
                                Toast.makeText(getApplicationContext(), "Hi " + user + ", Your location is successfully Added!", Toast.LENGTH_SHORT).show();

                                // Launch login activity
                                Intent intent = new Intent(
                                        locdb.this,
                                        UserActivity.class);
                                startActivity(intent);
                                finish();
                            } else {

                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Addition Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("latitude", String.valueOf(latitude));
                        params.put("longitude", String.valueOf(longitude));

                        return params;
                    }
                };
                // Adding request to request queue
                AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
            }

            private void showDialog() {
                if (!progressDialog.isShowing())
                    progressDialog.show();
            }

            private void hideDialog() {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }





}