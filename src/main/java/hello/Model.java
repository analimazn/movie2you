package hello;


import java.util.List;
import java.util.LinkedList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model {
	
	ObjectContainer dbFilme = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/filme.db4o");
	
	//Adicionar filme ao banco verificando pelo metodo isFilmeAvailable
	public boolean addFilme(Filme filme){
		if(isFilmeAvailable(filme.getNome())){
			dbFilme.store(filme);
			dbFilme.commit();
			return true;
		}
		return false;
	}
	
	//Verificar se filme ja existe no banco
	public boolean isFilmeAvailable(String nameFilm){
		Query query = dbFilme.query();
		query.constrain(Filme.class);
	    ObjectSet<Filme> allFilmes = query.execute();
	    for(Filme filmes:allFilmes){
	    	if(filmes.getNome().equals(nameFilm)) return false;
	    }
	    return true;
	}
	
	//Metodo que retorna os filmes com base no genero pesquisado
	public List<Filme> searchFilme(String genero, String diretor, String ano) {
		List<Filme> result = new LinkedList<Filme>();
		Query query = dbFilme.query();
		query.constrain(Filme.class);
	    ObjectSet<Filme> allFilmes = query.execute();

	    for(Filme filme:allFilmes){
	    	if(filme.getGenero().equals(genero) && 
	    		filme.getDiretor().equals(diretor) && 
	    		filme.getAno().equals(ano)) {
	    			result.add(filme);
	    	}
	    }
		return result;
	}
	
	//Retorna uma lista com todos os filmes encontrados
	public List<Filme> getFilmes(){	
		Query query = dbFilme.query();
		query.constrain(Filme.class);
		List<Filme> filmesEncontrados = query.execute();
		return filmesEncontrados;
	}
	
	public List<Filme> buscarGenero(String genero){			
		List<Filme> result = new LinkedList<Filme>();
		Query query = dbFilme.query();
		query.constrain(Filme.class);
	    ObjectSet<Filme> filmesEncontrados = query.execute();
		
	    for(Filme filme:filmesEncontrados){   	
	    	if(filme.getGenero().equals(genero)) result.add(filme);	    	
	    }	
	    return result;
	}
	
	public Filme buscarNome(String nome){
		Query query = dbFilme.query();
		query.constrain(Filme.class);
	    ObjectSet<Filme> filmesEncontrados = query.execute();
		for(Filme filme:filmesEncontrados){
			if(filme.getNome().equals(nome)) return filme;
		}		
		return null;
	}
	
}

