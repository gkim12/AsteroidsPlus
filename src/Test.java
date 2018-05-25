

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Test extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		System.exit(0);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Test");
		stage.setResizable(false);
		 
		Group root = new Group();
		
		ImageView i = new ImageView();
		i.setImage(new Image("images/ball.png"));
		
		i.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				i.setImage(new Image("images/fireBall.png"));
			}

		});
		
		root.getChildren().add(i);
		Scene scene = new Scene(root, 1070, 710);
		stage.setScene(scene);


		stage.show();
	}

}
