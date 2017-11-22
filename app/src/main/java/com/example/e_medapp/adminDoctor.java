package com.example.e_medapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class adminDoctor implements Serializable {

    @SerializedName("DoctorNo")
    public int DoctorNo;

    @SerializedName("DoctorID")
    public String DoctorID;

    @SerializedName("DoctorName")
    public String DoctorName;

    @SerializedName("DoctorPassword")
    public String DoctorPassword;

    @SerializedName("DoctorEmail")
    public String DoctorEmail;

    @SerializedName("DoctorPhone")
    public int DoctorPhone;

    @SerializedName("DoctorType")
    public String DoctorType;

}