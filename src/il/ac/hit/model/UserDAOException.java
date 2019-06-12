package il.ac.hit.model;

/** User related exception, thrown in HibernateToDoListDAO and user set validation errors **/

public class UserDAOException extends Exception{
	
	
	/** empty contractor **/
	public UserDAOException() {
		super();
	}

	/** exception shows massage and cause **/
	public UserDAOException(String message, Throwable cause) {
		super(message, cause);
	}
	/** exception shows the massage **/
	public UserDAOException(String message) {
		super(message);
	}

	
}
