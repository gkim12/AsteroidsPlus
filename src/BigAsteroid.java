public class BigAsteroid extends Asteroid{

	public BigAsteroid() {
		super();
		//setImage(new Image("images/BigAsteroid0"));
		setxVelocity(25);
		setyVelocity(25);
	}
	
	@Override
	public void act(long now) {
		if(getStage() == 5) {
			//setImage(new Image("images/BigAsteroid1"));
		}
		if(getStage() == 10) {
			//setImage(new Image("images/BigAsteroid2"));
		}
		if(getStage() > 15) {
			getWorld().remove(this);
		}
	}

	@Override
	public void updateImage() {
		// TODO Auto-generated method stub
		
	}

}
