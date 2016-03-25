import java.math.*;
import java.util.*;
/*
 * @author: Thomas James Nix
 * @version: 3/18/2016
 */
public class Sort_and_Scan {
	
	//Fields for the original array of points, the sorted array of points,
	//and the point with the least X value (for shifting purposes)
	Point[] pointArray;
	Point[] sortedPointArray;
	Point leastXPoint;

	/*
	 * Constructor for setting the pointArray equal to the received arrayOfPoints
	 * @param Point[] arrayOfPoints : passed in array of points
	 */
	public Sort_and_Scan(Point[] arrayOfPoints){
		pointArray=arrayOfPoints;
	}
	
	/*
	 * this method finds the point with the smallest X value (for shifting purposes)
	 */
	public void findLeastXPoint(){
		
		double leastVal = Double.MAX_VALUE;		
		for (int i = 0; i<pointArray.length; i++){
			if (pointArray[i].xPos<leastVal){
				leastVal=pointArray[i].xPos;
				leastXPoint=pointArray[i];
			}
		}
	}
	
	/*
	 * This function shifts all of the points by the X and Y value of the point with the lowest
	 * X-value, which is shifted to the origin.
	 */
	public void shift(){
		findLeastXPoint();
		double xShift=leastXPoint.xPos;
		double yShift=leastXPoint.yPos;		
		for (int i = 0; i < pointArray.length; i++){
			pointArray[i].xPos-=xShift;
			pointArray[i].yPos-=yShift;	
		}
	}
	
	/*
	 * This function finds the polar angle from the origin point (shifted) to 
	 * each other point on the graph
	 */
	public void findPolarAngle(){
		for (int i = 0; i < pointArray.length; i++){
			if(pointArray[i].xPos==0){
				//Sets the angle for the origin point to the maximum double value so it's placed
				//last in the sorted array when using sortPoints()
				pointArray[i].polarAngleFromOrigin=Double.MAX_VALUE;
			}else{
			pointArray[i].polarAngleFromOrigin=Math.toDegrees(Math.atan(pointArray[i].yPos/pointArray[i].xPos));
			}
		}
	}
	
	/*
	 * This method sorts the points according to their polar angles in ascending order.
	 * This is accomplished by nested loops and if statements using the MAX_VALUE property
	 * of the double class to ensure that the minimum value is found through every iteration.
	 */
	public void sortPoints(){	
		//The hashmap is used for storing points with their respective polar angles
		HashMap<Integer, Double> aHashMap = new HashMap<>();
		
		//Add all points and their polar angles to a hashmap
		for (int i = 0; i < pointArray.length; i++){
			if (pointArray[i].polarAngleFromOrigin!=Double.MAX_VALUE){			
			aHashMap.put(pointArray[i].pointNumber, pointArray[i].polarAngleFromOrigin);
			}
		}
		
		//Sorts the hashmap and creates an array with the points sorted by polar angle
		//in ascending order
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
		
		//creates an array of sorted points based off the array of sorted point numbers
		sortedPointArray=new Point[pointArray.length-1];
		for (int i = 0; i < pointArray.length-1; i++){
			sortedPointArray[i] = pointArray[sortedPointNums[i]];
		}	
		
		//checks to see if any two points that are directly next to each other
		//points are parallel to each other (collinear) and sets the point with the highest
		//y-value to appear first in the sorted point array
		for (int i = 0; i<sortedPointArray.length-1; i++){
			if (sortedPointArray[i].xPos==sortedPointArray[i+1].xPos){
				if (sortedPointArray[i].yPos<sortedPointArray[i+1].yPos){
					Point tempPointHolder = sortedPointArray[i];
					sortedPointArray[i]=sortedPointArray[i+1];
					sortedPointArray[i+1]=tempPointHolder;
				}
			}
		}
		
		//prints out the sorted points
		System.out.println("\n\nSorted Points:");
		for (int i = 0; i < sortedPointArray.length; i++){
			System.out.println("Point: \t" + sortedPointArray[i].pointNumber);
		}
	}
	
	/*
	 * This is the class that finds the convex hull of the graph through the use of Graham's Scan.
	 * This is done through iterations in a while loops of checking if the next point to be visited
	 * is in the correct direction (counter-clockwise) of the current point and past point.
	 * This method alters the past, current, and next points if the direction is clockwise
	 * and checks for collinear points.
	 */
	public void convexHull(){
		/*
		 * Variables defined for keeping track of the past, current, and present points.
		 * Initializes the three respective points to points 1, 2, and 3 (following the shifted origin point).
		 * The array is used for retrieving the next point after iterations of the while loop and if/else statement.
		 */
		int[] finalizedList = new int[pointArray.length];
		int finalizedCounter=0;
		Point currentPoint, pastPoint, pointToVisit;
		int arrayCounter = 2;
		currentPoint=sortedPointArray[arrayCounter-1];
		pastPoint=sortedPointArray[arrayCounter-2];
		pointToVisit=sortedPointArray[arrayCounter];

		System.out.println("\n\nAnalysis:\n");
			//This while loop checks that we haven't completed the convex hull
			while (pointToVisit!=sortedPointArray[sortedPointArray.length-1]){
				//This if statement checks for correct orientation of the next point
				if (currentPoint.compareTo(pastPoint, pointToVisit)>=0){
					System.out.println("Starting points:"+"\nPast point:"+pastPoint.pointNumber+"("+pastPoint.xPos+","+pastPoint.yPos+")"+
										"\tCurrent:"+currentPoint.pointNumber+"("+currentPoint.xPos+","+currentPoint.yPos+")"+
											"\tNext point::"+pointToVisit.pointNumber+"("+pointToVisit.xPos+","+pointToVisit.yPos+")");
				
					/*
					 * This set of statements checks if the point is already added to the finalized list of points
					 * in the convex hull. This is necessary for repeat iterations after altered points in the
					 * else statement below.
					 */
					Boolean arrayContains = false;
					for (int i = 0; i<finalizedList.length; i++){
						if (finalizedList[i]==pastPoint.pointNumber){
							arrayContains=true;
						}
					}
					if (!arrayContains){
						finalizedList[finalizedCounter]=pastPoint.pointNumber;
						finalizedCounter++;
					}
					
					//If the direction is correct, this set of statements follows to the next point
					//and logs the past point into the finalized array of points for the convex hull.
					arrayCounter++;
					pastPoint=currentPoint;
					currentPoint=pointToVisit;
					pointToVisit=sortedPointArray[arrayCounter];
					
					System.out.println("Next set of points:"+"\nPast point:"+pastPoint.pointNumber+"("+pastPoint.xPos+","+pastPoint.yPos+")"+
							"\tCurrent:"+currentPoint.pointNumber+"("+currentPoint.xPos+","+currentPoint.yPos+")"+
								"\tNext point::"+pointToVisit.pointNumber+"("+pointToVisit.xPos+","+pointToVisit.yPos+")" +"\n");
				
				//The else statement is used for points that are clockwise rather than counterclockwise.
				}else{
					/*
					 * This set of statements alerts that an incorrect direction to the next point was found
					 * and sets the points appropriately for back-tracking and skipping the point
					 * to go to the next point that is correctly oriented.
					 */
					System.out.println("Found invalid turn! Correcting...");
					currentPoint=pointToVisit;
					arrayCounter++;
					pointToVisit=sortedPointArray[arrayCounter];
					
					System.out.println("Past point:"+pastPoint.pointNumber+"("+pastPoint.xPos+","+pastPoint.yPos+")"+
							"\tCurrent:"+currentPoint.pointNumber+"("+currentPoint.xPos+","+currentPoint.yPos+")"+
								"\tNext point::"+pointToVisit.pointNumber+"("+pointToVisit.xPos+","+pointToVisit.yPos+")");
					System.out.println("\n\n");
				}
			}
			/*
			 * This set of statements adds the past, current, and next points to the convex hull 
			 * array when the last set of points is reached.
			 * It then adds the point of least X value, the shifted origin point at the end
			 * since this point is both the beginning and end of the convex hull.
			 */
			finalizedList[finalizedCounter]=pastPoint.pointNumber;
			finalizedList[finalizedCounter+1]=currentPoint.pointNumber;
			finalizedList[finalizedCounter+2]=pointToVisit.pointNumber;
			finalizedList[finalizedCounter+3]=leastXPoint.pointNumber;
			
			System.out.println("Synopsis (Convex Hull):\n\n");
			for (int i = 0; i<finalizedList.length; i++){
				if (finalizedList[i]!=0){
					System.out.println("Point: " + finalizedList[i]);
				}
			}	
		}
	}

