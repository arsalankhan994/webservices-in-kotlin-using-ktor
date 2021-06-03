package com.erselan.table

import com.erselan.entity.UserEntity
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Users : Table() {

    const val USER_ID = "id"
    const val USER_FIRST_NAME = "firstName"
    const val USER_LAST_NAME = "lastName"
    const val USER_PHONE_NUMBER = "phoneNumber"
    const val USER_EMAIL_ADDRESS = "emailAddress"
    const val USER_PASSWORD = "password"
    const val PRIMARY_KEY_NAME = "PK_User_ID"

    val id = integer(USER_ID).autoIncrement()
    val firstName = varchar(USER_FIRST_NAME, length = 20)
    val lastName = varchar(USER_LAST_NAME, 20)
    val phoneNumber = varchar(USER_PHONE_NUMBER, 20)
    val emailAddress = varchar(USER_EMAIL_ADDRESS, 30)
    val password = varchar(USER_PASSWORD, 20)
    override val primaryKey = PrimaryKey(id, name = PRIMARY_KEY_NAME)

    fun toUser(it: ResultRow) : UserEntity = UserEntity (
        id = it[id],
        firstName = it[firstName],
        lastName = it[lastName],
        phoneNumber = it[phoneNumber],
        emailAddress = it[emailAddress],
        password = it[password]
    )
}