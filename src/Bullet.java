import javafx.scene.image.Image;

public class Bullet extends Actor{

	private double xVelocity;
	private double yVelocity;
	private boolean didHit = false;
	
	public Bullet() {
		setImage(new Image("images/fireBall.png"));
		xVelocity = 5;
		yVelocity = 5;
	}
	
	@Override
	public void act(long now) {
		if(getOneIntersectingObject(Asteroid.class) == null){
			didHit = false;
		}
		if (getOneIntersectingObject(Asteroid.class) != null && !didHit) {
			didHit = true;
			Asteroid asteroid = getOneIntersectingObject(Asteroid.class);
			
		}
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
