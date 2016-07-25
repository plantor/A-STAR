package yildizle.pathfinding.astar;


public interface Node{

	public int getG();
	public int getH();
	public void setG(int g);
	public void setH(int h);
	public int getF();
	public boolean isValid(Object o);
	public void setParent(Node node);
	public Node getParent();
	public Node[] getNeighbours();
	public void setNeighbours(Node[] ary);
	/**
	 * CenterX Koordinate
	 */
	public float getX();
	/**
	 * CenterY Koordinate
	 */
	public float getY();
	/**
	 * Begehungskosten
	 */
	public float getCost(Object o);
	
}
