package com.erselan

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

/*
As we define ApplicationKt module inside our application.conf file
so this is the entry point of our application
*/
fun Application.module() {

    /*
    Using ContentNegotiation to convert all request and response into json
    */
    install(ContentNegotiation) {
        json()
    }

    /*
    Testing our first route
    */
    routing {
        get {
            call.respondText(
                "Hello World",
                status = HttpStatusCode.OK
            )
        }
    }
}

