import javafx.scene.image.Image;

public class BigAsteroid extends Asteroid{

	public BigAsteroid() {
		super();
		setHealth(15);
		setImage(new Image("images/ball.png"));
	}
	
	@Override
	public void act(long now) {
		move(getxVelocity(), getyVelocity());
	}

	@Override
	public void updateImage() {
		//if(getHealth() < 10) setImage(new Image("images/bigAsteroidCracked"));
		//if(getHealth() < 5) setImage(new Image("images/bigAsteroidVeryCracked"));
		if(getHealth() <= 0) {
			getWorld().addScore((int)(getScoreCoef() * RocketWorld.PTS_ASTEROID_DESTOYED * getWorld().getPTS_coef()));
			getWorld().remove(this);
		}
	}

	@Override
	public double getScoreCoef() {
		// TODO Auto-generated method stub
		return 3;
	}

}
