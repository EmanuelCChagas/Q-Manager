package com.emanuelcchagas.q_manager.models

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import java.lang.reflect.Type

data class ListaFilaItem(
    val idHashFila: String,
    var dsFilaNome: String,
    var idMinutosQtd:Int,
    val idTamanhoMaximo: Int,
    var idTamanhoAtual: Int
)

fun ConverterHashMapParaListaFilaItem(idHashFila: String, valor: HashMap<*, *>): ListaFilaItem {

    fun obterValorEmInt(chave: String, valorPadrao: Int):Int{
        if(valor.containsKey(chave)){
            val vld =  valor.get(key = chave)
            if(vld is Int) return vld;
            if(vld is Long) return vld.toInt();
        }
        return valorPadrao
    }
    return ListaFilaItem(
        idHashFila,
        valor.get(key = "dsFilaNome") as String,
        obterValorEmInt("idMinutosQtd", 0),
        (valor.get(key = "idTamanhoMaximo") as Long).toInt(),
        obterValorEmInt("idTamanhoAtual",0)
    )
}

@Composable
fun ListaItem(
    item: ListaFilaItem,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFD9D9D9))
            .height(100.dp)
            .padding(start = 7.dp, end = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = item.dsFilaNome,
            fontSize = TextUnit(6f, type = TextUnitType.Em)
        )
        Spacer(modifier = Modifier.width(20.dp))
        if(item.idMinutosQtd > 0){
            Text(text = item.idMinutosQtd.toString() + " min")
        } else{
            Text(text = "Livre")
        }

        Spacer(modifier = Modifier.width(20.dp))
        Text(text = item.idTamanhoAtual.toString() + "/" + item.idTamanhoMaximo.toString())
    }
    Divider (
        color = Color.Black,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
}
