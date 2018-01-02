package Customer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CatalogOrderControl 
{
	
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/Customer/CatalogOrderrFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Catalog"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
