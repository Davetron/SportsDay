<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Welcome to Grails</title>
<style type="text/css" media="screen">
#status {
	background-color: #eee;
	border: .2em solid #fff;
	margin: 2em 2em 1em;
	padding: 1em;
	width: 12em;
	float: left;
	-moz-box-shadow: 0px 0px 1.25em #ccc;
	-webkit-box-shadow: 0px 0px 1.25em #ccc;
	box-shadow: 0px 0px 1.25em #ccc;
	-moz-border-radius: 0.6em;
	-webkit-border-radius: 0.6em;
	border-radius: 0.6em;
}

.ie6 #status {
	display: inline;
	/* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
}

#status ul {
	font-size: 0.9em;
	list-style-type: none;
	margin-bottom: 0.6em;
	padding: 0;
}

#status li {
	line-height: 1.3;
}

#status h1 {
	text-transform: uppercase;
	font-size: 1.1em;
	margin: 0 0 0.3em;
}

#page-body {
	margin: 2em 1em 1.25em 18em;
}

h2 {
	margin-top: 1em;
	margin-bottom: 0.3em;
	font-size: 1em;
}

p {
	line-height: 1.5;
	margin: 0.25em 0;
}

#controller-list ul {
	list-style-position: inside;
}

#controller-list li {
	line-height: 1.3;
	list-style-position: inside;
	margin: 0.25em 0;
}

@media screen and (max-width: 600px) {
	#status {
		display: none;
	}
	#page-body {
		margin: 0 1em 1em;
	}
	#page-body h1 {
		margin-top: 0;
	}
}
</style>
<g:javascript library="jquery-ui" />
<r:require module="jquery-ui" />
<r:require modules="bootstrap"/>
</head>
<body>
	<script>
		$(function() {
			$("#status").tabs();
		});
	</script>
	<div id="status" role="complementary">
		<h1>Overall Standings</h1>
		<ul>
			<li><a href="#tabs-1">Mens</a></li>
			<li><a href="#tabs-2">Womens</a></li>
		</ul>
		<div id="tabs-1">
			<p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a,
				risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris.
				Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem.
				Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo.
				Vivamus sed magna quis ligula eleifend adipiscing. Duis orci.
				Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam
				molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut
				dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique
				tempus lectus.</p>
		</div>
		<div id="tabs-2">
			<p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus
				gravida ante, ut pharetra massa metus id nunc. Duis scelerisque
				molestie turpis. Sed fringilla, massa eget luctus malesuada, metus
				eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet
				fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam.
				Praesent in eros vestibulum mi adipiscing adipiscing. Morbi
				facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut
				posuere viverra nulla. Aliquam erat volutpat. Pellentesque
				convallis. Maecenas feugiat, tellus pellentesque pretium posuere,
				felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris
				consectetur tortor et purus.</p>
		</div>
	</div>

	<div id="page-body" role="main">
		<h1>Welcome to Grails</h1>
		<p>Congratulations, you have successfully started your first
			Grails application! At the moment this is the default page, feel free
			to modify it to either redirect to a controller or display whatever
			content you may choose. Below is a list of controllers that are
			currently deployed in this application, click on each to execute its
			default action:</p>

		<div id="controller-list" role="navigation">
			<h2>Available Controllers:</h2>
			<ul>
				<g:each var="c"
					in="${grailsApplication.controllerClasses.sort { it.fullName } }">
					<li class="controller"><g:link
							controller="${c.logicalPropertyName}">
							${c.fullName}
						</g:link></li>
				</g:each>
			</ul>
		</div>
	</div>
</body>
</html>
