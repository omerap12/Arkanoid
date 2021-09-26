    package basicgeometry;
    import gameobjects.Block;
    /**
     * @author Omer Aplatony <omerap12@gmail.com>
     * @version 1.0
     * @since 13.04.2021
     * */
    public class Point {
        /**
         * class - one dimensional Point representation.
         * @param args - irrelevant
         */

        private double x;
        private double y;
        private Rectangle rectangle;
        private Block block;

        /**
         * Constructor.
         * @param x the x coordinate of the point
         * @param y the y coordinate of the point
         */
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * @param r set rectangle.
         */
        public void setRectangle(Rectangle r) {
            this.rectangle = r;
        }

        /**
         * @param b sst block.
         */
        public void setBlock(Block b) {
            this.block = b;
        }

        /**
         * @return block
         */
        public Block getBlock() {
            return block;
        }

        /**
         * function that return the distance between other point.
         * @param other - another Point
         * @return the distance between two points.
         */
        public double distance(Point other) {
            //distance formula
            double beforeSqrt = Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2);
            return Math.sqrt(beforeSqrt);
        }

        /**
         * funtion that return true if two points are the same.
         * @param other - other Point
         * @return true if tww points are equal.
         */
        public boolean equals(Point other) {
            //declaration of a very small number - epsilon
            double epsilon = Math.pow(10, -15);
            //if x of the first point minus x of the second is smaller than epsilon and the same about y return true
            if (Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon) {
                return true;
            }
            return false;
        }

        /**
         * function that return the x value.
         * @return the x coordinate of the point
         */
        public double getX() {
            return x;
        }
        /**
         *  function that return the y value.
         * @return the y coordinate of the point
         */
        public double getY() {
            return y;
        }

        /**
         * @return get rectangle
         */
        public Rectangle getRectangle() {
            return this.rectangle;
        }
    }