import javafx.scene.image.Image;

public class SmallAsteroid extends Asteroid{

	public SmallAsteroid() {
		super();
		setHealth(5);
		setImage(new Image("images/fireball.png"));
		setxVelocity(0);
		setyVelocity(0);
	}

	@Override
	public void act(long now) {

	}

	@Override
	public void updateImage() {
		if(getHealth() <= 0) getWorld().remove(this);
	}

}
