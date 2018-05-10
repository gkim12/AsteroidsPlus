import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{
	
	public abstract void act(long now);
	
	public void move(double dx, double dy){
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public World getWorld(){
		return (World) super.getParent();
	} 
	
	public double getHeight(){
		return super.getBoundsInParent().getHeight();
	}
	
	public double getWidth(){
		return super.getBoundsInParent().getWidth();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
		ArrayList<A> listIntersecting = new ArrayList<>();
		List<A> listWorldObjects = getWorld().getObjects(cls); //returns all objects of type A
		for(A object: listWorldObjects) {
			
			if(this.intersects(object.getLayoutBounds())) listIntersecting.add(object);
			//if object's bounds intersect (this), add the object to list of intersecting
		}
		
		return listIntersecting;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> listIntersecting = getIntersectingObjects(cls); //get all intersecting objects
		//if there are intersecting objects, return the first one, else return null 
		if(listIntersecting.size() > 0) return listIntersecting.get(0);
		else return null;
	}
	
	public boolean hasIntersectingObjects() {
		return getIntersectingObjects(Actor.class).size() > 0;
	}
}
