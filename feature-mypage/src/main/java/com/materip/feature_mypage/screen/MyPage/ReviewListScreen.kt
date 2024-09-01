package com.materip.feature_mypage.screen.MyPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.materip.core_common.ErrorState
import com.materip.core_designsystem.component.NormalTopBar
import com.materip.core_designsystem.component.ReviewDescItem
import com.materip.core_model.response.DefaultListResponseDto
import com.materip.core_model.response.ReviewItem
import com.materip.core_model.ui_model.ReviewDescClass
import com.materip.feature_mypage.view_models.MyPage.ReviewListUiState
import com.materip.feature_mypage.view_models.MyPage.ReviewListViewModel

@Composable
fun ReviewListRoute(
    navBack: () -> Unit,
    navReviewDescription: () -> Unit,
    viewModel: ReviewListViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val errState = viewModel.errorState.collectAsStateWithLifecycle()

    ReviewListScreen(
        uiState = uiState.value,
        errState = errState.value,
        navBack = navBack,
        navReviewDescription = navReviewDescription
    )
}

@Composable
fun ReviewListScreen(
    uiState: ReviewListUiState,
    errState: ErrorState,
    navBack: () -> Unit,
    navReviewDescription: () -> Unit,
){
    when(uiState){
        ReviewListUiState.Loading -> {
            CircularProgressIndicator()
        }
        ReviewListUiState.Error -> {
            Text(
                text = "Error",
                fontSize = 100.sp,
                color = Color.Red
            )
        }
        is ReviewListUiState.Success -> {
            ReviewListMainContent(
                reviews = uiState.reviews.result,
                totalCount = uiState.reviews.totalCount,
                navBack = navBack,
                navReviewDescription = navReviewDescription
            )
        }
    }
}

@Composable
private fun ReviewListMainContent(
    reviews: List<ReviewItem>,
    totalCount: Int,
    navBack: () -> Unit,
    navReviewDescription: () -> Unit
){
    val totalCountText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)))){
            append("총 ")
        }
        withStyle(style = SpanStyle(fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.roboto_medium)))){
            append("${totalCount}")
        }
        withStyle(style = SpanStyle(fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)))){
            append("개")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
    ){
        NormalTopBar(
            title = "받은 동행 후기",
            titleFontWeight = FontWeight(700),
            onBackClick = navBack,
            onClick = {/* 미사용 */}
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = totalCountText,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
        )
        Spacer(Modifier.height(14.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(reviews){review ->
                ReviewDescItem(
                    destination = review.getRegionText(),
                    period = review.getDuration(),
                    startDate = review.startDate,
                    endDate = review.endDate,
                    profileUrl = review.profileImageUrl,
                    nickname = review.nickname,
                    age = "review.age", /** 받아오는 데이터로 변경 */
                    gender = "review.gender", /** 받아오는 데이터로 변경 */
                    content = review.detailContent,
                    onClick = navReviewDescription
                )
            }
        }
    }
}

@Preview
@Composable
fun ReviewListUITest(){
    ReviewListScreen(
        uiState = ReviewListUiState.Success(
            reviews = DefaultListResponseDto(
                totalCount = 3,
                result = listOf(
                    ReviewItem(
                        startDate = "2024.07.20",
                        endDate = "2024.07.22",
                        nickname = "닉네임",
                        profileImageUrl = "",
                        region = "부산",
                        detailContent = "같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰"
                    ),
                    ReviewItem(
                        startDate = "2024.07.20",
                        endDate = "2024.07.22",
                        nickname = "닉네임",
                        profileImageUrl = "",
                        region = "부산",
                        detailContent = "같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰"
                    ),
                    ReviewItem(
                        startDate = "2024.07.20",
                        endDate = "2024.07.22",
                        nickname = "닉네임",
                        profileImageUrl = "",
                        region = "부산",
                        detailContent = "같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰 같이 여행해서 좋았다는 리뷰"
                    ),
                )
            )
        ),
        errState = ErrorState.Loading,
        navBack = {},
        navReviewDescription = {}
    )
}