import javafx.scene.image.Image;

public class Bullet extends Actor{

	private double xVelocity;
	private double yVelocity;
	private boolean didHit = false;
	
	public Bullet() {
		setImage(new Image("images/bullet.png"));
		xVelocity = 200;
		yVelocity = -200;
	}
	
	@Override
	public void act(long now) {
		if(getOneIntersectingObject(Asteroid.class) == null){
			didHit = false;
		}
		if (getOneIntersectingObject(Asteroid.class) != null && !didHit) {
			didHit = true;
			getWorld().remove(this);
			Asteroid asteroid = getOneIntersectingObject(Asteroid.class);
			asteroid.setStage(asteroid.getStage() + 1);
		}
		move(xVelocity, yVelocity);
	}

	public double getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
}
