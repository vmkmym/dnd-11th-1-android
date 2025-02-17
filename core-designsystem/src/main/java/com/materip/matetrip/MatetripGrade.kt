package com.materip.core_designsystem

import com.materip.core_designsystem.icon.Badges
import com.materip.core_designsystem.theme.MateTripColors
import com.materip.core_model.ui_model.Grade
import com.materip.core_model.ui_model.GradeTag

object MatetripGrade {
    val level_1 = Grade(
        GradeTag.ROOKIE.name,
        "새싹 메이트",
        MateTripColors.level_1_color,
        Badges.level_1_badge
    )
    val level_2 = Grade(
        GradeTag.ELITE.name,
        "우수 메이트",
        MateTripColors.level_2_color,
        Badges.level_2_badge
    )
    val level_3 = Grade(
        GradeTag.PASSIONATE.name,
        "열정 메이트",
        MateTripColors.level_3_color,
        Badges.level_3_badge
    )
    val level_4 = Grade(
        GradeTag.VETERAN.name,
        "베테랑 메이트",
        MateTripColors.level_4_color,
        Badges.level_4_badge
    )
}