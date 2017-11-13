package ClosestPoints;

import java.util.ArrayList;
import java.util.List;

public class Point {
    int lineNumber;
    List<Double> coordinates;

    public  Point(){
        lineNumber = 0;
        this.coordinates = new ArrayList<>();
    }

    public Point(int lineNumber, List<Double> coordinates){
        this.lineNumber = lineNumber;
        this.coordinates = coordinates;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Point: " +
                "lineNumber=" + lineNumber +
                " ,with coordinates=" + coordinates;
    }
}
