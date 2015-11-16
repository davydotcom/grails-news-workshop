package grails.news

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Post)
class PostSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "A Post should have a title, url, and text property defined"() {
    	given:
    		def post = new Post()
    	expect:
    		post.metaClass.hasProperty(post, 'title')
    		post.metaClass.hasProperty(post, 'url')
    		post.metaClass.hasProperty(post, 'text')
    		post.metaClass.hasProperty(post, 'dateCreated')
    		post.metaClass.hasProperty(post, 'lastUpdated')
    }

    /**
    * By Default Grails GORM require all fields be filled in on a given GORM class.
    */
    void "A Post should fail if no title is specified"() {
    	given:
    		def post = new Post(title: null, url: 'http://grails.org', text: 'Sample text')
    	expect:
    		post.validate() == false
    }

    void "A Post should be invalid if the name title is blank"() {
    	given:
    		def post = new Post(title: '', url: 'http://grails.org', text: 'Sample text')
    	expect:
    		post.validate() == false
    }

    /**
    * For this test to pass we need to set some constraints on our GORM domainClass
    * Hint: https://grails.github.io/grails-doc/3.1.0.M2/ref/Constraints/nullable.html
    */
    void "A Post should save if a url is not specified but a text message is"() {
    	given:
    		def post = new Post(title: 'Grails Rocks', url: null, text: 'Sample text')
    	expect:
    		post.validate() == true
    }

	/**
    * For this test to pass we need to set some constraints on our GORM domainClass
    * Hint: https://grails.github.io/grails-doc/3.1.0.M2/ref/Constraints/nullable.html
    */
    void "A Post should save if a text message is blank but a url is specified"() {
    	given:
    		def post = new Post(title: 'Grails Rocks', url: 'http://grails.org', text: null)
    	expect:
    		post.validate() == true
    }

	/** 
	* Tests to make sure that either a url or message body is set
	* Hint: See the GORM custom validator reference: 
	* http://grails.github.io/grails-doc/3.1.0.M2/ref/Constraints/validator.html
	*/
    void "A Post should fail to save if both a url and a text is unspecified"() {
    	given:
    		def post = new Post(title: 'Grails Rocks')
    	expect:
    		post.validate() == false
    }

    /**
    * A Post should get updated with a timestamp of when it was created upon save
    * Hint: http://grails.github.io/grails-doc/3.1.0.M2/ref/Database%20Mapping/autoTimestamp.html
    */
    void "A Post should get assigned a dateCreated value upon save"() {
    	given: 
    		def post = new Post(title: 'Grails Rocks', url: 'http://grails.org')
    	expect:
    		post.save(flush:true).dateCreated != null
    }

    /**
    * Bonus: When this record is hooked up to a backed data store it may become necessary
    * to further clarify the data type of the 'text property'. For example with mysql you might want
    * to change the data type from VARCHAR. To do this look at the mapping settings
    * Hint: https://grails.github.io/grails-doc/3.1.0.M2/guide/GORM.html#ormdsl
    */

}
