package il.ac.hit.model;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

public class TestToDoList {

	@Test
	public void test() {
		SessionFactory factory = HibernateToDoListDAO.getSessionFactory();

		
	
		HibernateToDoListDAO dao = HibernateToDoListDAO.getHibernateToDoListDAO();

		try {
			Item p1 = new Item("shop",false,"12:00",100);
			Item p2 = new Item("run",false,"17:30",100);
			Item p3 = new Item("sleep",false,"14:00",100);
			
			Item p4 = new Item("workout",false,"13:00",200);
			Item p5 = new Item("make project",false,"17:30",200);
			Item p6 = new Item("do homework",false,"14:00",200);
			
			Item p7 = new Item("shop",false,"14:00",200);
			Item p8 = new Item("run",true,"19:30",200);
			Item p9 = new Item("watch tv",true,"16:00",200);

			dao.addItem(p1);
			dao.addItem(p2);
			p2.setDeadLine("20:00");
			dao.update(p2);
			dao.addItem(p3);
			dao.addItem(p4);
			dao.addItem(p5);
			dao.addItem(p6);
			dao.addItem(p7);
			dao.addItem(p8);
			dao.addItem(p9);
			dao.deleteItem(p1);
			dao.deleteItem(p5);
			dao.deleteItem(p6);
			List<Item> items=dao.getItems(100);
			List<Item> items2=dao.getItems(200);
			for(int i=0;i<items.size();i++)
				System.out.println(items.get(i));
			for(int i=0;i<items2.size();i++)
				System.out.println(items2.get(i));


		} catch (ItemDAOException e) {
			e.printStackTrace();
			System.out.println("ItemDAOException error");
			
		}


	}


}


