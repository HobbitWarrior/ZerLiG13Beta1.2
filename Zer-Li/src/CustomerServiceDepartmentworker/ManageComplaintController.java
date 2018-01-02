package CustomerServiceDepartmentworker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManageComplaintController 
{
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/CustomerServiceDepartmentworker/ManageComplaintFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Manage Open Complaint"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
