package com.materip.feature_mypage.view_models.MyPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.materip.core_common.ErrorState
import com.materip.core_common.Result
import com.materip.core_common.asResult
import com.materip.core_model.response.BoardItemWithRequestId
import com.materip.core_repository.repository.accompany_repository.AccompanyRepository
import com.materip.feature_mypage.pager.SendApplicationPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SendTravelApplicationViewModel @Inject constructor(
    private val accompanyRepository: AccompanyRepository
): ViewModel() {

    private val invalidTokenError = MutableStateFlow<Boolean>(false)
    private val notFoundTokenError = MutableStateFlow<Boolean>(false)
    private val generalError = MutableStateFlow<Pair<Boolean, String?>>(Pair(false, null))
    val errorState: StateFlow<ErrorState> = combine(invalidTokenError, notFoundTokenError, generalError){ invalidToken, notFoundToken, general ->
        ErrorState.AuthError(
            invalidToken,
            notFoundToken,
            general
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ErrorState.Loading
    )

    val uiState: StateFlow<SendTravelApplicationUiState> = errorState.map{
        if (it is ErrorState.AuthError && it.isInvalid()) {throw Exception("Error")}
    }.asResult().map{ result ->
        when(result){
            Result.Loading -> SendTravelApplicationUiState.Loading
            is Result.Success -> SendTravelApplicationUiState.Success
            is Result.Error -> SendTravelApplicationUiState.Error
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SendTravelApplicationUiState.Loading
    )

    fun applicationPagingSource(): Flow<PagingData<BoardItemWithRequestId>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {getSendApplication()},
    ).flow.cachedIn(viewModelScope)

    private fun getSendApplication() = SendApplicationPagingSource(accompanyRepository = accompanyRepository)

}

sealed interface SendTravelApplicationUiState {
    data object Loading: SendTravelApplicationUiState
    data object Error: SendTravelApplicationUiState
    data object Success: SendTravelApplicationUiState
}