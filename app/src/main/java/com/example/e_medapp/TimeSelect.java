package com.example.e_medapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class TimeSelect extends AppCompatActivity  {

    final String LOG = "TimeSelect";
    TextView tvName;
    EditText etDate1, etDoctorID1;
    Calendar  myCalendar;
    private adminPatient AdminPatient;
    Button buttonTime ;
    EditText editTextFromTime;

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate1.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);

        myCalendar= Calendar.getInstance();
        AdminPatient = (adminPatient) getIntent().getSerializableExtra("item");

        tvName = (TextView) findViewById(R.id.textView20);
        etDate1 = (EditText) findViewById(R.id.editText70);
        etDoctorID1 = (EditText) findViewById(R.id.editText60);
        editTextFromTime = (EditText) findViewById(R.id.editText3);


            tvName.setText(AdminPatient.PatientName);
            etDate1.setText(AdminPatient.Date);
            etDoctorID1.setText(AdminPatient.DoctorID);
             Log.d(LOG, "TimeSelect");



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

        etDate1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(TimeSelect.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        editTextFromTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TimeSelect.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTextFromTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });

        buttonTime = (Button)findViewById(R.id.buttonTime);




    buttonTime.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (editTextFromTime != null) {


                HashMap postData = new HashMap();
                postData.put("patientNo", "" + AdminPatient.patientNo);
                postData.put("PatientID", AdminPatient.PatientID);
                postData.put("PatientName", AdminPatient.PatientName);
                postData.put("PatientPassword", AdminPatient.PatientPassword);
                postData.put("PatientEmail", AdminPatient.PatientEmail);
                postData.put("PatientPhone", "" + AdminPatient.PatientPhone);
                postData.put("PatientType", AdminPatient.PatientType);
                postData.put("Date", etDate1.getText().toString());
                postData.put("Time", editTextFromTime.getText().toString());
                postData.put("DoctorID", etDoctorID1.getText().toString());
                postData.put("mobile", "android");


                PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(TimeSelect.this,
                        postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        Log.d(LOG, s);
                        if (s.contains("success")) {
                            Toast.makeText(TimeSelect.this, "Update Successfully", Toast.LENGTH_LONG).show();
                            //Intent in = new Intent(TimeSelect.this, adminPatientViewActivity.class);
                            //startActivity(in);
                        }
                    }
                });
                taskUpdate.execute("http://minissan.comlu.com/patientUpdate.php");
            }
            else
            {
                Toast.makeText(TimeSelect.this, "Please Select Time", Toast.LENGTH_LONG).show();
            }
        }
    });

    }
}