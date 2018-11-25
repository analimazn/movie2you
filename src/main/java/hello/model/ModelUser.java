package hello.model;


import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import hello.classes.User;

public class ModelUser {
	
	ObjectContainer dbUser = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/user.db4o");
	
	public boolean addUser(User user){
		if(isUserAvailable(user.getEmail())){
			dbUser.store(user);
			dbUser.commit();
			return true;
		}
		return false;
	}
	
	public boolean isUserAvailable(String email){
		Query query = dbUser.query();
		query.constrain(User.class);
	    ObjectSet<User> allUSers = query.execute();
	    for(User user: allUSers){
	    	if(user.getEmail().equals(email)) 
	    		return false;
	    }
	    return true;
	}
	
	public boolean validateLogin(String email, String password){
		Query query = dbUser.query();
		query.constrain(User.class);
	    ObjectSet<User> allUSers = query.execute();
	    for(User user: allUSers){
	    	if(user.getEmail().equals(email) && user.getPassword().equals(password)) 
	    		return true;
	    }
	    return false;
	}
	
	public List<User> getUsers(){	
		Query query = dbUser.query();
		query.constrain(User.class);
		List<User> listUsers = query.execute();
		return listUsers;
	}
}

