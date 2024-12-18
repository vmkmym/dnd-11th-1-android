package com.materip.feature_mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.materip.feature_mypage.screen.MyPage.EditProfileRoute
import com.materip.feature_mypage.screen.MyPage.MyPageRoute
import com.materip.feature_mypage.screen.MyPage.PreviewRoute
import com.materip.feature_mypage.screen.MyPage.ProfileDescriptionRoute
import com.materip.feature_mypage.screen.MyPage.Quiz100Route
import com.materip.feature_mypage.screen.MyPage.ReviewDescriptionRoute
import com.materip.feature_mypage.screen.MyPage.ReviewEvaluationRoute
import com.materip.feature_mypage.screen.MyPage.ReviewListRoute
import com.materip.feature_mypage.screen.MyPage.SendApplicationRoute
import com.materip.feature_mypage.screen.MyPage.WriteReviewRoute
import com.materip.core_model.navigation.MyPageRoute
import com.materip.feature_mypage.screen.MyPage.ReceivedApplicationRoute
import com.materip.feature_mypage.screen.MyPage.ReceivedApplicationScreen

fun NavController.navigateToMyPageGraph() = navigate(MyPageRoute.MyPageGraph.name){
    launchSingleTop = true
    popUpTo("home"){inclusive = false}
}
fun NavController.navigateToMyPage() = navigate(MyPageRoute.MyPageRoute.name){
    launchSingleTop = true
    popUpTo("home"){inclusive = false}
}
fun NavController.navigateToEditProfile() = navigate(MyPageRoute.EditProfileRoute.name)
fun NavController.navigateToProfileDescription() = navigate(MyPageRoute.ProfileDescriptionRoute.name)
fun NavController.navigateToQuiz100() = navigate(MyPageRoute.Quiz100Route.name)
fun NavController.navigateToPreview() = navigate(MyPageRoute.PreviewRoute.name)
fun NavController.navigateToReviewEvaluation() = navigate(MyPageRoute.ReviewEvaluationRoute.name)
fun NavController.navigateToReviewList() = navigate(MyPageRoute.ReviewListRoute.name)
fun NavController.navigateToReviewDescription(reviewId: Int) = navigate("${MyPageRoute.ReviewDescriptionRoute.name}/${reviewId}")
fun NavController.navigateToSendApplication(applicationId: Int) = navigate("${MyPageRoute.SendApplicationRoute.name}/${applicationId}")
fun NavController.navigateToWriteReview(boardId: Int) = navigate("${MyPageRoute.WriteReviewRoute.name}/${boardId}")
fun NavController.navigateToReceivedApplication(applicationId: Int) = navigate("${MyPageRoute.ReceivedApplicationRoute.name}/${applicationId}")

fun NavGraphBuilder.myPageGraph(
    navBack: () -> Unit,
    navEditProfile: () -> Unit,
    navProfileDescription: () -> Unit,
    navQuiz100: () -> Unit,
    navPreview: () -> Unit,
    navSendApplication: (applicationId: Int) -> Unit,
    navReviewEvaluation: () -> Unit,
    navReviewDescription: (reviewId: Int) -> Unit,
    navReviewList: () -> Unit,
    navReviewWrite: (boardId: Int) -> Unit,
    navReceivedApplication: (applicationId: Int) -> Unit,
    navPostBoard: (boardId: Int) -> Unit
){
    navigation(
        startDestination = MyPageRoute.MyPageRoute.name,
        route = MyPageRoute.MyPageGraph.name
    ){
        composable(route = MyPageRoute.MyPageRoute.name){
            MyPageRoute(
                navEditProfile = navEditProfile,
                navProfileDescription = navProfileDescription,
                navQuiz100 = navQuiz100,
                navPreview = navPreview,
                navSendApplication = navSendApplication,
                navReviewWrite = navReviewWrite,
                navReceivedApplication = navReceivedApplication,
                navBack = navBack
            )
        }
        composable(route = MyPageRoute.EditProfileRoute.name){
            EditProfileRoute(navBack = navBack)
        }
        composable(route = MyPageRoute.ProfileDescriptionRoute.name){
            ProfileDescriptionRoute(navBack = navBack)
        }
        composable(route = MyPageRoute.Quiz100Route.name){
            Quiz100Route(navBack = navBack)
        }
        composable(route = MyPageRoute.PreviewRoute.name){
            PreviewRoute(
                navBack = navBack,
                navReviewEvaluation = navReviewEvaluation,
                navReviewList = navReviewList,
                navReviewDescription = navReviewDescription
            )
        }
        composable(route = MyPageRoute.ReviewEvaluationRoute.name){
            ReviewEvaluationRoute(navBack = navBack)
        }
        composable(route = MyPageRoute.ReviewListRoute.name){
            ReviewListRoute(
                navBack = navBack,
                navReviewDescription = navReviewDescription
            )
        }
        composable(
            route = "${MyPageRoute.ReviewDescriptionRoute.name}/{reviewId}",
            arguments = listOf(navArgument("reviewId"){type = NavType.IntType})
        ){
            val reviewId = it.arguments?.getInt("reviewId")
            ReviewDescriptionRoute(
                id = reviewId,
                navBack = navBack
            )
        }
        composable(
            route = "${MyPageRoute.SendApplicationRoute.name}/{applicationId}",
            arguments = listOf(navArgument("applicationId"){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt("applicationId")
            SendApplicationRoute(
                id = id,
                navBack = navBack,
                navPostBoard = navPostBoard
            )
        }
        composable(
            route = "${MyPageRoute.WriteReviewRoute.name}/{boardId}",
            arguments = listOf(navArgument("boardId"){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt("boardId")
            WriteReviewRoute(
                id = id,
                navBack = navBack
            )
        }
        composable(
            route = "${MyPageRoute.ReceivedApplicationRoute.name}/{applicationId}",
            arguments = listOf(navArgument("applicationId"){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt("applicationId")
            ReceivedApplicationRoute(
                id = id,
                navProfileDetail = navProfileDescription,
                navBack = navBack
            )
        }
    }
}