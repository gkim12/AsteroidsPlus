import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class SmallAsteroid extends Asteroid{

	public SmallAsteroid() {
		super();
		setStage(5);
		setImage(new Image("images/asteroid.png"));
		setxVelocity(0);
		setyVelocity(0);
	}

	@Override
	public void act(long now) {

	}

	@Override
	public void updateImage() {
		// TODO Auto-generated method stub
		if(getStage() < 3) setImage(new Image("images/asteroidCracked.png"));
		if(getStage() <= 0) getWorld().remove(this);
	}

}
