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
 * Edges hit - for level one in game.
 */
public class EdgesHit implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setClean(false);
    }
}
