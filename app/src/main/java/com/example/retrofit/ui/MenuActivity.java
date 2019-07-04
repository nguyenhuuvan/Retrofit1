package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.retrofit.R;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

         toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void creat(View view) {
        startActivity(new Intent(this,CreatActivity.class));
    }

    public void danhsach(View view) {
        startActivity(new Intent(this, ListUserActivity.class));
    }
    public void update(View view) {
        startActivity(new Intent(this,UpdateActivity.class));
    }
}
