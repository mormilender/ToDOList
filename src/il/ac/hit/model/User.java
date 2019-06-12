package il.ac.hit.model;

/**
 * this class represents the users of this application 
 * in it we save the information about the user which is: id,password and name
 * with it we can verify the users, build a table for users, manage it and connect items to specific users
 **/

public class User {
	
	private int id;
	private String password;
	private String name;
	
	/**Constructor that receive: name,password and id **/
	public User(String name, String password, int id) throws UserDAOException {//constructor
		this(name, password);
		setId(id);
	}
	
	
	/**Constructor that receive: name and password **/
	public User(String name,String password) throws UserDAOException {
		setName(name);
		setPassword(password);
	}

	public User() {
		
	}

	/**returns user name **/
	public String getName() {
		return name;
	}

	/**set user name **/
	public void setName(String name)throws UserDAOException {
		if(name==" " || name.length()==0){// checking name validation
			throw new UserDAOException("Empty string: name");
		}
		else this.name = name;
	}
	/**get user password **/
	public String getPassword() {
		return password;
	}
	/**set user password **/
	public void setPassword(String password)throws UserDAOException {
		if(password==" " || password.length()==0){// checking password validation
			throw new UserDAOException("Empty string: password");
		}
		else this.password = password;
	}
	/**get user id **/
	public int getId() {
		return id;
	}
	/**set user id **/
	public void setId(int id) throws UserDAOException{
		String temp=id+"";
		try{
		    int checkedId = Integer.parseInt(temp);//if the exception was not thrown then id is int
		    this.id = id;
		}catch (NumberFormatException ex) {
			throw new UserDAOException("wrong input type: id");
		}
	}
}
