$(document).ready(function(){
			

			$("#busca").click(function() {

				var genero = $( "#genero option:selected" ).text();
				
				$("#dadosFilme1").empty();
				$("#dadosFilme2").empty();
				$("#dadosFilme3").empty();

				$("#dadosFilme").empty();
					
				var url = "/filme/"+genero+"";
				
				$.getJSON(url, function(data) {
						
						var itensDivs = [];
						var itensTable = [];
						
						$.each(data, function(i){
						
							itensDivs.push("<p>" + this.nome + "</p><p>" + this.diretor + "</p><p>" + 
								this.genero + "</p><p>"+ this.ano + "</p><p>");

							itensTable.push("<tr><td>" + this.nome + "</td><td>" + this.diretor + 
								"</td><td>" + this.genero + "</td><td>" + this.ano + "</td></tr>");

						});

						var index = 1;

						var indexOne = Math.floor(Math.random() * 5);
						var indexTwo = Math.floor(Math.random() * 5);
						var indexThree = Math.floor(Math.random() * 5);

						console.log(indexOne + " " + indexTwo + " " + indexThree);

						$("#dadosFilme1").append(itensDivs[indexOne]);
						$("#dadosFilme2").append(itensDivs[indexTwo]);
						$("#dadosFilme3").append(itensDivs[indexThree]);
						$("#dadosFilme").append(itensTable);
						
				});

			});

		});
