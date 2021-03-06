class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'post', action:'index')
        "/save"(controller: 'post', action:'save')
        "/create"(controller: 'post', action:'create')
        "/register"(controller: 'register', action: 'create')
        "/register/save"(controller: 'register', action: 'save')
        

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
