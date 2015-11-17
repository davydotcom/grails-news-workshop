class UrlMappings {

    static mappings = {
        
        "/"(controller: 'post', action: 'index')
        "/create"(controller: 'post', action: 'create')
        "/save"(controller: 'post', action: 'save')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
