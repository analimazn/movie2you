package hello;

import static spark.Spark.*;


public class MainServer {
	
	final static Model model = new Model();
	
    public static void main(String[] args) {

		// Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 8080;
        }
        port(port);

		//Servir conteudo html, css e javascript
		staticFileLocation("/static");

		inicializarFilmes();

		Controller controller = new Controller(model);
		
		controller.buscarFilme();
		controller.buscarFilmeNome();
				
    }
    
    public static void inicializarFilmes(){
    	//Comedia
    	model.addFilme(new Filme("Crazy, Stupid, Love", "Glenn Ficarra", "Comedia", "2011"));
    	model.addFilme(new Filme("Easy A", "Will Gluck", "Comedia", "2010"));
    	model.addFilme(new Filme("Mr. Right", "Paco Cabezas", "Comedia", "2015"));
    	model.addFilme(new Filme("The Big Sick", "Michael Showalter", "Comedia", "2017"));
    	model.addFilme(new Filme("Crazy Rich Asians", "Jon M. Chu", "Comedia", "2018"));


    	//Suspense
    	model.addFilme(new Filme("The Da Vinci Code", "Ron Howard", "Suspense", "2006"));
    	model.addFilme(new Filme("Miami Vice", "Michael Mann", "Suspense", "2006"));
    	model.addFilme(new Filme("The Prestige", "Christopher Nolan", "Suspense", "2006"));
    	model.addFilme(new Filme("Push", "Paul McGuigan", "Suspense", "2009"));
    	model.addFilme(new Filme("Stoker", "Park Chan-wook", "Suspense", "2013"));
    	model.addFilme(new Filme("Neon Demon", "Nicolas Winding Refn", "Suspense", "2016"));


    	//Ficcao
    	model.addFilme(new Filme("District 9", "Neill Blomkamp", "Ficcao", "2009"));
    	model.addFilme(new Filme("Arrival", "Denis Villeneuve", "Ficcao", "2016"));
    	model.addFilme(new Filme("Avatar", "James Cameron", "Ficcao", "2009"));
    	model.addFilme(new Filme("Inception", "Christopher Nolan", "Ficcao", "2010"));
    	model.addFilme(new Filme("Edge of Tomorrow", "Doug Liman", "Ficcao", "2014"));
    	model.addFilme(new Filme("Blade Runner 2049", "Denis Villeneuve", "Ficcao", "2017"));

    	//Acao

    	model.addFilme(new Filme("Avengers", "Joss Whedon", "Acao", "2012"));
    	model.addFilme(new Filme("Mad Max: Fury Road", "George Miller", "Acao", "2015"));
    	model.addFilme(new Filme("John Wick", "David Leitch", "Acao", "2014"));
    	model.addFilme(new Filme("300", "Zack Snyder", "Acao", "2006"));
    	model.addFilme(new Filme("Kick Ass", "Matthew Vaughn", "Acao", "2010"));

    	//Animacao
    	model.addFilme(new Filme("Moana", "Ron Clements", "Animacao", "2016"));
    	model.addFilme(new Filme("Inside Out", "Pete Docter", "Animacao", "2015"));
    	model.addFilme(new Filme("Big Hero 6", "Don Hall", "Animacao", "2014"));
    	model.addFilme(new Filme("Coraline", "Henry Selick", "Animacao", "2009"));
    	model.addFilme(new Filme("Shrek", "Cicky Jenson", "Animacao", "2001"));

    	//Aventura
    	model.addFilme(new Filme("Hunger Games", "Gary Ross", "Aventura", "2012"));
    	model.addFilme(new Filme("The Hobbit", "Peter Jackson", "Aventura", "2012"));
    	model.addFilme(new Filme("King Kong", "Peter Jackson", "Aventura", "2005"));
    	model.addFilme(new Filme("Sherlock Holmes", "Guy Ritchie", "Aventura", "2010"));
    	model.addFilme(new Filme("Star Trek", "J.J. Abrams", "Aventura", "2009"));

    	//Cult
    	model.addFilme(new Filme("Pulp Fiction", "Quentin Tarantino", "Cult", "1994"));
    	model.addFilme(new Filme("Fight Club", "David Fincher", "Cult", "1999"));
    	model.addFilme(new Filme("Clockwork Orange", "Stanley Kubrick", "Cult", "1972"));
    	model.addFilme(new Filme("Scarface", "Brian De Palma", "Cult", "1983"));
    	model.addFilme(new Filme("Showgirls", "Paul Verhoven", "Cult", "1995"));

    	//Documentario
    	model.addFilme(new Filme("Hot Girls Wanted", "Ronna Gradus", "Documentario", "2015"));
    	model.addFilme(new Filme("Blackfish", "Gabriela Cowperthwaite", "Documentario", "2013"));
    	model.addFilme(new Filme("Icaro", "Bryan Fogel", "Documentario", "2017"));
    	model.addFilme(new Filme("Man on Wire", "James Marsh", "Documentario", "2008"));
    	model.addFilme(new Filme("Jiro Dreams of Sushi", "David Gelb", "Documentario", "2011"));

    	//Thrash											
    	model.addFilme(new Filme("Machete", "Robert Rodriguez", "Trash", "2010"));
    	model.addFilme(new Filme("Plan 9 of Sidereal Space", "Ed Wood", "Trash", "1959"));
    	model.addFilme(new Filme("Drag me to Hell", "Sam Raimi", "Trash", "2009"));
    	model.addFilme(new Filme("Evil Dead 2", "Sam Raimi", "Trash", "1987"));
    	model.addFilme(new Filme("Sharknado", "David Michael Latt", "Trash", "2013"));

    	//Romance
    	model.addFilme(new Filme("It Happened One Night", "James Wan", "Romance", "1934"));
    	model.addFilme(new Filme("Casablanca", "Frank Capra", "Romance", "1942"));
    	model.addFilme(new Filme("Carol", "Todd Haynes", "Romance", "2015"));
    	model.addFilme(new Filme("Singing in the Rain", "Gene Kelly", "Romance", "1952"));
    	model.addFilme(new Filme("Her", "Spike Jonze", "Romance", "2013"));


    	//Fantasia					
    	model.addFilme(new Filme("The Neverending Story", "Wolfgang Petersen", "Fantasia", "1984"));
    	model.addFilme(new Filme("Time Bandits", "Terry Gilliam", "Fantasia", "1981"));
    	model.addFilme(new Filme("Lost Horizon", "James Hilton", "Fantasia", "1937"));
    	model.addFilme(new Filme("Pan�s Labyrinth", "Guillermo del Toro", "Fantasia", "2006"));
    	model.addFilme(new Filme("Orlando", "Sally Potter", "Fantasia", "1992"));


    	//Drama				
    	model.addFilme(new Filme("12 Years a Slave", "Steve McQueen", "Drama", "2013"));
    	model.addFilme(new Filme("Citizen Kane", "Orson Welles", "Drama", "1941"));
    	model.addFilme(new Filme("All About Eve", "Joseph L. Mankiewicz", "Drama", "1950"));
    	model.addFilme(new Filme("Metropolis", "Fritz Lang", "Drama", "1927"));
    	model.addFilme(new Filme("Moonlight", "Barry Jenkins", "Drama", "2016"));
    }
}
