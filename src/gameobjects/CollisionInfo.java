/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;

import basicgeometry.Point;
import interfaces.Collidable;

/**
 * The class creates an object that contain the collision point with other object and the object.
 */
public class CollisionInfo {
    private Point collsionPoint;
    private Collidable object;

    /**
     * constructor.
     * @param collsionPoint - the point which the collision occur.
     * @param object - the object which the point is on.
     */
    public CollisionInfo(Point collsionPoint, Collidable object) {
        this.collsionPoint = collsionPoint;
        this.object = object;
    }

    /**
     * function that return the collision point.
     * @return collision point
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collsionPoint;
    }

    /**
     * function that return the object.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return object;
    }
}