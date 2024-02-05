package g60552.atl.acii.controller;

import g60552.atl.acii.model.AsciiPaint;

public class Application {
    private AsciiPaint paint = new AsciiPaint();

    public void start() {
        paint.newCircle(25 , 25, 10, 'e');
        paint.newRectangle(5, 5, 20, 15, 'r');
        System.out.println(paint);
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }
}
