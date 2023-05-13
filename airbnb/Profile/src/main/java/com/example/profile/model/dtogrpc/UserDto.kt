package com.example.profile.model.dtogrpc

import com.example.profile.model.Role
import com.example.profile.model.User

data class UserDto @JvmOverloads constructor(
    val id: UserNameDto? = null,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val telephone: String,
    val roles: MutableSet<Role>
) {
    companion object {
        @JvmStatic
        fun convert(from: User): UserDto {
            return UserDto(
                from.id?.let{ UserNameDto.convert(it, from.username) },
                from.name,
                from.surname,
                from.email,
                from.password,
                from.telephone,
                from.roles
            )
        }
    }
}