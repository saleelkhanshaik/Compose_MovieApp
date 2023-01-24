package com.example.movieapp.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp


@Composable
fun MovieRow(
    movieDetails: Movie ,
    isClickBle : Boolean = true,
    itemClick: (Movie) -> Unit
) {
    var isExpended by rememberSaveable {
        mutableStateOf(false)
    }
    val arrowIcon = remember(isExpended) {
        if (isExpended) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable(enabled = isClickBle) {
                itemClick(movieDetails)
            },
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                color = MaterialTheme.colorScheme.onPrimary,
                shadowElevation = 4.dp,
                tonalElevation = 4.dp,
            ) {
                loadImage(movieDetails.images[0])
            }
            Column(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()) {
                Text(
                    text = movieDetails.title, style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Director: "+movieDetails.director, style = MaterialTheme.typography.titleSmall)
                Text(text = "Released: "+movieDetails.year, style = MaterialTheme.typography.titleMedium)
                AnimatedVisibility(visible = isExpended) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Red,
                                    fontSize = 14.sp
                                )
                            ) {
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ){
                                append(movieDetails.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))
                        Divider(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Director: "+movieDetails.director, style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Actors: "+movieDetails.actors, style = MaterialTheme.typography.titleSmall)
                        Text(text = "Rating: "+movieDetails.rating, style = MaterialTheme.typography.titleMedium)
                    }
                }
                Icon(imageVector = arrowIcon, contentDescription = "Down Arrow",
                    tint = Color.DarkGray, modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            isExpended = !isExpended
                        }
                        .align(alignment = CenterHorizontally))
            }

        }

    }
}

@Composable
fun loadImage(movieImage:String){
    Image(
        painter = rememberAsyncImagePainter(
            model =
            ImageRequest.Builder(LocalContext.current).data(movieImage)
                .crossfade(true).build()
        ),
        contentDescription = "imageValue", contentScale = ContentScale.Crop
    )
}