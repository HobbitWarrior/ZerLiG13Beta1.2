package Users;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ImageIcon;

import Expert.SurveyAnalayzingControl;
import ServerDB.EchoServer;
import ServerDB.ServerGuiApp;
import client.ChatClient;
import common.ChatIF;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ocsf.client.AbstractClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class LoginContol  
{
	
	public static Vector<User> AllUsers=new Vector<User>();


	private ChatClient myClient;
	
	@FXML
    private Label TitleLabel;

    @FXML
    private Button ConnectBtn;

    @FXML
    private Label UserNameLabel;

   
    private ImageView ImageZerli;

    @FXML
    private Label Password;
    
    @FXML
    private TextField txtUserName;
    
    @FXML
    private PasswordField txtPassword;

    
    
   @FXML
    void ConnectToSystemEvent(ActionEvent event) 
    {
	   int port=5555;
	   String ip="localhost";
	   try 
	   {
		myClient = new ChatClient(ip,port);	//create new client to get all users in db (server)
	   } 
	   catch (IOException e) 
	   {
		   System.out.println("Cannot create client");	  
	   }
	   
	   myClient.sendRequestToGetAllUsers(); //send request to get all users from db (server)
	 
	   while( myClient.isConnected());	//wait until client (this class) get all users from the db!
	   String userPermition="";
	   String UserNameToCheck = txtUserName.getText();		//get username from textfield
	   String PasswordToCheck = txtPassword.getText();		//get password from passwordfield (kind of textfield)
	   int i=0;
	   
	   
	   for(i=0 ; i< AllUsers.size() ; i++)				//scan all user to check the details we just got from human
	   {
		   User person= AllUsers.get(i);
		   if(person.getUserName().equals(UserNameToCheck))		//if you found the userName in the list (vector) of users
		   {
			   

				   if(person.getPassword().equals(PasswordToCheck)) //then check if his password correct
				   {
					   //password here is correct
					   if(person.getStatus()==false)	//check if the system manager blocked this account
					   {
						   //error message on borbidden account
						   Alert BorbbidenAccountAlert = new Alert(AlertType.WARNING);
						   BorbbidenAccountAlert.setTitle("Account forbidden");
						   BorbbidenAccountAlert.setHeaderText("Your account has been blocked");
						   BorbbidenAccountAlert.setContentText("Please contact the system manager!");
					   BorbbidenAccountAlert.showAndWait();
					   }
					   else	
					   {
						   userPermition = person.getPermition(); 	//all details approved
						  
					   }
					   break;
				   }
			   
				   else
				   {
					   //password here is incorrect
					   Alert WrongPassword = new Alert(AlertType.ERROR);
					   WrongPassword.setTitle("Wrong password");
					   WrongPassword.setHeaderText("You inserted a wrong password!");
					   WrongPassword.setContentText("Please, try again.");
					   WrongPassword.showAndWait();
					   break;

				   }
				  
			 }
	   }
	   
	   if( i== AllUsers.size())
	   {
		   //userName here is incorrect
		   Alert WrongPassword = new Alert(AlertType.ERROR);
		   WrongPassword.setTitle("Wrong UserName");
		   WrongPassword.setHeaderText("You inserted a invalid username!");
		   WrongPassword.setContentText("Please, try again.");
		   WrongPassword.showAndWait();
	   }
	   
	   
	   myClient=null;		//safe step
	   AllUsers.clear(); 		//delete all users that we got in order to get updated list of users in the next pressing on the button
	   
	   //from here we will open new window according to the permition
	   
	   if(! userPermition.equals(""))
	   {
		   ((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
	   }
	   Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root=null;
	   
	   if(userPermition.equals("expert"))
	   {
			try 
			{
				root = loader.load(getClass().getResource("/expert/SurveyAnalayzeFrame.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open Expert Window");;
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Insert Survey Conclustions"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
	   }
	   
	   
	   
	   else if(userPermition.equals("systemmanager"))
	   {
			try 
			{
				root = loader.load(getClass().getResource("/SystemManager/ManagementFrame.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open SystemManager Window");;
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("System Managment"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
		   
	   }
	   
	   
	   else if(userPermition.equals("branchworker"))
	   {
			try 
			{
				root = loader.load(getClass().getResource("/BranchWorker/surveyResultFrame.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open SystemManager Window");;
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Insert new Survey Result"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
	   }
	   
	   
	   else if(userPermition.equals("branchmanager"))
	   {

			try 
			{
				root = loader.load(getClass().getResource("/BranchManager/BranchManagerMainFrame.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open BranchManager Window");
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Branch Manager Main Menu"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
	   }
	   
	   
	   else if(userPermition.equals("chainworker"))
	   {
			try 
			{
				root = loader.load(getClass().getResource("/ChainWorker/CatalogEditFrame.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open BranchManager Window");;
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Catalog Managment"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
	   }
	   
	   else if(userPermition.equals("customer"))
	   {
			try 
			{
				root = loader.load(getClass().getResource("/Customer/CustomerMainWindow.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open BranchManager Window");;
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Customer Main Menu"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
	   }
	   
	   else if(userPermition.equals("customerservicedepartmentemployee"))
	   {
		   try 
			{
				root = loader.load(getClass().getResource("/CustomerServiceDepartmentworker/CustomerServiceDepartmentworkerMainWindow.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open BranchManager Window");;
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Customer Service Department Worker Main Menu"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
	   }
	   
	   
	   else if(userPermition.equals("chainmanager"))
	   {
		   try 
			{
				root = loader.load(getClass().getResource("/ChainManager/ChainManagerMainWindow.fxml").openStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Cannot Open BranchManager Window");;
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Chain Manager Main Menu"); // name of the title of the window
			primaryStage.setScene(scene);		
			primaryStage.show();
	   }
	   
	}
   
    
 
   
   
   
	public void start(Stage primaryStage) throws IOException  
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/Users/LoginWindow.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Login To The System"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}

	 
}
