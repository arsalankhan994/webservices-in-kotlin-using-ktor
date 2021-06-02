package com.erselan.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val password: String,
    val confirmPassword: String? = ""
)
