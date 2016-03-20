import java.math.*;
import java.util.*;
public class Sort_and_Scan {
	
	Point[] pointArray;
	Point leastXPoint;
	Stack sortedPoints = new Stack();
	
	public Sort_and_Scan(Point[] arrayOfPoints){
		pointArray=arrayOfPoints;
	}
	
	public void findLeastXPoint(){
		double leastVal = Double.MAX_VALUE;
		
		for (int i = 0; i<pointArray.length; i++){
			if (pointArray[i].xPos<leastVal){
				leastVal=pointArray[i].xPos;
				leastXPoint=pointArray[i];
			}
		}
	}
	
	public void shift(){
		findLeastXPoint();
		double xShift=leastXPoint.xPos;
		double yShift=leastXPoint.yPos;
		
		for (int i = 0; i < pointArray.length; i++){
			pointArray[i].xPos-=xShift;
			pointArray[i].yPos-=yShift;
			
		}
		
		
	}
	
	public void findPolarAngle(){
		for (int i = 0; i < pointArray.length; i++){
			pointArray[i].polarAngleToOrigin=Math.toDegrees(Math.atan(pointArray[i].xPos/pointArray[i].yPos));
			
		}
	}
	
	public int compareTo(Point thisPoint, Point thatPoint){
		int result=0;
		
		//-1 means counter-clockwise
		//1 means clockwise
		//0 means parallel
		if (thisPoint.xPos*thatPoint.yPos > thisPoint.yPos*thatPoint.xPos){
			result=-1;
		}else if (thisPoint.xPos*thatPoint.yPos < thisPoint.yPos*thatPoint.xPos){
			result=1;
		}else{
			result=0;
		}
		return result;
	}	
	
	public void sortPoints(){
		
		/*NOTE:::
		 * create a temporary hashmap for the calculations
		 * Then you can reference the points by the sorted array of points
		 * ex:
		 * sorted array goes 3,6,4,8...
		 * If you use a temporary hashmap, you can call
		 * hashmap.get(sortedarray[0]) to get the value of the first point
		 * 
		 * Also then you can recreate the pointArray in sorted order
		 */
		Point[] tempArray = new Point[pointArray.length];
		tempArray = pointArray;
		
		HashMap<Integer, Double> aHashMap = new HashMap<>();
		for (int i = 0; i < pointArray.length; i++){
			if (!Double.isNaN(pointArray[i].polarAngleToOrigin)){			
			aHashMap.put(pointArray[i].pointNumber, pointArray[i].polarAngleToOrigin);
			}
		}

		
		int[] sortedPointNums = new int[pointArray.length-1];
		int arrayCounter = 0;
		int whileLoopCounter = 0;
		while (whileLoopCounter<sortedPointNums.length){
			double currentMin=Double.MAX_VALUE;
			int pointOfCurrentMin = 0;
			for (int pointValues : aHashMap.keySet()){
				if (aHashMap.get(pointValues)<currentMin){
					currentMin=aHashMap.get(pointValues);
					pointOfCurrentMin=pointValues;
				}
			}
			aHashMap.replace(pointOfCurrentMin, Double.MAX_VALUE);
			sortedPointNums[arrayCounter]=pointOfCurrentMin;
			arrayCounter++;
			whileLoopCounter++;
		}
		
		System.out.println("\n\nSorted points: \n");
		for (int i = 0; i < sortedPointNums.length; i++){
		System.out.println("Point " + sortedPointNums[i]);
		}
	}

	public Stack convexHull(){
		Stack thisStack = new Stack();
		return thisStack;
	}
}
;