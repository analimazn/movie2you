package hello.classes;

public class Movie {

	private String name;	
	private String director;
	private String genre;
	private String year;
		
	
	public Movie(String name, String director, String genre, String year){
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.year = year;		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean compare(String genre, String director, String year){
		if(genre.equals(this.genre) && director.equals(this.director) && year.equals(this.year)){
			return true;
		} else {
			return false;
		}
	}
	
	public String getMovie() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name).append("\n");
		sb.append(this.director).append("\n");
		sb.append(this.genre).append("\n");
		sb.append(this.year).append("\n");
		return sb.toString();
	}
	
}
