import javafx.scene.image.Image;

public class Bullet extends Actor{

	private double xVelocity;
	private double yVelocity;
	private int aliveTime;
	
	public Bullet() {
		setImage(new Image("images/bullet.png"));
		xVelocity = 200;
		yVelocity = -200;
		aliveTime = 0;
	}
	
	@Override
	public void act(long now) {
		aliveTime++;
		if (aliveTime == 50) {
			aliveTime = 0;
			getWorld().remove(this);
			return;
		}
		if (hasIntersectingObjects() && getIntersectingObjects(Asteroid.class).size() > 0) {
			Asteroid asteroid = getIntersectingObjects(Asteroid.class).get(0);
			asteroid.setHealth(asteroid.getHealth() - 1);
			System.out.println(asteroid.getClassName() + ":" + asteroid.getHealth());
			getWorld().remove(this);
			return;
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
