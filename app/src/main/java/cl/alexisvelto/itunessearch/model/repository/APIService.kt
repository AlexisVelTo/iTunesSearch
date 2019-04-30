package cl.alexisvelto.itunessearch.model.repository

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface APIService {
    @GET
    fun getJson(@Url url:String): Call<JsonObject>
}