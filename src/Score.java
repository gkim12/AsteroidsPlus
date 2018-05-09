import javafx.scene.text.Text;

public class Score extends Text {
	private int score;
	
	public Score() {
		score = 0;
		
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		updateDisplay();
	}
	
	public void decreaseBy1K() {
		setScore(getScore()-1000);
	}
	
	public void increaseBy100() {
		setScore(getScore()+100);
	}

	public void updateDisplay() {
		setText("" + score);
	}
}
