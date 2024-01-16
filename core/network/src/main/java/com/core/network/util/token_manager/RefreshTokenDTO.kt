package com.core.network.util.token_manager

data class RefreshTokenDTO(
    val id: Int,
    val accessToken: String,
    val refreshToken: String
)
