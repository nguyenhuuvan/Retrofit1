package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.retrofit.APIService;
import com.example.retrofit.R;
import com.example.retrofit.model.Error;
import com.example.retrofit.model.Token;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edEmail;
    private TextInputEditText edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initAction();
    }

    private void initAction() {
        edEmail.setText("eve.holt@reqres.in");
        edPass.setText("cityslicka");

    }

    private void initView() {
        edEmail = (TextInputEditText) findViewById(R.id.edEmail);
        edPass = (TextInputEditText) findViewById(R.id.edPass);
    }

    public void login(View view) {
        String pass = edPass.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(this, "Nhập email", Toast.LENGTH_SHORT).show();
        } else {
            if(pass.isEmpty()){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://reqres.in/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService service = retrofit.create(APIService.class);
                Call<Error> call = service.loginUser2(email);
                call.enqueue(new Callback<Error>() {
                    @Override
                    public void onResponse(Call<Error> call, Response<Error> response) {
                        if(response.code()==400){
                            Toast.makeText(LoginActivity.this, "Missing password", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<Error> call, Throwable t) {
                        Log.e("onFailure", "onFailure: " + t.getMessage());
                    }
                });
            }else{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://reqres.in/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService service = retrofit.create(APIService.class);
                Call<Token> call = service.loginUser(email, pass);
                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if(response.body()==null){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

                        }
                    else if(response.body().getToken().equals("QpwL5tke4Pnpja7X4")){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }

                    }


                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Log.e("onFailure", "onFailure: " + t.getMessage());
                    }
                });
            }
        }
    }}
