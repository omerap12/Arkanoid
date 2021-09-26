/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package basicgeometry;
import java.awt.Color;
import java.util.ArrayList;
/**
 * The class represent a Rectangle in 2D.
 */
public class Rectangle {
    static final int ZERO = 1;
    static final int ONE = 1;
    static final int TWO = 2;
    static final int THREE = 3;
    static final int FOUR = 4;


    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private Line lineUpper;
    private Line lineLower;
    private Line lineRight;
    private Line lineLeft;
    private Line[] lines = new Line[FOUR];
    private Color color;
    private boolean isPaddle;

    /**
     * Constructor.
     * @param upperLeft - upperleft point of the rectangle.
     * @param width - width of the rectangle.
     * @param height - height of the rectangle.
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        //getting edges points
        this.upperRight = new Point(width + (int) this.upperLeft.getX(), (int) this.upperLeft.getY());
        this.lowerLeft = new Point((int) this.upperLeft.getX(), (int) this.upperLeft.getY() + height);
        this.lowerRight = new Point(width + (int) this.lowerLeft.getX(), (int) this.lowerLeft.getY());
        this.isPaddle = false;

        //getting lines
        lines[ZERO]  = new Line(this.upperLeft, this.upperRight);
        this.lineUpper = lines[ZERO];
        lines[ONE]  = new Line(this.lowerLeft, this.lowerRight);
        this.lineLower = lines[ONE];
        lines[TWO]  = new Line(this.upperLeft, this.lowerLeft);
        this.lineLeft = lines[TWO];
        lines[THREE]  = new Line(this.upperRight, this.lowerRight);
        this.lineRight = lines[THREE];

    }



    /**
     * The function Return a (possibly empty) List of intersection points with the specified line.
     * @param line - other line
     * @return array list of points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //creating the array list
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        //iterating through the rectangle lines
        for (int i = 0; i < FOUR; i++) {
            //if line intersecting with one of the rectangle lines
            if (line.isIntersecting(lines[i])) {
                //get the point
                Point point = line.intersectionWith(lines[i]);
                //set point rectangle
                point.setRectangle(this);
                //add point to the list
                pointArrayList.add(point);
            }
        }
        //return list
        return pointArrayList;
    }

    /**
     * The function set paddle true.
     */
    public void setPaddle() {
        this.isPaddle = true;
    }

    /**
     * The function return if the rectangle is paddle.
     * @return - this.isPaddle
     */
    public boolean isPaddle() {
        return this.isPaddle;
    }

    /**
     * The fucntion set the rectangle color.
     * @param colorObject - color object
     */
    public void setColor(Color colorObject) {
        this.color = colorObject;
    }

    /**
     * The function return the width of the rectangle.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * The function return the height of the rectangle.
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The function Returns the upper-left point of the rectangle.
     * @return point upper left.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * The function Returns the upper-right point of the rectangle.
     * @return point upper right.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
    /**
     * The function Returns the lower-left point of the rectangle.
     * @return point lower left
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }
    /**
     * The function Returns the lower-right point of the rectangle.
     * @return point lower right.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * The function Returns the upper line of the rectangle.
     * @return line upper
     */
    public Line lineUpper() {
        return this.lineUpper;
    }
    /**
     * The function Returns the lower line of the rectangle.
     * @return line lower
     */
    public Line lineLower() {
        return this.lineLower;
    }
    /**
     * The function Returns the right line of the rectangle.
     * @return line right
     */
    public Line lineRight() {
        return this.lineRight;
    }
    /**
     * The function Returns the left line of the rectangle.
     * @return line left
     */
    public Line lineLeft() {
        return this.lineLeft;
    }

    /**
     * The function Returns the color of the rectangle.
     * @return color
     */
    public Color getColor() {
        return color;
    }

}