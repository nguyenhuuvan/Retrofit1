package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit.APIService;
import com.example.retrofit.R;
import com.example.retrofit.model.CreatUser;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvJob;
    private TextView tvCreatAt, tvID;
    private Toolbar toolbar;
    private TextInputEditText edName, edJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);

        tvName = (TextView) findViewById(R.id.tvName);
        tvJob = (TextView) findViewById(R.id.tvJob);
        tvCreatAt = (TextView) findViewById(R.id.tvCreatAt);
        tvID = (TextView) findViewById(R.id.tvID);
        edName = findViewById(R.id.edName);
        edJob = findViewById(R.id.edJob);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void Creat(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        String name = edName.getText().toString().trim();
        String job = edJob.getText().toString().trim();
        if (name.isEmpty() || job.isEmpty()) {
            if (name.isEmpty())
                edName.setError("Nhập tên");
            if(job.isEmpty())
                edJob.setError("Nhập công việc");
        } else {
            Call<CreatUser> call = service.creat(name, job);
            call.enqueue(new Callback<CreatUser>() {
                @Override
                public void onResponse(Call<CreatUser> call, Response<CreatUser> response) {
                    tvJob.setText("Job: " + response.body().getJob());
                    tvName.setText("Name: " + response.body().getName());
                    tvCreatAt.setText("CreatAt: " + response.body().getCreatedAt());
                    tvID.setText("ID: " + response.body().getId());
                }


                @Override
                public void onFailure(Call<CreatUser> call, Throwable t) {
                    Log.e("onFailure", "onFailure: " + t.getMessage());
                }
            });
        }
    }
}
