package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public interface DrawingShapeInterface {
    void mousePressed(double x, double y);

    void mouseDragged(double x, double y);

    void mouseReleased(double x, double y);

    void draw(GraphicsContext g);

    void drawOutline(GraphicsContext g);
}
