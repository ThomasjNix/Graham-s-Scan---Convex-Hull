import java.math.*;
import java.util.*;
public class Sort_and_Scan {
	
	Point[] pointArray;
	Point[] sortedPointArray;
	Point leastXPoint;

	
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
			pointArray[i].polarAngleToOrigin=Math.toDegrees(Math.atan(pointArray[i].yPos/pointArray[i].xPos));
			
		}
	}
	

	
	public void sortPoints(){
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
		
		
		sortedPointArray=new Point[pointArray.length-1];
		for (int i = 0; i < pointArray.length-1; i++){
			sortedPointArray[i] = pointArray[sortedPointNums[i]];
		}		
		System.out.println("\n\nSorted Points:");
		for (int i = 0; i < sortedPointArray.length; i++){
			System.out.println("Point: \t" + sortedPointArray[i].pointNumber);
		}
	}
	
	public void convexHull(){

		LinkedList<Point> x1 = new LinkedList<Point>();
		Point currentPoint, pastPoint, pointToVisit;
		Point[] grahamArray = new Point[sortedPointArray.length];
		
		int arrayCounter = 2;
		currentPoint=sortedPointArray[arrayCounter-1];
		pastPoint=sortedPointArray[arrayCounter-2];
		pointToVisit=sortedPointArray[arrayCounter];
		
		System.out.println("\n\nAnalysis:\n");
			while (pointToVisit!=sortedPointArray[sortedPointArray.length-1]){
				if (currentPoint.compareTo(pastPoint, pointToVisit)>=0){
					System.out.println("Before"+"\nPast point:"+pastPoint.pointNumber+"("+pastPoint.xPos+","+pastPoint.yPos+")"+
										"\tCurrent:"+currentPoint.pointNumber+"("+currentPoint.xPos+","+currentPoint.yPos+")"+
											"\tPTV:"+pointToVisit.pointNumber+"("+pointToVisit.xPos+","+pointToVisit.yPos+")");
					arrayCounter++;
					pastPoint=currentPoint;
					currentPoint=pointToVisit;
					pointToVisit=sortedPointArray[arrayCounter];
					System.out.println("After"+"\nPast point:"+pastPoint.pointNumber+"("+pastPoint.xPos+","+pastPoint.yPos+")"+
							"\tCurrent:"+currentPoint.pointNumber+"("+currentPoint.xPos+","+currentPoint.yPos+")"+
								"\tPTV:"+pointToVisit.pointNumber+"("+pointToVisit.xPos+","+pointToVisit.yPos+")");
					
				}else{
					//System.out.println("Current array index:" + arrayCounter);
				//	System.out.println("Invalid direction found, breaking loop...");
				//	break;
					System.out.println("Found invalid turn! Correcting...");
					currentPoint=pointToVisit;
					arrayCounter++;
					pointToVisit=sortedPointArray[arrayCounter];
					System.out.println("Past point:"+pastPoint.pointNumber+"("+pastPoint.xPos+","+pastPoint.yPos+")"+
							"\tCurrent:"+currentPoint.pointNumber+"("+currentPoint.xPos+","+currentPoint.yPos+")"+
								"\tPTV:"+pointToVisit.pointNumber+"("+pointToVisit.xPos+","+pointToVisit.yPos+")");
					System.out.println("\n\n");
				}
			}
			
		}
		
		//while we have more points to go to that aren't the origin
		//if the next point is ccw
		//set that point equal to the current point
		//add that point to a "finalized" list
		//if the following point is not ccw, remove the just added point from the 
		//"Finalized" list and add the point that was not ccw
		//to make it do it over and over, we can use a while the next point is not ccw
		//or something like that
		
	}

