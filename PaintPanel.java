package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent> {
    private String mode="○";
//    private String mode="Circle";
    private PaintModel model;
    private Shape drawingShape;

    public Circle circle; // This is VERY UGLY, should somehow fix this!!
    public Rectangle rectangle;
    public Squiggle squiggle;
    public Oval oval;
    public Square square;
    public Triangle triangle;
    private Point startingPoint = new Point(0, 0);
    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
//        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        this.mode=mode;
        System.out.println(this.mode);

        switch (this.mode) {
            case "Oval":
                drawingShape = new Oval(new Point(0, 0));
                break;

        }
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline"

        if (drawingShape == null) return;
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        double x1 = mouseEvent.getX();
        double y1 = mouseEvent.getY();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            drawingShape.mousePressed(x1, y1);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            drawingShape.mouseDragged(x1, y1);
            this.repaint2();
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            drawingShape.mouseReleased(x1, y1);
            this.model.addShape(drawingShape);
            drawingShape = new Oval(new Point(0, 0));
            this.repaint2();
        }

        // "Circle", "Rectangle", "Square", "Squiggle", "Triangle", "Polyline"
        switch(this.mode){
//            case "Oval":
//                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
//                    System.out.println("Started Oval");
//                    Point corner1 = new Point(mouseEvent.getX(), mouseEvent.getY());
//                    this.oval = new Oval(corner1);
//                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
//                    if (this.oval != null) {
//                        Point corner2 = new Point(mouseEvent.getX(), mouseEvent.getY());
//                        this.oval.setCorner2(corner2);
//                        this.repaint();
//                    }
//                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
//                    Point corner2 = new Point(mouseEvent.getX(), mouseEvent.getY());
//                    this.oval.corner2 = corner2;
//                    this.model.addOval(this.oval);
//                    System.out.println("Added Oval");
//                    this.repaint();
//                    this.oval = null;
//                }
//                break;
            case "○":
//            case "Circle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                    startingPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.circle=new Circle(centre, 0);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if (this.circle != null) {
                        if (mouseEvent.getX() < startingPoint.x || mouseEvent.getY() < startingPoint.y) {
                            if(mouseEvent.getX() < startingPoint.x && mouseEvent.getY() < startingPoint.y){
                                this.circle.setCentre(new Point(mouseEvent.getX(), mouseEvent.getY()));
                                double x = startingPoint.x - mouseEvent.getX();
                                double y = startingPoint.y - mouseEvent.getY();
                                double radius = Math.min(Math.abs(x),Math.abs(y));
                                this.circle.setRadius(radius);
                                this.repaint();
                            } else if(mouseEvent.getX() > startingPoint.x && mouseEvent.getY() < startingPoint.y){
                                this.circle.setCentre(new Point(startingPoint.x, mouseEvent.getY()));
                                double x = mouseEvent.getX() - startingPoint.x;
                                double y = startingPoint.y - mouseEvent.getY();
                                double radius = Math.min(Math.abs(x),Math.abs(y));
                                this.circle.setRadius(radius);
                                this.repaint();
                            } else if(mouseEvent.getX() < startingPoint.x && mouseEvent.getY() > startingPoint.y) {
                                this.circle.setCentre(new Point(mouseEvent.getX(), startingPoint.y));
                                double x = startingPoint.x - mouseEvent.getX();
                                double y = mouseEvent.getY() - startingPoint.y;
                                double radius = Math.min(Math.abs(x),Math.abs(y));
                                this.circle.setRadius(radius);
                                this.repaint();
                            }
                        } else {
                            this.circle.setCentre(startingPoint);
                            double x = mouseEvent.getX() - this.circle.getCentre().x;
                            double y = mouseEvent.getY() - this.circle.getCentre().y;
                            double radius = Math.min(Math.abs(x), Math.abs(y));
                            this.circle.setRadius(radius);
                            this.repaint();
                        }
                    }
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.circle!=null){
                                // Problematic notion of radius and centre!!
                                this.circle.setRadius(this.circle.getRadius());
                                this.model.addCircle(this.circle);
                                System.out.println("Added Circle");
                                this.circle=null;
                                this.repaint();
                        }
                }
                break;
                case "▭":
//            case "Rectangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Rectangle");
                    Point topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.rectangle = new Rectangle(topLeft, 0, 0);
                    this.repaint();
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(this.rectangle!=null) {
                        double changed_width = Math.abs(mouseEvent.getX() - this.rectangle.getTopLeft().x);
                        double changed_height = Math.abs(mouseEvent.getY() - this.rectangle.getTopLeft().y);
                        this.rectangle.setWidth(changed_width);
                        this.rectangle.setHeight(changed_height);
                        this.repaint();
                    }
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.rectangle!=null){
                        double new_width = mouseEvent.getX();
                        double new_height = mouseEvent.getY();
                        this.rectangle.setWidth(new_width);
                        this.rectangle.setHeight(new_height);
                        this.model.addRectangle(this.rectangle);
                        System.out.println("Added Rectangle");
                        this.repaint();
                        this.rectangle=null;
                    }
                }
                break;
                case "□":
//              case "Square":
                    if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                        System.out.println("Started Square");
                        Point topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
                        this.square = new Square(topLeft, 0, 0);
                        this.repaint();
                    } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                        if(this.square!=null) {
                            double changed_X = Math.abs(mouseEvent.getX() - this.square.getTopLeft().x);
                            double changed_Y = Math.abs(mouseEvent.getY() - this.square.getTopLeft().y);
                            double sideLength = Math.min(changed_X, changed_Y);
                            this.square.setWidth(sideLength);
                            this.square.setHeight(sideLength);
                            this.repaint();
                        }
                    } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                        if(this.square!=null){
                            double new_X = mouseEvent.getX();
                            double new_Y = mouseEvent.getY();
                            double sideLength = Math.min(new_X, new_Y);
                            this.square.setWidth(sideLength);
                            this.square.setHeight(sideLength);
                            this.model.addSquare(this.square);
                            System.out.println("Added Square");
                            this.square=null;
                            this.repaint();
                        }
                    }
                case "〰":
//            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.squiggle = new Squiggle(point);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.squiggle.point2 = point;
                    this.model.addSquiggle(this.squiggle);
                    this.squiggle = null;
                    this.repaint();
                }
                break;
                case "△":
//            case "Triangle": break;
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Triangle");
                    Point p1 = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.triangle = new Triangle(p1, 0, 0);
                    this.repaint();
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(this.triangle!=null) {
                        double changed_width = Math.abs(mouseEvent.getX() - this.triangle.getP1().x);
                        double changed_height = Math.abs(mouseEvent.getY() - this.triangle.getP1().y);
                        this.triangle.setWidth(changed_width);
                        this.triangle.setHeight(changed_height);
                        this.repaint();
                    }
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.triangle!=null){
                        double new_width = mouseEvent.getX();
                        double new_height = mouseEvent.getY();
                        this.triangle.setWidth(new_width);
                        this.triangle.setHeight(new_height);
                        this.model.addTriangle(this.triangle);
                        System.out.println("Added Triangle");
                        this.triangle=null;
                        this.repaint();
                    }
                }
                break;
//            case "Polyline": break;
                case "ᕒ": break;
            default: break;
        }
    }

    public void repaint2() {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        for (Shape shape: model.getShapes()) {
            shape.draw(g2d);
        }
        if (drawingShape != null) {
            drawingShape.drawOutline(g2d);
        }
    }
    public void repaint() {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        // Draw Lines
        ArrayList<Point> points = this.model.getPoints();

        g2d.setFill(Color.RED);
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Draw Circles
        ArrayList<Circle> circles = this.model.getCircles();

        g2d.setFill(Color.GREEN);
        for (Circle c : this.model.getCircles()) {
            double x = c.getCentre().x;
            double y = c.getCentre().y;
            double radius = c.getRadius();
            g2d.fillOval(x, y, radius, radius);
        }

        if (this.circle != null) {
            g2d.setStroke(Color.GREEN);
            double x = circle.getCentre().x;
            double y = circle.getCentre().y;
            double radius = circle.getRadius();
            g2d.strokeOval(x, y, radius, radius);
        }

        // Draw Rectangles
        ArrayList<Rectangle> rectangles = this.model.getRectangles();

        g2d.setFill(Color.BLUE);
        for (Rectangle r : this.model.getRectangles()) {
            double x = r.getTopLeft().x;
            double y = r.getTopLeft().y;
            double width = r.getWidth();
            double height = r.getHeight();
            g2d.fillRect(x, y, width, height);
        }
        if (this.rectangle != null) {
            g2d.setStroke(Color.BLUE);
            double x = rectangle.getTopLeft().x;
            double y = rectangle.getTopLeft().y;
            double width = rectangle.getWidth();
            double height = rectangle.getHeight();
            g2d.strokeRect(x, y, width, height);
        }

        // Draw Triangles
        ArrayList<Triangle> triangles = this.model.getTriangles();

        g2d.setFill(Color.BLUEVIOLET);
        for(Triangle t: this.model.getTriangles()){
            double x = t.getP1().x;
            double y = t.getP1().y;
            double width = t.getWidth();
            double height = t.getHeight();
            g2d.fillPolygon(new double[]{x, x + width, x + width/2}, new double[]{y, y, y + height}, 3);
        }
        if (this.triangle != null) {
            g2d.setStroke(Color.BLUEVIOLET);
            double x = triangle.getP1().x;
            double y = triangle.getP1().y;
            double width = triangle.getWidth();
            double height = triangle.getHeight();
            g2d.strokePolygon(new double[]{x, x + width, x + width/2}, new double[]{y, y, y + height}, 3);
        }

        // Draw Squiggles
        ArrayList<Squiggle> squiggles = this.model.getSquiggles();
        g2d.setFill(Color.PURPLE);
        for (Squiggle s : this.model.getSquiggles()) {
            double x1 = s.getPoint1().x;
            double y1 = s.getPoint1().y;
            double x2 = s.getPoint2().x;
            double y2 = s.getPoint2().y;
            g2d.strokeLine(x1, y1, x2, y2);
        }
        // Draw Squares
        ArrayList<Square> squares = this.model.getSquares();

        g2d.setFill(Color.BLACK);
        for(Square sq: this.model.getSquares()){
            double x = sq.getTopLeft().x;
            double y = sq.getTopLeft().y;
            double width = sq.getWidth();
            double height = sq.getHeight();
            g2d.fillRect(x, y, width, height);
        }
        if (this.square != null) {
            g2d.setStroke(Color.BLACK);
            double x = square.getTopLeft().x;
            double y = square.getTopLeft().y;
            double width = square.getWidth();
            double height = square.getHeight();
            g2d.strokeRect(x, y, width, height);
        }

        // Draw Ovals
//        ArrayList<Oval> ovals = this.model.getOvals();
//        g2d.setFill(Color.RED);
//        for (Oval o : this.model.getOvals()) {
//            double x =o.getCorner1().x;
//            double y = o.getCorner1().y;
//            double width = Math.abs(o.getCorner2().x - x);
//            double height = Math.abs(o.getCorner2().y - y);
//            g2d.fillOval(x, y, width, height);
//        }
//        if (this.oval != null) {
//            g2d.setStroke(Color.RED);
//            double x = oval.getCorner1().x;
//            double y = oval.getCorner1().y;
//            double width = Math.abs(oval.getCorner2().x - x);
//            double height = Math.abs(oval.getCorner2().y - y);
//            g2d.strokeOval(x, y, width, height);
//        }
    }
}
