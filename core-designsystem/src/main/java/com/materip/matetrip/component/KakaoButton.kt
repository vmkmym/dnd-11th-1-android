package com.materip.core_designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.materip.core_designsystem.R
import com.materip.core_designsystem.theme.MateTripColors.KakaoButtonColor
import com.materip.core_designsystem.theme.MateTripColors.KakaoTextColor

@Composable
fun KakaoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(size = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = KakaoButtonColor
        ),
        onClick = onClick,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_kakao),
                contentDescription = "Kakao Icon",
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = "카카오톡으로 간편 로그인",
                color = KakaoTextColor,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.noto_sans_kr)),
                fontWeight = FontWeight(500)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UITest(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        KakaoButton(
            modifier = Modifier.fillMaxWidth().height(54.dp),
            onClick = {}
        )
    }
}