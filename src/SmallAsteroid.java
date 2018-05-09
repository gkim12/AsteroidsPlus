public class SmallAsteroid extends Asteroid{

	public SmallAsteroid() {
		super();
		//setImage(new Image("images/SmallAsteroid"));
		setxVelocity(50);
		setyVelocity(50);
	}
	
	@Override
	public void act(long now) {
		if(getStage() > 0) {
			getWorld().remove(this);
		}
	}

}
