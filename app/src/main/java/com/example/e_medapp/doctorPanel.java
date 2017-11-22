package com.example.e_medapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class doctorPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_panel);

        final Button DoctorLogoutButton = (Button) findViewById(R.id.DoctorLogoutButton);
        final TextView ViewDoctorProfile = (TextView) findViewById(R.id.tvDoctorMyDetails);
        final TextView tvDoctorMyAppointment = (TextView)findViewById(R.id.tvDoctorMyAppointment);
        DoctorLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(doctorPanel.this, "Logout Successfully",
                        Toast.LENGTH_SHORT).show();
                Intent DoctorLogoutIntent = new Intent(doctorPanel.this, MainActivity.class);
                doctorPanel.this.startActivity(DoctorLogoutIntent);
            }
        });


        Intent DoctorIntent = getIntent();
       final String DoctorID = DoctorIntent.getStringExtra("DoctorID");
       final String DoctorPassword = DoctorIntent.getStringExtra("DoctorPassword");
        /*tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        }); */

        ViewDoctorProfile.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   final String DoctorID = etDoctorID.getText().toString();
               // final String DoctorPassword = etDoctorPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                              //  Intent intent = new Intent(DoctorLoginActivity.this, doctorPanel.class);
                                Intent DoctorIntent = new Intent(doctorPanel.this, DoctorDetails.class);

                                String DoctorName = jsonResponse.getString("DoctorName");
                                String DoctorEmail = jsonResponse.getString("DoctorEmail");
                                int DoctorPhone = jsonResponse.getInt("DoctorPhone");
                                String DoctorType = jsonResponse.getString("DoctorType");
                                DoctorIntent.putExtra("DoctorID", DoctorID);
                                DoctorIntent.putExtra("DoctorName", DoctorName);
                                DoctorIntent.putExtra("DoctorEmail", DoctorEmail);
                                DoctorIntent.putExtra("DoctorPhone", DoctorPhone);
                                DoctorIntent.putExtra("DoctorType", DoctorType);

                                doctorPanel.this.startActivity(DoctorIntent);
                               // DoctorLoginActivity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(doctorPanel.this);
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
                RequestQueue queue = Volley.newRequestQueue(doctorPanel.this);
                queue.add(DoctorLoginRequest);
            }
        });

        tvDoctorMyAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DoctorViewPatientIntent = new Intent(doctorPanel.this, DoctorViewPatientAppointmentActivity.class);
                doctorPanel.this.startActivity(DoctorViewPatientIntent);
            }
        });
    }
}
