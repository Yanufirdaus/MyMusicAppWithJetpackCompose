package com.example.mymusicapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mymusicapp.ui.theme.MyMusicAppTheme

@Composable
fun MusicListItem(
    name: String,
    photoUrl: String,
    artist: String,
    ytLink: String,
    releaseYear: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = releaseYear,
                fontWeight = FontWeight.Light,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = artist,
                fontWeight = FontWeight.Medium,
            )
        }
        IconButton(
            onClick = {
                val ytLink = ytLink
                uriHandler.openUri(ytLink)
            },
            modifier = Modifier
                .size(60.dp)
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
        ){
            Icon(
                imageVector = Icons.Default.PlayArrow,
                modifier = Modifier.size(100.dp),
                contentDescription = "info",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MusicListItemPreview() {
    MyMusicAppTheme {
        MusicListItem(
            name = "Pompeii",
            photoUrl = "",
            ytLink = "",
            artist = "Bastille",
            releaseYear = "2010"
        )
    }
}
