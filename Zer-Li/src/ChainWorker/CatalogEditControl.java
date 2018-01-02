package ChainWorker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CatalogEditControl 
{
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/ChainWorker/CatalogEditFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Catalog Managment"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
