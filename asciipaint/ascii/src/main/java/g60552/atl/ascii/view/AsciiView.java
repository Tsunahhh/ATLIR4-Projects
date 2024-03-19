package g60552.atl.ascii.view;

import g60552.atl.ascii.model.AsciiPaint;
import g60552.atl.ascii.model.ColorShape;
import g60552.atl.ascii.model.Shape;

import java.util.List;

/**
 * Representation of the view of the asciipaint
 */
public class AsciiView {

    private AsciiPaint ap;

    /**
     * Construct the view
     * @param ap AsciiPaint instance
     */
    public AsciiView(AsciiPaint ap) {
        this.ap = ap;
    }

    /**
     * Create a string view of the paint table.
     * @return a String to print after
     */
    public String asAscii() {
        char s;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.ap.getHeight(); i++) {
            for (int j = 0; j < this.ap.getWidth(); j++) {
                s = this.ap.getColorPos(j, i);
                res.append((s != ' ') ? s + " " : "  ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    /**
     * Return the list of shape like index - color
     * @return String represent the list
     */
    public String asList() {
        StringBuilder res = new StringBuilder();
        List<Shape> l = this.ap.getShapesList();
        for (int i = 0; i < l.size(); i++) {
            ColorShape cs = (ColorShape) l.get(i);
            res.append(i).append(" - ").append(cs.getColor()).append("\n");
        }
        return res.toString();
    }

    /**
     * Show the paint table.
     */
    public void show() {
        System.out.println(asAscii());
    }

    /**
     * Show list of shapes on the paint like index - color.
     */
    public void list() {
        System.out.println(asList());
    }

    /**
     * Show the help menu with commands examples.
     */
    public void help() {
        System.out.println("""
                ### Ascii Menu
                    - add rectangle <x> <y> <width> <height> <color>
                    - add circle <x> <y> <radius> <color>
                    - add square <x> <y> <size> <color>
                    - move <index> <x> <y>
                    - color <index> <new color>
                    - delete <index>
                    - group <i1> [<i2> ...]
                    - ungroup <index>
                    - list
                    - show
                    - help
                    - exit
                """);
    }
}
