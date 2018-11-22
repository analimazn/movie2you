$(document).ready(function(){
			

			$("#busca").click(function() {
				
				/*  FIXME:
				 *	Precisa achar algum jeito de fazer a tabela nao aparecer se a pessoa clicou no botão
				 *	sem escolher um genero. 
				 */
				//document.getElementById('tabela').style.display = "block";
				/*  FIXME:
				 *	Isso é uma gambiarra pra o bg preto nao 'acabar' quando a tabela aparecer
				 */
				//document.getElementById('content').style.height = "150vh";
				var genero = $( "#genero option:selected" ).text();
				
				$("#dadosFilme1").empty();
				$("#dadosFilme2").empty();
				$("#dadosFilme3").empty();
					
				var url = "/filme/"+genero+"";
				
				$.getJSON(url, function(data) {
						
						var itensDivs = [];
						
						$.each(data, function(i){
						
							itensDivs.push("<p>" + this.nome + "</p><p>" + this.diretor + "</p><p>" + 
								this.genero + "</p><p>"+ this.ano);

						});

						var index = 1;

						var indexOne = Math.floor(Math.random() * 5);
						var indexTwo = Math.floor(Math.random() * 5);
						var indexThree = Math.floor(Math.random() * 5);

						$("#dadosFilme1").append(itensDivs[indexOne]);
						$("#dadosFilme2").append(itensDivs[indexTwo]);
						$("#dadosFilme3").append(itensDivs[indexThree]);
						
				});

			});

		});
