package cl.alexisvelto.itunessearch.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.alexisvelto.itunessearch.R
import cl.alexisvelto.itunessearch.model.Song
import cl.alexisvelto.itunessearch.model.repository.SongRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel (application: Application): AndroidViewModel(application) {
    val context = application.applicationContext
    val songRepository = SongRepository.getInstance(context)
    val songs = MutableLiveData<ArrayList<Song>>()
    var isLoading = MutableLiveData<Boolean>()

    fun loagSongs(query:String){
        isLoading.value = true
        songRepository.getSongs(query,songs).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<ArrayList<Song>>(){
                override fun onComplete() {
                    isLoading.value = false
                }
                override fun onNext(songResult: ArrayList<Song>) {
                    songs.value = songResult
                }
                override fun onError(e: Throwable) {
                    Log.e("SONG_SEARCHER", e.message)
                    Toast.makeText(context, context.getString(R.string.error_text), Toast.LENGTH_LONG).show()
                }
            })
    }
}