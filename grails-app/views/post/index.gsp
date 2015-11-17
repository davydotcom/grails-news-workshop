<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Top Ranked Posts | Grails News</title>
    </head>
    <body>
    	<g:if test="${!posts}">
    		<p>No Posts have been submitted yet</p>
    	</g:if>
    	<ol class='posts-list' start="${params.offset}">
    		<g:each var="post" in="${posts}">
    			<li>
    				<g:if test="${post.url}">
    					<g:link url="${post.url}" target="_blank">${post.title}</g:link>
					</g:if>
					<g:else>
						<g:link controller="post" action="show">${post.title}</g:link>
					</g:else>
                    <small>${post.points} points by ${post.user?.username}
                        <g:if test="${votingAllowed[post.id]}">
                            &nbsp;--&nbsp;
                            <g:link controller='post' action='upvote' id="${post.id}">Up Vote</g:link>&nbsp;
                            <g:link controller='post' action='upvote' id="${post.id}">Down Vote</g:link>
                        </g:if>
                    </small>
    			</li>
    		</g:each>
    	</ol>
    </body>
</html>