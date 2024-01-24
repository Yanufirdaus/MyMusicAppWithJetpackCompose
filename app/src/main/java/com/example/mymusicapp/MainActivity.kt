package com.example.mymusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mymusicapp.data.MusicRepository
import com.example.mymusicapp.data.MusicViewModel
import com.example.mymusicapp.data.ViewModelFactory
import com.example.mymusicapp.ui.MusicListItem
import com.example.mymusicapp.ui.theme.MyMusicAppTheme
import androidx.compose.material3.SearchBar
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymusicapp.ui.Screen
import com.example.mymusicapp.ui.detail.DetailMusic
import com.example.mymusicapp.ui.profile.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMusicAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "homeScreen") {
                        composable("homeScreen") {
                            MyMusicApp(
                                navController = navController,
                                navigateToDetail = { rewardId, musicName, pothoUtl, artist, lyrics, ytUrl, spotifyUrl, totalView, releaseYear ->
                                    navController.navigate(Screen.DetailMusic.createRoute(
                                        rewardId,
                                        musicName,
                                        pothoUtl,
                                        artist,
                                        lyrics,
                                        ytUrl,
                                        spotifyUrl,
                                        totalView,
                                        releaseYear
                                    ))
                                }
                            )
                        }
                        composable("profileScreen") {
                            ProfileScreen()
                        }
                        composable(
                            route = Screen.DetailMusic.route,
                            arguments = listOf(
                                navArgument("musicId") { type = NavType.StringType },
                                navArgument("musicName") { type = NavType.StringType },
                                navArgument("pothoUtl") { type = NavType.StringType },
                                navArgument("artist") { type = NavType.StringType },
                                navArgument("lyrics") { type = NavType.StringType },
                                navArgument("ytUrl") { type = NavType.StringType },
                                navArgument("spotifyUrl") { type = NavType.StringType },
                                navArgument("totalView") { type = NavType.StringType },
                                navArgument("releaseYear") { type = NavType.StringType },
                            ),
                        ) {
                            val id = it.arguments?.getString("musicId") ?: ""
                            val name = it.arguments?.getString("musicName") ?: ""
                            val pothoUtl = it.arguments?.getString("pothoUtl") ?: ""
                            val artist = it.arguments?.getString("artist") ?: ""
                            val lyrics = it.arguments?.getString("lyrics") ?: ""
                            val ytUrl = it.arguments?.getString("ytUrl") ?: ""
                            val spotifyUrl = it.arguments?.getString("spotifyUrl") ?: ""
                            val totalView = it.arguments?.getString("totalView") ?: ""
                            val releaseYear = it.arguments?.getString("releaseYear") ?: ""
                            DetailMusic(
                                musicId = id,
                                musicName = name,
                                pothoUtl = pothoUtl,
                                artist = artist,
                                lyrics = lyrics,
                                ytUrl = ytUrl,
                                spotifyUrl = spotifyUrl,
                                totalView = totalView,
                                releaseYear = releaseYear,
                                navigateBack = {
                                    navController.navigateUp()
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyMusicApp(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MusicViewModel = viewModel(factory = ViewModelFactory(MusicRepository())),
    navigateToDetail: (String, String, String, String, String, String, String, String, String) -> Unit,
) {
    Column {
        Banner(navController = navController)
        val groupedMusic by viewModel.groupedMusic.collectAsState()

        Box(modifier = modifier
            .padding(0.dp, 8.dp, 0.dp, 8.dp)) {
            LazyColumn {
                groupedMusic.forEach { (initial, music) ->
                    stickyHeader {
                        CharacterHeader(initial)
                    }
                    items(music, key = { it.id }) { music ->
                        MusicListItem(
                            name = music.name,
                            photoUrl = music.pothoUtl,
                            artist = music.artist,
                            ytLink = music.ytUrl,
                            releaseYear = music.releaseYear,
                            modifier = Modifier
                                .clickable{
                                    if (music.id.isNotEmpty()) {
                                        navigateToDetail(
                                            music.id,
                                            music.name,
                                            music.pothoUtl,
                                            music.artist,
                                            music.lyrics,
                                            music.ytUrl,
                                            music.spotifyUrl,
                                            music.totalView,
                                            music.releaseYear)
                                    }
                                }
                                .fillMaxWidth()
                                .padding(vertical = 6.dp, horizontal = 4.dp)
                                .background(color = MaterialTheme.colorScheme.secondary)
                        )
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MyMusicAppPreview() {
    val navController = rememberNavController()
    val navigateToDetail: ((String, String, String, String, String, String, String, String, String) -> Unit)
            = "" as ((String, String, String, String, String, String, String, String, String) -> Unit)
    MyMusicAppTheme {
        MyMusicApp(navController = navController, navigateToDetail = navigateToDetail)
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MusicViewModel = viewModel(factory = ViewModelFactory(MusicRepository()))
) {
    val query by viewModel.query
    Box(modifier = modifier
        .background(MaterialTheme.colorScheme.secondary)
        .fillMaxWidth()
        .height(200.dp),
        contentAlignment = Alignment.Center
    ){
        Row {
            Box(modifier = modifier
                .weight(1f)
                .height(200.dp)){

            }
            Box(modifier = modifier
                .width(250.dp)
                .height(200.dp),
                contentAlignment = Alignment.Center
            ){
                Column {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.Black,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = stringResource(id = R.string.app_subtitle),
                        color = Color.Black,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )

                    searchBar(
                        query = query,
                        onQueryChange = viewModel::search,
                        modifier = Modifier
                            .height(55.dp)
                    )
                }

            }
            Box(modifier = modifier
                .weight(1f)
                .height(200.dp)
                .padding(8.dp, 8.dp, 8.dp, 8.dp),
                contentAlignment = Alignment.TopEnd){
                    IconButton(
                        onClick = {
                            navController.navigate("profileScreen")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            modifier = Modifier.size(45.dp),
                            contentDescription = "info",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
            }
        }
    }
}

@Composable
fun CharacterHeader(
    char: Char,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Text(
            text = char.toString(),
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_music))
        },
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
    ) {
    }
}

