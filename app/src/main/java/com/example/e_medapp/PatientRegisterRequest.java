package com.example.e_medapp;

/**
 * Created by LENOVO on 10/16/2016.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PatientRegisterRequest extends StringRequest {
    private static final String PATIENT_REGISTER_REQUEST_URL = "http://minissan.comlu.com/patientRegistration.php";
    private Map<String, String> params;

    public PatientRegisterRequest(String PatientID, String PatientName, String PatientPassword, String PatientEmail, int PatientPhone, String PatientType, Response.Listener<String> listener) {
        super(Method.POST, PATIENT_REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("PatientID", PatientID);
        params.put("PatientName", PatientName);
        params.put("PatientPassword", PatientPassword);
        params.put("PatientEmail", PatientEmail);
        params.put("PatientPhone", PatientPhone + "");
        params.put("PatientType", PatientType);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}