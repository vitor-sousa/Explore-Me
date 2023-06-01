package com.vitorsousa.exploreme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitorsousa.exploreme.ui.theme.ExploreMeTheme
import com.vitorsousa.home.ui.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           ExploreMeApp()
        }
    }
}

@Composable
fun ExploreMeApp() {
    ExploreMeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 5.dp
        ) {
            HomeScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreMeAppPreview() {
    ExploreMeApp()
}