import java.math.*;
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
		
		public double compareTo(Point prevPoint, Point nextPoint){
			
			double result = 0;
			//-1 means counter-clockwise
			//1 means clockwise
			//0 means parallel
			/*
			if (yPos<0){
				if ((Math.abs(yPos)*nextPoint.xPos) >= (xPos*nextPoint.yPos)){
					result=true;
				}else{
					result=false;
				}
			}else if (nextPoint.yPos<0){
				if ((yPos*nextPoint.xPos) >= (xPos*Math.abs(nextPoint.yPos))){
					result=true;
				}else{
					result=false;
				}
			}else{
					if ((yPos*nextPoint.xPos) >= (xPos*nextPoint.yPos)){
						result=true;
					}else{
						result=false;
					}
			}*/
			result=(xPos-prevPoint.xPos)*(nextPoint.yPos-prevPoint.yPos)-(yPos-prevPoint.yPos)*(nextPoint.xPos-prevPoint.xPos);
			
			
			return result;
		}	

}
