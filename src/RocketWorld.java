import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RocketWorld extends World {
	
	public long nextAsteroidSpawn = 0;
	public long nextPowerUpSpawn = 0;
	public final long SPAWN_DELAY = 8000000000l;
	
	public RocketWorld(Game game) {
		super(game);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		List<Bullet> listBullets = getObjects(Bullet.class);
		for(int i = 0; i < listBullets.size(); i++) {
			if(Math.abs(listBullets.get(i).getX()) > 2000 || Math.abs(listBullets.get(i).getY())  > 2000) remove(listBullets.get(i));
		}	
		
		spawnAsteroid(now);
		spawnPowerUp(now);
		
		getCurrentGame().powerUpLabel.getChildren().remove(1, getCurrentGame().powerUpLabel.getChildren().size());
		if(getCurrentGame().getActivePU().size() > 0) {
			for(PowerUp pu : getCurrentGame().getActivePU()) {
				getCurrentGame().powerUpLabel.getChildren().add(new ImageView(pu.getImage()));
				Text timerText = new Text("" + (pu.getFinishTime()-now)/1000000000);
				timerText.setFont(new Font(15));
				timerText.setFill(Color.CRIMSON);
				getCurrentGame().powerUpLabel.getChildren().add(timerText);
			}
		}
	}
	
	public void spawnAsteroid(double currentTime) {
		if(currentTime < nextAsteroidSpawn)
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
		add(a);
		a.setX(xPos);
		a.setY(yPos);
		
		Rocket r = getObjects(Rocket.class).get(0);
		double dir = Math.sqrt(Math.pow(r.getX() - a.getX(), 2) + Math.pow(r.getY() - a.getY(), 2));
		double dirX = (r.getX() - a.getX()) / dir;
		double dirY = (r.getY() - a.getY()) / dir;
		a.setxVelocity(dirX * speedConstant);
		a.setyVelocity(dirY * speedConstant);
		
		nextAsteroidSpawn = (long) (currentTime + SPAWN_DELAY);
	}

	public void spawnPowerUp(double currentTime) {
		if(currentTime < nextPowerUpSpawn)
			return;
		PowerUp pu = null;
		switch(new Random().nextInt(3)){
		case 0:
			pu = new FasterAccelPU();
			break;
		case 1:
			pu = new FasterBulletsPU();
			break;
		case 2:
			pu = new ExtraLifePU();
			break;
		}
		add(pu);
		
		double xPos = new Random().nextInt(1070);
		double yPos = new Random().nextInt(710);
		pu.setX(xPos);
		pu.setY(yPos);
		
		nextPowerUpSpawn = (long) (currentTime + SPAWN_DELAY);
	}
}
