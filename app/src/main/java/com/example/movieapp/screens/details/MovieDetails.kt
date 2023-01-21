package com.example.movieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(navController: NavController, name: String?) {
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
    ) {it ->
            Column(modifier = Modifier.padding(it).fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = name?.toString()?:"",
                style = MaterialTheme.typography.labelLarge)
            }

        }
}