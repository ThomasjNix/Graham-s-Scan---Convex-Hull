
public class Point {

		double xPos=0;
		double yPos=0;
		double polarAngleToOrigin=0;
		int pointNumber;
		
		public Point (double inXPos, double inYPos){
			xPos=inXPos;
			yPos=inYPos;
		}
		
		public double getAngle(){
			return polarAngleToOrigin;
		}

}
