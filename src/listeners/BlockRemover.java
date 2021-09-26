/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package listeners;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;
import gameobjects.GameLevel;
import interfaces.HitListener;

/**
 * Block remover - to remove block when hit.
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel level
     */
    public BlockRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = gameLevel.getBlockCounter();
    }

    /**
     * Blocks that are hit should be removed.
     * @param beingHit  object is hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        this.remainingBlocks.decrease(1);
    }
}
