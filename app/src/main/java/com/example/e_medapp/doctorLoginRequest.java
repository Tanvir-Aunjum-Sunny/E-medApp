package com.example.e_medapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class doctorLoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://minissan.comlu.com/doctorLogin.php";
    private Map<String, String> params;

    public doctorLoginRequest(String DoctorID, String DoctorPassword, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("DoctorID", DoctorID);
        params.put("DoctorPassword", DoctorPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}