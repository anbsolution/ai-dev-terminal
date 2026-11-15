package com.devterminal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DevTerminalApp() }
    }
}

@Composable
fun DevTerminalApp() {
    var selected by remember { mutableIntStateOf(0) }
    val pages = listOf("Home", "Terminal", "GitHub", "AI", "Settings")
    val navy = Color(0xFF101827)
    val blue = Color(0xFF3978F6)

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = blue,
            background = navy,
            surface = Color(0xFF1C293B)
        )
    ) {
        Scaffold(
            containerColor = navy,
            bottomBar = {
                NavigationBar(
                    containerColor = Color(0xFF172235)
                ) {
                    pages.forEachIndexed { index, page ->
                        NavigationBarItem(
                            selected = selected == index,
                            onClick = { selected = index },
                            icon = {},
                            label = { Text(page) }
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(
                    "AI Dev Terminal",
                    color = Color.White,
                    fontSize = 26.sp
                )
                Text(
                    "Your development workspace",
                    color = Color.LightGray
                )
                Text(
                    pages[selected],
                    color = Color.White,
                    fontSize = 22.sp
                )

                val details = when (selected) {
                    0 -> listOf(
                        "Local Terminal" to "Termux connection",
                        "Cloud Terminal" to "Remote Linux workspace",
                        "GitHub Manager" to "Repository management",
                        "AI Assistant" to "AI integration ready"
                    )
                    1 -> listOf("Terminal" to "Waiting for connection")
                    2 -> listOf("GitHub" to "Connect your account")
                    3 -> listOf("AI Assistant" to "AI connection pending")
                    else -> listOf("Settings" to "Application preferences")
                }

                details.forEach { (title, subtitle) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF24344A)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(title, color = Color.White)
                            Text(subtitle, color = Color.LightGray)
                        }
                    }
                }
            }
        }
    }
}
