var updateStandings = function() {
    
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

var listEvents = function(day){
    
    var eventListRequest = $.ajax({
	url : "http://localhost:9090/SportsDay/event/listDayEvents?day="+day,
	type : "GET",
	dataType : "json"
    });

    eventListRequest.done(function(response) {
	
	console.log("listEvents response:" + response);
	//$.each(response)
	
	/*
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
	*/
    });
};

var buildEventSelect = function(){
    
};

var setButtons = function(){
    $( "#check*" ).button(); 
    var count = $( "#check*" ).size();
    console.log("Calling set button: " + count);
};



$(document).ready(function(){
    console.log("Doc's ready");
    
    updateStandings();

    listEvents("saturday");
    
    var count = $( ".sortable" ).size();
    console.log("Calling sortable on " + count + " items");
    
    $( ".sortable" ).sortable({
	      placeholder: "ui-state-highlight"
	    });
    
    $( ".sortable" ).disableSelection();
    
    setButtons();
    
    $('#event1').css( "display", "block" );
    
    $("tr").click(function(event) {
	  event.preventDefault();
	  
	  $('#myModal').modal('hide');

	  var eventId = $(this).attr('id');
	  console.log("click for event " + eventId);
	  var target = "#event" + eventId; 
	  console.log("target panel:" + target + ":");

	  // the .not() makes it so if we are on that page, it doesn't fade in and out the same content
	  
	  $('.eventPanel:visible').not(target).fadeOut('fast', function() {
	    // after we fade out old content, fade in new
	    //$(target).show();
	    $(target).slideDown();
	  });
	});
	    
});


