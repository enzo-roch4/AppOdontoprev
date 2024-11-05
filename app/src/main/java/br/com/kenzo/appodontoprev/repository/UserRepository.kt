package br.com.kenzo.appodontoprev.repository

import br.com.kenzo.appodontoprev.api.RetrofitClient
import br.com.kenzo.appodontoprev.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    private val apiService = RetrofitClient.instance

    fun getUsers(callback: (List<User>?) -> Unit) {
        apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun createUser(user: User, callback: (User?) -> Unit) {
        apiService.createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback(null)
            }
        })
    }
}
