package com.emanuelcchagas.q_manager.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emanuelcchagas.q_manager.models.Tela

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(navController: NavController){
//   Scaffold(
//        topBar = {
//            TopAppBar(
//            title = { Text(text = "Abc")},
//            colors = TopAppBarDefaults.largeTopAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
//                )
//           )
//        }
//        ){
//       it
//       Column (
//
//       ){
//           Text(text = "Texto" + i)
//       }
//   }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF6F71E5), Color(0xFF8D162B))
                    )
                ),
            verticalArrangement =  Arrangement.Bottom
        ){
            Text(
                color = Color.White,
                modifier = Modifier.padding(start = 12.dp, end = 3.dp, bottom = 8.dp),
                text = "Bem Vindo ao Q-Manager",
                fontSize = TextUnit(13f, type = TextUnitType.Em),
                fontWeight = FontWeight.Bold,
                lineHeight = TextUnit(1f, TextUnitType.Em)
            )
            Text(color = Color.White, modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp), text = "O seu tempo melhor aproveitado",)
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
                        containerColor = Color.White,
                        contentColor = Color(0xFF5D5FEF)
                    ),
                    onClick = { navController.navigate(route = Tela.CreateAccount.route)  }) {
                    Text(text = "Criar Conta")
                }
            }
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp), horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier
                        .height(56.dp)
                        .width(112.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White),
                    onClick = { navController.navigate(route = Tela.Login.route) }) {
                    Text(text = "Login?")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    TelaInicial(navController = rememberNavController())
}