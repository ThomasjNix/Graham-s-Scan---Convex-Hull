import java.util.*;
import java.io.*;
import java.math.*;
public class driver {
	
	public static void main(String[] args) throws IOException { 
		File fileIn = new File("points.txt");
		Scanner fileRead = new Scanner(fileIn);
		
		
		int lineCounter=0;
		while(fileRead.hasNextLine()){
			lineCounter++;
			fileRead.nextLine();
		}
		fileRead.close();
		System.out.println("Number of lines: " + lineCounter);
		
		double[] XArray = new double[lineCounter];
		double[] YArray = new double[lineCounter];
		
		int pointCounter=0;
		Scanner fileRead2 = new Scanner(fileIn);		
		while(fileRead2.hasNextLine()){
			StringTokenizer lineSplit = new StringTokenizer(fileRead2.nextLine(), ", ");
			XArray[pointCounter]=Double.parseDouble(lineSplit.nextToken());
			YArray[pointCounter]=Double.parseDouble(lineSplit.nextToken());
			pointCounter++;
		}
		fileRead2.close();
		Point[] arrayOfPoints = new Point[XArray.length];
		for (int i = 0; i < arrayOfPoints.length; i++){
			arrayOfPoints[i]=new Point(XArray[i], YArray[i]);
			arrayOfPoints[i].pointNumber=i;
		}
		
		Sort_and_Scan findStuffOut = new Sort_and_Scan(arrayOfPoints);
		findStuffOut.shift();
		findStuffOut.findPolarAngle();
		for (int i = 0; i < XArray.length; i++){
			System.out.println("Point: " + findStuffOut.pointArray[i].pointNumber + "\tPosition (" + findStuffOut.pointArray[i].xPos + "," + findStuffOut.pointArray[i].yPos +
					")\tPolar Angle: " + findStuffOut.pointArray[i].polarAngleToOrigin);
		}
		
		findStuffOut.sortPoints();
		findStuffOut.convexHull();
		
	}
}

	