/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;
import basicgeometry.Line;
import basicgeometry.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * The class stores game objects that need to "communicate" with each other.
 */
public class GameEnvironment {
    private DrawSurface surface;
    private ArrayList<Collidable> collidables;
    private KeyboardSensor keyboardSensor;

    /**
     * Constructor.
     * creating new Array list of collidables
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * function that return the surface object.
     * @return Drawsurface object
     */
    public DrawSurface getSurface() {
        return surface;
    }

    /**
     * The function add the given collidable to the environment.
     * @param c - new collidable
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The function set Keyboard sensor.
     * @param sensor - the keyboardSensor object
     */
    public void setKeyboardSensor(KeyboardSensor sensor) {
        this.keyboardSensor = sensor;
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * The function return collsion info of the closest collision point of a moving object with the collidables.
     * @param trajectory - the line of the moving object
     * @return - collison info object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //creating new Array list of points
        List<Point> points = new ArrayList<Point>();

        //iterating through the collides objects
        for (int i = 0; i < collidables.size(); i++) {
            //getting the collision points list using intersectionPoints function from the collidable object
            List<Point> pointsFromCollision = collidables.get(i).intersectionPoints(trajectory);
            for (int j = 0; j < pointsFromCollision.size(); j++) {
                //set the points with the matching rectangle
                pointsFromCollision.get(j).setRectangle(collidables.get(i).getCollisionRectangle());
            }
            //add all points to the list
            points.addAll(pointsFromCollision);
        }
        //if list size is zero , means no collision occur , return null
        if (points.size() == 0) {
            return null;
        }
        //set start index and start minDistance
        int index = 0;
        double minDistance = points.get(index).distance(trajectory.start());
        //iterating through the points and calculating distance
        for (int i = 1; i < points.size(); i++) {
            //if distance is smaller than the current distance
            if (points.get(i).distance(trajectory.start()) < minDistance) {
                //set index and minDistance to that index and that distance
                index = i;
                minDistance = points.get(i).distance(trajectory.start());
            }
        }
        //get the selected point into variable
        Point selectedPoint = points.get(index);
        //if the selected point on the paddle
        if (selectedPoint.getRectangle().isPaddle()) {
            //creating collison info object with the selected point and new paddle, return the collisionInfo
            CollisionInfo collisionInfo = new CollisionInfo(selectedPoint, new Paddle(selectedPoint.getRectangle(),
                    keyboardSensor));
            return collisionInfo;
        } else {
            //creating collison info object with the selected point and new Block, return the collisionInfo
            Block updated = selectedPoint.getBlock();
            CollisionInfo collisionInfo = new CollisionInfo(selectedPoint, updated);
            return collisionInfo;
        }
    }

    /**
     * The function set the game enviroment surface.
     * @param d - the DrawSurface object
     */
    public void setSurface(DrawSurface d) {
        this.surface = d;
    }

    /**
     * The function return the collidables list.
     * @return collidables list
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

}