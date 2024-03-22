package g60552.atl.ascii.controller;

import g60552.atl.ascii.model.AsciiPaint;
import g60552.atl.ascii.view.AsciiView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main class for application
 */
public class Application {
    public static Scanner in = new Scanner(System.in);
    private AsciiPaint paint;

    /**
     * Ask while stdin find an integer in the buffer
     * @param message error message
     * @return an integer
     */
    private int robustInteger(String message) {
        while (!in.hasNextInt()) {
            in.next();
            System.out.println(message);
        }
        return in.nextInt();
    }

    /**
     * Set parameters like size of paint
     */
    private  void settings() {
        int heigth, width;
        String ifSettings;
        System.out.print("### Settings <yes,no> ? "); ifSettings = in.next();
        if (ifSettings.equals("yes")) {
            System.out.print("Height: ");
            heigth = robustInteger("Need an Integer for height !");
            System.out.print("Width: ");
            width = robustInteger("Need an Integer for width !");
            paint = new AsciiPaint(width, heigth);
        } else {
            paint = new AsciiPaint();
        }
    }

    /**
     * Command to add a shape on the paint
     * @param m matched command
     */
    private void addShape(Matcher m) {
        int x = Integer.parseInt(m.group(2));
        int y = Integer.parseInt(m.group(3));
        char color;
        switch (m.group(1)) {
            case "rectangle":
                double width = Double.parseDouble(m.group(4));
                double height = Double.parseDouble(m.group(5));
                color = m.group(6).charAt(0);
                paint.newRectangle(x, y, width, height, color);
                break;
            case "square":
                double side = Double.parseDouble(m.group(4));
                color = m.group(5).charAt(0);
                paint.newSquare(x, y, side, color);
                break;
            case "circle":
                double radius = Double.parseDouble(m.group(4));
                color = m.group(5).charAt(0);
                paint.newCircle(x, y, radius, color);
                break;
            case "line":
                int x1 = Integer.parseInt(m.group(2));
                int y1 = Integer.parseInt(m.group(3));
                int x2 = Integer.parseInt(m.group(4));
                int y2 = Integer.parseInt(m.group(5));
                color = m.group(6).charAt(0);
                paint.newLine(x1, y1, x2, y2, color);
        }
    }

    /**
     * Command to move the shape
     * @param m matched command
     */
    private void move(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        int x = Integer.parseInt(m.group(2));
        int y = Integer.parseInt(m.group(3));
        this.paint.move(idx, x, y);
    }

    /**
     * Command to set color of shape
     * @param m matched command
     */
    private void setCol(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        char c = m.group(2).charAt(0);
        this.paint.setColor(idx, c);
    }

    private void delShape(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        this.paint.delShape(idx);
    }

    private void group(Matcher m){
        String match = m.group();
        List<Integer> indexes = extractIndexes(match);
        paint.group(indexes);
    }

    private void ungroup(Matcher m) {
        int idx = Integer.parseInt(m.group(1));
        paint.ungroup(idx);
    }

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
     * Start application.
     */
    public void start() {
        Matcher match;
        Pattern cmdRec = Pattern.compile("^add\\s+(rectangle)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$");
        Pattern cmdSqr = Pattern.compile("^add\\s+(square)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$");
        Pattern cmdCirc = Pattern.compile("^add\\s+(circle)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$");
        Pattern cmdLine = Pattern.compile("^add\\s+(line)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+([a-zA-Z])$");
        Pattern cmdMov = Pattern.compile("^move\\s+(-?\\d+)\\s+(-?\\d+)\\s+(\\d+)$");
        Pattern cmdCol = Pattern.compile("^color\\s+(\\d+)\\s+([a-zA-Z])$");
        Pattern cmdDel = Pattern.compile("^delete\\s+(\\d+)$");
        Pattern cmdGrp = Pattern.compile("^group(\\s+\\d+)+$");
        Pattern cmdUgp = Pattern.compile("^ungroup\\s+(\\d+)$");
        String action = "";
        settings();
        AsciiView asciiView = new AsciiView(this.paint);
        asciiView.show();
        while (!action.equals("exit")) {
            action = in.nextLine();
            if (action.equals("show")) asciiView.show();
            if (action.equals("list")) asciiView.list();
            if (action.equals("help")) asciiView.help();
            if (action.equals("undo")) paint.undo();
            if (action.equals("redo")) paint.redo();
            match = cmdRec.matcher(action);
            if (match.find()) this.addShape(match);
            match = cmdSqr.matcher(action);
            if (match.find()) this.addShape(match);
            match = cmdCirc.matcher(action);
            if (match.find()) this.addShape(match);
            match = cmdLine.matcher(action);
            if (match.find()) this.addShape(match);
            match = cmdMov.matcher(action);
            if (match.find()) this.move(match);
            match = cmdCol.matcher(action);
            if (match.find()) this.setCol(match);
            match = cmdDel.matcher(action);
            if (match.find()) this.delShape(match);
            match = cmdGrp.matcher(action);
            if (match.find()) this.group(match);
            match = cmdUgp.matcher(action);
            if (match.find()) this.ungroup(match);
            System.out.print(">: ");
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
