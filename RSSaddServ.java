package qwerty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RSSaddServ
 */
public class RSSaddServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String a=request.getParameter("tp");
		String b=request.getParameter("desc");
		
		
		HttpSession session=request.getSession();
		
		Connection con=MyConnect.connect();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into RSSFEEDS (topic,desc) values(?,?)");
			ps.setString(1,a);
			ps.setString(2,b);
			int l=ps.executeUpdate();
			if(l==1)
			{
				session.setAttribute("msg", "New RSS Added successfully");
				getServletContext().getRequestDispatcher("/adp.jsp").forward(request, response);
			}
			else
			{
				session.setAttribute("msg", "recheck; RSS Addition failed");
				getServletContext().getRequestDispatcher("/adp.jsp").include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
	}
