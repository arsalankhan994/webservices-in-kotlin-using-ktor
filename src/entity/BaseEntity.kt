package com.erselan.entity

import kotlinx.serialization.Serializable

@Serializable
data class BaseEntity<T>(
    private val statusCode: Int,
    private val message: String,
    private val data: T?
)
