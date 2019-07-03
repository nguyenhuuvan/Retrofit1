package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditUserAtivity extends AppCompatActivity {
    private TextInputEditText edEmail;
    private TextInputEditText edFirstName;
    private TextInputEditText edLastName;
    private ImageView imgAvatar;
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_ativity);


        getData();
        initView();
        initAction();

    }

    private void getData() {
       id = getIntent().getExtras().getInt("id",0);
       email = getIntent().getExtras().getString("email");
       firstName = getIntent().getExtras().getString("firstname");
       lastName = getIntent().getExtras().getString("lastname");
       avatar = getIntent().getExtras().getString("avatar");
    }

    private void initAction() {
        edEmail.setText(email);
        edFirstName.setText(firstName);
        edLastName.setText(lastName);
        Glide.with(this).load(avatar).into(imgAvatar);
    }

    private void initView() {
        edEmail =  findViewById(R.id.edEmail);
        edFirstName = findViewById(R.id.edFirstName);
        edLastName =  findViewById(R.id.edLastName);
        imgAvatar = findViewById(R.id.imageAvatar);
    }

    public void edit(View view) {
        Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
    }
}
