<!DOCTYPE html>
<html>
<head>
<title>Bootstrap 101 Template</title>
<meta name="layout" content="mainLayout" />
<!-- Bootstrap -->
<r:require module="jquery-ui" />
<r:require modules="bootstrap" />
</head>
<body>

	<!-- MAIN CONTENT BODY -->
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span12">
				<%--<h1>European Athletics League - Round 1</h1>
				--%>
				<h1>Title</h1>
			</div>
		</div>

		<div class="row-fluid">

			<div class="span3">
				<!--Sidebar title bar-->
				<div class="row-fluid">
					<div id="headerbar" class="span12">
						<h3>Overall Standings</h3>
					</div>
				</div>

				<!--Sidebar content-->
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-bordered" id="standings">
							<!--  replaced/populated via sportsday.js -->
						</table>
					</div>
				</div>
			</div>

			<div class="span9">
				<!--Body content-->
				<g:each in="${eventInstanceList}" status="i" var="eventInstance">
					<div id="event${eventInstance.id}" class="eventPanel">

						<div class="row-fluid">
							<div id="headerbar" class="span12">
								<h3>
									${eventInstance.name}
									(
									<g:formatDate format="E HH:mm" date="${eventInstance.time}" />
									) <a href="#myModal" role="button"
										class="btn btn-large btn-primary" data-toggle="modal">Select
										Event</a>
								</h3>
							</div>

						</div>

						<div class="row-fluid">
							<div id="eventlist" class="span2">
								<ul class="placing">
									<g:each in="${eventInstance.athletes}" status="j" var="athlete">
										<li class="ui-state-default">
											${j+1}
										</li>
									</g:each>
								</ul>
							</div>
							<div id="eventlist" class="span10">
								<ul class="sortable">
									<g:each in="${eventInstance.athletes}" status="j" var="athlete">
										<!-- <li class="ui-state-default">${athlete}<input type="checkbox" id="check${athlete.country}" /><label id="check1label" for="check${athlete.country}">DNF</label></li> -->
										<li class="ui-state-default" id="${athlete.id}_${eventInstance.id}">
											${athlete}
										</li>
									</g:each>
								</ul>
							</div>
						</div>
					</div>
				</g:each>
			</div>

		</div>
	</div>

	<!-- Event Select Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">


		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4>Select Event</h4>
		</div>

		<div class="modal-body">
			<div class="container-fluid">
				<div class="row-fluid">

					<div class="span6">
						<table class="table table-hover table-condensed"
							id="saturdayEvents">
							<thead>
								<tr>
									<th>Time</th>
									<th>Event</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${saturdayEvents}" status="i" var="event">
									<tr id="${event.id}">
										<td><g:formatDate format="E HH:mm" date="${event.time}" /></td>
										<td>
											${event.name}
										</td>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>

					<div class="span6">
						<table class="table table-hover table-condensed" id="sundayEvents">
							<thead>
								<tr>
									<th>Time</th>
									<th>Event</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${sundayEvents}" status="i" var="event">
									<tr id="${event.id}">
										<td><g:formatDate format="E HH:mm" date="${event.time}" /></td>
										<td>
											${event.name}
										</td>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>

		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
	</div>
	
</body>
</html>