import java.sql.*;  
import java.io.*; 
import java.util.*;



//#############################        Pateint ########################

class patient{
void profile(String email)throws Exception{
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","user123");  
  
PreparedStatement ps=con.prepareStatement("select * from patient where email=?");  
  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 


Scanner scan=new Scanner(System.in);
 
  
//System.out.println("enter email:");  
//email=br.readLine();  
  
ps.setString(1,email);

  
ResultSet rs=ps.executeQuery(); 
if (rs.next() == false) {
      System.out.println("empty");
    }



 
while(rs.next())  
System.out.println(rs.getString("email")+"  "+rs.getString("name")+"  "+rs.getString("dob")+"  "+rs.getString("address")+"  "+rs.getInt("phone")+"  "+rs.getString("medrec"));  
con.close();
}
} 










//########################### Doctor ##############################



class doctor{
void medrec_wr(String username,String date)throws IOException 
    { 
	Scanner scan=new Scanner(System.in);
          
	
        String filename=username.concat(date)+".txt";
          
        FileWriter fw=new FileWriter(filename); 
  
       String str="";
	int count=0;  
	while(count==0){
        str = scan.nextLine();
	if(str.compareTo("END")==0){
	count=1;}
        for (int i = 0; i < str.length(); i++) 
            fw.write(str.charAt(i)); 
}
  
        System.out.println("Writing successful"); 
          
        fw.close();
}
}












//#######                    LOGIN                  ######################################################################



public class login {
public static void main(String args[])throws Exception{

Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","user123");  
  
PreparedStatement ps=con.prepareStatement("select * from login where email=? and passwd=? and type=?");  
  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
String email;
String passwd;
int type;
Scanner scan=new Scanner(System.in);
System.out.println("1:patient,2:doctor");
type=scan.nextInt(); 
  
System.out.println("enter email:");  
email=br.readLine();  
System.out.println("enter passwd:");  
passwd=br.readLine(); 
System.out.println("enter type:");  
type=Integer.parseInt(br.readLine());  
ps.setString(1,email);
ps.setString(2,passwd);
ps.setInt(3,type);
  
ResultSet rs=ps.executeQuery(); 
if (rs.next() == false) {
      System.out.println("Wrong entry");
    }



 
while(rs.next())  
System.out.println(rs.getString("email")+"  "+rs.getString("passwd")+"  "+rs.getInt("type"));  
con.close(); 
 
if(type==1){
patient p=new patient();

int i=scan.nextInt();
if(i==1){
p.profile(email);}
}
else if(type==2){
doctor d=new doctor();
System.out.println("enter username and date to write the medicla record of patient");
String username=scan.nextLine();
String date=scan.nextLine();
d.medrec_wr(username,date);

}
}
}


