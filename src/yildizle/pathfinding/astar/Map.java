package yildizle.pathfinding.astar;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Map {

	private Node[][] map;
	private Settings settings;
	
	public Map(int tileSize, int mapTileHeight, int mapTileWidth, String mapName){
		this.settings = new Settings(tileSize, mapTileHeight, mapTileWidth, mapName);
		map = new Node[mapTileHeight][mapTileWidth];
	}
	
	public void placeNode(Node node, int line, int column){
		map[line][column] = node;
	}
	
	/**
	 * Zum Testen Hindernis setzen
	 */
	public void setNotValid(int line, int column){
		((MyNode)this.map[line][column]).setValid(false);
		((MyNode)this.map[line][column]).color = Color.red;
	}
	

	/**
	 * Holt Node von der Map
	 * Wenn die Position ausserhalb der Map liegt, wird eine IndexOutOfBounds Exception geworfen.
	 */
	public Node getNode(int line, int column){
		return map[line][column];
	}
	
	/**
	 * Holt Node von der Map
	 * Wenn die Position ausserhalb der Map liegt, wird null zurueckgegeben.
	 */
	private Node getNode_(int line, int column){
		Node node;
		try{
			node =	map[line][column];
		} catch(IndexOutOfBoundsException ex){
			node = null;
		}
		return node;
	}
	
	public void initNodes(){
		int id = 0;
		for(int z = 0; z < settings.getTileHeight(); z++){
			for(int s = 0; s < settings.getTileWidth(); s++){
				placeNode(new MyNode(s * settings.getTileSize() + settings.getTileSize() / 2, z * settings.getTileSize()  + settings.getTileSize() / 2,  ""+id), z, s);
				id++;
			}
		}
	}
	
	public void initNeighbours(){
		Node ary[];
		for(int z = 0; z < settings.getTileHeight(); z++){
			for(int s = 0; s < settings.getTileWidth(); s++){
				ary = new Node[8];
				ary[0] = getNode_(z-1, s-1);
				ary[1] = getNode_(z-1, s);
				ary[2] = getNode_(z-1, s+1);
				ary[3] = getNode_(z, s-1);
				ary[4] = getNode_(z, s+1);
				ary[5] = getNode_(z+1,s-1);
				ary[6] = getNode_(z+1,s);
				ary[7] = getNode_(z+1,s+1);
	
				map[z][s].setNeighbours(ary);
			}
		}
	}
	
	/**
	 * Nimmt kartesische Koordinaten und wandelt sie int array Koordinaten
	 * und gibt die entsprechende Zeile/Spalte im Array zurueck.
	 * Die Zeile wird in pos[0] und die Spalte wird in pos[1] geschrieben.
	 */
	public void getTilePosFromCartesianCoords(int x, int y, int[] pos){
		int line = y / (this.settings.getTileSize());
		int column = x / (this.settings.getTileSize());
		pos[0] = line;
		pos[1] = column;
	}
	
	/**
	 * Wandelt array Koordinaten in kartesische Koordinaten
	 * in pos[0] steht x-Koordinate
	 * in pos[1] steht y-Koordinate 
	 */
	public void getCartesianCoordsFromTilePos(int line, int column, int[] pos){
		pos[0] = column * this.settings.getTileSize();
		pos[1] = line * this.settings.getTileSize();
	}
	
	public void render(Graphics g){
		for(int i = 0; i < settings.getTileHeight(); i ++){
			for(int column = 0; column < settings.getTileWidth(); column++){
				Node node = map[i][column];
				g.setColor(((MyNode)node).color);
				/**
				 * x, y Koordinaten von Node sind Mittelpunktkoordinaten
				 */
				g.translate(- settings.getTileSize() / 2, - settings.getTileSize() / 2);
				g.drawRect(node.getX(), node.getY(), settings.getTileSize(), settings.getTileSize());
				g.translate(settings.getTileSize() / 2, settings.getTileSize() / 2);
				
				/*
				 * Zeichnet die parents der einzelnen Nodes.
				Node[] nodes = node.getNeighbours();
				float x = node.getX();
				float y = node.getY();
				g.translate(settings.getTileSize() / 2, settings.getTileSize() / 2);
				for(int asdf = 0; asdf < nodes.length; asdf++){
					Node n = nodes[asdf];
					if(n == null)continue;
					switch(asdf){
					case 0:
						g.drawLine(x, y, x - 5, y - 5);
						break;
					case 1:
						g.drawLine(x, y, x, y - 5);
						break;
					case 2:
						g.drawLine(x, y, x + 5, y - 5);
						break;
					case 3:
						g.drawLine(x, y, x - 5, y);
						break;
					case 4:
						g.drawLine(x, y, x + 5, y);
						break;
					case 5:
						g.drawLine(x, y, x - 5, y + 5);
						break;
					case 6:
						g.drawLine(x, y, x, y + 5);
						break;
					case 7:
						g.drawLine(x, y, x + 5, y + 5);
						break;
					}
				}
				*/
			}
		}
	}
	
}
