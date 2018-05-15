import javafx.scene.image.Image;

public class BigAsteroid extends Asteroid{

	public BigAsteroid() {
		super();
		setHealth(20);
		setImage(new Image("images/ball.png"));
		setxVelocity(25);
		setyVelocity(25);
	}
	
	@Override
	public void act(long now) {
	
	}

	@Override
	public void updateImage() {
		//if(getHealth() < 10) setImage(new Image("images/bigAsteroidCracked"));
		//if(getHealth() < 5) setImage(new Image("images/bigAsteroidVeryCracked"));
		if(getHealth() <= 0) getWorld().remove(this);
	}

}
