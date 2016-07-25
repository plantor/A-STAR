package yildizle.pathfinding.astar;


public class Settings {
	private int tileSize, mapTileHeight, mapTileWidth;
	private String mapName;
	
	/**
	 * Einstellungen fuer eine Map
	 */
	public Settings(int tileSize, int mapTileHeight, int mapTileWidth, String mapName){
		this.tileSize = tileSize;
		this.mapTileHeight = mapTileHeight;
		this.mapTileWidth = mapTileWidth;
		this.mapName = mapName;
	}
	
	/**
	 * mapHeight in tiles
	 */
	public int getTileHeight(){
		return mapTileHeight;
	}
	
	/**
	 * mapWidth in tiles
	 */
	public int getTileWidth(){
		return mapTileWidth;
	}
	
	/**
	 * A tile is a squad.
	 * tileSize
	 */
	public int getTileSize()
	{
		return tileSize;
	}
	

	public String getMapName(){
		return mapName;
	}
}
