/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package listeners;
import gameobjects.Ball;
import gameobjects.Block;
import interfaces.HitListener;
import levels.DirectHit;

/**
 * direct hit class - check for level one in game.
 */
public class DirectHitListener implements HitListener {
    private DirectHit directHit;

    /**
     * constructor.
     * @param directHit hit
     */
    public DirectHitListener(DirectHit directHit) {
        this.directHit = directHit;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (hitter.getClean()) {
            this.directHit.setSuccess(true);
        }
    }
}
