package com.example.mymusicapp.ui.detail


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mymusicapp.ui.theme.MyMusicAppTheme


@Composable
fun DetailMusic(
    musicId: String,
    musicName: String,
    artist : String,
    lyrics : String,
    ytUrl : String,
    spotifyUrl : String,
    totalView : String,
    releaseYear : String,
    pothoUtl: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    var text by remember { mutableStateOf(lyrics) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Box (
                modifier = modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ){
                Row {
                    Box(
                        modifier = modifier
                            .fillMaxHeight()
                            .width(150.dp)
                            .background(MaterialTheme.colorScheme.secondary)
                    ){
                        var pothoUrl : String
                        pothoUtl.let {
                            pothoUrl = it
                        }
                        AsyncImage(
                            model = pothoUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Box(modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(start = 8.dp),
                        contentAlignment = Alignment.CenterStart
                    ){
                        Column (

                        ){
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                        append("Judul :")
                                    }
                                    append("\n$musicName")
                                }
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                        append("Artist :")
                                    }
                                    append("\n$artist")
                                }
                            )
                            Spacer(modifier = modifier.height(24.dp))
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                        append("Tahun rilis :")
                                    }
                                    append("\n$releaseYear")
                                }
                            )
                            Text(
                                modifier = modifier.padding(top = 8.dp),
                                fontSize = 10.sp,
                                color = Color.Gray,
                                text = "$totalView view on YouTube"
                            )
                        }
                    }
                }
            }
            Box (
                modifier = modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ){
                Row {
                    Box (
                        modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                            .border(2.dp, Color.Black)
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable {
                                uriHandler.openUri(ytUrl)
                            },
                        contentAlignment = Alignment.Center
                    ){
                        AsyncImage(
                            model = "https://firebasestorage.googleapis.com/v0/b/paaproject-4a080.appspot.com/o/youtube.png?alt=media&token=b170c598-f81b-41e6-8dee-5c309921cad7",
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )
                    }

                    Box (
                        modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .border(2.dp, Color.Black)
                            .background(MaterialTheme.colorScheme.secondary)
                            .clickable {
                                uriHandler.openUri(spotifyUrl)
                            },
                        contentAlignment = Alignment.Center
                    ){
                        AsyncImage(
                            model = "https://firebasestorage.googleapis.com/v0/b/paaproject-4a080.appspot.com/o/spotify.png?alt=media&token=40425d2c-d140-43a1-a2ff-ad4fba897c45",
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )
                    }
                }
            }

            Text(
                text = "Lyrics",
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(top = 8.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = text,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MyMusicAppPreview() {
    val navigateBack: (() -> Unit)= null as (() -> Unit)
    MyMusicAppTheme {
        DetailMusic(
            musicId = "",
            musicName = "",
            pothoUtl = "",
            artist = "",
            lyrics = "",
            ytUrl = "",
            spotifyUrl = "",
            totalView = "",
            releaseYear= "",
            navigateBack = navigateBack)
    }
}