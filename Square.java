package ca.utoronto.utm.assignment2.paint;

public class Square extends Shape {
    private Point topLeft;
    private double width;
    private double height;

    public Square(Point topleft, double width, double height){
        this.topLeft = topleft;
        this.width = width;
        this.height = height;
    }

    public Point getTopLeft() {
        return topLeft;
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

