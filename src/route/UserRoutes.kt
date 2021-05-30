package com.erselan.route

import com.erselan.constant.RoutesConstant
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

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
    Define route string as /user
    so all calls related to user are handled by this route function
    */
    route(RoutesConstant.USER_ROUTES) {
        get {
            call.respondText(text = "Arsalan Khan", status = HttpStatusCode.OK)
        }
    }
}