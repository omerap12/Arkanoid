/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;

/**
 * Counter class to game.
 */
public class Counter {
    private int count;

    /**
     * constructor.
     * @param count to update
     */
    public Counter(int count) {
        this.count = count;
    }
    /**
     * @param number add number to current count.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }
    /**
     * @param number subtract number from current count.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }
    /**
     * @return get current count.
     */
    int getValue() {
        return this.count;
    }
    /**
     * @param number to set
     */
    void setCount(int number) {
        this.count = number;
    }
    /**
     * print count.
     */
    void printCount() {
        System.out.println(count);
    }
}