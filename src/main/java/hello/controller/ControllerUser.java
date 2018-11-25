package hello.controller;

import static spark.Spark.*;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;
import hello.model.ModelUser;
import hello.classes.Movie;
import hello.classes.User;
import org.apache.http.NameValuePair;
import org.apache.http.client.URLEncodedUtils;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerUser {
	private ModelUser modelUser;
	
	public ControllerUser(ModelUser modelUser){
		this.modelUser = modelUser;
	}
	
	/*public void getMovie(){
		get("/movie/:genre", (req, res) -> {
			moviesSorted.clear();
			List<Movie> moviesFound = modelMovie.searchGenre(req.params(":genre"));		
			Collections.shuffle(moviesFound);
			for (int i = 0; i <= 2; i++) {
				moviesSorted.add(moviesFound.get(i));
			}
			return new Gson().toJson(moviesSorted);			
		});
	}*/
	
	public static void postUser() {
		post("/movie/insertElement", (req, res) -> {
	        List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), String.valueOf(Charset.defaultCharset()));
	        Map<String, String> params = toMap(pairs);

	        try {
	        	System.out.println(params);
	        	System.out.println(req);
	        	System.out.println(res);

	        	String username = String.valueOf(params.get("username"));
	        	String email = String.valueOf(params.get("email"));
	        	String password = String.valueOf(params.get("password"));
	        	System.out.println(username + "\n" + email + "\n" + password);
	        	return username;
	        }
	        catch (Exception e){
	            return "Error: " + e.getMessage();
	        }
	    });
	}
	
	private static Map<String, String> toMap(List<NameValuePair> pairs){
        Map<String, String> map = new HashMap<>();
        for(int i=0; i<pairs.size(); i++){
            NameValuePair pair = pairs.get(i);
            map.put(pair.getName(), pair.getValue());
        }
        return map;
    }
}
