package qwerty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogServlet
 */
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String a=request.getParameter("log");
		String b=request.getParameter("pswd");
		 
		HttpSession session=request.getSession();
		if(a.equals("")||b.equals(""))
		{
			
			session.setAttribute("msg","no null");
			getServletContext().getRequestDispatcher("/log.jsp").include(request, response);
		}
		else
		{
			try{
				Connection con=MyConnect.connect();
				PreparedStatement ps=con.prepareStatement("select * from loginn where emailid=? and password=?");
				ps.setString(1,a);
				ps.setString(2,b);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					session.setAttribute("msg","logged successfull");
					session.setAttribute("email",a);
					getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
				}
				else
				{
					session.setAttribute("msg","try again");
					getServletContext().getRequestDispatcher("/log.jsp").include(request, response);
				}
				
			}catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		
	}

}
