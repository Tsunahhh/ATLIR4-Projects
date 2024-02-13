package g60552.atl.ascii.model;

/**
 * Class represent a Square
 */
public class Square extends Rectangle {

    /**
     * Create a square with option
     * @param upperLeft reference Point
     * @param side size of side
     * @param color color
     */
    public Square(Point upperLeft, double side, char color) {
        super(upperLeft, side, side, color);
    }
}
