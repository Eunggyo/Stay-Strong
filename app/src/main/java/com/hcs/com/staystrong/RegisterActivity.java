package com.hcs.com.staystrong;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

// 회원가입 자바파일

public class RegisterActivity extends Activity {
  private String userID;
  private String userPassword;
  private String userEmail;
  private AlertDialog dialog;
  private boolean validate = false; // 사용할 수 있는 회원 ID인지 체크

  @Override
protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_register);

      final EditText idText = (EditText) findViewById(R.id.idText);
      final EditText passwordText = (EditText) findViewById(R.id.passwordText);
      final EditText emailText = (EditText) findViewById(R.id.emailText);

      final Button validateButton = (Button) findViewById(R.id.validateButton);
      validateButton.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View view) {
            String userID = idText.getText().toString();
            if(validate) //validate 체크가 되어있으면
            {
                return;
            }
            if(userID.equals("")) //ID가 빈칸이면 예외발생
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = builder.setMessage("아이디는 빈 칸 일 수 없습니다.")
                        .setPositiveButton("확인",null)
                        .create();
                dialog.show();
                return;
            }
            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) { //중복체크 진행

                    // jSONOBject를 이용해 해당 웹사이트에 접속한 뒤 response를 받을 수 있게 한다.
                    try
                    {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            dialog = builder.setMessage("사용할 수 있는 아이디 입니다.")
                                    .setPositiveButton("확인",null)
                                    .create();
                            dialog.show();
                            idText.setEnabled(false);
                            validate = true;
                            idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            dialog = builder.setMessage("사용할 수 없는 아이디 입니다.")
                                    .setPositiveButton("확인",null)
                                    .create();
                            dialog.show();

                        }
                    }

                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            ValidateRequest validateRequest = new ValidateRequest(userID , responseListener);
              RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
              queue.add(validateRequest);
          }
      });

      final Button registerButton = (Button) findViewById(R.id.registerButton);
      registerButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String userID = idText.getText().toString();
              String userPassword = passwordText.getText().toString();
              String userEmail = emailText.getText().toString();

              if(!validate)
              {
                  AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                  dialog = builder.setMessage("먼저 중복 체크를 해주세요.")
                          .setNegativeButton("확인",null)
                          .create();
                  dialog.show();
                  return;
              }

              if(userID.equals("") || userPassword.equals("") || userEmail.equals("")){
                  AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                  dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                          .setNegativeButton("확인",null)
                          .create();
                  dialog.show();
                  return;
              }

              Response.Listener<String> responseListener = new Response.Listener<String>() {

                  @Override
                  public void onResponse(String response) {
                      try
                      {
                          JSONObject jsonResponse = new JSONObject(response);
                          boolean success = jsonResponse.getBoolean("success");
                          if(success) {

                              AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                              dialog = builder.setMessage("회원등록에 성공했습니다. .")
                                      .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                              Intent logIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                                              RegisterActivity.this.startActivity(logIntent);
                                          }
                                      })
                                      .create();
                              dialog.show();

                          }
                          else {
                              AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                              dialog = builder.setMessage("회원 등록에 실패했습니다.")
                                      .setPositiveButton("확인",null)
                                      .create();
                              dialog.show();

                          }
                      }

                      catch (Exception e)
                      {
                        e.printStackTrace();
                      }
                  }
              };
              RegisterRequest registerRequest = new RegisterRequest(userID , userPassword , userEmail, responseListener);
              RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
              queue.add(registerRequest);

          }
      });
  }

  //회원등록 이후 실행
  @Override
    protected void onStop() {
      super.onStop();
      if(dialog != null)
      {
          dialog.dismiss();
          dialog = null;
      }
  }
}