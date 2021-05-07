package com.cahstudio.tesapotekk24.datasource.remote.model

data class AuthResponse(
        var status: String? = null,
        val operation: String? = null,
        val user: User? = null
) {

    data class User(
            val partnerID: String,
            val partnerCode: String,
            val name: String,
            val description: String
    )
}