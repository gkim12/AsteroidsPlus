import javafx.scene.image.Image;

public class FasterBulletsPU extends PowerUp {

	
	public FasterBulletsPU() {
		setImage(new Image("images/fireBall.png"));
	}

	@Override
	public void start() {
		
		setInitialValue(getTargetObject().FIRE_DELAY);
		setOpacity(0.6);
		setTouched(true);
		getTargetObject().FIRE_DELAY = (long) (getInitialValue()*0.6); // increases fire rate
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		getTargetObject().FIRE_DELAY = getInitialValue();
		
	}

}
