package hello;

public class Filme {

	private String nome;	
	private String diretor;
	private String genero;
	private String ano;
		
	
	public Filme(String nome, String diretor, String genero, String ano){
		this.nome = nome;
		this.diretor = diretor;
		this.genero = genero;
		this.ano = ano;		
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getDiretor(){
		return diretor; 
	}
	
	public String getGenero(){
		return genero;
	}
	
	public String getAno(){
		return ano;
	}
	

	public boolean comparar(String genero, String diretor, String ano){
		if(genero.equals(this.genero) && diretor.equals(this.diretor) && ano.equals(this.ano)){
			return true;
		} else {
			return false;
		}
	}
	
}
