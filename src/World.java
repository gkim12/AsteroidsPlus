import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private AnimationTimer timer;
	private ArrayList<KeyCode> keyCodes;

	public World() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				act(arg0);
				// calls act on itself and on all objects in the world
				for (Actor a : getObjects(Actor.class)) {
					a.act(arg0);
				}

			}
		};
		keyCodes = new ArrayList<KeyCode>();
		// start(); *We only call start in application (Game.java)*
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void add(Actor actor) {
		getChildren().add(actor);
	}

	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		ArrayList<A> list = new ArrayList<>();
		for (Object c : getChildren()) {
			if (cls.isAssignableFrom(c.getClass())) {
				list.add((A) (c));
			}
		}
		return list;
	}

	public abstract void act(long now);

	public void addKey(KeyCode c) {
		if(!keyCodes.contains(c)) keyCodes.add(c);
	}

	public boolean removeKey(KeyCode c) {
		System.out.println("released " + c.getName());
		return keyCodes.remove(c);
	}

	public boolean isKeyDown(KeyCode c) {
		return keyCodes.contains(c);
	}
	public ArrayList<KeyCode> getKeyCodes(){
		return keyCodes;
	}
}
