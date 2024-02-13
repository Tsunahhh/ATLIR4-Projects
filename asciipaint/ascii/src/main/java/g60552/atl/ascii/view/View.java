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
    public static void help() {
        System.out.println("""
                ### Ascii Menu
                    add rectangle posX posY height width color
                    add circle posX posY radius color
                    add square posX posY size color
                    move index posX posY
                    color index newColor
                    list
                    show
                    help
                """);
    }
}
