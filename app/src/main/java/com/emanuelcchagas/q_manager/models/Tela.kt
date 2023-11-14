package com.emanuelcchagas.q_manager.models

sealed class Tela(val route: String){
    object Inicial: Tela(route = "tela_inicial")
    object Login: Tela(route = "tela_login")
    object CreateAccount: Tela(route = "tela_create_account")
    object HomeUsuario: Tela(route = "tela_home_usuario")
}
