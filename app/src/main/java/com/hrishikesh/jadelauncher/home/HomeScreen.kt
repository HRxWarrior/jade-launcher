package com.hrishikesh.jadelauncher.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    viewModel: AppsViewModel = viewModel()
) {

    val apps by viewModel.apps.collectAsState()

    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp)
    ) {

        items(apps) { app ->

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {

                        val intent =
                            context.packageManager
                                .getLaunchIntentForPackage(
                                    app.packageName
                                )

                        intent?.let {
                            context.startActivity(it)
                        }
                    },

                horizontalAlignment =
                    Alignment.CenterHorizontally,

                verticalArrangement =
                    Arrangement.Center
            ) {

                app.icon?.let {

                    Image(
                        bitmap = it.toBitmap()
                            .asImageBitmap(),

                        contentDescription =
                            app.appName
                    )
                }

                Text(
                    text = app.appName
                )
            }
        }
    }
}
