import javafx.scene.image.Image;

public class FasterAccelPU extends PowerUp {


	public FasterAccelPU() {
		super();
		setImage(new Image("images/boostPU.png"));
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		setTouched(true);
		
		setOpacity(0);
		setInitialValue((long)(getTargetObject().ROCKET_ACCEL * 100000));
		getTargetObject().ROCKET_ACCEL = getTargetObject().ROCKET_ACCEL + 0.11;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		getTargetObject().ROCKET_ACCEL = getTargetObject().ROCKET_ACCEL - 0.11;
	}

	

}
