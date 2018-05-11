import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class MediumAsteroid extends Asteroid{

	public MediumAsteroid() {
		
		super();
		setStage(5);
		setImage(new Image("images/fireBall.png"));
		setxVelocity(0);
		setyVelocity(0);
	}
	
	@Override
	public void act(long now) {

	}

	@Override
	public void updateImage() {
		// TODO Auto-generated method stub
		if(getStage() <= 0) getWorld().remove(this);
	}

}
