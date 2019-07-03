package com.example.retrofit;

import com.example.retrofit.model.Error;
import com.example.retrofit.model.Example;
import com.example.retrofit.model.Job;
import com.example.retrofit.model.Token;
import com.example.retrofit.model.Update;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @GET("/api/users?page=2")
    @FormUrlEncoded
    Call<Example> getListUser();

    @POST("/api/login")
    Call<Token> loginUser(@Field("email") String email,
                          @Field("password") String password
                             );

    @POST("/api/login")
    @FormUrlEncoded
    Call<Error> loginUser2(@Field("email") String email
    );

    @POST("/api/users")
    @FormUrlEncoded
    Call<Job> creat(@Field("name") String name,
                        @Field("job") String job
    );
    @PUT("/api/users/2")
    @FormUrlEncoded
    Call<Update> updatePost(@Field("name") String name,
                            @Field("job") String job);
}
