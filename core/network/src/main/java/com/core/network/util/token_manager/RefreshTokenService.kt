package com.core.network.util.token_manager

import retrofit2.http.Body
import retrofit2.http.POST

internal interface RefreshTokenService {

    // TODO: Replace [Unit] with service request body
    @POST("auth/refresh-token")
    suspend fun getRefreshedToken(
        @Body requestBody: Unit,
    ): retrofit2.Response<RefreshTokenResponseDTO>
}
