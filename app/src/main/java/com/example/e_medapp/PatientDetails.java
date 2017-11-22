package com.example.e_medapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PatientDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        Intent PatientIntent = getIntent();
        String PatientID = PatientIntent.getStringExtra("PatientID");
        String PatientName = PatientIntent.getStringExtra("PatientName");
        String PatientEmail = PatientIntent.getStringExtra("PatientEmail");
        int PatientPhone = PatientIntent.getIntExtra("PatientPhone", -1);
        String PatientType = PatientIntent.getStringExtra("PatientType");

        TextView tvPatientID = (TextView) findViewById(R.id.tvPatientID);
        TextView tvPatientName = (TextView) findViewById(R.id.tvPatientName);
        TextView tvPatientEmail = (TextView) findViewById(R.id.tvPatientEmail);
        TextView tvPatientPhone = (TextView) findViewById(R.id.tvPatientPhone);
        TextView tvPatientType = (TextView) findViewById(R.id.tvPatientType);


        // Display user details
        tvPatientID.setText(PatientID);
        tvPatientName.setText(PatientName);
        tvPatientEmail.setText(PatientEmail);
        tvPatientPhone.setText(PatientPhone + "");
        tvPatientType.setText(PatientType);

    }
}
