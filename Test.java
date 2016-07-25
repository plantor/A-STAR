package yildizle.pathfinding.astar;


import java.util.LinkedList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Test extends BasicGame {

	private Map map;
	private LinkedList<Position> positions = new LinkedList<Position>();
	public Test(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.translate(50,50);
		this.map.render(g);
		g.setColor(Color.red);
		int index = 0;
		for(Position pos: positions){
			g.drawString(index+"", pos.x+7, pos.y);
			index++;
			g.drawOval(pos.x - 2, pos.y - 2, 4, 4);
		}
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		this.map = new Map(32,20,20,"Testmap");
		this.map.initNodes();
		this.map.initNeighbours();	
		
		
		
		
		/**
		 * Lade Hindernisse
		 */
		String obstacles = "1 0,1 1,1 2,2 2,3 2,3 3,3 4,3 5,4 5,4 6,7 11,8 11,7 12,6 12,5 12,4 12,4 13,4 14,4 16,4 15,6 14,7 14,8 14,6 15,6 16,5 6,6 6,7 8,7 9,8 8,8 7,8 6,8 5,8 4,8 3,8 2,8 0,8 1,6 8,5 8,4 8,3 8,2 8,0 8,0 9,0 10,0 11,1 11,2 11,2 12,2 13,2 14,2 15,2 16,10 9,10 10,10 11,10 12,10 14,11 14,12 14,13 14,13 15,14 15,15 15,15 14,15 13,15 12,14 12,13 12,12 12,12 11,12 10,12 9,11 9,15 9,15 8,15 7,15 6,15 5,16 5,17 5,17 4,17 3,17 2,17 1,16 9,16 10";
		String pair[] = obstacles.split(",");
		String coords[] = new String[2];
		for(int i = 0; i < pair.length; i++){
			coords = pair[i].split(" ");
			this.map.setNotValid(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
		}
		
		
	}

	
	private Node start, end;
	/**
	 * Mit Linksklick startpunkt setzen
	 * Mit Rechtsklick endpunkt setzen und wegfindung starten.
	 * Mit mittlerer Maustaste Hindernis setzen
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int cc){
		if(button == 0){
			/**
			 * Farbe der Knoten zuruecksetzen.
			 */
			if(start != null){
				((MyNode)start).color = Color.white;
			}
			if(end != null){
				((MyNode)end).color = Color.white;
			}
			
			int pos[] = new int[2];
			this.map.getTilePosFromCartesianCoords(x - 50,y - 50, pos);
			start = this.map.getNode(pos[0], pos[1]);
			positions.clear();
		}
		if(button == 1){
			if(end != null){
				((MyNode)end).color = Color.white;
			}
			
			int pos[] = new int[2];
			this.map.getTilePosFromCartesianCoords(x - 50,y - 50, pos);
			end = this.map.getNode(pos[0], pos[1]);
			
			((MyNode)start).color = Color.green;
			((MyNode)end).color = Color.gray;
			
			Path path = A_STAR_PATHFINDER.findPath(null, start, end);
			
			this.positions = path.getPositions();
			this.positions = PathProcessor.process(path).getPositions();
			
		}
		if(button == 2){
			
			Node node;
			int pos[] = new int[2];
			this.map.getTilePosFromCartesianCoords(x - 34,y - 34, pos);
			this.map.setNotValid(pos[0], pos[1]);
			
			/**
			Zeitmessung Test
			
			String pathfindingStartEnd = "19 19,16 6,18 1,9 2,11 10,14 14,7 13,2 1,1 12,7 15,16 15,11 3,0 19";
			String pair[];
			String coords[] = new String[2];
			pair = pathfindingStartEnd.split(",");
			TimeMeasure time = new TimeMeasure();
			for(int i = 0; i < pair.length; i++){
				coords = pair[i].split(" ");
				Node node = map.getNode(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
				time.start();
				A_STAR_PATHFINDER.findPath(null, map.getNode(0, 0), node);
				time.end();
			}
			System.out.println("average pathfinding time: "+ time.getAverage()+" ns");
			*/
		}
	}
	
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
	}
	
	public static void main(String[] args) throws SlickException {
		Test world = new Test("A*-Test");
		AppGameContainer app = new AppGameContainer(world);
		app.setUpdateOnlyWhenVisible(true);
		app.setTargetFrameRate(60);
		// app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
		app.setShowFPS(false);
		app.setDisplayMode(960, 960, false);
		app.start();
	}
	
}
