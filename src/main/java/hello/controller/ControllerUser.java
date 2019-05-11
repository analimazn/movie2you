package hello.controller;

import static spark.Spark.*;

import java.nio.charset.Charset;
import java.util.List;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.URLEncodedUtils;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hello.model.ModelMovie;
import hello.model.ModelUser;
import hello.classes.User;

public class ControllerUser {
	private ModelUser modelUser;
	
	public ControllerUser(ModelUser modelUser){
		this.modelUser = modelUser;
	}
	
	public void postUser() {
		post("/movie/insertElement", (req, res) -> {
	        List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), String.valueOf(Charset.defaultCharset()));
	        Map<String, String> params = toMap(pairs);

	        try {
	        	String username = String.valueOf(params.get("username"));
	        	String email = String.valueOf(params.get("email"));
	        	String password = String.valueOf(params.get("password"));
	        	System.out.println(username + "\n" + email + "\n" + password);
	        	
	        	if (username.isEmpty() && email.isEmpty() && password.isEmpty()) {
	        		return "Por favor, entre com valores válidos";
	        	} else if (!modelUser.addUser(new User(username, email, password))) {
	        		return "E-mail já cadastrado";
	        	}
	        	return "Cadastrado com sucesso!";
	        }
	        catch (Exception e){
	            return "Your request fail: " + e.getMessage();
	        }
	    });
	}
	
	public void postLogin() {
		post("/movie/insertLogin", (req, res) -> {
	        List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), String.valueOf(Charset.defaultCharset()));
	        Map<String, String> params = toMap(pairs);

	        try {
	        	String email = String.valueOf(params.get("email"));
	        	String password = String.valueOf(params.get("password"));
	        	System.out.println(email + "\n" + password);
	        	
	        	if (email.isEmpty() && password.isEmpty()) {
	        		return "Por favor, entre com valores válidos";
	        	}  else if(modelUser.isUserAvailable(email)) {
	        		return "E-mail não encontrado";
	        	} else if (!modelUser.validateLogin(email, password)) {
	        		return "E-mail e/ou senha incorretos";
	        	}
	        	return true;
	        }
	        catch (Exception e){
	            return "Your request fail: " + e.getMessage();
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
	
	/*public static void postUser() {
		post("/movie/insertElement", (req, res) -> {
			 Map<String, String> map = JsonUtil.parse(req.body());
             return "Hello " + map.get("username") + "!";
		});
	}
	
    public static class JsonUtil {
        public final static Map<String, String> parse(String object) {
            return new Gson().fromJson(object, Map.class);
        }
    }*/
}
	
