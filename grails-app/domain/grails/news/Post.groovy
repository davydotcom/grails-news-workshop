package grails.news

class Post {
	String title
	String text
	String url
    User user
	Date dateCreated
	Date lastUpdated
    Double score = 0
    Double points = 0

    static constraints = {
    	url nullable:true, validator: { val, obj ->
    			if(!val && !obj.text) {
    				return ['urlMissing']
    			}
    	}
    	text nullable:true, validator: { val, obj ->
    			if(!val && !obj.url) {
    				return ['textMissing']
    			}
    	}
    }

    static mapping = {
    	// text type: 'text'
    }
}
