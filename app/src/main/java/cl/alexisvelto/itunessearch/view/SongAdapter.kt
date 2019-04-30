package cl.alexisvelto.itunessearch.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import cl.alexisvelto.itunessearch.R
import cl.alexisvelto.itunessearch.model.Song
import com.squareup.picasso.Picasso


class SongAdapter(context:Context) : RecyclerView.Adapter<SongAdapter.SongViewHolder>(){
    private var context = context
    private var songList = ArrayList<Song>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    fun replaceData(newData: ArrayList<Song>){
        songList = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return songList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.songName.text = song.songName
        holder.songArtist.text = song.artistName
        holder.album.text = song.collectionName
        Picasso.get().load(song.collectionImage).into(holder.albumImage)

    }

    class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var albumImage: ImageView = itemView.findViewById(R.id.imageViewSongAlbum)
        var songName: TextView = itemView.findViewById(R.id.textViewSongName)
        var songArtist: TextView = itemView.findViewById(R.id.textViewSongArtist)
        var album: TextView = itemView.findViewById(R.id.textViewAlbum)
    }

}