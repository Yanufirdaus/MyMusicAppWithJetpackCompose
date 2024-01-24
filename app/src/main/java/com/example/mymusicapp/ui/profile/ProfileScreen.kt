package com.example.mymusicapp.ui.profile


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mymusicapp.R
import com.example.mymusicapp.ui.theme.MyMusicAppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Box(modifier = modifier
                    .height(320.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ){
                Column {
                    Box (
                            modifier = Modifier
                                    .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                    ){
                        AsyncImage(
                                model = "https://firebasestorage.googleapis.com/v0/b/paaproject-4a080.appspot.com/o/profile.jpg?alt=media&token=77ec17e2-0aa7-4ffe-a16f-1241811b1702",
                                contentDescription = "Foto Profil",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                        .padding(8.dp)
                                        .size(150.dp)
                                        .clip(shape = CircleShape)
                                        .border(4.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                        )
                    }
                    Spacer(modifier = modifier.padding(16.dp))
                    Box (
                            modifier = Modifier
                                    .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                    ){
                        Text(
                                text = stringResource(id = R.string.user_name),
                                color = Color.Black,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
            Box(modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Email : \nyanufirdaus@gmail.com",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MyMusicAppPreview() {
    MyMusicAppTheme {
        ProfileScreen()
    }
}