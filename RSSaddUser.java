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
 * Servlet implementation class RSSaddUser
 */
public class RSSaddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String topic=request.getParameter("val");
		HttpSession session=request.getSession();
		
		String email=(String)session.getAttribute("email");
		
		Connection con=MyConnect.connect();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into MYRSS values(?,?)");
			ps.setString(1,topic);
			ps.setString(2,email);
			int l=ps.executeUpdate();
			if(l==1)
			{
				session.setAttribute("msg", "RSS Added successfully");
				getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
			}
			else
			{
				session.setAttribute("msg", "recheck; RSS Addition failed");
				getServletContext().getRequestDispatcher("/profile.jsp").include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
