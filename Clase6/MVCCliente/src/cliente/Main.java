package cliente;
	
import cliente.view.Login;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Login login = new Login();
		login.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
