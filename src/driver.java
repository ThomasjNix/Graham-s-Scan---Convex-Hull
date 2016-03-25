import java.util.*;
import java.io.*;
/*
 * @author: Thomas James Nix
 * @version: 3/18/2016
 */
public class driver {
	
	/* 
	 * This class is the main driver class that calls functions from Sort_and_Scan and Point
	 * as well as creating a Sort_and_Scan object to find the convex hull of a set of points
	 * using the Graham's Scan defined in Sort_and_Scan
	 */
	public static void main(String[] args) throws IOException { 
		//Reads the file in
		File fileIn = new File("points.txt");
		Scanner fileRead = new Scanner(fileIn);
		
		//Counts the number of lines in the file
		int lineCounter=0;
		while(fileRead.hasNextLine()){
			lineCounter++;
			fileRead.nextLine();
		}
		fileRead.close();
		System.out.println("Number of lines: " + lineCounter);
		
		//Creates arrays for the X and Y values listed on each line
		double[] XArray = new double[lineCounter];
		double[] YArray = new double[lineCounter];
		
		//New scanner used to actually read the file
		//Sets values of X and Y for each line into the respective arrays
		int pointCounter=0;
		Scanner fileRead2 = new Scanner(fileIn);		
		while(fileRead2.hasNextLine()){
			StringTokenizer lineSplit = new StringTokenizer(fileRead2.nextLine(), ", ");
			XArray[pointCounter]=Double.parseDouble(lineSplit.nextToken());
			YArray[pointCounter]=Double.parseDouble(lineSplit.nextToken());
			pointCounter++;
		}
		fileRead2.close();
		
		//Creates an array of Point objects, initializing each point with the X and Y values
		//using the constructor
		Point[] arrayOfPoints = new Point[XArray.length];
		for (int i = 0; i < arrayOfPoints.length; i++){
			arrayOfPoints[i]=new Point(XArray[i], YArray[i]);
			arrayOfPoints[i].pointNumber=i;
		}
		
		//Creates an object of the Sort_and_Scan class using the Point array
		//Calls the shift function to shift all points according to the point of least X value shifted to the origin
		//Calls the findPolarAngle class to gather the polar angles of all the points according to the new origin point
		//Prints the newly shifted points along with their new positions and polar angles respectively
		Sort_and_Scan GrahamsHullObject = new Sort_and_Scan(arrayOfPoints);
		GrahamsHullObject.shift();
		GrahamsHullObject.findPolarAngle();
		for (int i = 0; i < XArray.length; i++){
			System.out.println("Point: " + GrahamsHullObject.pointArray[i].pointNumber + "\tPosition (" + GrahamsHullObject.pointArray[i].xPos + "," + GrahamsHullObject.pointArray[i].yPos +
					")\tPolar Angle: " + GrahamsHullObject.pointArray[i].polarAngleFromOrigin);
		}
		
		//These last two calls sort the points and create the convex hull using the GrahamsHullObject
		GrahamsHullObject.sortPoints();
		GrahamsHullObject.convexHull();
		
	}
}

	