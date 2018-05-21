import java.util.List;
import java.util.Random;

public class RocketWorld extends World {
	
	public long nextSpawn = 0;
	public final long SPAWN_DELAY = 2000000000l;
	
	public RocketWorld() {
		super();
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		List<Bullet> listBullets = getObjects(Bullet.class);
		for(int i = 0; i < listBullets.size(); i++) {
			if(Math.abs(listBullets.get(i).getX()) > 2000 || Math.abs(listBullets.get(i).getY())  > 2000) remove(listBullets.get(i));
		}	
		
		spawnAsteroid(now);
	}
	
	public void spawnAsteroid(double currentTime) {
		if(currentTime < nextSpawn)
			return;
		Asteroid a = null;
		double speedConstant = 0;
		switch(new Random().nextInt(3)){
		case 0:
			a = new SmallAsteroid();
			speedConstant = 3;
			break;
		case 1:
			a = new MediumAsteroid();
			speedConstant = 2;
			break;
		case 2:
			a = new BigAsteroid();
			speedConstant = 1;
			break;
		}
		
		double xPos = 0;
		double yPos = 0;
		switch(new Random().nextInt(4)){
		case 0: // top
			xPos = new Random().nextInt(1070);
			yPos = 0;
			break;
		case 1: // left
			xPos = 0;
			yPos = new Random().nextInt(710);
			break;
		case 2: // right
			xPos = 1070;
			yPos = new Random().nextInt(710);
			break;
		case 3: // bottom
			yPos = 710;
			xPos = new Random().nextInt(1070);
			break;
		}
		a.setX(xPos);
		a.setY(yPos);
		
		Rocket r = getObjects(Rocket.class).get(0);
		double dir = Math.sqrt(Math.pow(r.getX() - a.getX(), 2) + Math.pow(r.getY() - a.getY(), 2));
		double dirX = (r.getX() - a.getX()) / dir;
		double dirY = (r.getY() - a.getY()) / dir;
		a.setxVelocity(dirX * speedConstant);
		a.setyVelocity(dirY * speedConstant);
		add(a);
		
		nextSpawn = (long) (currentTime + SPAWN_DELAY);
	}

}
