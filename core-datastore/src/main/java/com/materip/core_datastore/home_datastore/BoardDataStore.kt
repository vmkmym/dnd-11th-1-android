package com.materip.core_datastore.home_datastore

import com.materip.core_common.ResultResponse
import com.materip.core_model.accompany_board.create.BoardRequestDto
import com.materip.core_model.accompany_board.all.BoardListResponse
import com.materip.core_model.accompany_board.id.BoardIdDto
import com.materip.core_model.accompany_board.id.GetBoardDetailDto
import com.materip.core_model.accompany_board.profile.GetUserProfile
import com.materip.core_model.accompany_board.request.CompanionRequest
import com.materip.core_model.request.PagingRequestDto

interface BoardDataStore {
    suspend fun getBoard(boardRequest: PagingRequestDto): ResultResponse<BoardListResponse>
    suspend fun postBoard(board: BoardRequestDto): ResultResponse<BoardIdDto>
    suspend fun getBoardDetail(id: Int): ResultResponse<GetBoardDetailDto>
    suspend fun postCompanionRequest(companionRequest: CompanionRequest): ResultResponse<Unit>
    suspend fun deleteBoard(id: Int): ResultResponse<Unit>
    suspend fun getUserProfile(): ResultResponse<GetUserProfile>
}