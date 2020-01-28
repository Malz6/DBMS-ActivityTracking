import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sreg")
public class sreg extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
	
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String usn=request.getParameter("usn");  
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String sex=request.getParameter("sex");
		String id=request.getParameter("id");
		String email=request.getParameter("email");
		String pswd=request.getParameter("psw");  
		String num = request.getParameter("num");
		//BigInteger num= new BigInteger(n);
		int sem=Integer.parseInt(request.getParameter("sem"));
		       
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/activity","root","");  
			
			PreparedStatement sl=con.prepareStatement("insert into slogin values(?,?)");
			
			sl.setString(1,usn);  
			sl.setString(2,pswd);  
			
			PreparedStatement st =con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?)");
			
			st.setString(1,usn);
			st.setString(2,fname);
			st.setString(3,lname);
			st.setString(5,sex);
			st.setInt(4,sem);
			st.setString(6,id);
			st.setString(7,num);
			st.setString(8,email);
			
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