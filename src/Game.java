import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
	
	World rocketWorld;
	Rocket rocket;
	
	public HBox powerUpLabel;
	public HBox livesBox;
	public HBox scoreBox;
	private int highestScore;
	

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
		rocketWorld = new RocketWorld(this);
		rocket = new Rocket();
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
		PowerUp powerUp1 = new FasterBulletsPU();
		powerUp1.setX(150);
		powerUp1.setY(150);
		PowerUp powerUp2 = new FasterAccelPU();
		powerUp2.setX(190);
		powerUp2.setY(155);
		PowerUp powerUp3 = new ShieldPU();
		powerUp3.setX(230);
		powerUp3.setY(160);
		rocketWorld.add(powerUp1);
		rocketWorld.add(powerUp2);
		rocketWorld.add(powerUp3);
			
		
		/*
		 * Mouse and Key events added
		 */
		
		powerUp1.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Image i = Rocket.SHIELD_IMG;
				powerUp1.setImage(i);
			}

		});
		
		rocket.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				//powerUpLabel.getChildren().remove(0);
//				System.out.println();
//				
//				System.out.println(rocket.getClass().getName());
//				System.out.println("Lives: " + rocket.getLives());
//				System.out.println("Fire_d: " + rocket.FIRE_DELAY/10e8);
//				System.out.println("Accel: " + rocket.ROCKET_ACCEL);
//				System.out.println("k: " + rocketWorld.PTS_coef);
//				
//				System.out.println();
			
			}

		});
		
		rocket.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				rocketWorld.printPressedKeys();
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
		
		/*
		 * UI Settings
		 */

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
		livesBox.getChildren().add(new ImageView(new Image("images/rocket.png")));
		livesBox.getChildren().add(new Text());
		livesBox.setAlignment(Pos.CENTER);
		
		scoreBox = new HBox();
		Text scoreText = new Text("Score: ");
		scoreText.setStyle("-fx-font-weight: bold");
		Text scoreNum = new Text("" + rocketWorld.getScore());
		scoreNum.setStyle("-fx-font-style: italic");
		
		scoreBox.getChildren().addAll(scoreText, scoreNum);
		scoreBox.setAlignment(Pos.CENTER);
		
		//powerUpLabel.setAlignment();
		horizBar.getChildren().addAll(scoreBox, livesBox, powerUpLabel, new Text("'Asteroid' by Jake, Artur, George.   Designed in Cupertino, California"));
		rocketPane.setBottom(horizBar);
		Scene scene = new Scene(rocketPane, 1070, 710);
		stage.setScene(scene);
		rocketWorld.start();
		rocket.changeLives(3);

		stage.show();
		
		

		rocketWorld.requestFocus();
	}

	public List<PowerUp> getActivePU() {
		return activePU;
	}
	
	public void removeActivePUs() {
		for(int i = 0; i < activePU.size(); i++) {
			activePU.remove(0);
		}
	}
	
	public int getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}

	public <A extends PowerUp> boolean containsPUOfClass(java.lang.Class c) {
		for(PowerUp p: activePU) if(p.getClass().equals(c)) return true;
		return false;
	}
	
	public <A extends PowerUp> PowerUp getPUOfClass(java.lang.Class c) {
		for(PowerUp p: activePU) if(p.getClass().equals(c)) return p;
		return null;
	}
	
	public void resetGame() {
		rocket.resetPos();
		rocket.changeLives(3);
		rocketWorld.setPaused(false);
		rocketWorld.setScore(0);
		rocketWorld.removePressedKeys();
		
	}
	

	
	
}
