import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

public class Rocket extends Actor {

	private double xSpeed;
	private double ySpeed;
	private boolean isActing;
	private boolean shieldOn;
	public final double BULLET_SPEED = 20;
	public double ROCKET_ACCEL = .11;
	public final double ROCKET_MAX_SPEED = 8;
	public long FIRE_DELAY = 200000000l;// 200 milliseconds -> 5
												// bullets/second
	public static final Image SHIELD_IMG = new Image("file:ball.png"); 
	public long nextShot = 0;
	private AudioClip player;
	private int lives = 3;

	public Rocket() {
		setImage(new Image("images/rocket.png"));
		isActing = false;
		shieldOn = false;
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
		if (getOneIntersectingObject(Asteroid.class) != null) {
			if(!isShieldOn()) {
				
				changeLives(Math.max(lives-1, 0));
				
				if(lives < 1) {
					getWorld().gameOver();
					return;
				}
				
				double resetX = 1070 / 2 - getWidth() / 2;
				double resetY = 710 / 2-  getHeight() / 2;
				
				setX(resetX);
				setY(resetY);
				xSpeed = 0;
				ySpeed = 0;
				
				for(Actor a: getIntersectingObjects(Actor.class)) {
					if(!a.equals(this)) getWorld().remove(a);
				}
//				
//				else {
//					getWorld().gameOver();
//				}
				
			}
			if(isShieldOn()){
				Asteroid a = getOneIntersectingObject(Asteroid.class);
				getWorld().addScore((int) (RocketWorld.PTS_ASTEROID_DESTOYED * getWorld().getPTS_coef()));
				getWorld().remove(a);
				return;
			}
			
			return;
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
	
	public void changeLives(int l) {
		lives = l;
		HBox livesB = getWorld().getCurrentGame().livesBox;
		livesB.getChildren().remove(0, livesB.getChildren().size());
		Image rocketImage = new Image("images/rocket.png");
		//for (int i = 0; i < l; i++) {
			ImageView rocketView = new ImageView(rocketImage);
			rocketView.setScaleX(0.3);
			rocketView.setScaleY(0.3);
			livesB.getChildren().add(rocketView);
		//}
			livesB.getChildren().add(new Text("X  " + lives));
			Text t = (Text) (livesB.getChildren().get(1));
			t.setStyle("-fx-font-weight: bold");
	}
	
	
	
	public int getLives() {
		return lives;
	}

	public boolean isShieldOn() {
		return shieldOn;
	}

	public void setShieldOn(boolean shieldOn) {
		this.shieldOn = shieldOn;
	}
}
