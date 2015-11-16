import grails.news.*
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(UrlMappings)
@Mock(PostController)
class UrlMappingsSpec extends Specification {

	/**
	* This uses the assertForwardUrlMapping helper to verify that the specified routes are defined
	* and pointed to the appropriate controller actions
	* Hint: http://grails.github.io/grails-doc/3.1.0.M2/guide/single.html#urlmappings
	*/
    void "All Url Mapping patterns for the Posts controller should be defined"() {
        expect:
        assertForwardUrlMapping("/", controller: 'post', action: 'index')
        assertForwardUrlMapping("/create", controller: 'post', action: 'create')
        assertForwardUrlMapping("/save", controller: 'post', action: 'save', method: 'POST')
    }
}