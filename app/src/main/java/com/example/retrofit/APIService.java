package com.example.retrofit;

import com.example.retrofit.model.CreatUser;
import com.example.retrofit.model.FullApiUser;
import com.example.retrofit.model.Token;
import com.example.retrofit.model.Update;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIService {
    @GET("/api/users?page=2")
    Call<FullApiUser> getListUser();

    @POST("/api/login")
    @FormUrlEncoded
    Call<Token> loginUser(@Field("email") String email,
                          @Field("password") String password
                             );


    @POST("/api/users")
    @FormUrlEncoded
    Call<CreatUser> creat(@Field("name") String name,
                          @Field("job") String job
    );
    @PUT("/api/users/2")
    @FormUrlEncoded
    Call<Update> update(@Field("name") String name,
                            @Field("job") String job);
}
