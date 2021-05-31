package com.erselan.entity

data class BaseEntity<T>(
    private val statusCode: Int,
    private val message: String,
    private val data: T?
)
