package ChainManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BranchReportBrowseControl 
{
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/ChainManager/BranchBrowseReportFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Browse Chain Branch"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
