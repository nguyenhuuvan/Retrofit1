package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit.APIService;
import com.example.retrofit.R;
import com.example.retrofit.model.Example;
import com.example.retrofit.model.Job;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatActivity extends AppCompatActivity {
    private TextView edName;
    private TextView edJob;
    private TextView edUpdateAt,edID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);

        edName = (TextView) findViewById(R.id.edName);
        edJob = (TextView) findViewById(R.id.edJob);
        edUpdateAt = (TextView) findViewById(R.id.edUpdateAt);
        edID = (TextView) findViewById(R.id.edID);
    }

    public void Creat(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<Job> call = service.creat("Nguyễn Hữu Văn","Intern Android");
        call.enqueue(new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                edJob.setText(response.body().getJob());
                edName.setText(response.body().getName());
                edUpdateAt.setText(response.body().getCreatedAt());
                edID.setText(response.body().getId());
            }


            @Override
            public void onFailure(Call<Job> call, Throwable t) {
                Log.e("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }
}
