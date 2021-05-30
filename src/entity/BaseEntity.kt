package com.erselan.entity

data class BaseEntity(
    private val statusCode: Int,
    private val message: String,
    private val data: Any?
)
