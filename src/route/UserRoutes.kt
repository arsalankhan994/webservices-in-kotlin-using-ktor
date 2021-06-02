package com.erselan.route

import com.erselan.constant.KeyConstant
import com.erselan.constant.ResponseMessageConstant
import com.erselan.constant.RoutesConstant
import com.erselan.entity.BaseEntity
import com.erselan.entity.UserEntity
import com.erselan.table.Users
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/*
Define Appilication extension function for User Routes
*/
fun Application.userRoutes() {
    routing {
        userRoutes()
    }
}

private fun Route.userRoutes() {

    /*
    Insert some data
    */
    transaction {
        SchemaUtils.create(Users)

        Users.insert {
            it[firstName] = "Arsalan"
            it[lastName] = "Khan"
            it[phoneNumber] = "123456789"
            it[emailAddress] = "arsalankhan994@gmail.com"
            it[password] = "123456"
        }

        Users.insert {
            it[firstName] = "Arsalan 1"
            it[lastName] = "Khan 1"
            it[phoneNumber] = "123456789"
            it[emailAddress] = "arsalankhan994@gmail.com"
            it[password] = "123456"
        }

        Users.insert {
            it[firstName] = "Arsalan 2"
            it[lastName] = "Khan 2"
            it[phoneNumber] = "123456789"
            it[emailAddress] = "arsalankhan994@gmail.com"
            it[password] = "123456"
        }
    }


    /*
    Define route string as /user
    so all calls related to user are handled by this route function
    */
    route(RoutesConstant.USER_ROUTE) {
        getAllUsers()
        getUserById()
        addUser()
    }
}

private fun Route.getUserById() {
    get(RoutesConstant.ID_ROUTE) {
        val id = call.parameters[KeyConstant.KEY_ID] ?: return@get missingIdParameter()
        try {
            val users = transaction { Users.selectAll().map { Users.toUser(it) } }
            val userEntity = users.find { it.id == id.toInt() } ?: return@get userNotFoundResponse()
            call.respond(
                BaseEntity(
                    statusCode = HttpStatusCode.OK.value,
                    message = ResponseMessageConstant.RESPONSE_MESSAGE_GET_SINGLE_USERS,
                    data = userEntity
                )
            )
        } catch (exception: NumberFormatException) {
            return@get invalidParameter()
        }
    }
}

private fun Route.getAllUsers() {
    get {
        val users = transaction {
            Users.selectAll().map { Users.toUser(it) }
        }
        call.respond(
            BaseEntity(
                statusCode = HttpStatusCode.OK.value,
                message = ResponseMessageConstant.RESPONSE_MESSAGE_GET_ALL_USERS,
                data = users
            )
        )
    }
}

private fun Route.addUser() {
    post {
        val userEntity = call.receive<UserEntity>()
        transaction {
            Users.insert {
                it[firstName] = userEntity.firstName
                it[lastName] = userEntity.lastName
                it[phoneNumber] = userEntity.phoneNumber
                it[emailAddress] = userEntity.emailAddress
                it[password] = userEntity.password
            }
        }
        call.respond(
            BaseEntity<String>(
                statusCode = HttpStatusCode.OK.value,
                message = ResponseMessageConstant.USER_CREATED_SUCCESSFULLY,
                data = null
            )
        )
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.userNotFoundResponse() {
    call.respond(
        BaseEntity<String>(
            message = ResponseMessageConstant.USER_NOT_FOUND,
            statusCode = HttpStatusCode.NotFound.value,
            data = null)
    )
}

private suspend fun PipelineContext<Unit, ApplicationCall>.missingIdParameter() {
    call.respond(
        BaseEntity<String>(
            message = ResponseMessageConstant.MISSING_ID_PARAMETER,
            statusCode = HttpStatusCode.BadRequest.value,
            data = null)
    )
}

private suspend fun PipelineContext<Unit, ApplicationCall>.invalidParameter() {
    call.respond(
        BaseEntity<String>(
            message = ResponseMessageConstant.INVALID_PARAMETERS,
            statusCode = HttpStatusCode.BadRequest.value,
            data = null)
    )
}