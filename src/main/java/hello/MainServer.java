package hello;

import static spark.Spark.*;
import hello.model.*;
import hello.controller.*;
import hello.classes.*;

public class MainServer {
	
	final static ModelMovie modelMovie = new ModelMovie();
	final static ModelUser modelUser = new ModelUser();
	
    public static void main(String[] args) {
    	
		// Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 6060;
        }
        port(port);

        //Servir conteudo html, css e javascript
		staticFileLocation("/static");

		inicializarFilmes();

		ControllerMovie controllerMovie = new ControllerMovie(modelMovie);
		ControllerUser controllerUser = new ControllerUser(modelUser);
		
		controllerMovie.getMovie();
		controllerMovie.getMovieName();
		controllerUser.postUser();
		
		
    }
    
    public static void inicializarFilmes(){
    	//Comedia
    	modelMovie.addMovie(new Movie("Crazy, Stupid, Love", "Glenn Ficarra", "Comedia", "2011"));
    	modelMovie.addMovie(new Movie("Easy A", "Will Gluck", "Comedia", "2010"));
    	modelMovie.addMovie(new Movie("Mr. Right", "Paco Cabezas", "Comedia", "2015"));
    	modelMovie.addMovie(new Movie("The Big Sick", "Michael Showalter", "Comedia", "2017"));
    	modelMovie.addMovie(new Movie("Crazy Rich Asians", "Jon M. Chu", "Comedia", "2018"));


    	//Suspense
    	modelMovie.addMovie(new Movie("The Da Vinci Code", "Ron Howard", "Suspense", "2006"));
    	modelMovie.addMovie(new Movie("Miami Vice", "Michael Mann", "Suspense", "2006"));
    	modelMovie.addMovie(new Movie("The Prestige", "Christopher Nolan", "Suspense", "2006"));
    	modelMovie.addMovie(new Movie("Push", "Paul McGuigan", "Suspense", "2009"));
    	modelMovie.addMovie(new Movie("Stoker", "Park Chan-wook", "Suspense", "2013"));
    	modelMovie.addMovie(new Movie("Neon Demon", "Nicolas Winding Refn", "Suspense", "2016"));


    	//Ficcao
    	modelMovie.addMovie(new Movie("District 9", "Neill Blomkamp", "Ficcao", "2009"));
    	modelMovie.addMovie(new Movie("Arrival", "Denis Villeneuve", "Ficcao", "2016"));
    	modelMovie.addMovie(new Movie("Avatar", "James Cameron", "Ficcao", "2009"));
    	modelMovie.addMovie(new Movie("Inception", "Christopher Nolan", "Ficcao", "2010"));
    	modelMovie.addMovie(new Movie("Edge of Tomorrow", "Doug Liman", "Ficcao", "2014"));
    	modelMovie.addMovie(new Movie("Blade Runner 2049", "Denis Villeneuve", "Ficcao", "2017"));

    	//Acao

    	modelMovie.addMovie(new Movie("Avengers", "Joss Whedon", "Acao", "2012"));
    	modelMovie.addMovie(new Movie("Mad Max: Fury Road", "George Miller", "Acao", "2015"));
    	modelMovie.addMovie(new Movie("John Wick", "David Leitch", "Acao", "2014"));
    	modelMovie.addMovie(new Movie("300", "Zack Snyder", "Acao", "2006"));
    	modelMovie.addMovie(new Movie("Kick Ass", "Matthew Vaughn", "Acao", "2010"));

    	//Animacao
    	modelMovie.addMovie(new Movie("Moana", "Ron Clements", "Animacao", "2016"));
    	modelMovie.addMovie(new Movie("Inside Out", "Pete Docter", "Animacao", "2015"));
    	modelMovie.addMovie(new Movie("Big Hero 6", "Don Hall", "Animacao", "2014"));
    	modelMovie.addMovie(new Movie("Coraline", "Henry Selick", "Animacao", "2009"));
    	modelMovie.addMovie(new Movie("Shrek", "Cicky Jenson", "Animacao", "2001"));

    	//Aventura
    	modelMovie.addMovie(new Movie("Hunger Games", "Gary Ross", "Aventura", "2012"));
    	modelMovie.addMovie(new Movie("The Hobbit", "Peter Jackson", "Aventura", "2012"));
    	modelMovie.addMovie(new Movie("King Kong", "Peter Jackson", "Aventura", "2005"));
    	modelMovie.addMovie(new Movie("Sherlock Holmes", "Guy Ritchie", "Aventura", "2010"));
    	modelMovie.addMovie(new Movie("Star Trek", "J.J. Abrams", "Aventura", "2009"));

    	//Cult
    	modelMovie.addMovie(new Movie("Pulp Fiction", "Quentin Tarantino", "Cult", "1994"));
    	modelMovie.addMovie(new Movie("Fight Club", "David Fincher", "Cult", "1999"));
    	modelMovie.addMovie(new Movie("Clockwork Orange", "Stanley Kubrick", "Cult", "1972"));
    	modelMovie.addMovie(new Movie("Scarface", "Brian De Palma", "Cult", "1983"));
    	modelMovie.addMovie(new Movie("Showgirls", "Paul Verhoven", "Cult", "1995"));

    	//Documentario
    	modelMovie.addMovie(new Movie("Hot Girls Wanted", "Ronna Gradus", "Documentario", "2015"));
    	modelMovie.addMovie(new Movie("Blackfish", "Gabriela Cowperthwaite", "Documentario", "2013"));
    	modelMovie.addMovie(new Movie("Icaro", "Bryan Fogel", "Documentario", "2017"));
    	modelMovie.addMovie(new Movie("Man on Wire", "James Marsh", "Documentario", "2008"));
    	modelMovie.addMovie(new Movie("Jiro Dreams of Sushi", "David Gelb", "Documentario", "2011"));

    	//Thrash											
    	modelMovie.addMovie(new Movie("Machete", "Robert Rodriguez", "Trash", "2010"));
    	modelMovie.addMovie(new Movie("Plan 9 of Sidereal Space", "Ed Wood", "Trash", "1959"));
    	modelMovie.addMovie(new Movie("Drag me to Hell", "Sam Raimi", "Trash", "2009"));
    	modelMovie.addMovie(new Movie("Evil Dead 2", "Sam Raimi", "Trash", "1987"));
    	modelMovie.addMovie(new Movie("Sharknado", "David Michael Latt", "Trash", "2013"));

    	//Romance
    	modelMovie.addMovie(new Movie("It Happened One Night", "James Wan", "Romance", "1934"));
    	modelMovie.addMovie(new Movie("Casablanca", "Frank Capra", "Romance", "1942"));
    	modelMovie.addMovie(new Movie("Carol", "Todd Haynes", "Romance", "2015"));
    	modelMovie.addMovie(new Movie("Singing in the Rain", "Gene Kelly", "Romance", "1952"));
    	modelMovie.addMovie(new Movie("Her", "Spike Jonze", "Romance", "2013"));


    	//Fantasia					
    	modelMovie.addMovie(new Movie("The Neverending Story", "Wolfgang Petersen", "Fantasia", "1984"));
    	modelMovie.addMovie(new Movie("Time Bandits", "Terry Gilliam", "Fantasia", "1981"));
    	modelMovie.addMovie(new Movie("Lost Horizon", "James Hilton", "Fantasia", "1937"));
    	modelMovie.addMovie(new Movie("Pan�s Labyrinth", "Guillermo del Toro", "Fantasia", "2006"));
    	modelMovie.addMovie(new Movie("Orlando", "Sally Potter", "Fantasia", "1992"));


    	//Drama				
    	modelMovie.addMovie(new Movie("12 Years a Slave", "Steve McQueen", "Drama", "2013"));
    	modelMovie.addMovie(new Movie("Citizen Kane", "Orson Welles", "Drama", "1941"));
    	modelMovie.addMovie(new Movie("All About Eve", "Joseph L. Mankiewicz", "Drama", "1950"));
    	modelMovie.addMovie(new Movie("Metropolis", "Fritz Lang", "Drama", "1927"));
    	modelMovie.addMovie(new Movie("Moonlight", "Barry Jenkins", "Drama", "2016"));
    }
}
