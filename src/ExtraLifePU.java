import javafx.scene.image.Image;

public class ExtraLifePU extends PowerUp {

	public ExtraLifePU() {
		super();
		setImage(new Image("images/rocket.png"));
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
