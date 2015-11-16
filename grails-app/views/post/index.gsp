<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Top Ranked Posts | Grails News</title>
    </head>
    <body>
    	<ol class='posts-list' start="${params.offset}">
    		<g:each var="post" in="${posts}">
    			<li>
    				<g:if test="${post.url}">
    					<g:link url="${post.url}" target="_blank">${post.title}</g:link>
					</g:if>
					<g:else>
						<g:link controller="posts" action="show">${post.title}</g:link>
					</g:else>
    			</li>
    		</g:each>
    	</ol>
    </body>
</html>