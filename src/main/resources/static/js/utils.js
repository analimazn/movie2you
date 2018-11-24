$(document).ready(function(){
			
	$("#busca").click(function() {

		var genre = $( "#genre option:selected" ).text();
		
		$("#dadosFilme1").empty();
		$("#dadosFilme2").empty();
		$("#dadosFilme3").empty();
			
		var url = "/movie/"+genre+"";
		
		$.getJSON(url, function(data) {	
			var itemsDivs = [];
			
			$.each(data, function(){
				itemsDivs.push("<p>" + this.name + "</p><p>" + 
					this.director + "</p><p>" + 
					this.genre + "</p><p>"+ 
					this.year + "</p>");
			});
			$("#dadosFilme1").append(itemsDivs[0]);
			$("#dadosFilme2").append(itemsDivs[1]);
			$("#dadosFilme3").append(itemsDivs[2]);
				
		});

	});

	/*$("#cadastrar").click(function() {
		var genre = $( "#genre option:selected" ).text();
		
		$("#dadosFilme1").empty();
		$("#dadosFilme2").empty();
		$("#dadosFilme3").empty();
			
		var url = "/movie/"+genre+"";
		
		$.getJSON(url, function(data) {	
			var itemsDivs = [];
			
			$.each(data, function(){
				itemsDivs.push("<p>" + this.name + "</p><p>" + 
					this.director + "</p><p>" + 
					this.genre + "</p><p>"+ 
					this.year + "</p>");
			});
			$("#dadosFilme1").append(itemsDivs[0]);
			$("#dadosFilme2").append(itemsDivs[1]);
			$("#dadosFilme3").append(itemsDivs[2]);
				
		});

	});*/

});
