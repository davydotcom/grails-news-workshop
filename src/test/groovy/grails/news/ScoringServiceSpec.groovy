package grails.news

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ScoringService)
@Mock([User,Post,UserVote])
class ScoringServiceSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	void "upVotePost() should create a UserVote record for a user and a post with a value of 1"() {
		given:
			def user = new User(username: 'test', password: 'password').save()
			def post = new Post(title: 'Test', url: 'http://www.google.com', user: user).save()
		when:
			service.upVotePost(post,user)
		then:
			UserVote.countByUserAndPost(user,post) == 1
			UserVote.findByUserAndPost(user,post)?.value == 1
	}

	void "downVotePost() should create a UserVote record for a user and a post with a value of 1"() {
		given:
			def user = new User(username: 'test2', password: 'password').save()
			def post = new Post(title: 'Test2', url: 'http://www.google.com', user: user).save()
		when:
			service.downVotePost(post,user)
		then:
			UserVote.countByUserAndPost(user,post) == 1
			UserVote.findByUserAndPost(user,post)?.value == -1
	}


	void "upVotePost() should not create a user vote record if already voted"() {
		given:
			def user = new User(username: 'test', password: 'password').save()
			def post = new Post(title: 'Test', url: 'http://www.google.com', user: user).save()
		when:
			service.upVotePost(post,user)
			service.upVotePost(post,user)
		then:
			UserVote.countByUserAndPost(user,post) == 1
	}

	void "downVotePost() should not create a user vote record if already voted"() {
		given:
			def user = new User(username: 'test', password: 'password').save()
			def post = new Post(title: 'Test', url: 'http://www.google.com', user: user).save()
		when:
			service.downVotePost(post,user)
			service.downVotePost(post,user)
		then:
			UserVote.countByUserAndPost(user,post) == 1
	}

	void "upVotePost should calculate a new score when called"() {
		given:
			def user = new User(username: 'test', password: 'password').save()
			def user2 = new User(username: 'test2', password: 'password').save()
			def post = new Post(title: 'Test', url: 'http://www.google.com', user: user).save()
		when:
			service.upVotePost(post,user)
			service.upVotePost(post,user2)
		then:
			post.score != 0
			post.points == 2
	}


	void "refreshAllScores() should generate a score for all posts"() {
		given:
			def user = new User(username: 'test', password: 'password').save()
			150.times() { 
				new Post(title: 'Test', url: 'http://www.google.com', user:user).save()
			}
		when:
			service.refreshAllScores()
		then:
			Post.list()?.findAll {it.score != 0}?.size() == 150
	}


	void "calculateScore() should generate a score based on 'Score = (P-1) / (T+2)^G'"() {
		expect:
			service.calculateScore(p,t,g).setScale(3, BigDecimal.ROUND_HALF_UP) == s
		where:
			p | t | g   | s
			5 | 1 | 1.8 | 0.554
			4 | 2 | 1.8 | 0.247
	}
}
