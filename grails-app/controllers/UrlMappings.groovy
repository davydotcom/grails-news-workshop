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
        
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
