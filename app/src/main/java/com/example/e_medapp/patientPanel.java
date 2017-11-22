package com.example.e_medapp;

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

public class patientPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_panel);

        final Button PatientLogoutButton = (Button) findViewById(R.id.PatientLogoutButton);
        final TextView ViewPatientProfile = (TextView) findViewById(R.id.tvPatientMyDetails);
        final TextView ViewPatientMyAppointment = (TextView) findViewById(R.id.tvPatientMyAppointment);
        final TextView ViewPatientViewDoctor = (TextView) findViewById(R.id.tvPatientViewDoctor);
        final TextView ViewPatientAppointment = (TextView) findViewById(R.id.tvPatientViewAppointment);
        final TextView PatientSubmitFeedback = (TextView) findViewById(R.id.tvPatientSubmitFeedback);

        PatientLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(patientPanel.this, "Logout Successfully",
                        Toast.LENGTH_SHORT).show();
                Intent PatientLogoutIntent = new Intent(patientPanel.this, MainActivity.class);
                patientPanel.this.startActivity(PatientLogoutIntent);
            }
        });
        ViewPatientViewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PatientViewDoctorIntent = new Intent(patientPanel.this, patientDoctorViewActivity.class);
                patientPanel.this.startActivity(PatientViewDoctorIntent);
            }
        });
        Intent PatientIntent = getIntent();
        final String PatientID = PatientIntent.getStringExtra("PatientID");
        final String PatientPassword = PatientIntent.getStringExtra("PatientPassword");
        /*tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        }); */

        ViewPatientProfile.setOnClickListener (new View.OnClickListener() {
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


                            {

                                //  Intent intent = new Intent(DoctorLoginActivity.this, doctorPanel.class);
                                Intent PatientIntent = new Intent(patientPanel.this, PatientDetails.class);

                                String PatientName = jsonResponse.getString("PatientName");
                                String PatientEmail = jsonResponse.getString("PatientEmail");
                                int PatientPhone = jsonResponse.getInt("PatientPhone");
                                String PatientType = jsonResponse.getString("PatientType");
                                PatientIntent.putExtra("PatientID", PatientID);
                                PatientIntent.putExtra("PatientName", PatientName);
                                PatientIntent.putExtra("PatientPassword", PatientPassword);
                                PatientIntent.putExtra("PatientEmail", PatientEmail);
                                PatientIntent.putExtra("PatientPhone", PatientPhone);
                                PatientIntent.putExtra("PatientType", PatientType);

                                patientPanel.this.startActivity(PatientIntent);
                                // DoctorLoginActivity.this.startActivity(intent);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                patientLoginRequest PatientLoginRequest = new patientLoginRequest(PatientID, PatientPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(patientPanel.this);
                queue.add(PatientLoginRequest);
            }
        });

       /* ViewDoctorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent DoctorProfileIntent = new Intent(doctorPanel.this, DoctorDetails.class);
                doctorPanel.this.startActivity(DoctorProfileIntent);
            }
        }); */
        ViewPatientMyAppointment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);




                                //  Intent intent = new Intent(DoctorLoginActivity.this, doctorPanel.class);
                                Intent PatientIntent = new Intent(patientPanel.this, patientMyAppointment.class);

                                String PatientName = jsonResponse.getString("PatientName");
                                String PatientEmail = jsonResponse.getString("PatientEmail");
                                int PatientPhone = jsonResponse.getInt("PatientPhone");
                                String PatientType = jsonResponse.getString("PatientType");
                                PatientIntent.putExtra("PatientID", PatientID);
                                PatientIntent.putExtra("PatientName", PatientName);
                                PatientIntent.putExtra("PatientPassword", PatientPassword);
                                PatientIntent.putExtra("PatientEmail", PatientEmail);
                                PatientIntent.putExtra("PatientPhone", PatientPhone);
                                PatientIntent.putExtra("PatientType", PatientType);

                                patientPanel.this.startActivity(PatientIntent);
                                // DoctorLoginActivity.this.startActivity(intent);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                patientLoginRequest PatientLoginRequest = new patientLoginRequest(PatientID, PatientPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(patientPanel.this);
                queue.add(PatientLoginRequest);

            }
        });

        ViewPatientAppointment.setOnClickListener (new View.OnClickListener() {
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


                            {

                                //  Intent intent = new Intent(DoctorLoginActivity.this, doctorPanel.class);
                                Intent PatientIntent = new Intent(patientPanel.this, patientViewAppointment.class);

                                String PatientName = jsonResponse.getString("PatientName");
                                String PatientEmail = jsonResponse.getString("PatientEmail");
                                int PatientPhone = jsonResponse.getInt("PatientPhone");
                                String PatientType = jsonResponse.getString("PatientType");
                                String Date = jsonResponse.getString("Date");
                                String Time = jsonResponse.getString("Time");
                                String DoctorID = jsonResponse.getString("DoctorID");

                                PatientIntent.putExtra("PatientID", PatientID);
                                PatientIntent.putExtra("PatientName", PatientName);
                                PatientIntent.putExtra("PatientPassword", PatientPassword);
                                PatientIntent.putExtra("PatientEmail", PatientEmail);
                                PatientIntent.putExtra("PatientPhone", PatientPhone);
                                PatientIntent.putExtra("PatientType", PatientType);
                                PatientIntent.putExtra("Date", Date);
                                PatientIntent.putExtra("Time", Time);
                                PatientIntent.putExtra("DoctorID", DoctorID);

                                patientPanel.this.startActivity(PatientIntent);
                                // DoctorLoginActivity.this.startActivity(intent);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                patientLoginRequest PatientLoginRequest = new patientLoginRequest(PatientID, PatientPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(patientPanel.this);
                queue.add(PatientLoginRequest);
            }
        });

        PatientSubmitFeedback.setOnClickListener (new View.OnClickListener() {
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


                            {

                                //  Intent intent = new Intent(DoctorLoginActivity.this, doctorPanel.class);
                                Intent PatientIntent = new Intent(patientPanel.this, FeedbackPatientActivity.class);

                                String PatientName = jsonResponse.getString("PatientName");
                                String PatientEmail = jsonResponse.getString("PatientEmail");
                                int PatientPhone = jsonResponse.getInt("PatientPhone");
                                String PatientType = jsonResponse.getString("PatientType");
                                String Date = jsonResponse.getString("Date");
                                String Time = jsonResponse.getString("Time");
                                String DoctorID = jsonResponse.getString("DoctorID");

                                PatientIntent.putExtra("PatientID", PatientID);
                                PatientIntent.putExtra("PatientName", PatientName);
                                PatientIntent.putExtra("PatientPassword", PatientPassword);
                                PatientIntent.putExtra("PatientEmail", PatientEmail);
                                PatientIntent.putExtra("PatientPhone", PatientPhone);
                                PatientIntent.putExtra("PatientType", PatientType);
                                PatientIntent.putExtra("Date", Date);
                                PatientIntent.putExtra("Time", Time);
                                PatientIntent.putExtra("DoctorID", DoctorID);

                                patientPanel.this.startActivity(PatientIntent);
                                // DoctorLoginActivity.this.startActivity(intent);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                patientLoginRequest PatientLoginRequest = new patientLoginRequest(PatientID, PatientPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(patientPanel.this);
                queue.add(PatientLoginRequest);
            }
        });
    }
}
