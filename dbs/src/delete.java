import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class delete extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
	
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String usn=request.getParameter("usn");
		String name = request.getParameter("name");
		
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/activity","root",""); 
			PreparedStatement s = con.prepareStatement("delete from participate where usn=? and acname=?");
			s.setString(1, usn);
			s.setString(2, name);
			boolean i  = s.execute();
			if (i == true)
				out.println("Record Not Deleted ");
			else
				out.println("Record Successfully Deleted ! ");
					
				
		}
		catch (Exception e2)
		{
			System.out.println(e2);
		}  
	
	}

}