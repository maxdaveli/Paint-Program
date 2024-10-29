package ca.utoronto.utm.assignment2.paint;

public class Triangle {
    private Point p1;
    private double width;
    private double height;

    public Triangle(Point p1, double width, double height){
        this.p1 = p1;
        this.width = width;
        this.height = height;
    }

    public Point getP1() {
        return p1;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
