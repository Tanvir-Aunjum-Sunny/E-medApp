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

public class PatientLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        final EditText etPatientID = (EditText) findViewById(R.id.etPatientID);
        final EditText etPatientPassword = (EditText) findViewById(R.id.etPatientPassword);
        //final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bPSignIn = (Button) findViewById(R.id.bPatientSignIn);

        /*tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        }); */

        bPSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String PatientID = etPatientID.getText().toString();
                final String PatientPassword = etPatientPassword.getText().toString();

                if (PatientID == null && PatientPassword == null && PatientID.isEmpty() && PatientPassword.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PatientLoginActivity.this);
                    builder.setMessage("Login Failed")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                } else {
                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {

                                    Intent intent = new Intent(PatientLoginActivity.this, patientPanel.class);
                                    Intent PatientIntent = new Intent(PatientLoginActivity.this, PatientDetails.class);

                                    intent.putExtra("PatientID", PatientID);
                                    intent.putExtra("PatientPassword", PatientPassword);

                                    String PatientName = jsonResponse.getString("PatientName");
                                    String PatientEmail = jsonResponse.getString("PatientEmail");
                                    int PatientPhone = jsonResponse.getInt("PatientPhone");
                                    String PatientType = jsonResponse.getString("PatientType");
                                    PatientIntent.putExtra("PatientID", PatientID);
                                    PatientIntent.putExtra("PatientName", PatientName);
                                    PatientIntent.putExtra("PatientEmail", PatientEmail);
                                    PatientIntent.putExtra("PatientPhone", PatientPhone);
                                    PatientIntent.putExtra("DoctorType", PatientType);

                                    PatientLoginActivity.this.startActivity(PatientIntent);
                                    PatientLoginActivity.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(PatientLoginActivity.this);
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

                    patientLoginRequest PatientLoginRequest = new patientLoginRequest(PatientID, PatientPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PatientLoginActivity.this);
                    queue.add(PatientLoginRequest);
                }
            }
        });
    }
}
