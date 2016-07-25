package yildizle.pathfinding.astar;


public class TimeMeasure {
	private double timeStart;
	private double timeEnd;
	private double timeSummary;
	private int countMeasures;
	
	public void reset(){
		timeSummary = 0;
		countMeasures = 0;
	}
	
	public void start(){
		timeStart = System.nanoTime();
	}
	
	public void end(){
		timeEnd = System.nanoTime();
		timeSummary += timeEnd - timeStart;
		countMeasures++;
	}
	
	public double getAverage(){
		return (double)(timeSummary / (double)countMeasures);
	}
}
