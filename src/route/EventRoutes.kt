package com.erselan.route

import com.erselan.constant.RoutesConstant
import com.erselan.entity.EventEntity
import com.erselan.entity.UserEntity
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.random.Random

/*
Define Appilication extension function for Event Routes
*/
fun Application.eventRoutes() {
    routing {
        eventRoutes()
    }
}

private fun Route.eventRoutes() {
    val eventList = mutableListOf<EventEntity>()
    for (i in 1..5) {
        eventList.add(
            EventEntity( name = "Event $i", description = "Some Description $i")
        )
    }

    /*
    Define route string as /event
    so all calls related to user are handled by this route function
    */
    route(RoutesConstant.EVENT_ROUTES) {
        get {
            call.respond(eventList)
        }
    }
}