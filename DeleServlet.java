package qwerty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleServlet
 */
public class DeleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			String g=request.getParameter("f");
			
			System.out.println(g);
			
			try{
				
				
				Connection con=MyConnect.connect();
				PreparedStatement ps=con.prepareStatement("delete from regg where country=?");
				ps.setString(1,g);
				int i=ps.executeUpdate();
				if(i==1)
				{
					getServletContext().getRequestDispatcher("/delete.jsp").include(request, response);
				}
				else
				{
					getServletContext().getRequestDispatcher("/delete.jsp").include(request, response);
				}
				
				
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
			
	}

}
