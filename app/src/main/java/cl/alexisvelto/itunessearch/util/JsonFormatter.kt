package cl.alexisvelto.songfinder.util

import android.content.Context
import cl.alexisvelto.itunessearch.R
import cl.alexisvelto.itunessearch.model.Song
import com.google.gson.JsonElement

class JsonFormatter {
    companion object {
        fun format(jsonElement: JsonElement,context:Context): Song {
            var songName = context.getString(R.string.song_placeholder)
            var collectionName = context.getString(R.string.album_placeholder)
            var artistName = context.getString(R.string.artist_placeholder)

            var collectionImage = "http://i.imgur.com/DvpvklR.png"
            var collectionId = ""

            if (jsonElement.asJsonObject.has("trackName")) {
                songName = jsonElement.asJsonObject.get("trackName").asString
            }
            if (jsonElement.asJsonObject.has("collectionName")) {
                collectionName = jsonElement.asJsonObject.get("collectionName").asString
            }
            if (jsonElement.asJsonObject.has("artworkUrl100")) {
                collectionImage = jsonElement.asJsonObject.get("artworkUrl100").asString
            }
            if (jsonElement.asJsonObject.has("collectionId")) {
                collectionId = jsonElement.asJsonObject.get("collectionId").asString
            }
            if (jsonElement.asJsonObject.has("artistName")) {
                artistName = jsonElement.asJsonObject.get("artistName").asString
            }
            var song = Song(songName, collectionName,collectionImage,collectionId,artistName)
            return song
        }
    }
}