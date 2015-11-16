<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>New Post | Grails News</title>
    </head>
    <body>
        <!-- Reference https://grails.github.io/grails-doc/3.1.0.M2/ref/Tags/form.html -->
    	<g:form url="[action:'save', method:'POST']">
            <div class="form-group">
                <label for="post.title">Title</label><br/>
                <g:textField name="post.title" value="${post.title}"/>
            </div>
            <div class="form-group">
                <label for="post.url">Url</label><br/>
                <g:textField name="post.url" value="${post.url}"/>
            </div>
            <div class="form-group">
                <label for="post.text">Text</label><br/>
                <g:textArea name="post.text" value="${post.text}"/>
            </div>
            <div class="form-group">
                <g:actionSubmit value="Save"/>
            </div>
        </g:form>
    </body>
</html>