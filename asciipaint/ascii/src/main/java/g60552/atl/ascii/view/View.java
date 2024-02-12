package g60552.atl.ascii.view;

import g60552.atl.ascii.model.AsciiPaint;
import g60552.atl.ascii.model.Shape;

import java.util.List;

public class View {
    public static void show(AsciiPaint ap) {
        System.out.println(ap.asAscii());
    }

    public static void list(AsciiPaint ap) {
        System.out.println(ap.asList());
    }
}
