/*
 * @author: Thomas James Nix
 * @version: 3/18/2016
 */
public class Point {
		//These fields are the X value, Y value, polar angle to the origin point,
		//and point number (in order of file-read) for each respective point.
		double xPos=0;
		double yPos=0;
		double polarAngleFromOrigin=0;
		int pointNumber;
		
		/*
		 * This is the constructor that sets the X and Y value for each point
		 * @param double inXPos : the received X value for the point
		 * @param double inYPos : the received Y value for the point
		 */
		public Point (double inXPos, double inYPos){
			xPos=inXPos;
			yPos=inYPos;
		}
		
		/*
		 * This is the overridden compareTo function that takes the previous and next point
		 * as parameters (respective to the current point in the convexHull function of the
		 * Sort_and_Scan class) and calculates the integer representation of clockwise,
		 * counter-clockwise, and collinear.
		 * @param Point prevPoint : The received point previous to the current point
		 * @param Point nextPoint : The received point following the current point
		 */
		public double compareTo(Point prevPoint, Point nextPoint){
			/*
			 * Calculates the integer representation of the orientation to the current point.
			 * 
			 * -1 or less = counter-clockwise
			 * 0 = collinear
			 * 1 or greater = clockwise
			 */
			double result = 0;
			result=(xPos-prevPoint.xPos)*(nextPoint.yPos-prevPoint.yPos)-(yPos-prevPoint.yPos)*
					(nextPoint.xPos-prevPoint.xPos);

			return result;
		}	
}
