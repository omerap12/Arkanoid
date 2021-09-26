package basicgeometry;
import java.util.ArrayList;

/**
 * @author Omer Aplatony <omerap12@gmail.com>
 * @version 1.0
 * @since 13.04.2021
 */


public class Line {
    /**
     * @param args - one dimensional Line representation - implemented by two Points.
     */

    private final Point minStart;
    private final Point minEnd;
    private final Point start;
    private final Point end;
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double m;
    private final double b;
    private final boolean doestItStright;
    private final boolean isPoint;

    /**
     * Constructor.
     * @param minStart the start Point of the line.
     * @param minEnd the end Point of the line.
     */

    public Line(Point minStart, Point minEnd) {
        if (minStart.getY() > minEnd.getY()) {
            this.minEnd = minStart;
            this.minStart = minEnd;
        } else if (minStart.getY() < minEnd.getY()) {
            this.minEnd = minEnd;
            this.minStart = minStart;
        } else {
            if (minStart.getX() >= minEnd.getX()) {
                this.minEnd = minStart;
                this.minStart = minEnd;
            } else {
                this.minEnd = minEnd;
                this.minStart = minStart;
            }
        }
        this.end = minEnd;
        this.start = minStart;

        if (end.getX() == start.getX() && end.getY() == start.getY()) {
            this.isPoint = true;
        } else {
            this.isPoint = false;
        }
        this.x1 = minStart.getX();
        this.x2 = minEnd.getX();
        this.y1 = minStart.getY();
        this.y2 = minEnd.getY();

        // check if is there is no incline
        if (x1 == x2) {
            this.doestItStright = true;
        } else {
            this.doestItStright = false;
        }
        // calculate incline
        if (end.getX() == start.getX()) {
            this.m = 0;
        } else {
            this.m =  ((y1 - y2) / (x1 - x2));

        }
        // calculate b.
        if (doestItStright) {
            this.b = x1;
        } else if (this.m == 0) {
            this.b = y1;
        } else {
            this.b = (y1 - (this.m * x1));
        }
    }

    /**
     * Constructor.
     * @param x1 the x value of the starting Point
     * @param y1 the y value of the starting Point
     * @param x2 the x value of the ending Point
     * @param y2 the y value of the ending Point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startingPoint = new Point(x1, y1);
        Point endingPoint = new Point(x2, y2);
        if (y1 > y2) {
            this.minEnd = startingPoint;
            this.minStart = endingPoint;
        } else if (y1 < y2) {
            this.minEnd = endingPoint;
            this.minStart = startingPoint;
        } else {
            if (x1 >= x2) {
                this.minEnd = startingPoint;
                this.minStart = endingPoint;
            } else {
                this.minEnd = endingPoint;
                this.minStart = startingPoint;
            }
        }
        this.end = minEnd;
        this.start = minStart;

        if (end.getX() == start.getX() && end.getY() == start.getY()) {
            this.isPoint = true;
        } else {
            this.isPoint = false;
        }
        this.x1 = minStart.getX();
        this.x2 = minEnd.getX();
        this.y1 = minStart.getY();
        this.y2 = minEnd.getY();

        // check if is there is no incline
        if (x1 == x2) {
            this.doestItStright = true;
        } else {
            this.doestItStright = false;
        }
        // calculate incline
        if (end.getX() == start.getX()) {
            this.m = 0;
        } else {
            this.m =  ((y1 - y2) / (x1 - x2));

        }
        // calculate b.
        if (doestItStright) {
            this.b = x1;
        } else if (this.m == 0) {
            this.b = y1;
        } else {
            this.b = (y1 - (this.m * x1));
        }
    }

    /**
     * function that return the length of the line using distance formula.
     * @return distance of line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * function that returns the start point of the line.
     * @return start point.
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * function that returns the end point of the line.
     * @return end point.
     */
    public Point getEnd() {
        return this.end;
    }


    /**
     * function that return the middle point of the line.
     * @return middle point of the line
     */
    public Point middle() {
        //if start point and end point have already initialized
        if (start != null && end != null) {
            //getting x and y coordinate
            double x = (start.getX() + end.getX()) / 2;
            double y = (start.getY() + end.getY()) / 2;
            //return the point
            return new Point(x, y);
        } else {
            //if start and end point haven't already initialized,return the new point
            return new Point((x1 + x2) / 2, (y1 + y2) / 2);
        }
    }

    /**
     * function that return that start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        //if start point hasn't initialized yet
        if (start == null) {
            //return the point
            return new Point(x1, y1);
        }
        return start;
    }


    /**
     * function that return the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        if (end == null) {
            return new Point(x2, y2);
        }
        return end;
    }

    /**
     * function that checks if two line are identical.
     * @param other - other line.
     * @return true or false
     */
    public boolean isSameLine(Line other) {
        //checking if the points are equal
        if ((start.equals(other.start) && end.equals(other.end))
                || start.equals(other.equals(end)) && end.equals(other.start)) {
            return true;
        }
        return false;
    }


    /**
     * The function checks if parallel lines are intersecting.
     * @param other - the other line.
     * @return true or false
     */
    public boolean checkParallelLines(Line other) {
        if (this.b != other.b) {
            return false;
        }
        //check if both of the lines are point, if the point are equals.
        if (other.isPoint && this.isPoint) {
            if (other.minStart.equals(this.minStart)) {
                return true;
            }
            return false;
        }

        //check if only one line is point
        if (!(other.isPoint) && this.isPoint) {
            if ((this.y1 >= other.y2) && (this.y2 <= other.y2)) {
                return true;
            }
            return false;
        }
        if (!(this.isPoint) && other.isPoint) {
            if ((other.y2 >= this.y1) && (other.y2 <= this.y2)) {
                return true;
            }
            return false;
        }
        // check if the lines are overlapping.
        if ((other.minStart.equals(this.minEnd))) {
            return true;
        }
        if (other.minEnd.equals(this.minStart)) {
            return true;
        }
        return false;
    }

    /**
     * The function check if point is on the line.
     * @param a the point.
     * @param line - current line.
     * @return true or false.
     */
    public boolean isPointExistInLine(Line line, Point a) {
        double y = ((line.m * a.getX()) + line.b);
        double maxXvalue = Math.max(line.minStart.getX(), line.minEnd.getX());
        double minXvalue = Math.min(line.minStart.getX(), line.minEnd.getX());
        double minYvalue = Math.min(line.minStart.getY(), line.minEnd.getY());
        double maxYvalue = Math.max(line.minStart.getY(), line.minEnd.getY());
        if ((a.getX() <= maxXvalue) && a.getX() >= minXvalue && (y >= minYvalue) && y <= maxYvalue) {
            return true;
        }
        return false;
    }
    /**
     * The function checks if two lines are intersecting.
     * @param other the other line.
     * @return true or false.
     */
    public boolean isIntersecting(Line other) {
        //if both lines has no incline or the lines are parallel
        if ((this.doestItStright && this.doestItStright == other.doestItStright)
                || (!this.doestItStright && !other.doestItStright && this.m == other.m)) {
            boolean answer = checkParallelLines(other);
            return answer;
        }
        // finding point
        double intersectionX;
        double intersectionY;
        //checking if one of the lines has no incline
        if (this.doestItStright || other.doestItStright) {
            // if current line has no incline
            if (this.doestItStright) {
                // set values
                intersectionX = this.minStart.getX();
                intersectionY = other.m * intersectionX;
                intersectionY = intersectionY + other.b;
                Point intersection = new Point(intersectionX, intersectionY);
                double maximumYvalue = Math.max(this.minStart.getY(), this.minEnd.getY());
                double minimumYvalue = Math.min(this.minStart.getY(), this.minEnd.getY());
                boolean check;
                if (intersectionY <= maximumYvalue && intersectionY >= minimumYvalue) {
                    check =  true;
                } else {
                    check = false;
                }
                return  ((isPointExistInLine(other, intersection)) && check);
            } else {
                intersectionX = other.minStart.getX();
                intersectionY = this.m * intersectionX;
                intersectionY = intersectionY + this.b;
                Point intersect = new Point(intersectionX, intersectionY);
                double maximumYvalue = Math.max(other.minStart.getY(), other.minEnd.getY());
                double minimumYvalue = Math.min(other.minStart.getY(), other.minEnd.getY());
                boolean check;
                if (intersectionY <= maximumYvalue && intersectionY >= minimumYvalue) {
                    check =  true;
                } else {
                    check = false;
                }
                return ((isPointExistInLine(this, intersect)) && check);
            }
        }
        //calculating the intersection point
        intersectionX = (other.b - b) / (m - other.m);
        intersectionY = other.m * intersectionX;
        intersectionY = intersectionY + other.b;
        Point intersectionPoint = new Point(intersectionX, intersectionY);
        //send intersection point to check
        return (isPointExistInLine(this, intersectionPoint)) && (isPointExistInLine(other, intersectionPoint));
    }


    /**
     * The function return intersection point between two lines.
     * @param other - other line.
     * @return intersection point
     */

    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        //if lines are parallel
        if ((this.m == other.m) && (!this.doestItStright && !other.doestItStright)) {
            double minCurrentX = Math.min(this.minStart.getX(), this.minEnd.getX());
            double maxCurrentX = Math.max(this.minStart.getX(), this.minEnd.getX());
            double minOtherX = Math.min(other.minStart.getX(), other.minEnd.getX());
            double maxOtherX = Math.max(other.minStart.getX(), other.minEnd.getX());

            //if x is the same
            if (minOtherX == maxCurrentX) {
                return new Point(minOtherX, (this.m * minOtherX) + this.b);
            } else if (minCurrentX == maxOtherX) {
                return new Point(minCurrentX,  (this.m * minCurrentX) + this.b);
            }
            return null;
        }
        //if both lines have no incline
        if (this.doestItStright && other.doestItStright) {
            //if they meet at the start or at the end
            if (this.minStart.equals(other.minEnd)) {
                return this.minStart;
            } else if (other.minStart.equals(this.minEnd)) {
                return other.minStart;
            }
            if ((other.minEnd.equals(other.minStart) && !this.minEnd.equals(this.minStart))) {
                return other.minEnd;
            }
            if ((!other.minEnd.equals(other.minStart) && this.minEnd.equals(this.minStart))) {
                return this.minStart;
            }

            double minCurrentY = Math.min(this.minStart.getY(), this.minEnd.getY());
            double maxCurrentX = Math.max(this.minStart.getY(), this.minEnd.getY());
            double minOtherY = Math.min(other.minStart.getY(), other.minEnd.getY());
            double maxOtherY = Math.max(other.minStart.getY(), other.minEnd.getY());

            //if y values are the same
            if (minOtherY == maxCurrentX) {
                return new Point(this.minStart.getX(), maxCurrentX);
            } else if (minCurrentY == maxOtherY) {
                return new Point(this.minStart.getX(), minCurrentY);
            }
            return null;
        }
            double intersectionX;
            double intersectionY;
            // if just one line has no incline
            if (this.doestItStright || other.doestItStright) {
                if (this.doestItStright) {
                    intersectionX = this.minStart.getX();
                    intersectionY = (other.m * intersectionX);
                    intersectionY = intersectionY + other.b;
                    return new Point(intersectionX, intersectionY);
                } else {
                    intersectionX = other.minStart.getX();
                    intersectionY = (this.m * intersectionX);
                    intersectionY = intersectionY + this.b;
                    return new Point(intersectionX, intersectionY);
                }
            }
            intersectionX = (other.b - this.b) / (this.m - other.m);
            intersectionY = (this.m * intersectionX);
            intersectionY = intersectionY + this.b;
            return new Point(intersectionX, intersectionY);
    }

    /**
     * function that return the incline.
     * @return m (incline)
     */
    public double getm() {
        return m;
    }

    /**
     * function that return the b.
     * @return b
     */
    public double getb() {
        return b;
    }

    /**
     * The function returns the closest point of intersection with the rectangle.
     * @param rect - the rectangle
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> pointArrayList = (ArrayList<Point>) rect.intersectionPoints(this);
        if (pointArrayList.size() == 0) {
            return null;
        }
        int index = 0;
        double min = pointArrayList.get(index).distance(this.start);
        for (int i = 1; i < pointArrayList.size(); i++) {
            if (pointArrayList.get(i).distance(this.start) < min) {
                index = i;
                min = pointArrayList.get(i).distance(this.start);
            }
        }
        return pointArrayList.get(index);
    }
}

