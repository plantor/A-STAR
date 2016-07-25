package yildizle.pathfinding.astar;


import java.util.LinkedList;

public abstract class A_STAR_PATHFINDER { 
	
	/**
	 * Berechnet den kuerzesten Weg von start nach end.
	 * object: Entscheident, ob das Object ueber einem Feld sich bewegen darf und wie viel es kostet.
	 * 
	 * Die G kosten muessen ,vor jedem Einfuegen eines Nodes in die openList, zurueckgesetzt werden,
	 * da es das Programm sonst aufgrund einer Endlosschleife in der zweiten while-Schleife abstuerzt.
	 * 
	 * Ein Hinderniss wird so umgangen, dass es beim Laufen zum Zielpunkt nicht geschnitten wird.
	 * 
	 */
	public static Path findPath(Object object, Node start, Node end) {
		Node[] nachbarn = new Node[8];
		Node guenstigsterKnoten = null;
		LinkedList<Node> geschlosseneListe = new LinkedList<Node>();
		LinkedList<Node> offeneListe = new LinkedList<Node>();
		LinkedList<Node> kandidaten = new LinkedList<Node>();
		
		start.setG(0);
		start.setParent(null);
		offeneListe.add(start);
		
		while(true){
			if(offeneListe.isEmpty()){return null;} /** Ziel nicht gefunden */
			if(geschlosseneListe.contains(end)){break;} /** Ziel gefunden */
			
			guenstigsterKnoten = getGuenstigstenKnoten(offeneListe);
			offeneListe.remove(guenstigsterKnoten);
			
			geschlosseneListe.add(guenstigsterKnoten);
			
			nachbarn = guenstigsterKnoten.getNeighbours().clone();
			
			/**
			 * Hinderniss schneiden verhindern.
			 */
			if(nachbarn[1] != null && !nachbarn[1].isValid(object)){
				nachbarn[0] = null;
				nachbarn[2] = null;
			}
			if(nachbarn[3] != null && !nachbarn[3].isValid(object)){
				nachbarn[0] = null;
				nachbarn[5] = null;
			}
			if(nachbarn[4] != null && !nachbarn[4].isValid(object)){
				nachbarn[2] = null;
				nachbarn[7] = null;
			}
			if(nachbarn[6] != null && !nachbarn[6].isValid(object)){
				nachbarn[5] = null;
				nachbarn[7] = null;
			}
			
			kandidaten.clear();
			
			Node k;
			for(int i=0;i<nachbarn.length;i++){
				k = nachbarn[i];
				if(k == null)continue;
				if(geschlosseneListe.contains(k))continue;				
				if(!k.isValid(object))continue;

				if(offeneListe.contains(k)){	
					int g_kosten = (int)MathUtil.getDistance(guenstigsterKnoten.getX(), guenstigsterKnoten.getY(), k.getX(), k.getY());
					int sum_g_kosten = guenstigsterKnoten.getG() + g_kosten;
					if(sum_g_kosten<k.getG()){
						k.setG(sum_g_kosten);
						k.setParent(guenstigsterKnoten);
					}
				}else{
					kandidaten.add(k);
				}
			}
			
			for(Node node: kandidaten){
				node.setG(0);
				offeneListe.add(node);
				node.setParent(guenstigsterKnoten);
				node.setG(guenstigsterKnoten.getG() + (int)MathUtil.getDistance(guenstigsterKnoten.getX(), guenstigsterKnoten.getY(), node.getX(), node.getY()));
				node.setH((int)(Math.abs(end.getX() - node.getX()) + Math.abs(end.getY() - node.getY())));
			}
			
		}		
		
		Path path = new Path();
		
		
		Node a = end;
		Position p;
		while(a!=null){
			p = new Position();
			p.x = a.getX();
			p.y = a.getY();
			path.addFirst(p);
			a = a.getParent();
		}
		
		return path;

	}

	private static Node getGuenstigstenKnoten(LinkedList<Node> offene_liste) {
		Node anfang = offene_liste.get(0);
		Node temp = null;
		Node gesucht = anfang;
		for (int i = 1; i < offene_liste.size(); i++) {
			temp = offene_liste.get(i);
			if (temp.getF() < gesucht.getF()) {
				gesucht = temp;
			}
		}
		return gesucht;
	}

}
