package grails.news

class PostController {
    def springSecurityService

    def index() { 
    	def posts = Post.list(sort: 'dateCreated', order: 'desc', max:30, offset: params.offset ?: 0)
    	return [posts: posts]
    }

    def create() {
    	return [post: new Post()]
    }

    def save() {
    	def post = new Post(user: springSecurityService.currentUser)
    	bindData(post,params.post,[exclude: 'user'])

    	if(post.save(flush:true)) {
    		redirect action: 'index'
    	} else {
    		render view: 'create', model: [post: post, msg: 'An Error Occurred while saving the post']
    	}
    }
}
