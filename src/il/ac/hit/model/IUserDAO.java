package il.ac.hit.model;
/** 	This is an interface that includes the following methods: addUser ,getUser ,getUsers **/
public interface IUserDAO {


	public void addUser(User ob) throws UserDAOException;
    public User getUser(User ob) throws UserDAOException;
    public User[] getUsers() throws UserDAOException;


}
