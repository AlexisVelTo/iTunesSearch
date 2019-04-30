package cl.alexisvelto.itunessearch.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import cl.alexisvelto.itunessearch.model.Song
import cl.alexisvelto.songfinder.util.JsonFormatter
import cl.alexisvelto.songfinder.util.SingletonHolder
import com.google.gson.JsonObject
import kotlin.collections.ArrayList
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongRepository private constructor(context: Context){
    var context = context
    companion object : SingletonHolder<SongRepository, Context>(::SongRepository)

    fun getSongs(query:String): Observable<ArrayList<Song>> {
       return Observable.fromCallable {
            val retrofit1 = Retrofit.Builder()
                .baseUrl("http://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service1 = retrofit1.create<APIService>(APIService::class.java)
            val jsonCall = service1.getJson(query)
            var response = jsonCall.execute()
            var jsonarray = response.body()?.getAsJsonArray("results")
            var songs = ArrayList<Song>()
            jsonarray?.forEach {
                songs.add(JsonFormatter.format(it,context))
            }
            return@fromCallable songs
        }
    }
}