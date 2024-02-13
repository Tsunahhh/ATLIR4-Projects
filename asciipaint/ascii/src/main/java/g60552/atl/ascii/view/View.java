package g60552.atl.ascii.view;

import g60552.atl.ascii.model.AsciiPaint;

public class View {

    /**
     * Show the paint table.
     * @param ap paint
     */
    public static void show(AsciiPaint ap) {
        System.out.println(ap.asAscii());
    }

    /**
     * Show list of shapes on the paint like index - color.
     * @param ap paint
     */
    public static void list(AsciiPaint ap) {
        System.out.println(ap.asList());
    }

    /**
     * Show the help menu with commands examples.
     */
    public static void help() {
        System.out.println("""
                ### Ascii Menu
                    add rectangle posX posY width height color
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
