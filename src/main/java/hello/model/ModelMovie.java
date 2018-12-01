package hello.model;


import java.util.List;
import java.util.LinkedList;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import hello.classes.Movie;

public class ModelMovie {
	
	ObjectContainer dbMovie = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/movie.db4o");
	
	public boolean addMovie(Movie movie){
		if(isMovieAvailable(movie.getName())){
			dbMovie.store(movie);
			dbMovie.commit();
			return true;
		}
		return false;
	}
	
	public boolean isMovieAvailable(String nameMovie){
		Query query = dbMovie.query();
		query.constrain(Movie.class);
	    ObjectSet<Movie> allMovies = query.execute();
	    for(Movie movies: allMovies){
	    	if(movies.getName().equals(nameMovie)) 
	    		return false;
	    }
	    return true;
	}
	
	public List<Movie> searchMovie(String genre, String director, String year) {
		List<Movie> result = new LinkedList<Movie>();
		Query query = dbMovie.query();
		query.constrain(Movie.class);
	    ObjectSet<Movie> allMovies = query.execute();

	    for(Movie movie: allMovies){
	    	if(movie.getGenre().equals(genre) && 
	    		movie.getDirector().equals(director) && 
	    		movie.getYear().equals(year)) {
	    			result.add(movie);
	    	}
	    }
		return result;
	}
	
	public List<Movie> getMovies(){	
		Query query = dbMovie.query();
		query.constrain(Movie.class);
		List<Movie> moviesFound = query.execute();
		return moviesFound;
	}
	
	public List<Movie> searchGenre(String genre){			
		List<Movie> result = new LinkedList<Movie>();
		Query query = dbMovie.query();
		query.constrain(Movie.class);
	    ObjectSet<Movie> moviesFound = query.execute();
		
	    for(Movie movie: moviesFound){   	
	    	if(movie.getGenre().equals(genre)) 
	    		result.add(movie);	    	
	    }	
	    return result;
	}
	
	public Movie getName(String name){
		Query query = dbMovie.query();
		query.constrain(Movie.class);
	    ObjectSet<Movie> moviesFound = query.execute();
		
	    for(Movie movie: moviesFound){
			if(movie.getName().equals(name)) return movie;
		}		
		return null;
	}
	
}

