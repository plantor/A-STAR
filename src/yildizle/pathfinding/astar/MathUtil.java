package yildizle.pathfinding.astar;


public abstract class MathUtil {
	public static final float getDistance(float xpos, float ypos, float xpos2, float ypos2){
		return (float)Math.sqrt(Math.pow(xpos-xpos2,2) + Math.pow(ypos - ypos2, 2));
	}
	
	public static final float getSquareDistance(float xpos, float ypos, float xpos2, float ypos2){
		return (float)(Math.pow(xpos-xpos2,2) + Math.pow(ypos - ypos2, 2));
	}
}
