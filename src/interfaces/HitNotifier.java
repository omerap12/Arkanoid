/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package interfaces;

/**
 * Interface for hits.
 */
public interface HitNotifier {
    /**
     * @param hl Add hl as a listener to hit events.
     */
    void addHitListener(HitListener hl);
    /**
     * @param hl Remove hl from the list of listeners to hit events.
     */
    void removeHitListener(HitListener hl);
}