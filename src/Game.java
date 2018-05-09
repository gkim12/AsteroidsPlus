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
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {

	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Demo");
		stage.setResizable(false);
		// Group root = new Group();
		World ballWorld = new BallWorld();
		//ballWorld.
		Ball ball = new Ball();
		ball.setY(90);
		ballWorld.add(ball);
		double bricksX = 0;
		for (int i = 0; i < 10; i++) {
			Brick brickn = new Brick();
			brickn.setY(20);
			brickn.setX(bricksX);
			bricksX += brickn.getWidth();
			ballWorld.add(brickn);
		}

		Paddle paddle = new Paddle();
		ballWorld.add(paddle);
		paddle.setY(600);
		paddle.setX(500);
		ballWorld.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(arg0.getX() > 0 && arg0.getX() < ballWorld.getWidth())paddle.setX(arg0.getX() - 0.5 * paddle.getWidth());
			}

		});
		ballWorld.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				ballWorld.addKey(event.getCode());

			}

		});
		ballWorld.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				ballWorld.removeKey(event.getCode());

			}

		});

		BorderPane borderPane = new BorderPane();
		Rectangle leftRectangle = new Rectangle(70, 720);
		Rectangle rightRectangle = new Rectangle(70, 720);
		leftRectangle.setFill(Color.AQUAMARINE);
		rightRectangle.setFill(Color.AQUAMARINE);
		borderPane.setLeft(leftRectangle);
		borderPane.setRight(rightRectangle);

		borderPane.setCenter(ballWorld);
		Scene scene = new Scene(borderPane, 1080, 720);
		stage.setScene(scene);
		ballWorld.start();

		stage.show();

		ballWorld.requestFocus();
	}

}
