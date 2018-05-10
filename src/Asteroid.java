import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class Asteroid extends Actor{

	private int stage;
	private double xVelocity;
	private double yVelocity;
	
	
	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
		updateImage();
	}

	public abstract void updateImage();

	public double getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
}
