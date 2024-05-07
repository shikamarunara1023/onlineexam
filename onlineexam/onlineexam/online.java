//String rollno1=request.getParameter("rollno");
//String name1=request.getParameter("name");
import java.io.*; 
import java.sql.*; 
import javax.servlet.*;

import javax.servlet.http.*;
public class online extends HttpServlet
{
String message,rollno,name,ans1,ans2; int Total=0;
Connection connect; 
public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
{
response.setContentType("text/html"); 
PrintWriter out=response.getWriter(); 
try
{
String url="jdbc:mysql://localhost:8081/exam"; 
Class.forName("com.mysql.jdbc.Driver"); 
connect=DriverManager.getConnection(url,"root",""); 
message="Thank you for participating in online Exam";
}
catch(ClassNotFoundException cnfex){ cnfex.printStackTrace();
}
catch(SQLException sqlex){ sqlex.printStackTrace();
}
catch(Exception excp){ excp.printStackTrace();
}
rollno=request.getParameter("rollno");
name=request.getParameter("name"); 
ans1=request.getParameter("group1"); 
ans2=request.getParameter("group2"); 
if(ans1.equals("Lee"))
Total+=10;
if(ans2.equals("Client Scripting"))
Total+=10;
try
{
Statement stmt=connect.createStatement();
String query="INSERT INTO stu("+"rollno,Name,mark"+") VALUES('"+rollno+"','"+name+"','"+Total+"')";
int result=stmt.executeUpdate(query); 
stmt.close();
}
catch(SQLException ex){
}

out.println("<html>"); out.println("<head>"); 
out.println("</head>"); out.println("<body bgcolor=cyan>"); 
out.println("<center>"); out.println("<h1>"+message+"</h1>\n");
out.println("<h3>Yours results stored in our database</h3>"); 
out.print("<br><br>");
out.println("<b>"+"Participants and their Marks"+"</b>"); 
out.println("<br>"+"Rollno  Name   Mark"+"<br>");

try
{
Statement stmt1=connect.createStatement(); 
String query1="SELECT * FROM stu"; 
ResultSet rs=stmt1.executeQuery(query1); 
while(rs.next())
{
out.println(rs.getString(1)+"   "+rs.getString(2)+"     "+rs.getInt(3)+"<br>");
}
rs.close();
stmt1.close();
connect.close();
}
catch(SQLException ex){ } 
out.println("</center>");
out.println("</body></html>");
Total=0;
} }
