package com.example.e_medapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonAdmin = (Button)findViewById(R.id.buttonAdmin);
        final Button buttonPatient = (Button)findViewById(R.id.buttonPatient);
        final Button buttonDoctor = (Button)findViewById(R.id.buttonDoctor);

        buttonAdmin.setOnClickListener(new View.OnClickListener(){
           @Override
             public void onClick(View v){
               Intent adminLoginIntent = new Intent(MainActivity.this,AdminLoginActivity.class);
               MainActivity.this.startActivity(adminLoginIntent);
            }
        });

        buttonPatient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent patientLoginIntent = new Intent(MainActivity.this,PatientLoginActivity.class);
                MainActivity.this.startActivity(patientLoginIntent);
            }
        });

        buttonDoctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent doctorLoginIntent = new Intent(MainActivity.this,DoctorLoginActivity.class);
                MainActivity.this.startActivity(doctorLoginIntent);
            }
        });
    }
}
