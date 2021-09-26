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
 * ball remover - to remove ball when falls.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel to pley
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        this.remainingBalls = this.gameLevel.getBallCounter();
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }

}
