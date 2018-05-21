import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Lives extends HBox {
	private Image rocket = new Image("file:src/images/rocket.png");
	private int lives = 3;
	
	public Lives() {
		super.setLayoutX(0);
		super.setLayoutY(600);
	}
	
	public void updateLives() {
		for (int i = 0; i < getChildren().size(); i++) {
			getChildren().remove(getChildren().size() - 1 - i);
		}
		for (int i = 0; i < lives; i++) {
			ImageView rocketView = new ImageView(rocket);
			rocketView.setScaleX(0.5);
			rocketView.setScaleY(0.5);
			getChildren().add(rocketView);
		}
	}
	
	public void setLives(int i) {
		lives = i;
	}
	
	public int getLives() {
		return lives;
	}
}
