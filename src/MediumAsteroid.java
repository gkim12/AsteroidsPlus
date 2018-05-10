import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class MediumAsteroid extends Asteroid{

	public MediumAsteroid() {
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(getWorld().getObjects(Asteroid.class));
			}
			
		});
		
		setStage(5);
		setImage(new Image("images/fireBall.png"));
		setxVelocity(0);
		setyVelocity(0);
	}
	
	@Override
	public void act(long now) {
//		if(getStage() == 3) {
//			//setImage(new Image("images/MediumAsteroid1"));
//		}
//		if(getStage() > 6) {
//			getWorld().remove(this);
//		}
	}

	@Override
	public void updateImage() {
		// TODO Auto-generated method stub
		if(getStage() <= 0) getWorld().remove(this);
	}

}
