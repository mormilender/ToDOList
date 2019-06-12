package il.ac.hit.model;

import java.util.List;
/** 	This is an interface that includes the following methods: addItem ,deleteItem ,getItems ,update ,addUser ,getUser ,getUsers  **/
public interface IToDoListDAO {
	
		public void addItem(Item ob) throws ItemDAOException;
	    public void deleteItem(Item ob) throws ItemDAOException;
	    public List<Item> getItems(int user_id) throws ItemDAOException;
	    public boolean update(Item ob) throws ItemDAOException;
	    public void addUser(User ob) throws UserDAOException;
	    public User getUser(User ob) throws UserDAOException;
	    public List<User> getUsers() throws UserDAOException;

}
