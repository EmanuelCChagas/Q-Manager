@file:OptIn(ExperimentalMaterial3Api::class)

package com.emanuelcchagas.q_manager.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@Composable
fun TelaCreateAccount(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6F71E5), Color.White),
                    endY = 500.0f
                )
            ),
        verticalArrangement =  Arrangement.Center
    ){
        Text(
            color = Color.Black,
            modifier = Modifier.padding(start = 12.dp, end = 3.dp, top = 10.dp,bottom = 18.dp),
            text = "Criar Conta",
            fontSize = TextUnit(10f, type = TextUnitType.Em),
            fontWeight = FontWeight.Bold
        )
        Column(modifier = Modifier.padding(start = 12.dp, end = 3.dp, top = 10.dp,bottom = 8.dp)){
            Text(
                color = Color.Black,
                text = "Endereço de E-mail",
                fontSize = TextUnit(5f, type = TextUnitType.Em),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = "",
                onValueChange = { /**/ },
                label = { Text("Digite seu endereço de e-mail") }
            )
        }
        Column(modifier = Modifier.padding(start = 12.dp, end = 3.dp, top = 10.dp,bottom = 8.dp)){
            Text(
                color = Color.Black,
                text = "Senha",
                fontSize = TextUnit(5f, type = TextUnitType.Em),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = "",
                onValueChange = { /**/ },
                label = { Text("Digite sua senha") }
            )
        }
        Column(modifier = Modifier.padding(start = 12.dp, end = 3.dp, top = 10.dp,bottom = 8.dp)){
            Text(
                color = Color.Black,
                text = "Confirmar Senha",
                fontSize = TextUnit(5f, type = TextUnitType.Em),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = "",
                onValueChange = { /**/ },
                label = { Text("Digite sua senha") }
            )
        }
        Row (){
        }
        Row (
            Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 8.dp, start = 12.dp, end = 12.dp), horizontalArrangement = Arrangement.Center) {
            OutlinedButton(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5D5FEF),
                    contentColor = Color.White
                ),
                onClick = { /*TODO*/ }) {
                Text(text = "Criar Conta")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaCreateAccountPreview() {
    //TelaLogin(navController = rememberNavController())
}