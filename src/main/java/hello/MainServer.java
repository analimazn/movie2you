package hello;

import static spark.Spark.*;

import hello.classes.*;
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
		
		controllerMovie.getIndex();
		controllerMovie.getAllMovies();
		controllerMovie.getMovie();
		controllerUser.postUser();
		controllerUser.postLogin();	
    }
    
    public static void inicializarFilmes(){
    	//Comedia
    	modelMovie.addMovie(new Movie("001", "Easy A", "Will Gluck", "COMEDIA", "2010"));
		modelMovie.addMovie(new Movie("002", "Mr. Right", "Paco Cabezas", "COMEDIA", "2015"));
		modelMovie.addMovie(new Movie("003", "The Big Sick", "Michael Showalter", "COMEDIA", "2017"));
		modelMovie.addMovie(new Movie("004", "Crazy Rich Asians", "Jon M. Chu", "COMEDIA", "2018"));

		//Suspense
		modelMovie.addMovie(new Movie("005", "The Da Vinci Code", "Ron Howard", "SUSPENSE", "2006"));
		modelMovie.addMovie(new Movie("006", "Miami Vice", "Michael Mann", "SUSPENSE", "2006"));
		modelMovie.addMovie(new Movie("007", "The Prestige", "Christopher Nolan", "SUSPENSE", "2006"));
		modelMovie.addMovie(new Movie("008", "Push", "Paul McGuigan", "SUSPENSE", "2009"));
		modelMovie.addMovie(new Movie("009", "Stoker", "Park Chan-wook", "SUSPENSE", "2013"));
		modelMovie.addMovie(new Movie("010", "Neon Demon", "Nicolas Winding Refn", "SUSPENSE", "2016"));

		//Ficcao
		modelMovie.addMovie(new Movie("011", "District 9", "Neill Blomkamp", "FICCAO", "2009"));
		modelMovie.addMovie(new Movie("012", "Arrival", "Denis Villeneuve", "FICCAO", "2016"));
		modelMovie.addMovie(new Movie("013", "Avatar", "James Cameron", "FICCAO", "2009"));
		modelMovie.addMovie(new Movie("014", "Inception", "Christopher Nolan", "FICCAO", "2010"));
		modelMovie.addMovie(new Movie("015", "Edge of Tomorrow", "Doug Liman", "FICCAO", "2014"));
		modelMovie.addMovie(new Movie("016", "Blade Runner 2049", "Denis Villeneuve", "FICCAO", "2017"));

		//Acao
		modelMovie.addMovie(new Movie("017", "Avengers", "Joss Whedon", "ACAO", "2012"));
		modelMovie.addMovie(new Movie("018", "Mad Max: Fury Road", "George Miller", "ACAO", "2015"));
		modelMovie.addMovie(new Movie("019", "John Wick", "David Leitch", "ACAO", "2014"));
		modelMovie.addMovie(new Movie("020", "300", "Zack Snyder", "ACAO", "2006"));
		modelMovie.addMovie(new Movie("021", "Kick Ass", "Matthew Vaughn", "ACAO", "2010"));

		//Animacao
		modelMovie.addMovie(new Movie("022", "Moana", "Ron Clements", "ANIMACAO", "2016"));
		modelMovie.addMovie(new Movie("023", "Inside Out", "Pete Docter", "ANIMACAO", "2015"));
		modelMovie.addMovie(new Movie("024", "Big Hero 6", "Don Hall", "ANIMACAO", "2014"));
		modelMovie.addMovie(new Movie("024", "Coraline", "Henry Selick", "ANIMACAO", "2009"));
		modelMovie.addMovie(new Movie("026", "Shrek", "Cicky Jenson", "ANIMACAO", "2001"));

		//Aventura
		modelMovie.addMovie(new Movie("027", "Hunger Games", "Gary Ross", "AVENTURA", "2012"));
		modelMovie.addMovie(new Movie("028", "The Hobbit", "Peter Jackson", "AVENTURA", "2012"));
		modelMovie.addMovie(new Movie("029", "King Kong", "Peter Jackson", "AVENTURA", "2005"));
		modelMovie.addMovie(new Movie("030", "Sherlock Holmes", "Guy Ritchie", "AVENTURA", "2010"));
		modelMovie.addMovie(new Movie("031", "Star Trek", "J.J. Abrams", "AVENTURA", "2009"));

		//Cult
		modelMovie.addMovie(new Movie("031", "Pulp Fiction", "Quentin Tarantino", "CULT", "1994"));
		modelMovie.addMovie(new Movie("032", "Fight Club", "David Fincher", "CULT", "1999"));
		modelMovie.addMovie(new Movie("033", "Clockwork Orange", "Stanley Kubrick", "CULT", "1972"));
		modelMovie.addMovie(new Movie("034", "Scarface", "Brian De Palma", "CULT", "1983"));
		modelMovie.addMovie(new Movie("035", "Showgirls", "Paul Verhoven", "CULT", "1995"));

		//Documentario
		modelMovie.addMovie(new Movie("036", "Hot Girls Wanted", "Ronna Gradus", "DOCUMENTARIO", "2015"));
		modelMovie.addMovie(new Movie("037", "Blackfish", "Gabriela Cowperthwaite", "DOCUMENTARIO", "2013"));
		modelMovie.addMovie(new Movie("038", "Icaro", "Bryan Fogel", "DOCUMENTARIO", "2017"));
		modelMovie.addMovie(new Movie("039", "Man on Wire", "James Marsh", "DOCUMENTARIO", "2008"));
		modelMovie.addMovie(new Movie("040", "Jiro Dreams of Sushi", "David Gelb", "DOCUMENTARIO", "2011"));

		//Thrash                                            
		modelMovie.addMovie(new Movie("041", "Machete", "Robert Rodriguez", "TRASH", "2010"));
		modelMovie.addMovie(new Movie("042", "Plan 9 of Sidereal Space", "Ed Wood", "TRASH", "1959"));
		modelMovie.addMovie(new Movie("043", "Drag me to Hell", "Sam Raimi", "TRASH", "2009"));
		modelMovie.addMovie(new Movie("044", "Evil Dead 2", "Sam Raimi", "TRASH", "1987"));
		modelMovie.addMovie(new Movie("045", "Sharknado", "David Michael Latt", "TRASH", "2013"));

		//Romance
		modelMovie.addMovie(new Movie("046", "It Happened One Night", "James Wan", "ROMANCE", "1934"));
		modelMovie.addMovie(new Movie("047", "Casablanca", "Frank Capra", "ROMANCE", "1942"));
		modelMovie.addMovie(new Movie("048", "Carol", "Todd Haynes", "ROMANCE", "2015"));
		modelMovie.addMovie(new Movie("049", "Singing in the Rain", "Gene Kelly", "ROMANCE", "1952"));
		modelMovie.addMovie(new Movie("050", "Her", "Spike Jonze", "ROMANCE", "2013"));

		//Fantasia                  
		modelMovie.addMovie(new Movie("051", "The Neverending Story", "Wolfgang Petersen", "FANTASIA", "1984"));
		modelMovie.addMovie(new Movie("052", "Time Bandits", "Terry Gilliam", "FANTASIA", "1981"));
		modelMovie.addMovie(new Movie("053", "Lost Horizon", "James Hilton", "FANTASIA", "1937"));
		modelMovie.addMovie(new Movie("054", "Pan's Labyrinth", "Guillermo del Toro", "FANTASIA", "2006"));
		modelMovie.addMovie(new Movie("055", "Orlando", "Sally Potter", "FANTASIA", "1992"));

		//Drama             
		modelMovie.addMovie(new Movie("056", "12 Years a Slave", "Steve McQueen", "DRAMA", "2013"));
		modelMovie.addMovie(new Movie("057", "Citizen Kane", "Orson Welles", "DRAMA", "1941"));
		modelMovie.addMovie(new Movie("058", "All About Eve", "Joseph L. Mankiewicz", "DRAMA", "1950"));
		modelMovie.addMovie(new Movie("059", "Metropolis", "Fritz Lang", "DRAMA", "1927"));
		modelMovie.addMovie(new Movie("060", "Moonlight", "Barry Jenkins", "DRAMA", "2016"));
    }
}
