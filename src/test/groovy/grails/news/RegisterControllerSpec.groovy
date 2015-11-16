package grails.news

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RegisterController)
@Mock([User,Role,UserRole])
class RegisterControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create should return a 200 for registration"() {
        when:
        	controller.create()
    	then:
        	response.status == 200	
    }

    void "should create a user object when passed user params on save action"() {
    	given:
    		params.user = [
				username: 'testuser',
				password: 'password'
			]
    	when:
    		controller.save()
		then:
    		User.findByUsername('testuser') != null
    		response.redirectedUrl == '/'	
    }
}
