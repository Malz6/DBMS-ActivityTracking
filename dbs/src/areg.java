import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/areg")
public class areg extends HttpServlet
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
	
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String id=request.getParameter("id");  
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String sex=request.getParameter("sex");
		String email=request.getParameter("email");
		String pswd=request.getParameter("psw");  
		String num = request.getParameter("num");
		String des = request.getParameter("des");
		
		       
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/activity","root","");  
			
			PreparedStatement sl=con.prepareStatement("insert into alogin values(?,?)");
			
			sl.setString(1,id);  
			sl.setString(2,pswd);  
			
			PreparedStatement st=con.prepareStatement("insert into admin values(?,?,?,?,?,?,?)");
			
			st.setString(1,id);
			st.setString(2,fname);
			st.setString(3,lname);
			st.setString(4,sex);
			st.setString(5,des);
			st.setString(6,num);
			st.setString(7,email);
			
			int j=st.executeUpdate();
			int i=sl.executeUpdate();
			
			if(i>0 && j>0)  
				out.print("You are successfully registered...");  
			   
		       
		}
		catch (Exception e2)
		{
		System.out.println(e2);
	    }  
	
	}

}