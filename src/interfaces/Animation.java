/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package interfaces;
import biuoop.DrawSurface;

/**
 * interface of animation.
 */
public interface Animation {
    /**
     * function that do frame.
     * @param d - the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * function that determine when program needs to stop.
     * @return - boolean value.
     */
    boolean shouldStop();
}