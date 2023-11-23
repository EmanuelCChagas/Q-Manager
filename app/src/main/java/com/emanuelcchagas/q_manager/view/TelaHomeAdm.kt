package com.emanuelcchagas.q_manager.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emanuelcchagas.q_manager.models.ConverterHashMapParaListaFilaItem
import com.emanuelcchagas.q_manager.models.FilaItem
import com.emanuelcchagas.q_manager.models.FilaItemValor
import com.emanuelcchagas.q_manager.models.ListaFilaItem
import com.emanuelcchagas.q_manager.models.ListaItem
import com.emanuelcchagas.q_manager.models.Tela
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import java.util.Enumeration
import kotlin.collections.HashMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaHomeAdm(navController: NavController) {
    var listaItens by remember{ mutableStateOf(listOf<ListaFilaItem>()) }

    val database = Firebase.database
    val referenciaFilas = database.getReference("Fila")

    //nova fila
    val openDialog = remember { mutableStateOf(false) }
    val dsNomeFilaNova = remember { mutableStateOf("") }
    val idVagasTotaisNova = remember { mutableStateOf(1) }

    referenciaFilas.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val value: Any? = dataSnapshot.getValue<Any?>()
            if(value != null && value is HashMap<*,*>){
                for (vl in value){
                        val item = ConverterHashMapParaListaFilaItem(vl.key.toString(), vl.value as HashMap<*, *>);
                        if(!listaItens.contains(item)){
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
        } ,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(
                onClick = {
                    //navController.navigate(route = Tela.FilaEditAdm.navegar())
                    openDialog.value = true
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add icon")
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            items(listaItens) {
                ListaItem(it, {})
            }
        }
        Divider(
            color = Color.Black,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )

    }

    if (openDialog.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = contentColorFor(MaterialTheme.colorScheme.background)
                        .copy(alpha = 0.6f)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        openDialog.value = false
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            CriacaoFilaDialog(openDialog, dsNomeFilaNova, idVagasTotaisNova)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriacaoFilaDialog(
    openDialog: MutableState<Boolean>,
    dsNomeFilaNova: MutableState<String>,
    idTamanhoMaximo: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "Nome da Fila")
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = dsNomeFilaNova.value,
                onValueChange = { dsNomeFilaNova.value = it },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Vagas Totais")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp)
            ) {
                // decrease button
                IconButton(
                    onClick = { if(idTamanhoMaximo.value > 0) idTamanhoMaximo.value-= 1 },
                    modifier = Modifier.size(24.dp),
                    colors = IconButtonDefaults.iconButtonColors(
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

                Text(text = idTamanhoMaximo.value.toString())

                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = { idTamanhoMaximo.value += 1 },
                    modifier = Modifier.size(24.dp),
                    colors = IconButtonDefaults.iconButtonColors(
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
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            Button(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    var filaItem = FilaItemValor(dsNomeFilaNova.value, 0, idTamanhoMaximo.value, 0)
                   Firebase.database.getReference("Fila").push().setValue(filaItem)
                    openDialog.value = false
                }
            ) {
                Text("OK")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaHomeAdmPreview() {
    TelaHomeAdm(navController = rememberNavController())
}