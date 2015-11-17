package grails.news

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
import spock.lang.Shared

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PostController)
@Mock([Post,User,UserVote])
class PostControllerSpec extends Specification {
	@Shared User user

	/**
	* We want to tie the logged in user to the submitted post
	* To do this we can use the springSecurityService bean
	* Hint: http://grails-plugins.github.io/grails-spring-security-core/guide/helperClasses.html#springSecurityService
	*/
	def setup() {
		user = new User(username: 'testUser', password: 'password').save()
		def springSecurityService = [currentUser: user]
		controller.springSecurityService = springSecurityService
		controller.scoringService = Mock(ScoringService)
	}

	def cleanup() {
	}

	/**
	* The controller needs to return a list of posts for reference by the view
	* Hint: https://grails.github.io/grails-doc/3.1.0M2/ref/Domain%20Classes/list.html
	*/
	void "index should return a list of posts in the posts property"() {
		given:
			def post = new Post(title: "Article: 1", text: 'Sample Message', user: user)
			post.save(flush:true)
			post = new Post(title: "Article: 2", text: 'Sample Message', user: user)
			post.save(flush:true)
		when:
			def model = controller.index()
		then:
			model.posts != null
			model.posts.size() == 2
			model.posts[0] instanceof Post
	}

	/**
	* We want to change the default sort order of GORM to be dateCreated descending
	* Hint: https://grails.github.io/grails-doc/3.1.0.M2/ref/Domain%20Classes/list.html
	*/
	void "index should return a list sorted by score descending"() {
		given:
			def post = new Post(title: "Article: 1", text: 'Sample Message', user: user, score:2)
			post.save(flush:true)
			post = new Post(title: "Article: 2", text: 'Sample Message', user: user, score:1)
			post.save(flush:true)
		when:
			def model = controller.index()
		then:
			model.posts != null
			model.posts.size() == 2
			model.posts[0].score == 2
			model.posts[0].title == 'Article: 1'
	}

	void "index should also return a voteMap with the key being the id and the value being true/false depending on if the user is allowed to vote or not"() {
		given:
			def post = new Post(title: "Article: 1", text: 'Sample Message', user: user)
			post.save(flush:true)
			post = new Post(title: "Article: 2", text: 'Sample Message', user: user)
			post.save(flush:true)
			new UserVote(user: user, post: post, value: 1).save(flush:true)
		when:
			def model = controller.index()
		then:
			model.votingAllowed != null
			model.votingAllowed instanceof Map
	}

	/**
	* The page should not list more than 30 results per page
	* Hint: https://grails.github.io/grails-doc/3.1.0.M2/ref/Domain%20Classes/list.html
	*/
	void "index should return a max of 30 per page"() {
		given:
			50.times {
				def post = new Post(title: "Article: 1", text: 'Sample Message', user: user, dateCreated: new Date() -1)
				post.save(flush:true)	
			}
		when:
			def model = controller.index()
		then:
			model.posts != null
			model.posts.size() == 30
	}

	/**
	* With this test we want to skip results by an offset if passed as a ?offset=
	* Hint: http://grails.github.io/grails-doc/3.1.0.M2/ref/Controllers/params.html
	*/
	void "index should return a list if an offset is provided by said offset"() {
		given:
			def post = new Post(title: "Article: 1", text: 'Sample Message', user: user, score: 57)
			post.save(flush:true)
			post = new Post(title: "Article: 2", text: 'Sample Message', user: user, score: 1)
			post.save(flush:true)
		when:
			params.offset = 1
			def model = controller.index()
		then:
			model.posts != null
			model.posts.size() == 1
	}


	// Test Create Form Controller action
	void "create should return a non persisted Post domain class instance to the post property"() {
		when:
			def model = controller.create()
		then:
			model.post instanceof Post
			model.post.id == null
	}

	void "passing post parameters to the save action should create a post record"() {
		given:
			params.post = [
				title: 'Test Persistence',
				url: 'http://www.davydotcom.com'
			]
		when:
			controller.save()
		then:
			response.redirectedUrl == '/'
			Post.findByTitle('Test Persistence') != null
	}
}
