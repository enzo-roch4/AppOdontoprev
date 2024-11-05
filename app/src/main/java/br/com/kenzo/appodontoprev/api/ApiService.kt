package br.com.kenzo.appodontoprev.api

import br.com.kenzo.appodontoprev.model.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("users")
    fun createUser(@Body user: User): Call<User>
}
