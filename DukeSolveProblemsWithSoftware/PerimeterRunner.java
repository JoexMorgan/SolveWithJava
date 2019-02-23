import edu.duke.*;
import java.io.*;
import java.io.File;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    public int getNumPoints (Shape s) {
        int totalPoints = 0;
        //Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            totalPoints++;
        }
        return totalPoints;
    }
    public double getAverageLength (Shape s) {
        double averageLength = getPerimeter(s) / getNumPoints(s);
        return averageLength;
    }
    public double getLargestSide (Shape s) {
        double  bigGuy = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double x1 = currPt.getX();
            double y1 = currPt.getY();
            double x2 = prevPt.getX();
            double y2 = prevPt.getY();
            prevPt = currPt;
            double xDist = Math.pow((x2 - x1), 2);
            double yDist = Math.pow((y2 - y1), 2);
            double currDist = Math.sqrt(xDist + yDist);
            if (bigGuy < currDist) {
                bigGuy = currDist;
        }
    }
    return bigGuy;
}
public double getLargestX (Shape s) {
    double bigX = 0.0;
    //Point prevPt = s.getLastPoint();
    for (Point currPt : s.getPoints()) {
        double currX = currPt.getX();
        if (bigX < currX) {
            bigX = currX;
        }
    }
    return bigX;
}
public double getLargestPerimeterMultipleFiles () {
    double bigPerim = 0.0;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        if (length > bigPerim) {
            bigPerim = length;
        }
    }
    return bigPerim;
}
public void testPerimeterMultipleFiles () {
    System.out.println(getFileWithLargestPerimeter());
}
public String getFileWithLargestPerimeter () {
    DirectoryResource dr = new DirectoryResource();
    double bigPerim = 0.0;
    String fileName = "";
    for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        if (bigPerim < length) {
            bigPerim = length;
            fileName = f.getName();
        }
    }
    return fileName;
}
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int totalPoints = getNumPoints(s);
        double avgLen = getAverageLength(s);
        double bigSide = getLargestSide(s);
        double bigX = getLargestX(s);
        System.out.println("perimeter = " + length);
        //System.out.println("total points = " + totalPoints);
        System.out.println("average length of sides = " + avgLen);
        System.out.println("largest side is " + bigSide);
        System.out.println("largest x is " + bigX);
        
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
