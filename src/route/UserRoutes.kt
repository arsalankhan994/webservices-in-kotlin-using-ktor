package com.erselan.route

import com.erselan.constant.KeyConstant
import com.erselan.constant.ResponseMessageConstant
import com.erselan.constant.RoutesConstant
import com.erselan.entity.BaseEntity
import com.erselan.entity.UserEntity
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import kotlin.random.Random

/*
Define Appilication extension function for User Routes
*/
fun Application.userRoutes() {
    routing {
        userRoutes()
    }
}

private fun Route.userRoutes() {
    val usersList = mutableListOf<UserEntity>()
    for (i in 1..5) {
        usersList.add(
            UserEntity(
                id = i, firstName = "User $i", lastName = "Khan",
                phoneNumber = Random.nextInt(1, 100).toString(), emailAddress = "email $i",
                password = "12345", confirmPassword = "12345"
            )
        )
    }

    /*
    Define route string as /user
    so all calls related to user are handled by this route function
    */
    route(RoutesConstant.USER_ROUTE) {
        getAllUsers(usersList)
        getUserById(usersList)
    }
}

private fun Route.getUserById(usersList: MutableList<UserEntity>) {
    get(RoutesConstant.ID_ROUTE) {
        val id = call.parameters[KeyConstant.KEY_ID] ?: return@get missingIdParameter()
        try {
            val userEntity = usersList.find { it.id == id.toInt() } ?: return@get userNotFoundResponse()
            call.respond(
                BaseEntity(
                    statusCode = HttpStatusCode.OK.value,
                    message = ResponseMessageConstant.RESPONSE_MESSAGE_GET_ALL_USERS,
                    data = userEntity
                )
            )
        } catch (exception: NumberFormatException) {
            return@get invalidParameter()
        }
    }
}

private fun Route.getAllUsers(usersList: MutableList<UserEntity>) {
    get {
        call.respond(
            BaseEntity<List<UserEntity>>(
                statusCode = HttpStatusCode.OK.value,
                message = ResponseMessageConstant.RESPONSE_MESSAGE_GET_ALL_USERS,
                data = usersList
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