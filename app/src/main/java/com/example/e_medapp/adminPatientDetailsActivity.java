package com.example.e_medapp;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class adminPatientDetailsActivity extends AppCompatActivity {
    final String LOG = "DetailActivity";
    EditText etID, etName, etEmail, etPhone, etType, etTime, etDate, etDoctorID;

    private adminPatient AdminPatient;
    Calendar myCalendar;

    //ImageView ivImage;
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);
        myCalendar = Calendar.getInstance();
        etDate = (EditText) findViewById(R.id.etAdminPatientDate);
        etTime = (EditText) findViewById(R.id.etAdminPatientTime);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */

        //ImageLoader.getInstance().init(UILConfig.config(DetailActivity.this));

        etTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(adminPatientDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(adminPatientDetailsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        AdminPatient = (adminPatient) getIntent().getSerializableExtra("item");

        etID = (EditText) findViewById(R.id.etAdminPatientID);
        etName = (EditText) findViewById(R.id.etAdminPatientName);
        etEmail = (EditText) findViewById(R.id.etAdminPatientEmail);
        etPhone = (EditText) findViewById(R.id.etAdminPatientPhone);
        etType = (EditText) findViewById(R.id.etAdminPatientType);

        etDoctorID = (EditText) findViewById(R.id.etAdminPatientDoctor);

        if (AdminPatient != null) {
            etID.setText(AdminPatient.PatientID);
            etName.setText(AdminPatient.PatientName);
            etEmail.setText(AdminPatient.PatientEmail);
            etPhone.setText("0" + AdminPatient.PatientPhone);
            etType.setText(AdminPatient.PatientType);
            etDate.setText(AdminPatient.Date);
            etTime.setText(AdminPatient.Time);
            etDoctorID.setText(AdminPatient.DoctorID);
            Log.d(LOG, "DetailActivity");
            // ImageLoader.getInstance().displayImage(product.image_url, ivImage);
        }

        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final Button btnSendMsg = (Button) findViewById(R.id.btnSendMsg);
        // btnSendMsg.setOnClickListener(this);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap postData = new HashMap();
                postData.put("patientNo", "" + AdminPatient.patientNo);
                postData.put("PatientID", etID.getText().toString());
                postData.put("PatientName", etName.getText().toString());
                postData.put("PatientPassword", AdminPatient.PatientPassword);
                postData.put("PatientEmail", etEmail.getText().toString());
                postData.put("PatientPhone", etPhone.getText().toString());
                postData.put("PatientType", etType.getText().toString());
                postData.put("Date", etDate.getText().toString());
                postData.put("Time", etTime.getText().toString());
                postData.put("DoctorID", etDoctorID.getText().toString());
                postData.put("mobile", "android");


                PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(adminPatientDetailsActivity.this,
                        postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        Log.d(LOG, s);
                        if (s.contains("success")) {
                            Toast.makeText(adminPatientDetailsActivity.this, "Update Successfully", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(adminPatientDetailsActivity.this, adminPatientViewActivity.class);
                            startActivity(in);
                        }
                    }
                });
                taskUpdate.execute("http://minissan.comlu.com/patientUpdate.php");
            }
        });

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = etID.getText().toString();
                String Name = etName.getText().toString();
                String Email = etEmail.getText().toString();
                String Phone = etPhone.getText().toString();
                String Type = etType.getText().toString();
                String Date = etDate.getText().toString();
                String Time = etTime.getText().toString();
                String Doctor = etDoctorID.getText().toString();
                String Message = "IIUM Health Centre"+"\n"+"Your Appointment Details"+"\n"+"Doctor ID :"+Doctor+"\n"+
                        "Appointment Date : "+Date+"\n"+"Appointment Time :"+Time;

                    Intent intent = new Intent(getApplicationContext(), adminPatientViewActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                    //Get the SmsManager instance and call the sendTextMessage method to send message
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(Phone, null, Message, pi, null);

                    Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                            Toast.LENGTH_LONG).show();





            }
             });

        }
    }
