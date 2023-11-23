package com.emanuelcchagas.q_manager.models


data class FilaItemValor(
    var dsFilaNome: String,
    var idMinutosQtd:Int,
    val idTamanhoMaximo: Int,
    var idTamanhoAtual: Int
)
data class FilaItem(
    val identificao: String,
    val valor: FilaItemValor
)

fun ConverterHashMapParaFilaItem(idHashFila: String, valor: HashMap<*, *>): FilaItem {
    val filaValor = FilaItemValor(
        valor.get(key = "dsFilaNome") as String,
        (valor.get(key = "idMinutosQtd") as Long).toInt(),
        (valor.get(key = "idTamanhoMaximo") as Long).toInt(),
        (valor.get(key = "idTamanhoAtual") as Long).toInt()
    )
    return FilaItem(idHashFila, filaValor)
}