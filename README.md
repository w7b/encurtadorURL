<div align="center" class="introduction">
    <img width="600" height="150" src="images/spring-framework.png" alt="spring_logo">
    <h1>Url Shortener</h1>
    <a><img src="https://camo.githubusercontent.com/4791758c847fb6fed5034262835cac431716d9de320ac6287e9b1e1dd3df5830/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d4d49542d253233303444333631" /> </a> 
    <a><img src="https://camo.githubusercontent.com/a10b0ced9067fc390f485e53cc84a59348c50db73c34bdb37e1dedd890721f32/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c616e67756167652d6a6176612d677265656e" /></a>
    <a><img src="https://img.shields.io/badge/version-2.0-green" alt="badge icon"></a>
</div>

<hr/>
<br/>

<!-- About -->
## <img src="https://cdn2.iconfinder.com/data/icons/flat-pack-1/64/Computer-512.png" alt="todo list image icon" width="40px" align="center"> About
<div align="left" class="details">
    
    - Hexagonal Architecture.
    - SOLID principles used.
    - Unit tests were performed on Mapper and Service.
    - .env for confidential information.
    - Error message printed on request.
    - Row deleted from database after 30 minutes, cron checks invalid urls every 15 minutes.
    - Swagger Documentation

</div>

<hr/>
<br/>

<!-- Technologies -->
## <img src="https://cdn4.iconfinder.com/data/icons/general-office/91/General_Office_48-256.png" alt="todo list image icon" width="40px" align="center"> Technologies

<div>

    - Java 17
    - Spring Boot
    - Spring Data JPA
    - Spring Web
    - Docker
    - OpenAPI (Swagger)
    - PostgreSQL
    - Junit, Mockito and AssertJ
</div>
<hr/>
<br/>

<!-- Validations -->
## <img src="https://cdn4.iconfinder.com/data/icons/rating-validation-3/128/validation_stamp_approval_approve_check-512.png" alt ="image icon" width="40px" align="center"> Validations

<div> 

    REQUEST
    - "url_received" : ""
    PARAMETERS: {
        - NOT NULL,
        - More 20 characters,
        - Url need contains: "//" ":"
    }

    RESPONSE
    - "id" : ""
    - "url_received" : ""
    - "hash" : ""
    - "clickCount" : "0L"
    - "createAt" : "dd-MM-YYYY HH:MM:SS"
    - "expireAt" : "dd-MM-YYYY HH:MM:SS"
</div>