package com.example.e_medapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DoctorLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        final EditText etDoctorID = (EditText) findViewById(R.id.etDoctorID);
        final EditText etDoctorPassword = (EditText) findViewById(R.id.etDoctorPassword);
    //final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
         final Button bDSignIn = (Button) findViewById(R.id.bDoctorSignIn);

        /*tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        }); */

    bDSignIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String DoctorID = etDoctorID.getText().toString();
            final String DoctorPassword = etDoctorPassword.getText().toString();


            // Response received from the server
            if (DoctorID != null && DoctorPassword != null && !DoctorID.isEmpty() && !DoctorPassword.isEmpty()) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                Intent intent = new Intent(DoctorLoginActivity.this, doctorPanel.class);
                                Intent DoctorIntent = new Intent(DoctorLoginActivity.this, DoctorDetails.class);

                                intent.putExtra("DoctorID", DoctorID);
                                intent.putExtra("DoctorPassword", DoctorPassword);

                                String DoctorName = jsonResponse.getString("DoctorName");
                                String DoctorEmail = jsonResponse.getString("DoctorEmail");
                                int DoctorPhone = jsonResponse.getInt("DoctorPhone");
                                String DoctorType = jsonResponse.getString("DoctorType");
                                DoctorIntent.putExtra("DoctorID", DoctorID);
                                DoctorIntent.putExtra("DoctorName", DoctorName);
                                DoctorIntent.putExtra("DoctorEmail", DoctorEmail);
                                DoctorIntent.putExtra("DoctorPhone", DoctorPhone);
                                DoctorIntent.putExtra("DoctorType", DoctorType);

                                DoctorLoginActivity.this.startActivity(DoctorIntent);
                                DoctorLoginActivity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorLoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                doctorLoginRequest DoctorLoginRequest = new doctorLoginRequest(DoctorID, DoctorPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(DoctorLoginActivity.this);
                queue.add(DoctorLoginRequest);
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorLoginActivity.this);
                builder.setMessage("Login Failed")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        }



        });

    }
}