package com.cahstudio.tesapotekk24.datasource.remote.model

data class MemberListResponse(
        val status: String,
        val members: List<Member>
){

    data class Member(
            val id: String,
            val name: String,
            val dateOfBirth: String,
            val registered: String
    )
}