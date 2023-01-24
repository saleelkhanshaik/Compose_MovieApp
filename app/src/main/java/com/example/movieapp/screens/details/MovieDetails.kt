package com.example.movieapp.screens.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.getMovies
import com.example.movieapp.widget.MovieRow
import com.example.movieapp.widget.loadImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(navController: NavController, name: String?) {
    val filteredMovie = getMovies().first { it.id == name }
    Log.i("TAG", "MovieDetailsScreen: $filteredMovie")
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Details Screen")
            },
            navigationIcon = {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "TopBarIcon",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                titleContentColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = MaterialTheme.colorScheme.error
            )
        )
    }
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieRow(filteredMovie, false) {
                Log.i("TAG", "MovieDetailsScreen: ")
            }
            Text(
                text = filteredMovie.title,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)){
              items(items = filteredMovie.images){
                  Log.d("TAG", "MovieDetailsScreen:Image -->  $it")
                  Surface(
                      modifier = Modifier
                          .padding(4.dp).size(145.dp),
                      shape = RectangleShape,
                      color = MaterialTheme.colorScheme.onPrimary,
                      shadowElevation = 4.dp,
                      tonalElevation = 4.dp,
                  ) {
                      loadImage(it)
                  }
              }
            }
        }
    }
}