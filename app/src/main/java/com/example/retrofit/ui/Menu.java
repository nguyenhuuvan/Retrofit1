package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.retrofit.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void login(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void creat(View view) {
        startActivity(new Intent(this,CreatActivity.class));
    }

    public void danhsach(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
    public void update(View view) {
        startActivity(new Intent(this,UpdateActivity.class));
    }
}
