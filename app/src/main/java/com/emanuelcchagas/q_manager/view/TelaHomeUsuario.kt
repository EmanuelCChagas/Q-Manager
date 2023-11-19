package com.emanuelcchagas.q_manager.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emanuelcchagas.q_manager.models.ListaFilaItem
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlin.collections.HashMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaHomeUsuario(navController: NavController) {
    var listaItens by remember{ mutableStateOf(listOf<ListaFilaItem>()) }

    val database = Firebase.database
    val referenciaFilas = database.getReference("ListaFila")

    referenciaFilas.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val value: ArrayList<Any?>? = dataSnapshot.getValue<ArrayList<Any?>>()
            if(value != null){
                for (vl in value){
                    if(vl is HashMap<*, *>){
                        val item = ListaFilaItem(
                            (vl.get(key = "cdFila") as Long).toInt(),
                            vl.get(key = "dsFilaNome") as String,
                            (vl.get(key = "idMinutosQtd") as Long).toInt(),
                            (vl.get(key = "idTamanhoMaximo") as Long).toInt(),
                            (vl.get(key = "idTamanhoAtual") as Long).toInt()
                        )
                        listaItens  = listaItens + item
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
                modifier = Modifier.height(100.dp),
                title = {
                            Column(modifier = Modifier.fillMaxSize().padding(top = 3.dp)){
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
                    )
           )
        }
        ){
          innerPadding ->
           LazyColumn(
               modifier = Modifier.fillMaxSize().padding(innerPadding),
               verticalArrangement = Arrangement.spacedBy(16.dp),
           ) {
               items(listaItens){
                   ListaItem(it, {})
               }
           }
        }
}

@Composable
fun ListaItem(
    item: ListaFilaItem,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = item.dsFilaNome)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = item.idMinutosQtd.toString() + " min")
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = item.idTamanhoAtual.toString() + "/" + item.idTamanhoMaximo.toString())
    }

}



@Preview(showBackground = true)
@Composable
fun TelaHomeUsuarioPreview() {
    TelaHomeUsuario(navController = rememberNavController())
}