package com.materip.feature_mypage.screen.MyPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
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
import com.materip.core_designsystem.component.CircleImageView
import com.materip.core_designsystem.component.ConfirmationDialog
import com.materip.core_designsystem.component.CustomButton
import com.materip.core_designsystem.component.NormalTag
import com.materip.core_designsystem.component.NormalTopBar
import com.materip.core_designsystem.component.ProgressIndicatorPreview
import com.materip.core_designsystem.component.TravelPostItem
import com.materip.core_designsystem.icon.Icons
import com.materip.core_designsystem.theme.MateTripColors
import com.materip.core_model.request.AccompanyApplicationResponseDto
import com.materip.core_model.ui_model.SendApplicationClass
import com.materip.feature_mypage.view_models.MyPage.SendApplicationDescUiState
import com.materip.feature_mypage.view_models.MyPage.SendApplicationDescViewModel

@Composable
fun SendApplicationRoute(
    id: Int?,
    navBack: () -> Unit,
    navPostDescription: () -> Unit,
    viewModel: SendApplicationDescViewModel = hiltViewModel()
){
    viewModel.setId(id)
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val errState = viewModel.errorState.collectAsStateWithLifecycle()

    SendApplicationScreen(
        uiState = uiState.value,
        errState = errState.value,
        onClickCancel = { viewModel.cancelApplication() },
        navPostDescription = navPostDescription,
        navBack = navBack
    )
}

@Composable
fun SendApplicationScreen(
    uiState: SendApplicationDescUiState,
    errState: ErrorState,
    onClickCancel: () -> Unit,
    navPostDescription: () -> Unit,
    navBack: () -> Unit,
){
    when(uiState){
        SendApplicationDescUiState.Loading -> {
            CircularProgressIndicator()
        }
        SendApplicationDescUiState.Error -> {
            Text(
                text = "Error",
                fontSize = 100.sp,
                color = Color.Red
            )
        }
        is SendApplicationDescUiState.Success -> {
            SendApplicationContent(
                data = uiState.data,
                onClickCancel = onClickCancel,
                navBack = navBack
            )
        }
    }
}

@Composable
private fun SendApplicationContent(
    data: AccompanyApplicationResponseDto,
    onClickCancel: () -> Unit,
    navBack: () -> Unit
){
    val scrollState = rememberScrollState()
    val userInfo = data.profileInfo
    val boardInfo = data.boardThumbnail
    val requestInfo = data.requestInfo
    val alterText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Black)){
            append("동행 수락 후 카카오톡 오픈채팅")
        }
        withStyle(style = SpanStyle(color = MateTripColors.Gray_06)){
            append("에서 대화해 보세요!\n상대방의 동의 없는 링크 공유는")
        }
        withStyle(style = SpanStyle(color = Color.Black)){
            append("서비스 이용 제재의 사유")
        }
        withStyle(style = SpanStyle(color = MateTripColors.Gray_06)){
            append("가 됩니다.")
        }
    }
    var isCancellable by remember{mutableStateOf(true)}
    var isOpen by remember{mutableStateOf(false)}
    if(isOpen){
        ConfirmationDialog(
            dialogMsg = "취소된 동행 신청은 복구가 불가능해요.\n동행 신청을 취소하시나요?",
            onOkClick = {
                isOpen = false
                isCancellable = false
                onClickCancel()
            },
            onDismissRequest = {isOpen = false}
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
            .padding(bottom = 50.dp)
    ){
        NormalTopBar(
            title = "보낸 신청서",
            titleFontWeight = FontWeight(700),
            onBackClick = navBack,
            onClick = {/* 미사용 */}
        )
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ){
            TravelPostItem(
                destination = boardInfo.region,
                period = boardInfo.getDuration(),
                title = boardInfo.title,
                startDate = boardInfo.startDate,
                endDate = boardInfo.endDate,
                postImage = boardInfo.imageUrls[0],
                onClick = {/* 미사용 */}
            )
            Spacer(Modifier.height(30.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MateTripColors.Gray_03)
            Spacer(Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(
                        color = MateTripColors.Blue_03,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(12.dp),
            ){
                Text(
                    text = "From. ${boardInfo.nickname}",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                    fontWeight = FontWeight(500),
                    color = MateTripColors.Gray_11
                )
                Spacer(Modifier.height(18.dp))
                Text(
                    text = requestInfo.introduce,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                    fontWeight = FontWeight(400),
                    color = MateTripColors.Gray_10
                )
            }
            Spacer(Modifier.height(14.dp))
            Text(
                text = alterText,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                fontWeight = FontWeight(400)
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))
                    .border(
                        width = 1.dp,
                        color = MateTripColors.Blue_03,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 10.dp)
            ){
                Text(
                    modifier = Modifier.clickable{
                        /** sns link 클릭 시 이동? */
                    },
                    text = requestInfo.chatLink,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                    fontWeight = FontWeight(400)
                )
            }
            Spacer(Modifier.height(40.dp))
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(size = 10.dp),
                btnText = if(isCancellable) "동행 신청 취소" else "취소 완료",
                textColor = if(isCancellable) Color.White else MateTripColors.Gray_06,
                fontSize = 14.sp,
                btnColor = if(isCancellable) Color.Black else MateTripColors.Blue_03,
                isEnabled = isCancellable,
                onClick = {isOpen = true}
            )
        }
    }
}

@Composable
private fun ProfileView(
    nickname: String,
    age: String,
    gender: String,
    imageUrl: String,
    tags: List<String>,
    navProfileDescription: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MateTripColors.Blue_03,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(12.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            CircleImageView(
                size = 42.dp,
                imageUrl = imageUrl
            )
            Spacer(Modifier.width(10.dp))
            Column{
                Text(
                    text = nickname,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                    fontWeight = FontWeight(500),
                    color = MateTripColors.Gray_11
                )
                Text(
                    text = "${age} · ${gender}",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                    fontWeight = FontWeight(400),
                    color = MateTripColors.Gray_06
                )
            }
            Spacer(Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "프로필 보기",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                    fontWeight = FontWeight(400),
                    color = MateTripColors.Gray_11
                )
                Spacer(Modifier.width(4.dp))
                IconButton(
                    modifier = Modifier.size(12.dp),
                    onClick = navProfileDescription
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(Icons.navigate_next_icon),
                        contentDescription = "Navigation Btn"
                    )
                }
            }
        }
        Spacer(Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ){
            tags.forEach{tag ->
                NormalTag(
                    modifier = Modifier,
                    tagName = tag,
                    shape = RoundedCornerShape(size = 5.dp),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(com.materip.core_designsystem.R.font.noto_sans_kr)),
                        fontWeight = FontWeight(500),
                    ),
                    color = MateTripColors.Gray_02
                )
            }
        }
    }
}

@Composable
@Preview
private fun SendApplicationUITest(){
    SendApplicationScreen(
        uiState = SendApplicationDescUiState.Loading,
        errState = ErrorState.Loading,
        onClickCancel = {},
        navPostDescription = {},
        navBack = {}
    )
}