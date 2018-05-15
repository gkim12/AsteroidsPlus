import javafx.scene.image.Image;

public class MediumAsteroid extends Asteroid{

	public MediumAsteroid() {
		
		super();
		setHealth(10);
		setImage(new Image("images/asteroid.png"));
		setxVelocity(0);
		setyVelocity(0);  
	}
	
	@Override
	public void act(long now) {

	}

	@Override
	public void updateImage() {
		if(getHealth() < 5) setImage(new Image("images/asteroidCracked.png"));
		if(getHealth() <= 0) getWorld().remove(this);
	}

}
