package Customer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrdersControl 
{

	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/Customer/OrdersFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Order Process"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
