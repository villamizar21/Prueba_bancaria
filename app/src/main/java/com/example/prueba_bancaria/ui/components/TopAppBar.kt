package com.example.prueba_bancaria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_bancaria.R
import kotlinx.coroutines.launch

/**
 * @OptIn(ExperimentalMaterial3Api::class)
 * @Composable
 * Function that creates a customized top app bar using Scaffold and CenterAlignedTopAppBar from Material3.
 *
 * @param isMainView Indicates if the current view is the main view.
 * @param title Title of the top app bar.
 * @param body Main content of the top app bar.
 * @param clickListener Callback function to handle clicks on the navigation icon.
 * @param drawerState Optional DrawerState used to control the drawer's state (if a drawer is present).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    isMainView: Boolean,
    title: String,
    body: @Composable () -> Unit = {},
    clickListener: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.shadow(elevation = 2.dp, shape = RectangleShape),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFE20613),
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        clickListener()
                    }) {
                        if(!isMainView) {
                            Image(
                                modifier = Modifier
                                    .padding(0.66667.dp),
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "image description",
                                contentScale = ContentScale.None
                            )
                        }else{
                            Image(
                                modifier = Modifier
                                    .padding(0.66667.dp),
                                painter = painterResource(id = R.drawable.account_circle),
                                contentDescription = "image description",
                                contentScale = ContentScale.None
                            )
                        }
                    }
                },
                title = {
                    Text(text = title, fontSize = 20.sp, color = Color.White, textAlign = TextAlign.Right, fontWeight = FontWeight.Bold)
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                body()
            }
        }
    )
}
