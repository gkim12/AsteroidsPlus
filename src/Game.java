/**
 * Jake Nickel, Artur Gatin, George Kim, Period 2, Group 9, 5/6/18
 * Time taken: ~3 days
 * Reflection: This first installment of our Game project has been very useful, 
 * as it contained essentials of Git, as well as vital lessons on group
 * collaboration in a shared project. Resolving merge conflicts and coordinating
 * commits is great exposure to the real world of shared development, and learning
 * such skills early will, I'm sure, prove worthwhile. Overall, a great starting point
 * to the world of Bitbucket and Git, and I look forward to further development on this
 * project.
 */

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
	public HBox powerUpLabel;
	public HBox livesBox;
	private int lives = 3;

	private List<PowerUp> activePU;

	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * SCENE & STAGE Created
		 */
		stage.setTitle("Demo");
		stage.setResizable(false);
		// Group root = new Group();
		World rocketWorld = new RocketWorld(this);
		Rocket rocket = new Rocket();
		rocketWorld.setBackground(new Background(new BackgroundImage(new Image("images/space.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		/*
		 * Rocket is added
		 */
		rocket.setX(300);
		rocket.setY(300);
		rocket.setRotate(0);
		rocketWorld.add(rocket);
		//add a medium asteroid
		
		/*
		 * Asteroids are added
		 */
		MediumAsteroid ma = new MediumAsteroid();
		ma.setX(900);
		ma.setY(400);
		rocketWorld.add(ma);
		//add a small asteroid
		SmallAsteroid sa = new SmallAsteroid();
		sa.setX(700);
		sa.setY(200);
		rocketWorld.add(sa);
		
		/*
		 * PowerUps are added
		 */
		PowerUp powerUp1 = new FasterBulletsPU(this);
		powerUp1.setX(150);
		powerUp1.setY(150);
		PowerUp powerUp2 = new FasterAccelPU(this);
		powerUp2.setX(190);
		powerUp2.setY(155);
		rocketWorld.add(powerUp1);
		rocketWorld.add(powerUp2);
		
		
		/*
		 * Mouse and Key events added
		 */
		
		rocket.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				powerUpLabel.getChildren().remove(0);
			}

		});
		
		rocketWorld.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
			}

		});
		rocketWorld.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				rocketWorld.addKey(event.getCode());
			}

		});
		rocketWorld.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				rocketWorld.removeKey(event.getCode());

			}

		});
		activePU = new ArrayList<>();

		BorderPane rocketPane = new BorderPane();
		rocketPane.setCenter(rocketWorld);
		HBox horizBar = new HBox();
		horizBar.setPrefHeight(25);
		horizBar.setPadding(new Insets(10));
		horizBar.setSpacing(50);
		horizBar.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, new CornerRadii(0), new Insets(0))));
		horizBar.setAlignment(Pos.CENTER);
		powerUpLabel = new HBox();
		powerUpLabel.setAlignment(Pos.CENTER);
		powerUpLabel.setSpacing(7);
		Text PULabelStarting = new Text("Active Powerups: ");
		PULabelStarting.setStyle("-fx-font-weight: bold");
		PULabelStarting.setFont(new Font(10));
		powerUpLabel.getChildren().add(PULabelStarting);
		powerUpLabel.setPrefWidth(45);
		
		livesBox = new HBox();
		livesBox.setAlignment(Pos.CENTER);
		Image rocketImage = new Image("images/rocket.png");
		for (int i = 0; i < 3; i++) {
			ImageView rocketView = new ImageView(rocketImage);
			rocketView.setScaleX(0.5);
			rocketView.setScaleY(0.5);
			livesBox.getChildren().add(rocketView);
		}
		
		
		
		//powerUpLabel.setAlignment();
		horizBar.getChildren().addAll(livesBox, powerUpLabel, new Text("'Asteroid' by Jake, Artur, George.   Designed in Cupertino, California"));
		rocketPane.setBottom(horizBar);
		Scene scene = new Scene(rocketPane, 1070, 710);
		stage.setScene(scene);
		rocketWorld.start();

		stage.show();
		
		

		rocketWorld.requestFocus();
	}

	public List<PowerUp> getActivePU() {
		return activePU;
	}
	
	public void setLives(int i) {
		lives = i;
	}
	
	public int getLives() {
		return lives;
	}
	
	
}
