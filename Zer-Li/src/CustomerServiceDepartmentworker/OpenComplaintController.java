package CustomerServiceDepartmentworker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenComplaintController 
{
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/CustomerServiceDepartmentworker/NewComplaintsFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Open Complaint Ticket"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
