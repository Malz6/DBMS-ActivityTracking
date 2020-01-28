import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class view extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
	
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String usn=request.getParameter("usn");  
		
		try
		{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/activity","root","");  
		
		
			ResultSet rs;
			PreparedStatement s = con.prepareStatement("select * from participate where usn=? ");
			s.setString(1, usn);
			rs = s.executeQuery();
			
			String htmlRespone = "";
		    htmlRespone += "<!DOCTYPE html><html><head><style>body \r\n" + 
		    		"		{\r\n" + 
		    		"			font-family: Arial, Helvetica, sans-serif;\r\n" + 
		    		"			background-image: url('4.jpg');\r\n" + 
		    		"		  	background-repeat: no-repeat;\r\n" + 
		    		"		 	background-attachment: fixed;\r\n" + 
		    		"		 	background-size: cover;\r\n" + 
		    		"		 	color: green;\r\n" + 
		    		"		} h1 {color: black;text-align: center;}</style></head><body><h1>Events The Student Has Taken Part In :</h1>";
		    htmlRespone += "<p>USN : " + usn + "<br/><br/>";
		    while(rs.next())
		    {
		    	 String name = rs.getString(2);
		    	 String cert = rs.getString(4);
		    	 String date = rs.getString(3);
		    	 
		         htmlRespone += "Activity Name : " + name + "<br/>";      
		         htmlRespone += "Certificate : " + cert +"<br/>";
		         htmlRespone += " Date : " + date +"<br/><br/>";		
		    }
		    htmlRespone += "</p></body></html>";
		    
		    out.println(htmlRespone);
			
		}
		catch (Exception e2)
		{
		System.out.println(e2);
		}  
		
	}

}