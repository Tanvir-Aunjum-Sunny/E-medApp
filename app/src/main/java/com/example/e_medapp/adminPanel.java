package com.example.e_medapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class adminPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        final TextView tvAddnewDr = (TextView) findViewById(R.id.tvAddNewDr);
        final TextView tvAddnewPatient = (TextView) findViewById(R.id.tvAddNewPatient);
        final TextView tvAdminViewPatient = (TextView) findViewById(R.id.tvAdminViewPatients);
        final TextView tvAdminViewAppointment = (TextView) findViewById(R.id.tvViewAppointments);
        final TextView tvAdminViewDoctor = (TextView) findViewById(R.id.tvViewDoctor);
        final TextView tvAdminViewFeedback = (TextView) findViewById(R.id.tvViewFeedback);

        tvAddnewDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DoctorRegisterIntent = new Intent(adminPanel.this, doctorRegistration.class);
                adminPanel.this.startActivity(DoctorRegisterIntent);
            }
        });

        tvAdminViewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DoctorProfileIntent = new Intent(adminPanel.this, adminDoctorViewActivity.class);
                adminPanel.this.startActivity(DoctorProfileIntent);
            }
        });


        tvAddnewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PatientRegisterIntent = new Intent(adminPanel.this, patientRegistration.class);
                adminPanel.this.startActivity(PatientRegisterIntent);
            }
        });

        tvAdminViewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AdminViewPatientIntent = new Intent(adminPanel.this, adminPatientViewActivity.class);
                adminPanel.this.startActivity(AdminViewPatientIntent);
            }
        });

        tvAdminViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AdminViewPatientIntent = new Intent(adminPanel.this, adminViewAppointment.class);
                adminPanel.this.startActivity(AdminViewPatientIntent);
            }
        });

        tvAdminViewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AdminViewPatientIntent = new Intent(adminPanel.this, adminViewFeedback.class);
                adminPanel.this.startActivity(AdminViewPatientIntent);
            }
        });

        final Button adminLogoutButton = (Button) findViewById(R.id.adminLogoutButton);

        adminLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(adminPanel.this, "Logout Successfully",
                        Toast.LENGTH_SHORT).show();
                Intent AdminLogoutIntent = new Intent(adminPanel.this, MainActivity.class);
                adminPanel.this.startActivity(AdminLogoutIntent);
            }
        });
    }
}
