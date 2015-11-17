package grails.news

class UserVote {
	static belongsTo = [user: User, post: Post]

	Integer value = 1 // 1 or -1 depending on vote
    static constraints = {
    	user unique: ['post']
    }
}
