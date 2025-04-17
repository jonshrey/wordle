public class Point {
    private double x;
    private double y;

    private static final double EPSILON = Double.MIN_VALUE;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0.0d;
        this.y = 0.0d;
    }

    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Object other) {
        if (!(other instanceof Point)) {
            return false;
        } else {
            Point otherAsPoint = (Point) other;
            return Math.abs(this.x - otherAsPoint.x) <= EPSILON 
                && Math.abs(this.y - otherAsPoint.y) <= EPSILON;
        }
    }
}
