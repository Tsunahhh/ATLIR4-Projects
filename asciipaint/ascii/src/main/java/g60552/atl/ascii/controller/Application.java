package g60552.atl.ascii.controller;

import g60552.atl.ascii.model.AsciiPaint;
import g60552.atl.ascii.view.AsciiView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main class for application
 */
public class Application {

    private AsciiPaint paint;
    private InputManager inputManager;
    private AsciiView asciiView;

    /**
     * Constructor of the application.
     */
    public Application() {
        inputManager = new InputManager(this);
        settings();
        createCmdPattern();
        this.asciiView = new AsciiView(paint, inputManager.getHelpMessage());
    }

    /**
     * Set parameters like size of paint
     */
    private  void settings() {
        // onpréfère démarrer avec un truc par défaut et avoir des commandes pour changer.
        int heigth, width;
        String ifSettings;
        System.out.print("### Settings <yes,no> ? : ");
        ifSettings = inputManager.getNext();
        boolean isSettingsOk = false;
        while (ifSettings.equals("yes") && !isSettingsOk) {
            System.out.print("Height: ");
            heigth = inputManager.robustInteger("Need an Integer for height !");
            System.out.print("Width: ");
            width = inputManager.robustInteger("Need an Integer for width !");
            if (heigth > 0 && width > 0) {
                isSettingsOk = true;
                paint = new AsciiPaint(width, heigth);
            } else {
                System.out.println("the size should be positive and not null");
            }
        }
        if (paint == null) {
            paint = new AsciiPaint();
            System.out.print(">: ");
        }
    }

    /**
     * Command to add a shape on the paint
     * @param m matched command
     */
    public void addShape(Matcher m) {
        int x1 = Integer.parseInt(m.group(3));
        int y1 = Integer.parseInt(m.group(4));
        char color;
        switch (m.group(2)) {
            case "rectangle":
            case "rect":
            case "r":
                double width = Double.parseDouble(m.group(5));
                double height = Double.parseDouble(m.group(6));
                color = m.group(7).charAt(0);
                paint.newRectangle(x1, y1, width, height, color);
                break;
            case "square":
            case "squ":
            case "s":
                double side = Double.parseDouble(m.group(5));
                color = m.group(6).charAt(0);
                paint.newSquare(x1, y1, side, color);
                break;
            case "circle":
            case "circ":
            case "c":
                double radius = Double.parseDouble(m.group(5));
                color = m.group(6).charAt(0);
                System.out.println(x1 + " " + y1 + " " + radius + " " + color);
                paint.newCircle(x1, y1, radius, color);
                break;
            case "line":
            case "li":
            case "l":
                int x2 = Integer.parseInt(m.group(5));
                int y2 = Integer.parseInt(m.group(6));
                color = m.group(7).charAt(0);
                paint.newLine(x1, y1, x2, y2, color);
                break;
        }
    }

    /**
     * Command to move the shape
     * @param m matched command
     */
    public void move(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        int x = Integer.parseInt(m.group(2));
        int y = Integer.parseInt(m.group(3));
        this.paint.move(idx, x, y);
    }

    /**
     * Command to set color of shape
     * @param m matched command
     */
    public void setCol(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        char c = m.group(2).charAt(0);
        this.paint.setColor(idx, c);
    }

    /**
     * Delete a shape
     * @param m matched command
     */
     void delShape(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        this.paint.delShape(idx);
    }

    /**
     * Group shapes.
     * @param m matched command
     */
     void group(Matcher m){
        String match = m.group();
        List<Integer> indexes = extractIndexes(match);
        paint.group(indexes);
    }

    /**
     * Ungroup a group of shapes
     * @param m matched command
     */
     void ungroup(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        paint.ungroup(idx);
    }

    /**
     * Extract indexes from the input
     * @param input
     * @return
     */
    private List<Integer> extractIndexes(String input) {
        List<Integer> indexes = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            indexes.add(Integer.parseInt(matcher.group()));
        }

        return indexes;
    }

    /**
     * Generate all commands pattern for regex in the inputManager with help message.
     */
    private void createCmdPattern() {
        inputManager.AddRegexPattern(
                "^(add)\\s+(rectangle|rect|r)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$",
                "Add a Rectangle:\t\t\t add {rectangle|rect|r} <x> <y> <width> <height> <color>\n"
        );
        inputManager.AddRegexPattern(
                "^(add)\\s+(square|squ|s)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$",
                "Add a Square:\t\t\t\t add {square|squ|s} <x> <y> <size> <color>\n"
        );
        inputManager.AddRegexPattern(
                "^(add)\\s+(circle|circ|c)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$",
                "Add a Circle:\t\t\t\t add {circle|circ|c} <x> <y> <size> <color>\n"
        );
        inputManager.AddRegexPattern(
                "^(add)\\s+(line|li|l)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+([a-zA-Z])$",
                "Add a Line:\t\t\t\t\t add {line|li|l} <x1> <y1> <x2> <y2> <color>\n"
        );
        inputManager.AddRegexPattern(
                "^(move|mv)\\s+(\\d+)\\s+(-?\\d+)\\s+(-?\\d+)$",
                "Move the Shape:\t\t\t\t move <index> <x> <y>\n"
        );
        inputManager.AddRegexPattern(
                "^color\\s+(\\d+)\\s+([a-zA-Z])$",
                "Change the color:\t\t\t color <index> <color>\n"
        );
        inputManager.AddRegexPattern(
                "^(delete|del|d)\\s+(\\d+)$",
                "Delete a Shape:\t\t\t\t {delete|del|d} <index>\n"
        );
        inputManager.AddRegexPattern(
                "^(group|grp)(\\s+\\d+)+$",
                "Group some Shapes:\t\t\t {group|grp} <index> [<index2> ...] \n"
        );
        inputManager.AddRegexPattern(
                "^(ungroup|ugp)\\s+(\\d+)$",
                "Ungroup a group of shapes:\t {ungroup|ugp} <index>\n"
        );
        inputManager.AddRegexPattern(
                "^(show|sh)$",
                "Show the picture:\t\t\t {show|sh}\n"
        );
        inputManager.AddRegexPattern(
                "^(list|lis|l)$",
                "List of shapes:\t\t\t\t {list|lis|l}\n"
        );
        inputManager.AddRegexPattern(
                 "^(help|he|h)$",
                 "Help:\t\t\t\t\t\t {help|he|h}\n"
        );
        inputManager.AddRegexPattern(
                 "^(undo|un)$",
                 "Undo the last command:\t\t {undo|un}\n"
        );
        inputManager.AddRegexPattern(
                 "^(redo|re)$",
                 "Redo the last undo:\t\t\t {redo|re}\n"
        );
    }

    /**
     * Show the picture
     */
    public void show() {
        asciiView.show();
    }

    /**
     * Show the list of shapes.
     */
    public void list() {
        asciiView.list();
    }

    /**
     * Show the help message
     */
    public void help() {
        asciiView.help();
    }

    /**
     * Undo the last command
     */
    public void undo() {
        paint.undo();
    }

    /**
     * Redo the last undo
     */
    public void redo() {
        paint.redo();
    }

    /**
     * Start application.
     */
    public void start() {
        String action = "";
        while (!action.equals("exit")) {
            try {
                action = inputManager.getNextLine();
                inputManager.getMatchAndExec(action);
                System.out.print(">: ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * main
     * @param args arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }
}
