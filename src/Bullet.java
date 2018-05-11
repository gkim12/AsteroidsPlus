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
		
		if (hasIntersectingObjects() && getIntersectingObjects(Asteroid.class).size() > 0) {
			Asteroid asteroid = getIntersectingObjects(Asteroid.class).get(0);
			asteroid.setStage(asteroid.getStage() - 1);
			System.out.println(asteroid.getClassName() + ":" + asteroid.getStage());
			getWorld().remove(this);
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
