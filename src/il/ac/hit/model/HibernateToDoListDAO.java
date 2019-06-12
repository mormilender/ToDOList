package il.ac.hit.model;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * class representing model in our project and in charge of managing the database
 * */
public class HibernateToDoListDAO implements IToDoListDAO {
	
	 private static SessionFactory factory;
	 private static HibernateToDoListDAO doListDAO=null;
	 
	 //empty constructor
	 private HibernateToDoListDAO() {
			
		}
	 //Singleton implantation for HibernateToDoListDAO to get static class member of HibernateToDoListDAO
	 public static HibernateToDoListDAO getHibernateToDoListDAO()
		{
			if(doListDAO==null)
			{
				doListDAO = new HibernateToDoListDAO();
			}
			return doListDAO;
		}

	
	//Singleton implantation for HibernateToDoListDAO to get static class member of SessionFactory
	 public static synchronized SessionFactory getSessionFactory() {

	        if (factory == null) {
	        	factory = new AnnotationConfiguration().configure().buildSessionFactory();
	        }
	        return factory;
	    }

	 /** method to add a single to do list item **/
	@Override
	public void addItem(Item ob) throws ItemDAOException {
		Transaction tx=null;
		
        Session session = HibernateToDoListDAO.getSessionFactory().openSession();
        try {
        		 tx = session.beginTransaction();
                 session.save(ob);
                 session.getTransaction().commit();	
        }
        catch(HibernateException e){
            if (tx != null) tx.rollback();
            throw new ItemDAOException("Can't add item",e);
        }
        finally {
        	try {
            session.close();
            }
        	 catch(HibernateException e){
        
             }
        }
		
	}

	 /**method to delete a to do list item**/
	@Override
	public void deleteItem(Item ob) throws ItemDAOException {
		Transaction tx=null;
		  Session session = HibernateToDoListDAO.getSessionFactory().openSession();
        try {									//try to delete do item from table
            tx = session.beginTransaction();
            session.delete(ob);
            session.getTransaction().commit();
        }
       catch(HibernateException e){
            if (tx != null) tx.rollback();
           throw new ItemDAOException("Cant delete Item",e);
        }
        try {
            session.close();
            }
        	 catch(HibernateException e){
                
             }
		
	}

	 /**method to get list of all to do list items**/
	@Override
	public List<Item> getItems(int user_id) throws ItemDAOException {
			
		    List<Item> items ,result =new ArrayList<>();;
	        Transaction tx=null;
	        Item item=null;
	        Session session = HibernateToDoListDAO.getSessionFactory().openSession();
	        int k=0;
	        try {
	            tx=session.beginTransaction();
	            items = session.createQuery("from Item").list();//gets do item list from table 
	           if(items!=null) 
	           {
	            for (int i=0;i<items.size();i++) //gets only the do items of the user 
	             {
	            	 if(items.get(i).getUserId()==user_id)
	            	 {
	            		 
	            		 result.add(new Item(items.get(i).getValue(),items.get(i).getDone(),items.get(i).getDeadLine(),items.get(i).getUserId(),items.get(i).getId()));
	            	 }
	             }
	           }
	        }
	        catch(HibernateException e){
	            if (tx != null) tx.rollback();
	            throw new ItemDAOException("cant get Item",e);
	        }
	        try {
	            session.close();
	            }
	        	 catch(HibernateException e){
	                 
	             }
	        
	        return result;
	    }
	
	 /**method to update a to do list item**/
	@Override
	public boolean update(Item ob) throws ItemDAOException {
		List<Item> items ;
		items=getItems(ob.getUserId());
		for(int i=0;i<items.size();i++)
			if(ob.getId()==items.get(i).getId())//if already exist delete it and update with new one
			{
				deleteItem(items.get(i));
				addItem(ob);
				return true; 
			}
		return false;
	
	}
	
	 /**method to add user to our database (sign up)**/
	@Override
	public void addUser(User ob) throws UserDAOException {
		
		Transaction tx=null;
		  Session session = HibernateToDoListDAO.getSessionFactory().openSession();
        try {
        		 tx = session.beginTransaction();
                 session.save(ob);
                 session.getTransaction().commit();	
        }
        catch(HibernateException e){
            if (tx != null) tx.rollback();
            throw new UserDAOException("Can't add new user",e);
        }
        finally {
        	try {
            session.close();
            }
        	 catch(HibernateException e){
                 
             }
        }
	}
	 /**method to get single user**/
	@Override
	public User getUser(User ob) throws UserDAOException {
		List<User> users;
	
        try {
        	users= getUsers();
             for (int i=0;i<users.size();i++) //finds the given user from all users 
             {
            	 if(users.get(i).getName()==ob.getName() && users.get(i).getPassword()==ob.getPassword())
            	 {
            		 return users.get(i);
    		
            	 }
             }
        }
        catch(HibernateException e){
            throw new UserDAOException("cant get User",e);
        }
        
        return null;
	}
	 /**method to get list of all users**/
	@Override
	public List<User> getUsers() throws UserDAOException {
		List<User> users=null;
        Transaction tx=null;
        Session session = HibernateToDoListDAO.getSessionFactory().openSession();
        try {
            tx=session.beginTransaction();
            users = session.createQuery("from User").list();//gets users from table 
                
        }
        catch(HibernateException e){
            if (tx != null) tx.rollback();
            throw new UserDAOException("cant get User",e);
        }
        try {
            session.close();
            }
        	 catch(HibernateException e){
                
             }
        return users;
	}

}
