/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package interfaces;
import gameobjects.Ball;
import gameobjects.Block;

/**
 * interface for listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit  object is hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}