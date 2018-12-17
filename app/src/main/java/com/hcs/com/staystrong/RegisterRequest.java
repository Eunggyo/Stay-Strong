package com.hcs.com.staystrong;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// 웹서버와 통신이 가능하도록 자바파일 생성

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://eung89.cafe24.com/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID , String userPassword , String userEmail , Response.Listener<String> listener) {
        super(Method.POST, URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userEmail",userEmail);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
