package com.example.suites.model.grpcdto

data class UserUsernameDto @JvmOverloads constructor(
    val id: String? = "",
    val userUsername: String
) {
    companion object {
        @JvmStatic
        fun convert(id: String,userUsername: String): UserUsernameDto{
            return UserUsernameDto(id, userUsername)
        }
    }
}