package com.example.profile.model.dtogrpc

data class UserNameDto @JvmOverloads constructor(
    val id: String? = "",
    val userUsername: String
) {
    companion object {
        @JvmStatic
        fun convert(id: String,userUsername: String): UserNameDto{
            return UserNameDto(id, userUsername)
        }
    }
}