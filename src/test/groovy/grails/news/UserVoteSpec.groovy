package grails.news

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(UserVote)
@Mock([User,Post])
class UserVoteSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	/**
	* The new UserVote domain class should contain 2 belongsTo
	* properties for the User and Post record as well as a value Integer that could have a value of either -1 or 1
	* Hint: http://grails.github.io/grails-doc/3.0.x/ref/Domain%20Classes/belongsTo.html
	*/
	void "A user vote should have properties for the user, post, and a value"() {
		given:
			def userVote = new UserVote()
		expect:
			userVote.metaClass.hasProperty(userVote, 'user')
			userVote.metaClass.hasProperty(userVote, 'post')
			userVote.metaClass.hasProperty(userVote, 'value')
	}

	void "it should successfully save if all values are setup"() {
		given:
			def user = new User(username: 'testUser', password: 'password').save()
			def post = new Post(title: 'test', url: 'http://www.google.com', user: user).save()
			def userVote = new UserVote(user: user,post: post,value:1)
		expect:
			userVote.save() != null
	}

	// Bug in grails 3.1.0.M2
	// void "it should not allow multiple votes for the same user"() {
	// 	given:
	// 		def user = new User(username: 'testUser', password: 'password').save()
	// 		def post = new Post(title: 'test', url: 'http://www.google.com', user: user).save()
	// 		new UserVote(user: user,post: post,value:1).save()
	// 		def userVote = new UserVote(user: user,post: post,value:-1)
	// 	expect:
	// 		userVote.validate() == false
	// }
}
