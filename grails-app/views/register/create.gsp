<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Register | Grails News</title>
    </head>
    <body>
        <!-- Reference https://grails.github.io/grails-doc/3.1.0.M2/ref/Tags/form.html -->
    	<g:form url="[action:'save', method:'POST']">
            <div class="form-group">
                <label for="user.username">Username</label><br/>
                <g:textField name="user.username" value="${user?.username}"/>
            </div>
            <div class="form-group">
                <label for="user.password">Password</label><br/>
                <g:passwordField name="user.password"/>
            </div>
            
            <div class="form-group">
                <g:actionSubmit value="Save"/>
            </div>
        </g:form>
    </body>
</html>