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

/**
 * Servlet implementation class Upda
 */
public class Upda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upda() {
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
		String a=request.getParameter("fname");
		String b=request.getParameter("lname");
		String c=request.getParameter("phone");
		String d=request.getParameter("dob");
		String e=request.getParameter("g");
		String f=request.getParameter("addr");
		String g=request.getParameter("country");
		String h=request.getParameter("log");
	
		
		HttpSession session=request.getSession();
		if(a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals("")||f.equals("")||g.equals("")||h.equals(""))
		{
			
			session.setAttribute("msg", "null values not encouraged");
			getServletContext().getRequestDispatcher("/upda.jsp").include(request, response);

		}
		else
		{
			try{
				Connection con=MyConnect.connect();
				PreparedStatement ps=con.prepareStatement("update regg set(first,last,phone,dob,gen,addr,country)=(?,?,?,?,?,?,?) where emailid=?");
				ps.setString(1,a);
				ps.setString(2,b);
				ps.setString(3,c);
				ps.setString(4,d);
				ps.setString(5,e);
				ps.setString(6,f);
				ps.setString(7,g);
				ps.setString(8,h);
				
				
				int l=ps.executeUpdate();
				System.out.println(l);
				
				if(l==1 )
				{
					session.setAttribute("msg", "update successfull");
					getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
				}
				else
				{
					session.setAttribute("msg", "recheck; update fail");
					getServletContext().getRequestDispatcher("/upda.jsp").include(request, response);
				}
				
				
				
				
			}catch(Exception z)
			{
				System.out.println(z);
			}
			
		}
	}

}
