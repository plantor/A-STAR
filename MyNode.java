package yildizle.pathfinding.astar;


import org.newdawn.slick.Color;

public class MyNode implements Node{

	/**
	 * Node stuff
	 */
	private int h,g;
	private Node parent;
	private Node[] neighbours;
	private float xpos, ypos;
	public Color color;
	private String name;
	private boolean valid = true;
	
	public MyNode(int xpos, int ypos, String name){
		this.name = name;
		this.xpos = xpos;
		this.ypos = ypos;
		this.color = Color.white;
	}
		
	/**
	 * Zum Testen
	 */
	public void setValid(boolean valid){
		this.valid = valid;
	}
	
	
	public String getName(){
		return name;
	}
	
	/**
	 * Setze die Nachbarknoten dieses Tiles.
	 * <br>
	 * (Soll beim Parsen der Map aufgerufen werden.
	 */
	@Override
	public void setNeighbours(Node[] neighbours){
		this.neighbours = neighbours;
	}
	
	@Override
	public int getG() {
		return g;
	}

	@Override
	public int getH() {
		return h;
	}

	@Override
	public void setG(int g) {
		this.g = g;
	}

	@Override
	public void setH(int h) {
		this.h = h;
	}

	@Override
	public int getF() {
		return getG() + getH();
	}

	@Override
	public void setParent(Node node) {
		this.parent = node;
	}

	@Override
	public Node getParent() {
		return parent;
	}

	@Override
	public Node[] getNeighbours() {
		return neighbours;
	}

	@Override
	public float getCost(Object o) {
		return 1;
	}	

	@Override
	public boolean isValid(Object o) {
		return valid;
	}

	@Override
	public float getX() {
		return xpos;
	}

	@Override
	public float getY() {
		return ypos;
	}

}
