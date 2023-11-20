package com.emanuelcchagas.q_manager

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.emanuelcchagas.q_manager.models.Tela
import com.emanuelcchagas.q_manager.view.TelaCreateAccount
import com.emanuelcchagas.q_manager.view.TelaFilaEditAdm
import com.emanuelcchagas.q_manager.view.TelaHomeAdm
import com.emanuelcchagas.q_manager.view.TelaHomeUsuario
import com.emanuelcchagas.q_manager.view.TelaInicial
import com.emanuelcchagas.q_manager.view.TelaLogin

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController =  navController,
        startDestination = Tela.Inicial.route
    ){
        composable(
            route = Tela.Inicial.route
        ){
            TelaInicial(navController)
        }
        composable(
            route = Tela.Login.route
        ){
            TelaLogin(navController)
        }
        composable(
            route = Tela.CreateAccount.route
        ){
            TelaCreateAccount()
        }
        composable(
            route = Tela.HomeUsuario.route
        ){
            TelaHomeUsuario(navController)
        }
        composable(
            route = Tela.HomeAdm.route
        ){
            TelaHomeAdm(navController)
        }
        composable(
            route = Tela.FilaEditAdm.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
                defaultValue = 0
            })
        ){
            TelaFilaEditAdm(navController)
        }
    }
}