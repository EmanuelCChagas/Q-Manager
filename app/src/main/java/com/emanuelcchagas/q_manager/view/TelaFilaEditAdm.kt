package com.emanuelcchagas.q_manager.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emanuelcchagas.q_manager.models.ConverterHashMapParaListaFilaItem
import com.emanuelcchagas.q_manager.models.ListaFilaItem
import com.emanuelcchagas.q_manager.models.ListaItem
import com.emanuelcchagas.q_manager.models.Tela
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlin.collections.HashMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaFilaEditAdm(navController: NavController) {
    var listaItens by remember{ mutableStateOf(listOf<ListaFilaItem>()) }

    val database = Firebase.database
    val referenciaFilas = database.getReference("ListaFila")

    referenciaFilas.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val value: ArrayList<Any?>? = dataSnapshot.getValue<ArrayList<Any?>>()
            if(value != null){
                for (vl in value){
                    if(vl is HashMap<*, *>){
//                        val item = ConverterHashMapParaListaFilaItem(vl.key, vl.value);
//                        listaItens  = listaItens + item
                    }
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException())
        }
    })


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 2.dp)
                    ){
                        Text(text = "Nova Fila",
                            fontSize = TextUnit(6f, type = TextUnitType.Em),
                            fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ){
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Nome da Fila")
                Spacer(modifier = Modifier.width(18.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { /*TODO*/}
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Vagas Totais")
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    // decrease button
                    IconButton(
                        onClick = { /*Todo*/ },
                        modifier = Modifier.size(24.dp),
                        colors =  IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0xFFBAADAD)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowLeft,
                            contentDescription = "Decrease count",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "1")
                    Spacer(modifier = Modifier.width(12.dp))
                    IconButton(
                        onClick = { /*Todo*/ },
                        modifier = Modifier.size(24.dp),
                        colors =  IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0xFFBAADAD)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "Increase count",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Quantidade de Usuários")
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "13 Usuários Normais e 2 Pagantes")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(){

            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Button(
                    onClick = { /* Do something! */ },
                    colors = ButtonDefaults.textButtonColors(
                    containerColor = Color(0xFF86F89F)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Salvar", color = Color.White)
                }
                Button(
                    onClick = { /* Do something! */ },
                    colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Red
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Excluir Fila", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaFilaEditAdmPreview() {
    TelaHomeAdm(navController = rememberNavController())
}