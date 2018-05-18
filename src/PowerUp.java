

public abstract class PowerUp extends Actor {
	
	private long activeTime = 5000000000l; //5 seconds, 'l' for long
	//active time is same for all, though changeable
	private boolean touched = false;
	private long finishTime;
	private long initialValue; //value to be changed and returned to, whether it's acceleration, time or else.
	private Rocket targetObject;

	
	public void act(long now) {
		//if touched by a rocket, launch the powerup, set finish time to 5 seconds after the time it was touched
		if(hasIntersectingObjects() && getOneIntersectingObject(Rocket.class) != null && !touched) {
			setTargetObject(getOneIntersectingObject(Rocket.class));
			start();
			finishTime = now + activeTime;
		}
		
		if(touched) {
			setX(targetObject.getCenter().getX() - this.getWidth()/2);
			setY(targetObject.getCenter().getY() - this.getHeight()/2);
			if(now >= finishTime) {
				
				end();
				getWorld().remove(this);
				
			}
		}
		// TODO Auto-generated method stub

	}

	public abstract void start();
	public abstract void end();

	public long getActiveTime() {
		return activeTime;
	}
	
	public void setActiveTime(long activeTime) {
		this.activeTime = activeTime;
	}
	
	public boolean isTouched() {
		return touched;
	}
	
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public long getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(long initialValue) {
		this.initialValue = initialValue;
	}

	public long getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}
	
	public Rocket getTargetObject() {
		return targetObject;
	}
	
	public void setTargetObject(Rocket targetObject) {
		this.targetObject = targetObject;
	}
}
