import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class Asteroid extends Actor{

	private int health;
	private double xVelocity;
	private double yVelocity;
	
	public Asteroid() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(getClassName() + ":" + getHealth());
				System.out.println(xVelocity);
				System.out.println(yVelocity);
			}
			
		});
	}
	
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
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
	
	public String getClassName() {
		return this.getClass().getName();
	}
}
