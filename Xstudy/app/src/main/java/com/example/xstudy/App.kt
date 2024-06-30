package com.example.xstudy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xstudy.authentication.Authentication
import com.example.xstudy.authentication.Login
import com.example.xstudy.dashbord.DashBoardScreenRoute
import com.example.xstudy.home.HomeDisplay
import com.example.xstudy.navigation.Routes
import com.example.xstudy.repositories.AppViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun App(navController: NavController){
    val navControllerOne = rememberNavController()


    Scaffold (
    ){
            innerPadding ->

        NavHost(navController = navControllerOne, startDestination = Routes.HomeDisplay.routes,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = Routes.DashBoardScreenRoute.routes){
                DashBoardScreenRoute(navController)
            }
            composable(route = Routes.HomeDisplay.routes){
                HomeDisplay(navController)
            }
            composable(route = Routes.Authentication.routes){
                Authentication(navController = navController)
            }
        }
    }
}