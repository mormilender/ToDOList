package il.ac.hit.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import il.ac.hit.model.HibernateToDoListDAO;
import il.ac.hit.model.User;
import il.ac.hit.model.UserDAOException;

/** This Class receives User requests coming from the client ,handles them and sends responds back.
 * Has static variable named dao that is in charge of the connection with the model that handles the database
 **/
public class UserController {
	
	private static HibernateToDoListDAO dao;


	/**logIn - handles request to find and verify the user with the database **/
	public void logIn(HttpServletRequest request, HttpServletResponse response)
	{
		
		PrintWriter out = null;
		String msg=request.getParameter("username")+" doesn't exist!"; //default response message
		Map<String, String> options = new LinkedHashMap<>();
		List<User> users;
		try {
				out = response.getWriter();
				dao = HibernateToDoListDAO.getHibernateToDoListDAO();
				users = dao.getUsers(); //get all users from database
				
				//getting parameters from the received request
				String pass=request.getParameter("password");
				String username=request.getParameter("username");
			
				for(int i=0;i<users.size();i++)
				{
					if(users.get(i).getName().equals(username) && users.get(i).getPassword().equals(pass)) //finds if user already exists
					{
						request.getSession().setAttribute("userId", users.get(i).getId()); //add user id to session attributes for next page
						msg="success"; 
						options.put("id",users.get(i).getId()+""); //put user id in JSON response
						break;
					}
				}
			
			} catch (UserDAOException | IOException e) {
				msg="Error with Login";
			}
		options.put("result", msg);  //put final response message in JSON 
		
		//converting response to JSON
	    String json = new Gson().toJson(options);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.write(json);
	}


	/**signUp- handles request to add new user to the database if no such user already exists**/
	public void signUp(HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter out = null;
		String msg="Error"; //default response message
		Map<String, String> options = new LinkedHashMap<>();
		List <User> result;
		try {
			out = response.getWriter();
			dao = HibernateToDoListDAO.getHibernateToDoListDAO();
			result = dao.getUsers();//get all users from database
			boolean exists=false;
			for(int i=0;i<result.size();i++)
			{
				if(result.get(i).getName().equals(request.getParameter("username"))) //finds if the user already exists
				{
					exists=true;
					msg=request.getParameter("username")+" already exists!"; 
					break;

				}
			}
			if(!exists)//if such user doesn't exist
			{
				//getting parameters from the received request
				String pass=request.getParameter("password");
				String username=request.getParameter("username");
				
				User user=new User(username,pass); 
				dao.addUser(user); //add new user to database
				request.getSession().setAttribute("userId", user.getId());  //add user id to session attributes for next page
				msg="success";
				options.put("id",user.getId()+"");//put user id in JSON response
				
			}
		} catch (UserDAOException | IOException e1) {
			 msg="Error with sign up!";
		} 
		
		options.put("result", msg);//put final response message in JSON 
		
		//converting response to JSON
	    String json = new Gson().toJson(options);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.write(json);


	}


}
