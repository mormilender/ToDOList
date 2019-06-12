package il.ac.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import il.ac.hit.model.HibernateToDoListDAO;
import il.ac.hit.model.Item;
import il.ac.hit.model.ItemDAOException;
import il.ac.hit.model.User;
import il.ac.hit.model.UserDAOException;

/** This Class receives Item requests coming from the client ,handles them and sends responds back in JSON format.
 * Has static variable named dao that is in charge of the connection with the model that handles the database

 **/

public class ItemController {
	
	private static HibernateToDoListDAO dao;
	
	/**addItem - handles request to add new item to the database if no such item already exists**/
	public void addItem(HttpServletRequest request, HttpServletResponse response) 
	{
		PrintWriter out = null;
		String msg="Error"; //default response message
		Map<String, String> options = new LinkedHashMap<>();
		  
		Item item=null;
		List <Item> result;
		try {
			out = response.getWriter();
			dao = HibernateToDoListDAO.getHibernateToDoListDAO();
			
			//getting parameters from the received request
			int userId=Integer.parseInt(request.getParameter("userId"));
			String value=request.getParameter("value");
			String deadLine=request.getParameter("deadLine");
			
			result = dao.getItems(userId); //get all items of the user from database
			boolean exist=false;
			
			if(result!=null)
			{
				for(int i=0;i<result.size();i++)
				{
					if(result.get(i).getValue().equals(value) && result.get(i).getDeadLine().equals(deadLine)) //finds if the item already exists
					{
						exist=true;
						break;
					}
				}
			}
			
			if(!exist)//if the item doesn't exist we build the item and add it to the database
			{
				item=new Item(value,false,deadLine,userId);
				dao.addItem(item); //add new item to database
				options.put("id",item.getId()+""); //put new item id in JSON response
				msg="success";
			}
			else msg="item already exists";
		
		} catch (ItemDAOException | IOException e1) {
			msg="couldn't add the item: "+request.getParameter("value");
		}
		options.put("result", msg);//put final response message in JSON 
		
		//converting response to JSON
	    String json = new Gson().toJson(options);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.write(json);
	}
	
	/**deleteItem - handles request to delete item from the database if such item exists**/
	public void deleteItem(HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter out = null;
		String msg ="No such item exists";  //default response message
		Map<String, String> options = new LinkedHashMap<>();
		List<Item> result;
		try {
			out = response.getWriter();
			dao = HibernateToDoListDAO.getHibernateToDoListDAO();
			
			//getting parameters from the received request
			int userId=Integer.parseInt(request.getParameter("userId"));
			int id=Integer.parseInt(request.getParameter("id"));
			
			result = dao.getItems(userId);  //get all items of the user from database
			
			for(int i=0;i<result.size();i++)
			{
				
				if(result.get(i).getId()==id) //finds the item in the database
				{
					dao.deleteItem(result.get(i)); //delete item from the database
					msg="success";
					break;
				}
			}
		
			
		} catch (ItemDAOException | IOException e1) {
			
			msg ="couldn't delete the item: "+request.getParameter("id");
		}
		options.put("result", msg);//put final response message in JSON 
		
		//converting response to JSON
	    String json = new Gson().toJson(options);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.write(json);
	}
	

		
	/**getItems - handles request to get the list of items for the requesting user from the database**/
	public void getItems(HttpServletRequest request, HttpServletResponse response) 
	{
		List <Item> result;
		PrintWriter out = null;
		Map<String, String> options = new LinkedHashMap<>();
		String msg="Error"; //default response message
		try {
			out = response.getWriter();
			dao = HibernateToDoListDAO.getHibernateToDoListDAO();
			
			//getting parameters from the received request
			int userId=Integer.parseInt(request.getParameter("userId"));
			
			result = dao.getItems(userId);  //get all items of the user from database
			request.getSession().setAttribute("items",result); //add items list of the user to session attributes for next page
			msg="success";
			
		} catch (ItemDAOException | IOException e1) {
			 msg="couldn't get the items!";
		}
		options.put("result", msg);//put final response message in JSON 
		
		//converting response to JSON
	    String json = new Gson().toJson(options);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.write(json);
	}
	
	/**update - handles request to update existing item in the database if such item already exists**/
	public void update(HttpServletRequest request, HttpServletResponse response) 
	{
		PrintWriter out = null;
		List <Item> result;
		Map<String, String> options = new LinkedHashMap<>();
		String msg="Error"; //default response message
		Item temp;
		try {
			out = response.getWriter();
			dao = HibernateToDoListDAO.getHibernateToDoListDAO();
			
			//getting parameters from the received request
			int userId=Integer.parseInt(request.getParameter("userId"));
			String value=request.getParameter("value");
			boolean done=Boolean.parseBoolean(request.getParameter("done"));
			String deadLine=request.getParameter("deadLine");
			int id=Integer.parseInt(request.getParameter("id"));
			
			result = dao.getItems(userId);  //get all items of the user from database
			boolean exist=false;
			
			if(result!=null)
			{
				for(int i=0;i<result.size();i++)
				{
					if(result.get(i).getValue().equals(value) && result.get(i).getDeadLine().equals(deadLine) && result.get(i).getId()!=id) //finds if similar item already exists
					{
						exist=true;
						break;
					}
					
				}
			}
			
			if(!exist)//if similar item doesn't exist we create the updated item and replace with it the old one 
			{
				temp=new Item(value,done,deadLine,userId,id);
				dao.update(temp); //updates the item in the database
				options.put("id",temp.getId()+""); //put updated item id in JSON response
				msg="success";
			}
			else msg="item already exists";
		
		} catch (ItemDAOException | IOException e1){
			msg="couldn't update the item: "+request.getParameter("value");
		}
		options.put("result", msg);//put final response message in JSON 
		
		//converting response to JSON
	    String json = new Gson().toJson(options);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.write(json);
	}
	
}
