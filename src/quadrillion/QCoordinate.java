package quadrillion;

/**
 * Defines a simple integer two-tuple for the representation of 2D coordinates, for modelling purposes of Quadrillion.
 *
 * @author Unsal Ozturk
 * @version 20190316
 */
public class QCoordinate {
    private int x;
    private int y;

    /**
     * @param x x coordinate
     * @param y y coordinate
     */
    public QCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the two-tuple.
     *
     * @return The x coordinate of the two-tuple.
     */
    public int x() {
        return x;
    }

    /**
     * Returns the y coordinate of the two-tuple.
     *
     * @return The y coordinate of the two-tuple.
     */
    public int y() {
        return y;
    }

    /**
     * Sets the x coordinate of the two-tuple to a desired value.
     *
     * @param x The desired x coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of the two-tuple to a desired value.
     *
     * @param y The desired y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets both the x and y coordinate of the two-tuple to desired values.
     *
     * @param x The desired x coordinate.
     * @param y The desired y coordinate.
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Provides an equality check for two-tuples. Returns true if and only if the individual dimensions are equal to
     * one another. Returns false otherwise, or if the compared type is not a QCoordinate.
     *
     * @param o The other QCoordinate.
     * @return Equality of two QCoordinates.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof QCoordinate && x == ((QCoordinate) o).x() && y == ((QCoordinate) o).y();
    }

    @Override
    public int hashCode() {
        // Jon Skeet hash hack
        int hash = 17;
        hash += 31 * hash + x;
        hash += 31 * hash + y;
        return hash;
    }
}
