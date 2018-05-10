import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Rocket extends Actor{
	
	private double xSpeed;
	private double ySpeed;
	private boolean isActing;
	public final double BULLET_SPEED = 45;
	public final long FIRE_DELAY = 400000000;//400 milliseconds -> 2.5 bullets/second
	public long nextShot = 0;
	

	public Rocket() {
		setImage(new Image("images/rocket.png"));
		isActing = false;
	}
	
	@Override
	public void act(long now) {
		isActing = true;
		//setX(getX() + 0.2); - ROCKET IS NOT MOVING!!
		//System.out.println(getWorld().getKeyCodes().size());
		//System.out.println(getWorld().getClass().toString());
		if(getWorld().isKeyDown(KeyCode.LEFT) || getWorld().isKeyDown(KeyCode.A)) {
			//System.out.println("you pressed LEFT");
			setRotate(getRotate() - .36); //3.6
		}
		if(getWorld().isKeyDown(KeyCode.RIGHT) || getWorld().isKeyDown(KeyCode.D)) {
			//System.out.println("you pressed RIGHT");
			setRotate(getRotate() + .36); //3.6
		}
		if(getWorld().isKeyDown(KeyCode.SPACE)) fireBullet(getRotate(), now);
	}
	
	public void fireBullet(double angle, double currentTime) {
		if(currentTime < nextShot) return;
		Bullet bullet = new Bullet();
		bullet.setRotate(angle);
		bullet.setX(this.getX());
		bullet.setY(this.getY());
		double angleInRads = Math.toRadians(angle);
		bullet.setxVelocity(BULLET_SPEED*Math.cos(angleInRads));
		bullet.setyVelocity(BULLET_SPEED*Math.sin(angleInRads));
		getWorld().add(bullet);
		nextShot = (long) (currentTime + FIRE_DELAY);
		//getWorld().add();
	}

}
