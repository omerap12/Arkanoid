/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package listeners;
import gameobjects.Ball;
import gameobjects.Block;
import interfaces.HitListener;
/**
 * printing hit listner - check if ball hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * print hit event.
     * @param beingHit  object is hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}