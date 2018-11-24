package hello.controller;

import static spark.Spark.get;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import hello.model.ModelMovie;
import hello.classes.Movie;

public class ControllerMovie {
	
	private ModelMovie modelMovie;
	
	Set<Movie> moviesSorted = new LinkedHashSet<Movie>();

	public ControllerMovie(ModelMovie modelMovie){
		this.modelMovie = modelMovie;
	}
	
	public void getMovie(){
		get("/movie/:genre", (req, res) -> {
			moviesSorted.clear();
			List<Movie> moviesFound = modelMovie.searchGenre(req.params(":genre"));		
			Collections.shuffle(moviesFound);
			for (int i = 0; i <= 2; i++) {
				moviesSorted.add(moviesFound.get(i));
			}
			return new Gson().toJson(moviesSorted);			
		});
	}
	
	public void getMovieName(){
		get("/movie/:name", (req, res) -> {	
			Movie moviesFound = modelMovie.getName(req.params(":name"));	
			return new Gson().toJson(moviesFound);
		});
	}
}
