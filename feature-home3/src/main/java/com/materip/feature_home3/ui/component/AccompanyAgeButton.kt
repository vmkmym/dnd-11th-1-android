package com.materip.feature_home3.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.materip.core_designsystem.component.ToggleButton
import com.materip.core_designsystem.theme.MateTripColors.Gray_11
import com.materip.core_designsystem.theme.MateTripTypographySet
import com.materip.core_model.ui_model.PreferredAge

@Composable
fun AccompanyAgeButton(
    selectedAge: PreferredAge?,
    onAgeChange: (PreferredAge) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "모집 연령",
            color = Gray_11,
            style = MateTripTypographySet.title04,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            ToggleButton(
                buttonText = "동일 나이대",
                isSelected = selectedAge == PreferredAge.SAME,
                onClick = { onAgeChange(PreferredAge.SAME) },
                modifier = Modifier.weight(1f)
            )
            ToggleButton(
                buttonText = "상관없음",
                isSelected = selectedAge == PreferredAge.ANY,
                onClick = { onAgeChange(PreferredAge.ANY) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}