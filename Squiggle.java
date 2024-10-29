package ca.utoronto.utm.assignment2.paint;


public class Squiggle extends Shape {
    private Point point1;
    public Point point2;

    public Squiggle(Point point1){
        this.point1 = point1;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {return point2;}
}
