package com.example.recyclerviewapp.api_services
import com.example.recyclerviewapp.model.UserModel
import retrofit2.Response

import retrofit2.http.GET

interface GetUser {
    @GET("users")
    suspend fun getUsers() :  Response<List<UserModel>>
}