package il.ac.hit.model;

/** class representing the exception related to HibernateToDoListDAO and gets thrown when an error occurs in it **/

public class ItemDAOException extends Exception {

	/** Exception constructor with message **/
	public ItemDAOException(String message) {
        super(message);
    }
	/**Exception constructor with message and cause**/
    public ItemDAOException(String message, Throwable cause) {
        super(message, cause);

    }
    /** Empty exception constructor **/
    public ItemDAOException(){
    	super();
    } 

}
