# Webservices in Kotlin using Ktor

1. Separate Route class for Users
2. Handle Get, Post, Put and Delete requests from Clients
3. Using ContentNegotiation to convert classes and data into Json
4. Using BaseEntity for Generic Response
5. Using Extension Functions of Application Class, Route Class, Routing Class
6. Enable Auto-reload 


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

