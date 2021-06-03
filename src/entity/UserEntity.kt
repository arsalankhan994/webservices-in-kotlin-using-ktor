package com.erselan.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val id: Int,
    private val firstName: String,
    private val lastName: String,
    private val phoneNumber: String,
    private val emailAddress: String,
    private val password: String,
    private val confirmPassword: String
)
