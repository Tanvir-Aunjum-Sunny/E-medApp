package com.example.e_medapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class adminPatient implements Serializable {

    @SerializedName("patientNo")
    public int patientNo;

    @SerializedName("PatientID")
    public String PatientID;

    @SerializedName("PatientName")
    public String PatientName;

    @SerializedName("PatientPassword")
    public String PatientPassword;

    @SerializedName("PatientEmail")
    public String PatientEmail;

    @SerializedName("PatientPhone")
    public int PatientPhone;

    @SerializedName("PatientType")
    public String PatientType;

    @SerializedName("Date")
    public String Date;

    @SerializedName("Time")
    public String Time;

    @SerializedName("DoctorID")
    public String DoctorID;

    @SerializedName("Feedback")
    public String Feedback;

}