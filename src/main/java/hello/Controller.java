package hello;

import static spark.Spark.get;

import java.util.List;

import com.google.gson.Gson;

public class Controller {
	
	private Model model;
	
	
	public Controller(Model model){
		this.model = model;
	}
	
	public void buscarFilme(){
		get("/filme/:genero", (req, res) -> {				
			List<Filme> filmesEncontrados = model.buscarGenero(req.params(":genero"));
			System.out.println(filmesEncontrados );
			return new Gson().toJson(filmesEncontrados);			
		});
	}
	
	
	public void buscarFilmeNome(){
		get("/filme/:nome", (req, res) -> {	
			
			Filme filmesEncontrado = model.buscarNome(req.params(":nome"));	
			return new Gson().toJson(filmesEncontrado);
			
		});
	}
		

}
