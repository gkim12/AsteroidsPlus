import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Rocket extends Actor{
	
	private double xSpeed;
	private double ySpeed;
	private boolean isActing;
	

	public Rocket() {
		setImage(new Image("images/paddle.png"));
		isActing = false;
	}
	
	@Override
	public void act(long now) {
		isActing = true;
		setX(getX() + 0.2);
		//System.out.println(getWorld().getKeyCodes().size());
		//System.out.println(getWorld().getClass().toString());
		if(getWorld().isKeyDown(KeyCode.LEFT)) {
			//System.out.println("you pressed LEFT");
			setRotate(getRotate() - 3.6);
		}
		if(getWorld().isKeyDown(KeyCode.RIGHT)) {
			//System.out.println("you pressed RIGHT");
			setRotate(getRotate() + 3.6);
		}
		if(getWorld().isKeyDown(KeyCode.SPACE)) fireBullet(getRotate());
	}
	
	public void fireBullet(double angle) {
		Bullet bullet = new Bullet();
		bullet.setRotate(angle);
		double angleInRads = Math.toRadians(angle);
		bullet.setxVelocity(200*Math.cos(angleInRads));
		bullet.setyVelocity(200*Math.sin(angleInRads));
		getWorld().add(bullet);
		//getWorld().add();
	}

}
