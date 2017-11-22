package com.example.e_medapp;

/**
 * Created on 10/16/2016.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DoctorRegisterRequest extends StringRequest {
    private static final String DOCTOR_REGISTER_REQUEST_URL = "http://minissan.comlu.com/doctorRegistration.php";
    private Map<String, String> params;

    public DoctorRegisterRequest(String DoctorID, String DoctorName, String DoctorPassword, String DoctorEmail, String DoctorPhone, String DoctorType, Response.Listener<String> listener) {
        super(Method.POST, DOCTOR_REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("DoctorID", DoctorID);
        params.put("DoctorName", DoctorName);
        params.put("DoctorPassword", DoctorPassword);
        params.put("DoctorEmail", DoctorEmail);
        params.put("DoctorPhone", DoctorPhone);
        params.put("DoctorType", DoctorType);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}