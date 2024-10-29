package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval extends Shape implements DrawingShapeInterface {
    private Point corner1;
    public Point corner2;

    public Oval(Point corner1){
        this.corner1 = corner1;
        this.corner2 = new Point(corner1.x, corner1.y);
    }

    public Point getCorner1() {
        return this.corner1;
    }

    public Point getCorner2() {return this.corner2;}

    public void setCorner2(Point corner2) {
        this.corner2 = corner2;
    }

    public void draw(GraphicsContext g) {
        g.setStroke(Color.GREEN);
        double x = this.getCorner1().x;
        double y = this.getCorner1().y;
        double width = Math.abs(this.getCorner2().x - x);
        double height = Math.abs(this.getCorner2().y - y);
        g.fillOval(x, y, width, height);
    }
    public void drawOutline(GraphicsContext g) {
        g.setStroke(Color.GREEN);
        double x = this.getCorner1().x;
        double y = this.getCorner1().y;
        double width = Math.abs(this.corner2.x - x);
        double height = Math.abs(this.corner2.y - y);
        g.strokeOval(x, y, width, height);
    }

    public void mousePressed(double x, double y) {
        System.out.println("Started Oval");
        Point corner1 = new Point(x, y);
        this.corner1 = corner1;
    };

    public void mouseDragged(double x, double y) {
        Point corner2 = new Point(x, y);
        this.corner2 = corner2;

    }

    public void mouseReleased(double x, double y) {
        Point corner2 = new Point(x, y);
        this.corner2 = corner2;

    };


}
