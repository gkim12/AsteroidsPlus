import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Rocket extends Actor{
	
	private double xSpeed;
	private double ySpeed;
	

	public Rocket() {
		setImage(new Image("images/paddle.png"));
	}
	
	@Override
	public void act(long now) {
		if(getWorld().isKeyDown(KeyCode.LEFT)) setRotate(getRotate()+3.6);
		if(getWorld().isKeyDown(KeyCode.RIGHT)) setRotate(getRotate() + 3.6);
		if(getWorld().isKeyDown(KeyCode.SPACE)) fireBullet(getRotate());
	}
	
	public void fireBullet(double angle) {
		//getWorld().add();
	}

}
