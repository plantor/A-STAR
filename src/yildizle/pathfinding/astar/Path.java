package yildizle.pathfinding.astar;


import java.util.Iterator;
import java.util.LinkedList;

public class Path {
	private LinkedList<Position> positions = new LinkedList<Position>();
	
	public void addFirst(Position p){
		this.positions.addFirst(p);
	}
	
	public void addLast(Position p){
		this.positions.addLast(p);
	}
	
	public Iterator<Position>  getIterator(){
		return positions.iterator();
	}
	
	public LinkedList<Position> getPositions(){
		return positions;
	}
}
