package yildizle.pathfinding.astar;


import java.util.Iterator;

import org.newdawn.slick.geom.Vector2f;

/**
 * Entfernt ueberfluessige Positionen im EregbnisPfad vom A*-Algorithmus
 * 
 * @author snakemaster
 *
 */
public abstract class PathProcessor {

	/**
	 * Berechnet einen Pfad, der keine ueberfluessige Punkt hat. Veraendert
	 * nicht den eingegebenen Pfad. Der eingegebene Pfad ist geordnet Start zum
	 * Ziel von links nach rechts.
	 */
	public static Path process(final Path pathInput) {
		Path path = new Path();
		Vector2f currentVector = new Vector2f();
		Vector2f prevVector = new Vector2f();
		Iterator<Position> iterator = pathInput.getIterator();
		Position current = null;
		Position prev;
		/**
		 * Regel: Berechne Vektor zum Parent-Knoten. Wenn Vektoren ungleich,
		 * dann in Pfad einfuegen. Wenn kein Punkt mehr vorhanden, dann auch in
		 * Pfad einfuegen.
		 */

		if (pathInput.getPositions().size() == 0) {
			System.out.println("0");
			return path;
		}
		if (pathInput.getPositions().size() == 1) {
			System.out.println("1");
			path.addFirst(pathInput.getPositions().getFirst());
			return path;
		}
		if (pathInput.getPositions().size() == 2) {
			System.out.println("2");
			path.addLast(pathInput.getPositions().getFirst());
			path.addLast(pathInput.getPositions().getLast());
			return path;
		} else {
			System.out.println("3");
			// Mehr als 2 Elemente in path
			// Vorbereitung
			Position eins = iterator.next();
			Position zwei = iterator.next();
			prev = zwei;
			prevVector.x = zwei.x - eins.x;
			prevVector.y = zwei.y - eins.y;
			while (iterator.hasNext()) {
				current = iterator.next();
				currentVector.x = current.x - prev.x;
				currentVector.y = current.y - prev.y;

				if (currentVector.x != prevVector.x
						|| currentVector.y != prevVector.y) {
					path.addLast(prev);
				}

				if (!iterator.hasNext()) {
					path.addLast(current);
				}

				prev = current;
				prevVector.x = currentVector.x;
				prevVector.y = currentVector.y;
			}
		}

		return path;

	}

}
