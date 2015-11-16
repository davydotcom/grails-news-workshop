package grails.news

class RegisterController {

    def create() {

    }

    def save() {
    	def user = new User()

    	bindData(user, params.user)

    	if(user.save(flush:true)) {
    		redirect controller: 'post', action: 'index'
    	} else {
    		render view: 'create', model: [user: user, msg: 'An Error Occurred while creating the user']
    	}
    }
}
