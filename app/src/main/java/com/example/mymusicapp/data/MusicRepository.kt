package com.example.mymusicapp.data

import com.example.mymusicapp.model.Music
import com.example.mymusicapp.model.MusicData

class MusicRepository {
    fun getMusic(): List<Music> {
        return MusicData.music
    }

    fun searchMusic(query: String): List<Music>{
        return MusicData.music.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

}