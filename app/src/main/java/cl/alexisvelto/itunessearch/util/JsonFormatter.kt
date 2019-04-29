package cl.alexisvelto.songfinder.util

import cl.alexisvelto.itunessearch.model.Song
import com.google.gson.JsonElement

class JsonFormatter {
    companion object {
        fun format(jsonElement: JsonElement): Song {
            var songName = "Song name"
            var collectionName = "Collection name"
            var collectionImage = "http://i.imgur.com/DvpvklR.png"
            var collectionId = ""
            var artistName = "Artist name"

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