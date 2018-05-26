import javafx.scene.image.Image;

public class ShieldPU extends PowerUp {
	
	
	public ShieldPU() {
		super();
		setActiveTime(5000000000l);
		setImage(new Image("images/shieldPU.png"));
	}
	
	public void act(long now) {
		//if touched by a rocket, launch the powerup, set finish time to 5 seconds after the time it was touched
		if(hasIntersectingObjects() && getOneIntersectingObject(Rocket.class) != null && !isTouched()) {
			setTargetObject(getOneIntersectingObject(Rocket.class));
			if(!getWorld().getCurrentGame().containsPUOfClass(this.getClass())) {
				getWorld().getCurrentGame().getActivePU().add(this);
				start();
				setFinishTime(now + getActiveTime());
			}
			else {
				PowerUp existingShield = getWorld().getCurrentGame().getPUOfClass(this.getClass());
				existingShield.setFinishTime(existingShield.getFinishTime() + getActiveTime());
				getWorld().remove(this);
			}
		}
		
		if(isTouched()) {
			//Node n = getThisGame().powerUpLabel.getChildren().get(0);
			//((Text) n).setText("" + (finishTime-now)/1000000000);
			//setX(targetObject.getCenter().getX() - this.getWidth()/2);
			//setY(targetObject.getCenter().getY() - this.getHeight()/2);
			if(now >= getFinishTime()) {
				
				end();
				getWorld().getCurrentGame().getActivePU().remove(this);
				getWorld().remove(this);
				
			}
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		setTouched(true);
		
		setOpacity(0);
		setInitialValue(1); //initial value is not used
		getTargetObject().setShieldOn(true);
		getTargetObject().setImage(new Image("images/rocket_shieldOn.png"));
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		getTargetObject().setShieldOn(false);
		getTargetObject().setImage(new Image("images/rocket.png"));
	}

}
