import javafx.scene.image.Image;

public class ExtraLifePU extends PowerUp {

	public ExtraLifePU() {
		super();
		setImage(new Image("images/rocket.png"));
		setScaleX(0.7);
		setScaleY(0.7);
		setActiveTime(0);
	}

	@Override
	public void start() {
		setTouched(true);

		setOpacity(0);
		getTargetObject().changeLives(getTargetObject().getLives() + 1);
	}

	@Override
	public void end() {

	}

}
