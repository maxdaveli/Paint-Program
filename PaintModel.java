package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<Point> points=new ArrayList<Point>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles=new ArrayList<Rectangle>();
        private ArrayList<Squiggle> squiggles=new ArrayList<Squiggle>();
        private ArrayList<Oval> ovals=new ArrayList<Oval>();
        private ArrayList<Square> squares= new ArrayList<>();
        private ArrayList<Shape> shapes = new ArrayList<>();
        private ArrayList<Triangle> triangles=new ArrayList<Triangle>();


        public void addShape(Shape shape) {
                this.shapes.add(shape);
        }

        public ArrayList<Shape> getShapes() {return shapes;}

        public void addPoint(Point p){
                this.points.add(p);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Point> getPoints(){
                return points;
        }

        public void addCircle(Circle c){
                this.circles.add(c);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Circle> getCircles(){
                return circles;
        }

        public void addRectangle(Rectangle r){
                this.rectangles.add(r);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Rectangle> getRectangles(){
                return rectangles;
        }

        public void addSquiggle(Squiggle s){
                this.squiggles.add(s);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Squiggle> getSquiggles(){
                return squiggles;
        }

        public void addOval(Oval o){
                this.ovals.add(o);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Oval> getOvals() {return ovals;}


        public void addSquare(Square sq){
                this.squares.add(sq);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Square> getSquares(){return squares; }

        public ArrayList<Triangle> getTriangles(){
                return triangles;
        }

        public void addTriangle(Triangle t){
                this.triangles.add(t);
                this.setChanged();
                this.notifyObservers();
        }
}
