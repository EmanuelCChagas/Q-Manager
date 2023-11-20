package com.emanuelcchagas.q_manager.models

sealed class Tela(val route: String){
    object Inicial: Tela(route = "tela_inicial")
    object Login: Tela(route = "tela_login")
    object CreateAccount: Tela(route = "tela_create_account")
    object HomeUsuario: Tela(route = "tela_home_usuario")
    object HomeAdm: Tela(route = "tela_home_adm")
    object FilaEditAdm: Tela(route = "tela_fila_edit_adm?id={id}"){
        fun navegar(id: Int = 0): String {
            return "tela_fila_edit_adm?id=$id"
        }
    }


}
