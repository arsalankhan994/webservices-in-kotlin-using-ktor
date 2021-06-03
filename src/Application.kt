package com.erselan

import com.erselan.route.userRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import org.jetbrains.exposed.sql.Database

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
    Initializing Database
    */
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

    /*
    Initializing userRoutes by using extension function
    */
    userRoutes()
}

