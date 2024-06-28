package com.example.xstudy.navigation

sealed class Routes (val routes:String){

    data object App : Routes("app")
    data object Login : Routes("login")
    data object Register : Routes("register")
    data object HomeDisplay : Routes("homeDisplay")
    data object DashBoardScreenRoute : Routes("dashBoard")
    data object Authentication : Routes("authentication")

}