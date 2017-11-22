package com.example.e_medapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackPatientActivity extends AppCompatActivity {
   EditText etPatientSubmitFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_patient);
        Button buttonSubmitFeedback = (Button)findViewById(R.id.buttonSubmitFeedback);
        etPatientSubmitFeedback = (EditText)findViewById(R.id.etSubmitFeedback);

        buttonSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Feedback = etPatientSubmitFeedback.getText().toString();
                if (Feedback.isEmpty()) {
                    Toast.makeText(FeedbackPatientActivity.this, "Please write feedback",
                            Toast.LENGTH_LONG).show();
                }
                else{
                Intent PatientIntent = getIntent();
                final String PatientID = PatientIntent.getStringExtra("PatientID");
                final String PatientName = PatientIntent.getStringExtra("PatientName");
                final String PatientPassword = PatientIntent.getStringExtra("PatientPassword");
                final String PatientEmail = PatientIntent.getStringExtra("PatientEmail");
                final int PatientPhone = PatientIntent.getIntExtra("PatientPhone", -1);
                final String PatientType = PatientIntent.getStringExtra("PatientType");
                final String DoctorID = PatientIntent.getStringExtra("DoctorID");
                final String Date = PatientIntent.getStringExtra("Date");
                final String Time = PatientIntent.getStringExtra("Time");
                //final String Feedback = etPatientSubmitFeedback.getText().toString();

                /*if (Feedback.isEmpty() && Feedback == null) {
                    Toast.makeText(FeedbackPatientActivity.this, "Please write feedback",
                            Toast.LENGTH_LONG).show();

                } */

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(FeedbackPatientActivity.this, "Feedback Submitted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FeedbackPatientActivity.this, patientPanel.class);
                                FeedbackPatientActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackPatientActivity.this);
                                builder.setMessage("Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                FeedbackRequest feedRequest = new FeedbackRequest(PatientID, PatientName, PatientPassword, PatientEmail, PatientPhone, PatientType, Date, Time, DoctorID, Feedback, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FeedbackPatientActivity.this);
                queue.add(feedRequest);
                    Toast.makeText(FeedbackPatientActivity.this, "Feedback Submitted Successfully",
                            Toast.LENGTH_LONG).show();

                }
            }

        });
    }
}
