import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private boolean paused = false;
	private AnimationTimer timer;
	private ArrayList<KeyCode> keyCodes;
	private Game currentGame;
	private int score = 0;
	
	public double PTS_coef = 1;

	public World(Game game) {
		currentGame = game;
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				if (paused) return;
				act(arg0);
				// calls act on itself and on all objects in the world
				for (Actor a : getObjects(Actor.class)) {
					if(getObjects(Actor.class).contains(a)) a.act(arg0);
				}

			}
		};
		keyCodes = new ArrayList<KeyCode>();
		// start(); *We only call start in application (Game.java)*
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score+=score;
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
	
	public <A extends Actor> java.util.List<A> getObjectsIntersectingPointXY(java.lang.Class<A> cls, double x, double y) {
		ArrayList<A> listInter = new ArrayList<>();
		for (A a: getObjects(cls)) {
			if(a.getLayoutBounds().contains(x,y)) listInter.add(a);
		}
		return listInter;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public abstract void act(long now);

	public void addKey(KeyCode c) {
		if(!keyCodes.contains(c)) keyCodes.add(c);
	}

	public boolean removeKey(KeyCode c) {
//		System.out.println("released " + c.getName());
		return keyCodes.remove(c);
	}
	
	public void removePressedKeys(){
		for(int i = 0; i < keyCodes.size(); i++) {
			keyCodes.remove(0);
		}
	}
	
	public void printPressedKeys() {
		for(int i = 0; i < keyCodes.size(); i++) {
			System.out.print(keyCodes.get(i).toString() + " ");
		}
		System.out.println();
	}

	public boolean isKeyDown(KeyCode c) {
		return keyCodes.contains(c);
	}
	public ArrayList<KeyCode> getKeyCodes(){
		return keyCodes;
	}
	
	public double getPTS_coef() {
		return PTS_coef;
	}

	public void setPTS_coef(double pTS_coef) {
		PTS_coef = pTS_coef;
	}
	
public void gameOver() {
		paused = true;
		String scoreEval = "";
		if(getScore() > getCurrentGame().getHighestScore()) {
			scoreEval = "This is the new record!";
			getCurrentGame().setHighestScore(getScore());
		}
		else if(getScore() <= getCurrentGame().getHighestScore()) {
			scoreEval = "Your record was " + getCurrentGame().getHighestScore();
		}
		Alert lostAlert = new Alert(AlertType.INFORMATION, "Your final score was " + getScore() + "\n\n" + scoreEval, ButtonType.OK);
		lostAlert.setHeaderText("You lost! Better luck next time.");
		
		Alert nextGame = new Alert(AlertType.CONFIRMATION, "Would you like to continue?", ButtonType.YES, ButtonType.NO);
		nextGame.setHeaderText("Choose what to do next.");
		
		lostAlert.setOnHidden(new EventHandler<DialogEvent>() {

			@Override
			public void handle(DialogEvent arg0) {
				// TODO Auto-generated method stub
				lostAlert.close();
				
				Optional<ButtonType> r = nextGame.showAndWait();
				
				if(r.get() == ButtonType.YES){
					getCurrentGame().resetGame();
				} else {
					System.exit(0);
				}
			}
			
		});
		
		
		lostAlert.show();
		
		
		
	}
	
	
}
