import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Rocket extends Actor {

	private double xSpeed;
	private double ySpeed;
	private boolean isActing;
	public final double BULLET_SPEED = 20;
	public double ROCKET_ACCEL = .11;
	public final double ROCKET_MAX_SPEED = 8;
	public long FIRE_DELAY = 200000000l;// 200 milliseconds -> 5
												// bullets/second
	public long nextShot = 0;
	private AudioClip player;

	public Rocket() {
		setImage(new Image("images/rocket.png"));
		isActing = false;
		xSpeed = 0;
		ySpeed = 0;
		player = new AudioClip("file:src/audio/fire.wav");
		player.setVolume(0.15);
		player.setCycleCount(1);
	}

	@Override
	public void act(long now) {
		isActing = true;

		move(xSpeed, ySpeed);
		if (getWorld().isKeyDown(KeyCode.UP) || getWorld().isKeyDown(KeyCode.W)) {
			if (Math.sqrt(Math.pow(xSpeed + ROCKET_ACCEL * Math.cos(Math.toRadians(getRotate())), 2)
					+ Math.pow(ySpeed + ROCKET_ACCEL * Math.sin(Math.toRadians(getRotate())), 2)) <= ROCKET_MAX_SPEED) {
				xSpeed += ROCKET_ACCEL * Math.cos(Math.toRadians(getRotate()));
				ySpeed += ROCKET_ACCEL * Math.sin(Math.toRadians(getRotate()));
			}
		}
		if (getWorld().isKeyDown(KeyCode.LEFT) || getWorld().isKeyDown(KeyCode.A)) {
			setRotate(getRotate() - 2.9); // THIS CONTROLS THE ANGLE
		}
		if (getWorld().isKeyDown(KeyCode.RIGHT) || getWorld().isKeyDown(KeyCode.D)) {
			setRotate(getRotate() + 2.9); // THIS CONTROLS THE ANGLE
		}
		if (getWorld().isKeyDown(KeyCode.SPACE)) {
			fireBullet(getRotate(), now);
			
		}
	}

	public void fireBullet(double angle, double currentTime) {
		if (currentTime < nextShot)
			return; // makes you unable to fire too frequently
		Bullet bullet = new Bullet();
		bullet.setRotate(angle);
		Point2D rocketCenter = new Point2D(this.getX() + this.getWidth() / 2 - bullet.getWidth() / 2,
				this.getY() + this.getHeight() / 2 - bullet.getHeight() / 2);
		bullet.setX(rocketCenter.getX());
		bullet.setY(rocketCenter.getY());
		// we need to fix it so that bullets are fired exactly from the center
		// of the rocket
		double angleInRads = Math.toRadians(angle);
		bullet.setxVelocity(BULLET_SPEED * Math.cos(angleInRads));
		bullet.setyVelocity(BULLET_SPEED * Math.sin(angleInRads));
		getWorld().add(bullet);
		player.play();
		nextShot = (long) (currentTime + FIRE_DELAY); // update the next time
														// you can fire
	}
	
	public Point2D getCenter() {
		return new Point2D(this.getX() + this.getWidth()/2, this.getY() + this.getHeight()/2);
	}

}
