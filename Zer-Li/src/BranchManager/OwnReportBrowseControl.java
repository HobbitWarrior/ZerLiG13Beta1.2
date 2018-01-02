package BranchManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OwnReportBrowseControl 
{
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/BranchManager/SelfBrowseReportFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Browse Your Branch Report"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
