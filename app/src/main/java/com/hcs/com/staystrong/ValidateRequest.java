package com.hcs.com.staystrong;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {   //회원아이디 체크를 담당하는 클래스

    final static private String URL = "http://eung89.cafe24.com/UserValidate.php";
    private Map<String, String> parameters;

    public ValidateRequest(String userID  , Response.Listener<String> listener) {
        super(Method.POST, URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}

