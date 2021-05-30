package com.erselan.route

import com.erselan.constant.RoutesConstant
import com.erselan.entity.UserEntity
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
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
        usersList.add(UserEntity(id = i, firstName = "User $i", lastName = "Khan",
            phoneNumber = Random.nextInt(1, 100).toString(), emailAddress = "email $i",
            password = "12345", confirmPassword = "12345"))
    }

    /*
    Define route string as /user
    so all calls related to user are handled by this route function
    */
    route(RoutesConstant.USER_ROUTES) {
        get {
            call.respond(usersList)
        }
    }
}