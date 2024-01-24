package com.example.mymusicapp.ui

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object DetailMusic : Screen("home/{musicId}/{musicName}/{pothoUtl}/{artist}/{lyrics}/{ytUrl}/{spotifyUrl}/{totalView}/{releaseYear}") {
        fun createRoute(
            rewardId: String,
            musicName: String,
            pothoUtl: String,
            artist : String,
            lyrics : String,
            ytUrl : String,
            spotifyUrl : String,
            totalView : String,
            releaseYear: String
        ): String {
            val encodedPothoUtl = URLEncoder.encode(pothoUtl, StandardCharsets.UTF_8.toString())
            val encodedYtUrl = URLEncoder.encode(ytUrl, StandardCharsets.UTF_8.toString())
            val encodedSpotifyUrl = URLEncoder.encode(spotifyUrl, StandardCharsets.UTF_8.toString())

            return "home/$rewardId/$musicName/$encodedPothoUtl/$artist/$lyrics/$encodedYtUrl/$encodedSpotifyUrl/$totalView/$releaseYear"
        }
    }
}
