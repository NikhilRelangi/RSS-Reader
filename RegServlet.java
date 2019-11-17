package qwerty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegServlet() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a=request.getParameter("fname");
		String b=request.getParameter("lname");
		String c=request.getParameter("phone");
		String d=request.getParameter("dob");
		String e=request.getParameter("g");
		String f=request.getParameter("addr");
		String g=request.getParameter("country");
		String h=request.getParameter("log");
		String i=request.getParameter("pswd");
		String j=request.getParameter("pwd");
		
		HttpSession session=request.getSession();
		if(a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals("")||f.equals("")||g.equals("")||h.equals("")||i.equals("")||j.equals(""))
		{
			
			session.setAttribute("msg", "null values not encouraged");
			getServletContext().getRequestDispatcher("/reg.jsp").include(request, response);

		}
		else if(i.equals(j))
		{
			try{
				Connection con=MyConnect.connect();
				PreparedStatement ps=con.prepareStatement("insert into regg values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1,a);
				ps.setString(2,b);
				ps.setString(3,c);
				ps.setString(4,d);
				ps.setString(5,e);
				ps.setString(6,f);
				ps.setString(7,g);
				ps.setString(8,h);
				ps.setString(9,i);
				
				int l=ps.executeUpdate();
				System.out.println(l);
				PreparedStatement ps1=con.prepareStatement("insert into loginn values(?,?)");
				ps1.setString(1,h );
				ps1.setString(2,i);
				int y=ps1.executeUpdate();
				System.out.println(y);
				if(l==1 && y==1)
				{
					session.setAttribute("msg", "register successfull");
					getServletContext().getRequestDispatcher("/log.jsp").forward(request, response);
				}
				else
				{
					session.setAttribute("msg", "recheck; register fail");
					getServletContext().getRequestDispatcher("/reg.jsp").include(request, response);
				}
				
				
				
				
			}catch(Exception z)
			{
				System.out.println(z);
			}
			
		}else
		{
			session.setAttribute("msg", "confirm password not match");
			getServletContext().getRequestDispatcher("/reg.jsp").include(request, response);
		}
		
		
		
		
		
		
		
		
	}

}
