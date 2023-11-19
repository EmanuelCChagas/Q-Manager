package com.emanuelcchagas.q_manager.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun TelaLogin(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                color = Color.Black,
                modifier = Modifier.padding( top = 10.dp, end = 3.dp),
                text = "Login",
                fontSize = TextUnit(10f, type = TextUnitType.Em),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(28.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)){
                Text(
                    color = Color.Black,
                    text = "Endereço de E-mail",
                    fontSize = TextUnit(5f, type = TextUnitType.Em),
                    fontWeight = FontWeight.Bold
                )
                var dsEmail by remember{ mutableStateOf( "") }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = dsEmail,
                    onValueChange = { dsEmailNovo -> dsEmail = dsEmailNovo},
                    label = { Text(  text = "Digite seu endereço de e-mail") }
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)){
                Text(
                    color = Color.Black,
                    text = "Senha",
                    fontSize = TextUnit(5f, type = TextUnitType.Em),
                    fontWeight = FontWeight.Bold
                )
                var dsSenha by remember{ mutableStateOf( "") }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = dsSenha,
                    onValueChange = { dsSenhaNova -> dsSenha = dsSenhaNova},
                    label = { Text("Digite sua senha") }
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row (){
//                Checkbox(checked = false, onCheckedChange = {/*TODO*/})
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp), horizontalArrangement = Arrangement.Center) {
                OutlinedButton(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5D5FEF),
                        contentColor = Color.White
                    ),
                    onClick = { navController.navigate(route = Tela.HomeUsuario.route) }) {
                    Text(text = "Entrar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaLoginPreview() {
    TelaLogin(rememberNavController())
    //TelaLogin(navController = rememberNavController())
}