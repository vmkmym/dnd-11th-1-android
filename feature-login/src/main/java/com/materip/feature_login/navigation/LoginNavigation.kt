package com.materip.feature_login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.materip.feature_login.LoginRoute

fun NavController.navigateToLogin() = navigate(LoginRoute.LoginRoute.name)

fun NavGraphBuilder.login(
    navOnBoarding: () -> Unit,
    navMyPage: () -> Unit,
){
    composable(route = LoginRoute.LoginRoute.name){
        LoginRoute(
            navOnBoarding = navOnBoarding,
            navMyPage = navMyPage
        )
    }
}