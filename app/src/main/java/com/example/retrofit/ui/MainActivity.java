package com.example.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofit.APIService;
import com.example.retrofit.R;
import com.example.retrofit.adapter.UserAdapter;
import com.example.retrofit.listener.OnClick;
import com.example.retrofit.listener.OnDelete;
import com.example.retrofit.listener.OnEdit;
import com.example.retrofit.model.Example;
import com.example.retrofit.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnDelete, OnClick, OnEdit {
    private RecyclerView rvUser;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAction();
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList,this,this,this,this);
        rvUser.setAdapter(userAdapter);
        getData();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvUser.setLayoutManager(manager);

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<Example> call = service.getListUser();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                for (int i = 0; i<response.body().getData().size() ; i++) {
                    userList.add(response.body().getData().get(i));
                    userAdapter.changeDataset(userList);
                }

            }


            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("onFailure", "onFailure: " + t.getMessage());
            }
        });

    }
    private void initView() {
        rvUser = findViewById(R.id.rvUser);
    }

    private void initAction() {
        // Todo

    }

    @Override
    public void OnClick(int pos) {
        Intent intent = new Intent(this,EditUserAtivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id",userList.get(pos).getId());
        bundle.putString("firstname",userList.get(pos).getFirstName());
        bundle.putString("lastname",userList.get(pos).getLastName());
        bundle.putString("email",userList.get(pos).getEmail());
        bundle.putString("avatar",userList.get(pos).getAvatar());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void OnDelete(int pos) {
        Toast.makeText(this, "OnDelete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnEdit(int pos) {
        Toast.makeText(this, "OnEdit", Toast.LENGTH_SHORT).show();
    }
}
