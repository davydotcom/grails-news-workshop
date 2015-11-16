package grails.news

import grails.test.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class RegisterSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    /**
    * This is whats known as a functional test. It uses Geb to verify
    * the functional aspect of your webapp. Its asking us to create a view for our registerController
    * Hint: Look at the grails-app/views/post/create.gsp for a form example
    */
    void "a form with url /register/save should exist"() {
        when:"The register page is visited"
            go '/register'

        then:"The Register form exists"
            println $("form").@action
            $("form").@action == "/register/save"
            // $("form").size() == 1
    }

    void "the user form elements should exist"() {
        when:"The register page is visited"
            go '/register'

        then:
            $("input", name: 'user.username').size() == 1
            $("input", name: "user.password").size() == 1
    }
}
