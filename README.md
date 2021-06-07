# Webservices in Kotlin using Ktor

1. Separate Route class for Users
2. Handling Get, Post, Put and Delete requests from Clients
3. Implementing insert, update, delete, retrieve queries using H2 database
4. Using ContentNegotiation to convert classes and data into Json
5. Using BaseEntity for Generic Response
6. Using Extension Functions of Application Class, Route Class, Routing Class
7. Enable Auto-reload 


# For Enabling AutoReload

add these lines inside your application.conf file:
```
ktor {
    development = true
    deployment {
        ....
        watch = [ classes ]
    }
}
```

# BaseEntity for Generic Response

```
@Serializable
data class BaseEntity<T>(
    private val statusCode: Int,
    private val message: String,
    private val data: T?
)
```

# Database connection using JetBrains Exposed

```
Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
```

