package il.ac.hit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.google.gson.Gson;

import il.ac.hit.model.HibernateToDoListDAO;
import il.ac.hit.model.User;

import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;

/**
 * Servlet implementation class Router
 * directs the incoming requests to the requested Controller
 */
@WebServlet("/Router/*")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String pkg = "il.ac.hit.controller";   
	/**
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Router() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//processing the request into controller and action names
		String str = request.getPathInfo();
		String vec[] = str.split("/");
		String controller = vec[1];
		String action = vec[2];
	
		
		
		//composing the controller class name
		String controllerClassName = controller.substring(0,1).toUpperCase()+controller.substring(1)+"Controller";
		try {
			Class clazz = Class.forName(pkg+"."+controllerClassName);
			Object object = clazz.newInstance();
			Method method = clazz.getMethod(action,HttpServletRequest.class, HttpServletResponse.class);
			//directing the request to the method of the requested action
			method.invoke(object,request,response);
	
	
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			//in case of error in the router returns error response message
			PrintWriter out = response.getWriter();
			Map<String, String> options = new LinkedHashMap<>();
			options.put("result", "Error in Router");//put response message in JSON 
			
			//converting response to JSON
		    String json = new Gson().toJson(options);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    out.write(json);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
