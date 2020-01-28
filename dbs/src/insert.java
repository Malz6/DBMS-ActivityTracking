import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class insert extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
	
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String usn=request.getParameter("usn");  
		String name=request.getParameter("acname");
		String date=request.getParameter("date");
		String loc=request.getParameter("loc");
		String cert=request.getParameter("certificate");
		String type=request.getParameter("type");
		String club=request.getParameter("club");  
		       
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/activity","root","");
			int flag=0;
			ResultSet rs;
			Statement s = con.createStatement();
			
			boolean i=false,j=false,k=false;
			rs = s.executeQuery("select acname from activities");
			while(rs.next())
			{
				if(name.equals(rs.getString(1)))
				{
					flag=1;
					break;
				}
				else
					flag=0;
			}
			if (flag==0)
			{
				PreparedStatement st =con.prepareStatement("insert into activities values(?,?)");
				
				st.setString(1,name);
				st.setString(2,loc);
				j=st.execute();
			}
			
			PreparedStatement sl=con.prepareStatement("insert into participate values(?,?,?,?)");
			
			sl.setString(1,usn);  
			sl.setString(2,name);
			sl.setString(3,date);
			sl.setString(4,cert);
			i=sl.execute();
			
			
			if(type.equals("tech"))
			{
				rs = s.executeQuery("select acname from technical");
				while(rs.next())
				{
					if(name.equals(rs.getString(1)))
					{
						flag=1;
						break;
					}
					else
						flag=0;
				}
				if (flag==0)
				{
				
					
				    		PreparedStatement sf =con.prepareStatement("insert into technical values(?,?)");
				    		sf.setString(1,name);
				        	sf.setString(2,club);
				        	
				        	k=sf.execute();
				}
			}	
			else if(type.equals("non"))
			{
				rs = s.executeQuery("select acname from nontechnical");
				while(rs.next())
				{
					if(name.equals(rs.getString(1)))
					{
						flag=1;
						break;
					}
					else
						flag=0;
				}
				if (flag==0)
				{
						PreparedStatement sf =con.prepareStatement("insert into nontechnical values(?,?)");
			    		sf.setString(1,name);
			        	sf.setString(2,club);
			        	
			        	k = sf.execute();
				}
			}
			
			if (i==true || j==true || k==true)
			{
				out.println("Not successfull");
			}
			else
			{
				out.println("insert successfull");
			}
			
		}
		catch (Exception e2)
		{
		System.out.println(e2);
		}  
		
	}

}