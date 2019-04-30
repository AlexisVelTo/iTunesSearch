package cl.alexisvelto.itunessearch.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ViewSwitcher
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.alexisvelto.itunessearch.R
import cl.alexisvelto.itunessearch.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var songAapter: SongAdapter
    private lateinit var mSearchView: SearchView
    private lateinit var recyclerView: RecyclerView
    lateinit var viewSwitcher: ViewSwitcher
    lateinit var loadingDialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewSwitcher = findViewById(R.id.view_switcher)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.songs.observe(this, Observer {
            if (it.isEmpty()){
                if (R.id.layout_no_data === viewSwitcher.nextView.id) {
                    viewSwitcher.showNext()
                }
            } else {
                if (R.id.recyclerview_songs === viewSwitcher.nextView.id) {
                    viewSwitcher.showNext()
                }
            }
            songAapter.replaceData(it)
        })

        loadingDialog = Dialog(this)
        loadingDialog.setContentView(R.layout.dialog_loading)
        loadingDialog.setCancelable(false)

        mainViewModel.isLoading.observe(this, Observer{
            it?.let {
                if (it){
                    loadingDialog.show()
                } else if(it == false){
                    loadingDialog.dismiss()
                }
            }
        })
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview_songs)
        songAapter = SongAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = songAapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        mSearchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        mSearchView.maxWidth = Integer.MAX_VALUE
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.loadSongs(query!!)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
}
