package com.core.network.util.token_manager

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseDTO(
    val data: RefreshTokenDTO,
)
