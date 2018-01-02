package ServerDB;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import ServerDB.Product;
import Users.User;
import client.Message;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ocsf.server.*;



public class EchoServer extends AbstractServer implements Initializable
{
  //Class attributes *************************************************
	
  private String UserName;
  private String Password;
  private String DataBaseName;
  private Connection ServerDataBase;
  private boolean DB_ACCOUNT;
  
  //Class attributes *************************************************

  
  //Constructors ****************************************************
  
  public EchoServer(int port,  String UserName, String Password, String DataBaseName) 
  {
    super(port);
    ServerDataBase= connectToDB(UserName, Password, DataBaseName);
  }

  //Constructors ****************************************************

 
 
  public void handleMessageFromClient  (Object msg, ConnectionToClient client)
  {
	  
	  
	  if( msg instanceof String )
		 {
		  if ( ((String)msg).equals("Give Me All Users") )
		  {
			  System.out.println("Get all Users from DB");
			  

			  ArrayList<User> UsersFromDB=new ArrayList<User>();
			  try 
			  {
				  UsersFromDB=PutOutAllUsers(UsersFromDB);
				  
				  Message Msg= new Message(UsersFromDB, "User");
				    this.sendToAllClients(Msg);

				  return;
			  } 
			  catch (SQLException e) 
			  {
				  System.out.println("error-can't get data from db");
				  this.sendToAllClients("GetFail");
			  }
		  }
		 }
	  
	  	PreparedStatement ps = null;
	    System.out.println("Message received: " + msg + " from " + client);

	    try {
	    	ps=	parsingTheData( ServerDataBase,(ArrayList<String>) msg) ;
	    	saveUserToDB(ps);
		}
	    catch (Exception e)
	    {
			e.printStackTrace();
		}
	  
	    this.sendToAllClients("SUCSESS");
  }
	    
  
    //this method get all information from the DB and sent it to the comboBox of the clientGUI
  private ArrayList<User> PutOutAllUsers(ArrayList<User> UsersFromDB) throws SQLException 
  {
	  
	  Statement st = (Statement) ServerDataBase.createStatement();

	  ResultSet rs = st.executeQuery( "select * from Users ");
	  
	  while (rs.next())
	  {
		 int ID=rs.getInt(1);
		 String UserName=""+rs.getString(2);
		 String Password=""+rs.getString(3);
		 String Permition=""+rs.getString(4);
		 int Status=rs.getInt(5);

		 
		  
	    User UsersReturnToClient= new User(ID,UserName,Password,Permition,Status);
	    UsersFromDB.add(UsersReturnToClient);
	    
	  }
	  rs.close();
	  st.close();
	 
				
	    return UsersFromDB;
	  
  }



  protected void serverStarted()
  {
    System.out.println ("Server listening for connections on port " + getPort());    
  }
  
  
  
  protected void serverStopped()
  {
    System.out.println ("Server has stopped listening for connections.");
  }
  
  
  public synchronized PreparedStatement parsingTheData(Connection dbh,ArrayList<String> List) 
  {
	  PreparedStatement ps=null;
	    try
	    {
    		ps = dbh.prepareStatement(" UPDATE Product SET ProductID=? WHERE ProductName=?;");
            ps.setString(1,List.get(1));
            ps.setString(2,List.get(0));
            
            ps.executeUpdate();
         
	    	ps = dbh.prepareStatement(" UPDATE Product SET ProductName=? WHERE ProductName=?;");
	        ps.setString(1,List.get(2));
	        ps.setString(2,List.get(0));
	        
	        ps.executeUpdate();
	              
	    	ps = dbh.prepareStatement(" UPDATE Product SET ProductType=? WHERE ProductName=?;");
	        ps.setString(1,List.get(3));
	        ps.setString(2,List.get(0));
	        
	        ps.executeUpdate();
	    		
	    }
	    
	    
	    catch(SQLException ex)
	    {
	    	System.out.print("Sorry we had  problem, could not save changes to Database\n");
	    	this.sendToAllClients("UpdateFail");
	    }
	    this.sendToAllClients("UpdateSuccess");
	    return ps;
  }
  
  protected Connection connectToDB(String UserName, String Password, String DataBaseName)
  {
	  Connection ServerDataBase=null;  
	    try		
	    {
	    	String DataBaseAdress="jdbc:mysql://localhost:3306/" + DataBaseName;
	    	ServerDataBase = DriverManager.getConnection( DataBaseAdress, UserName , Password );
	    	System.out.print("Server connected to Database sucessfully!\n");	
	    	this.DB_ACCOUNT=true;
	    	
	    }
	    catch(SQLException ex)
	    {
	    	System.out.print("Sorry we had a problem, could not connect to DB server\n");	
	    	this.sendToAllClients("DBConnectFail");
	    	this.DB_ACCOUNT=false;
	    }
	    return ServerDataBase;

  }
  
  protected void saveUserToDB(PreparedStatement ps)
  {
	    try
	    {
	    	ps.executeUpdate();
	    }
	    catch(SQLException ex)
	    {
	    	System.out.print("Sorry we had a problem, Could not save in Database\n");
	    	this.sendToAllClients("UpdateFail");
	    }
	    this.sendToAllClients("UpdateSucces");
  }
  
  //Class methods ***************************************************
  
  
   



@Override
public void initialize(URL location, ResourceBundle resources) 
{
	// TODO Auto-generated method stub
	
}
  
  public boolean getStatusDBLogin()
  {
	  return this.DB_ACCOUNT;
  }
  
  
}