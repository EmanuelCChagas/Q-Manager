package com.emanuelcchagas.q_manager.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaHomeUsuario(navController: NavController) {
       Scaffold(
        topBar = {
            TopAppBar(
                title = {
                            Column(){
                                Text(text = "Bem Vindo",
                                    fontSize = TextUnit(10f, type = TextUnitType.Em),
                                    fontWeight = FontWeight.Bold)
                                Text(text = "Escolha uma fila abaixo para entrar",
                                    fontSize = TextUnit(4f, type = TextUnitType.Em),
                                    fontWeight = FontWeight.ExtraLight,
                                    color = Color(0xFF7B7B7B)
                                )
                            }
                        },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                    ),
                modifier = Modifier.height(10.dp)
           )
        }
        ){
           it
        }
}


@Preview(showBackground = true)
@Composable
fun TelaHomeUsuarioPreview() {
    TelaHomeUsuario(navController = rememberNavController())
}