var updateStandings = function() {

    console.log("Updating standings");

    var overAllStandingsRequest = $.ajax({
	url : "http://localhost:9090/SportsDay/event/overallStandings",
	type : "GET",
	dataType : "json"
    });

    overAllStandingsRequest.done(function(response) {
	var index = 1;
	var content = "<table class=\"table table-bordered\" id=\"standings\">" + "<thead><tr>" + "<th>Rank</th>" + "<th>Nation</th>"
		+ "<th>Points</th>" + "<th>Difference</th>" + "</tr></thead><tbody>";
	var previousValue = null;

	$.each(response, function(key, value) {
	    if ($.trim(key) == 'Ireland') {
		content += "<tr class=\"success\">";
	    } else {
		content += "<tr>";
	    }

	    content += "<td>" + index + "</td><td>" + key + "</td><td>" + value + "</td>";

	    if (previousValue > 0) {
		var diff = previousValue - value;
		content += "<td>" + diff + "</td>"
	    } else {
		content += "<td> - </td>"
	    }

	    content += "</tr>";
	    console.log(index + ":" + key + " : " + value);

	    previousValue = value;
	    index++;
	});

	content += "</tbody></table>";

	$('#standings').replaceWith(content);
    });
};

var updateEvent = function(object) {

    // var order = object.sortable('serialize');
    var order = object.sortable('toArray');
    console.log("order:" + order)

    var eventId;
    var queryString = {};
    for ( var i = 0; i < order.length; i++) 
    {
	var items = order[i].split("_");
	var rank = i + 1;
	queryString.eventId = items[1];
	queryString["athleteId" + items[0]] = rank;
    }

    console.log("New athleteId order for eventId:" + queryString);

    var updateEventResults = $.ajax({
	url : "http://localhost:9090/SportsDay/event/updateEventResults",

	type : "POST",

	// dataType : "json"

	data : queryString,

	success : function(json) {
	    updateStandings();
	},

	// code to run if the request fails; the raw request and
	// status codes are passed to the function
	error : function(xhr, status) {
	    alert("Sorry, there was a problem!");
	},

    });

};

var initSortables = function() {

    $(".sortable").sortable({

	placeholder : "ui-state-highlight",

	update : function(event, ui) {
	    updateEvent($(this));
	}
    });

    $(".sortable").disableSelection();
};

var setButtons = function() {
    $("#check*").button();
    var count = $("#check*").size();
    console.log("Calling set button: " + count);
};

var showEvent = function(object, event) {

    event.preventDefault();

    $('#myModal').modal('hide');

    var eventId = object.attr('id');
    console.log("click for event " + eventId);
    var target = "#event" + eventId;
    console.log("target panel:" + target + ":");

    // the .not() makes it so if we are on that page, it doesn't fade in and out
    // the same content
    $('.eventPanel:visible').not(target).fadeOut('fast', function() {
	// after we fade out old content, fade in new
	$(target).slideDown();
    });
};

$(document).ready(function() {

    console.log("Doc's ready");

    updateStandings();

    initSortables();

    // setButtons();

    // Listener setup for switching between events
    $('#event1').css("display", "block");
    $("tr").click(function(event) {
	showEvent($(this), event);
    });

});
