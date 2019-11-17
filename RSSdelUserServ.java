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
 * Servlet implementation class RSSdelUserServ
 */
public class RSSdelUserServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic=request.getParameter("val");
		HttpSession session=request.getSession();
		
		String email=(String)session.getAttribute("email");
		
		Connection con=MyConnect.connect();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("delete from MyRSS where rssid='"+topic+"'");
			int l=ps.executeUpdate();
			if(l==1)
			{
				request.setAttribute("msg", "Deleted");
				getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Not Deleted");
				getServletContext().getRequestDispatcher("/profile.jsp").include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
