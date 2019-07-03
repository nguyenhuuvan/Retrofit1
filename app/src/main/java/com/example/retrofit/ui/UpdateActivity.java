package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit.APIService;
import com.example.retrofit.R;
import com.example.retrofit.model.Job;
import com.example.retrofit.model.Update;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvJob;
    private TextView tvUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        tvName = (TextView) findViewById(R.id.tvName);
        tvJob = (TextView) findViewById(R.id.tvJob);
        tvUpdate = (TextView) findViewById(R.id.tvUpdate);
    }

    public void Update(View view) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<Update> call = service.updatePost("Nguyễn Hữu Văn","Intern Anroid");
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                tvJob.setText(response.body().getJob());
                tvName.setText(response.body().getName());
                tvUpdate.setText(response.body().getUpdatedAt());
            }


            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Log.e("onFailure", "onFailure: " + t.getMessage());
            }
        });

    }
}
