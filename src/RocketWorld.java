import java.util.List;

public class RocketWorld extends World {
	
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
	}

}
