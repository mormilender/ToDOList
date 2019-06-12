package il.ac.hit.model;


/**
 * this class represent the items in the to do list
 * each item has an id ,value,boolean variable that represents if the to do item was done,the time by which the to do item has to be done
 * and the id of the user whose the item is part of his list
  **/
public class Item {

	private int id;
	private String value;
	private boolean done;
	private String deadLine;
	private int userId;
	
	
	/**Constructor **/
	public Item(String value, boolean done,String deadLine ,int userId) throws ItemDAOException {
		setValue(value);
		setDone(done);
		setUserId(userId);
		setDeadLine(deadLine);	
		}
	
	
	/** Full Constructor that uses the second constructor **/
	public Item(String value, boolean done,String deadLine,int userId, int id) throws ItemDAOException {
		this(value,done,deadLine,userId);
		setId(id);
	}
	
	
	/**Empty constructor **/
	public Item() {
		
	}
	/**get value of the Item **/
	public String getValue() {
		return value;
	}
	/** Set value for the Item and checking validation **/
	public void setValue(String value)throws ItemDAOException {
		if(value==" " || value.length()==0){// checking value validation
			 throw new ItemDAOException("Empty string: value");
		}
		else this.value = value;
	}
	/**get boolean parameter done(if the task was completed )of the Item **/
	public boolean getDone() {
		return done;
	}
	/**set boolean parameter done(if the task was completed )of the Item and checking validation **/
	public void setDone(boolean done)throws ItemDAOException {
	 if(done || !done)// done is type boolean
			this.done = done;
	 else throw new ItemDAOException("wrong input type: done");
	}
	
	/**get Id of the Item **/
	public int getId() {
		return id;
	}
	/** Set Id for the Item and checking validation **/
	public void setId(int id)throws ItemDAOException {
		String temp=id+"";
		try{
		    int checkedId = Integer.parseInt(temp);//if the exeption was not thrown then id is int
		    this.id = id;
		}catch (NumberFormatException ex) {
			throw new ItemDAOException("wrong input type: id");
		}
		
	}
	/**get the User Id of the Item **/
	public int getUserId() {
		return userId;
	}

	/** Set User Id for the Item and checking validation **/
	public void setUserId(int userId) throws ItemDAOException {
		String temp=userId+"";
		try{
		    int checkedId = Integer.parseInt(temp);//if the exeption was not thrown then id is int
		    this.userId = userId;
		}catch (NumberFormatException ex) {
			throw new ItemDAOException("wrong input type: userId");
		}
	}
	
	/**get dead Line of the Item **/
	public String getDeadLine() {
		return deadLine;
	}
	
	
	/** Set dead Line for the Item and checking validation **/
	public void setDeadLine(String deadLine) throws ItemDAOException {
		if(deadLine==" " || deadLine.length()==0){// checking deadLine validation
			throw new ItemDAOException("Empty string: deadLine");
		}
		else this.deadLine = deadLine;
	}
	
	/** Override to toString**/
	@Override
	public String toString() {
		return "Item [id=" + id + ", value=" + value + ", done=" + done + ", deadLine=" + deadLine + ", userId="
				+ userId + "]";
	}
	/** Override to hashCode**/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadLine == null) ? 0 : deadLine.hashCode());
		result = prime * result + (done ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + userId;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/** Override to equals**/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (deadLine == null) {
			if (other.deadLine != null)
				return false;
		} else if (!deadLine.equals(other.deadLine))
			return false;
		if (done != other.done)
			return false;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}	
}
