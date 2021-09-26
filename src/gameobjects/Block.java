/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;
import basicgeometry.Line;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class creates a block from reactangle and color.
 * the class implements Collidable and Sprite interfaces
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private boolean isPaddle;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param rect - the reactangle object that makes the block
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.color = rect.getColor();
        this.isPaddle = this.rect.isPaddle();
        this.hitListeners = createNewList();
    }

    /**
     * function that return if the block is actually paddle.
     * @return true if so else false
     */
    public boolean isPaddle() {
        return isPaddle;
    }

    /**
     * @param gameLevel remove from game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * @return new list
     */
    public List<HitListener> createNewList() {
        List<HitListener> hitListenersList = new ArrayList<>();
        return hitListenersList;
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * @return collision block
     */
    public Block getCollisionBlock() {
        return this;
    }

    /**
     * @return list of hit listeners
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //set color
        surface.setColor(color);
        //draw the rectangle
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        //add the block to the sprites list
        g.addSprite(this);
        //add the block the collidiable list
        g.addCollidable(this);
        //draw the block
        drawOn(g.getEnvironment().getSurface());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean check = false;
        //getting the x,y value of the collision point
        double collisionPointX = collisionPoint.getX();
        double collisionPointY = collisionPoint.getY();
        //getting the the current velocity
        Velocity updatedVelocity = currentVelocity;

        //if the ball hits the upper/ lower bar
        if (((collisionPointX < rect.getUpperLeft().getX()) && (collisionPointX > rect.getUpperRight().getX()))
                || (collisionPointX > rect.getUpperLeft().getX()) && (collisionPointX < rect.getUpperRight().getX())) {
            //change vertical value
            updatedVelocity.setDy(currentVelocity.getDy() * -1);
            check = true;
        }
        //if the ball hits the right/ left bar
        if ((collisionPointY > rect.getLowerLeft().getY() && collisionPointY < rect.getUpperLeft().getY())
                || (collisionPointY < rect.getLowerLeft().getY() && collisionPointY > rect.getUpperLeft().getY())) {
            //change horizontal value
            updatedVelocity.setDx(currentVelocity.getDx() * -1);
            check = true;
        }
        if (!check) {
            //change vertical and horizontal values
            updatedVelocity.setDy(currentVelocity.getDy() * -1);
            updatedVelocity.setDx(currentVelocity.getDx() * -1);
        }
        this.notifyHit(hitter);
        return updatedVelocity;
    }

    @Override
    public List<Point> intersectionPoints(Line line) {
        //creating list of points
        List<Point> pointArrayList = new ArrayList<>();
        //if the line intersecting with left side of the rectangle
        if (line.isIntersecting(this.rect.lineLeft())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rect.lineLeft()));
        }
        //if the line intersecting with lower side of the rectangle
        if (line.isIntersecting(this.rect.lineLower())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rect.lineLower()));

        }
        //if the line intersecting with right side of the rectangle
        if (line.isIntersecting(this.rect.lineRight())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rect.lineRight()));

        }
        //if the line intersecting with lower side of the rectangle
        if (line.isIntersecting(this.rect.lineUpper())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rect.lineUpper()));

        }
        for (int i = 0; i < pointArrayList.size(); i++) {
            pointArrayList.get(i).setBlock(this);
        }
    return pointArrayList;
    }

    /**
     * @param hitter ball notify hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
