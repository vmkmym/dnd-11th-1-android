package com.materip.feature_home.intent

sealed class HomeIntent {
    data class UpdateTitle(val title: String) : HomeIntent()
    data class UpdateContent(val content: String) : HomeIntent()
    data class UpdateTagNames(val tagNames: List<String>) : HomeIntent()
    data class UpdateRegion(val region: String) : HomeIntent()
    data class UpdateStartDate(val startDate: String) : HomeIntent()
    data class UpdateEndDate(val endDate: String) : HomeIntent()
    data class UpdateCategory(val category: List<String>) : HomeIntent()
    data class UpdateAge(val preferredAge: String) : HomeIntent()
    data class UpdateGender(val preferredGender: String) : HomeIntent()
    data class UpdateCapacity(val capacity: Int) : HomeIntent()
    data class UpdateImageUris(val imageUris: List<String>) : HomeIntent()
    data class LoadBoardDetail(val boardId: Int) : HomeIntent()
}