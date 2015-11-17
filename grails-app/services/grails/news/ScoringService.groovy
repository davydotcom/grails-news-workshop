package grails.news

import grails.transaction.Transactional

@Transactional
class ScoringService {
	static transactional = false //we dont need transactional state here
    
    def sessionFactory

    def upVotePost(Post post, User user) {
    	def userVote = UserVote.findByPostAndUser(post,user)
    	if(!userVote) {
    		userVote = new UserVote(post: post, user: user, value: 1)
    		userVote.save(flush:true)
    		cacheScore(post,[flush:true])
    	}
    }

    def downVotePost(Post post, User user) {
    	def userVote = UserVote.findByPostAndUser(post,user)
    	if(!userVote) {
    		userVote = new UserVote(post: post, user: user, value: -1)
    		userVote.save(flush:true)
    		cacheScore(post,[flush:true])
    	}
    }

    def refreshAllScores() {
        def offset = 0
        def posts
        while(posts = Post.list(max:100, offset: offset)) {
            posts?.each{ post ->
                cacheScore(post)
                post.discard()
            }
            offset += 100
        }
    }

    def cacheScore(post,opts=[flush:true]) {
    	def now = new Date()
    	def pastTime = post.dateCreated?.time ?: now.time
    	def hours = Math.round((now.time - pastTime) / (60000l*60l))
    	def points = UserVote.findAllByPost(post).collect{it.value}.sum() ?: 0
    	post.score = calculateScore(points,hours)
        post.points = points
    	post.save(opts)
    }

    public BigDecimal calculateScore(p,t,g=1.8) {
    	(p-1)/((t+2)**g) as BigDecimal
    }

}
