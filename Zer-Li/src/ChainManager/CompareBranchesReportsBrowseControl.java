package ChainManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CompareBranchesReportsBrowseControl 
{
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/ChainManager/CompareBranceshReportsFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Compare Between Two Branches Of The Chain"); // name of the title of the window
		primaryStage.setScene(scene);
	  	primaryStage.show();
	}
}
