package grails.news

class Post {
	String title
	String text
	String url
    User user
	Date dateCreated
	Date lastUpdated

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
