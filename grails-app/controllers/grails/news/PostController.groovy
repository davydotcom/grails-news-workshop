package grails.news

class PostController {
    def springSecurityService
    def scoringService

    def index() { 
    	def posts = Post.list(sort: 'score', order: 'desc', max:30, offset: params.offset ?: 0)

        def userVotes = UserVote.findAllByUserAndPost(springSecurityService.currentUser,posts)
        def votingAllowed = posts.collectEntries { 
            [(it.id): (userVotes.find{userVote -> userVote.post.id == it.id} ? false : true)]
        }
    	return [posts: posts, votingAllowed: votingAllowed]
    }

    def create() {
    	return [post: new Post()]
    }

    def save() {
    	def post = new Post(user: springSecurityService.currentUser)
    	bindData(post,params.post,[exclude: 'user'])

    	if(post.save(flush:true)) {
            scoringService.upVotePost(post, springSecurityService.currentUser)
    		redirect action: 'index'
    	} else {
    		render view: 'create', model: [post: post, msg: 'An Error Occurred while saving the post']
    	}
    }

    def upvote(Integer id) {
        def post = Post.get(id)
        if(!post) {
            render status: 404
            return
        }
        scoringService.upVotePost(post, springSecurityService.currentUser)
        redirect action: 'index'
    }

    def downvote(Integer id) {
        def post = Post.get(id)
        if(!post) {
            render status: 404
            return
        }
        scoringService.downVotePost(post, springSecurityService.currentUser)
        redirect action: 'index'
    }
}
