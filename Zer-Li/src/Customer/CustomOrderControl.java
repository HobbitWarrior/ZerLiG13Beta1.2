package Customer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomOrderControl 
{
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/Customer/CustomOrderFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Customer Custom Order"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
