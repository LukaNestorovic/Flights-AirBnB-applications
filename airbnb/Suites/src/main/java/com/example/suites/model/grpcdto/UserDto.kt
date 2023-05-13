package com.example.suites.model.grpcdto

import com.example.suites.model.Role

data class UserDto @JvmOverloads constructor(
//    val id: UserNameDto? = null,
    val name: String? = "",
    val surname: String? = "",
    val email: String? = "",
    val password: String? = "",
    val telephone: String? = "",
    val roles: MutableSet<Role>
) {
}