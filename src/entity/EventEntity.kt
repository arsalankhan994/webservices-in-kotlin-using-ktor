package com.erselan.entity

import kotlinx.serialization.Serializable

@Serializable
data class EventEntity(
    val name: String,
    val description: String
)
