package g60552.atl.ascii.controller;

import g60552.atl.ascii.model.AsciiPaint;
import g60552.atl.ascii.view.View;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static Scanner in = new Scanner(System.in);
    private AsciiPaint paint;

    private int robustInteger(String message) {
        in.next();
        while (!in.hasNextInt()) {
            in.next();
            System.out.println(message);
        }
        return in.nextInt();
    }

    private  void settings() {
        int heigth, width;
        String ifSettings;
        System.out.println("### Settings <yes,no> ?"); ifSettings = in.next();
        if (ifSettings.equals("yes")) {
            System.out.print("Height: "); heigth = robustInteger("Need an Integer for height !");
            System.out.print("Width: "); width = robustInteger("Need an Integer for width !");
            paint = new AsciiPaint(width, heigth);
        } else {
            paint = new AsciiPaint();
        }
    }
    public void start() {
        Matcher match;
        Pattern cmdRec = Pattern.compile("^add rectangle\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$");
        Pattern cmdSqr = Pattern.compile("^add square\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$");
        Pattern cmdCirc = Pattern.compile("^add circle\\s+(\\d+)\\s+(\\d+)\\s+(\\d+(?:\\.\\d+)?)\\s+([a-zA-Z])$");
        Pattern cmdMov = Pattern.compile("^move\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)$");
        Pattern cmdCol = Pattern.compile("^color\\s+(\\d+)\\s+([a-zA-Z])$");
        String cmdList = "list";
        String cmdShow = "show";
        String action = "";

        settings();
        View.show(paint);
        while (!action.equals("exit")) {
            System.out.print(">: "); action = in.nextLine();
            if (action.equals("show")) View.show(paint);
            match = cmdRec.matcher(action);
            if (match.find()) {
                int x = Integer.parseInt(match.group(1));
                int y = Integer.parseInt(match.group(2));
                double width = Double.parseDouble(match.group(3));
                double height = Double.parseDouble(match.group(4));
                char color = match.group(5).charAt(0);
                paint.newRectangle(x, y, width, height, color);
            }
            match = cmdSqr.matcher(action);
            if (match.find()) {
                int x = Integer.parseInt(match.group(1));
                int y = Integer.parseInt(match.group(2));
                double side = Double.parseDouble(match.group(3));
                char color = match.group(4).charAt(0);
                paint.newSquare(x, y, side, color);
            }
            match = cmdCirc.matcher(action);
            if (match.find()) {
                int x = Integer.parseInt(match.group(1));
                int y = Integer.parseInt(match.group(2));
                double radius = Double.parseDouble(match.group(3));
                char color = match.group(4).charAt(0);
                paint.newCircle(x, y, radius, color);
            }
            match = cmdMov.matcher(action);
            if (match.find()) {
                int eList = Integer.parseInt(match.group(1));
                int x = Integer.parseInt(match.group(2));
                int y = Integer.parseInt(match.group(3));

            }
        }
    }
    private void test1() {
        paint = new AsciiPaint();
        paint.newSquare(4, 5, 6.6, 'c');
        paint.newCircle(25, 25, 13, 'L');
        paint.newCircle(16, 16, 13, 'T');
        paint.newRectangle(18, 18, 10, 15, 'R');
        View.show(paint);
    }
    public static void main(String[] args) {
        Application app = new Application();
        app.start();

    }
}
