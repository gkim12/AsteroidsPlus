import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Rocket extends Actor{
	
	private double xSpeed;
	private double ySpeed;
	private boolean isActing;
	public final double BULLET_SPEED = 45;
	public final long FIRE_DELAY = 200000000;//200 milliseconds -> 5 bullets/second
	public long nextShot = 0;
	

	public Rocket() {
		setImage(new Image("images/rocket.png"));
		isActing = false;
		
	}
	
	@Override
	public void act(long now) {
		isActing = true;
		//setX(getX() + 0.2); - CHANGE THIS IF YOU WNAT YOUR ROCKET TO MOVE
		if(getWorld().isKeyDown(KeyCode.LEFT) || getWorld().isKeyDown(KeyCode.A)) {
			setRotate(getRotate() - 2.4); // THIS CONTROLS THE ANGLE
		}
		if(getWorld().isKeyDown(KeyCode.RIGHT) || getWorld().isKeyDown(KeyCode.D)) {
			setRotate(getRotate() + 2.4); // THIS CONTROLS THE ANGLE
		}
		if(getWorld().isKeyDown(KeyCode.SPACE)) fireBullet(getRotate(), now);
		
	}
	
	public void fireBullet(double angle, double currentTime) {
		if(currentTime < nextShot) return; //makes you unable to fire too frequently
		Bullet bullet = new Bullet();
		bullet.setRotate(angle);
		Point2D rocketCenter = new Point2D(this.getX() + this.getWidth() / 2 - bullet.getWidth() / 2, this.getY() + this.getHeight() / 2 - bullet.getHeight() / 2);
		bullet.setX(rocketCenter.getX());
		bullet.setY(rocketCenter.getY());
		//we need to fix it so that bullets are fired exactly from the center of the rocket
		double angleInRads = Math.toRadians(angle);
		bullet.setxVelocity(BULLET_SPEED*Math.cos(angleInRads));
		bullet.setyVelocity(BULLET_SPEED*Math.sin(angleInRads));
		getWorld().add(bullet);
		nextShot = (long) (currentTime + FIRE_DELAY); //update the next time you can fire
	}

}
