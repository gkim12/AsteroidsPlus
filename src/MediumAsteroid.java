public class MediumAsteroid extends Asteroid{

	public MediumAsteroid() {
		super();
		//setImage(new Image("images/MediumAsteroid0"));
		setxVelocity(35);
		setyVelocity(35);
	}
	
	@Override
	public void act(long now) {
		if(getStage() == 3) {
			//setImage(new Image("images/MediumAsteroid1"));
		}
		if(getStage() > 6) {
			getWorld().remove(this);
		}
	}

}
