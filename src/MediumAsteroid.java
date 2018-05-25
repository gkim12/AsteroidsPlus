import javafx.scene.image.Image;

public class MediumAsteroid extends Asteroid{

	public MediumAsteroid() {
		
		super();
		setHealth(10);
		setImage(new Image("images/asteroid.png"));
	}
	
	@Override
	public void act(long now) {
		move(getxVelocity(), getyVelocity());
	}

	@Override
	public void updateImage() {
		if(getHealth() < 5) setImage(new Image("images/asteroidCracked.png"));
		if(getHealth() <= 0) {
			getWorld().addScore((int)(2 * RocketWorld.PTS_ASTEROID_DESTOYED * getWorld().getPTS_coef()));
			getWorld().remove(this);
		}
	}

}
