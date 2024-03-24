package g60552.atl.ascii.controller;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class represent all inputs methods and regex patterns
 */
public class InputManager {
    private LinkedHashMap<Pattern, String> cmdMap;
    private Scanner in;
    private Application app;

    /**
     * Constructor of the Input Manager.
     * @param app application.
     */
    InputManager(Application app) {
        this.app = app;
        in = new Scanner(System.in);
        cmdMap = new LinkedHashMap<>(); // Linked because, I want the order of added command in help message.
    }

    /**
     * Add a regex pattern to Input Manager.
     * @param pattern the pattern
     * @param helpMsg the help message
     */
    public void AddRegexPattern(String pattern, String helpMsg) {
        Pattern newCmd = Pattern.compile(pattern);
        cmdMap.put(newCmd, helpMsg);
    }

    /**
     * Execute a command if matched with a pattern
     * @param action String represent an action.
     */
    public void getMatchAndExec(String action) {
        if (action == null) {
            throw new IllegalArgumentException("action can't be null");
        }
        Matcher match;
        for (Pattern cmd : cmdMap.keySet()) {
            match = cmd.matcher(action);
            if (match.find()) {
                switch (match.group(1)) {
                    case "add":
                        app.addShape(match);
                        break;
                    case "move":
                    case "mv":
                        app.move(match);
                        break;
                    case "color":
                    case "c":
                        app.setCol(match);
                        break;
                    case "delete":
                    case "del":
                        app.delShape(match);
                        break;
                    case "group":
                    case "grp":
                        app.group(match);
                        break;
                    case "ungroup":
                    case "ugp":
                        app.ungroup(match);
                        break;
                    case "show":
                    case "sh":
                        app.show();
                        break;
                    case "list":
                    case "li":
                    case "l":
                        app.list();
                        break;
                    case "undo":
                    case "un":
                        app.undo();
                        break;
                    case "redo":
                    case "re":
                        app.redo();
                        break;
                    default:
                        app.help();
                }
            }
        }
    }

    /**
     * Ask while stdin find an integer in the buffer
     * @param message error message
     * @return an integer
     */
    public int robustInteger(String message) {
        while (!in.hasNextInt()) {
            in.next();
            System.out.println(message);
        }
        return in.nextInt();
    }

    /**
     * Get the next line.
     * @return the next line in the stdin.
     */
    public String getNextLine() {
        return in.nextLine().strip();
    }

    /**
     * Get the next word.
     * @return the next word in the stdin.
     */
    public String getNext() {
        return in.nextLine();
    }


    /**
     * Create and return the help message.
     * @return help message.
     */
    public String getHelpMessage() {
        StringBuilder res = new StringBuilder(" _   _      _         __  __                  \n" +
                "| | | | ___| |_ __   |  \\/  | ___ _ __  _   _ \n" +
                "| |_| |/ _ \\ | '_ \\  | |\\/| |/ _ \\ '_ \\| | | |\n" +
                "|  _  |  __/ | |_) | | |  | |  __/ | | | |_| |\n" +
                "|_| |_|\\___|_| .__/  |_|  |_|\\___|_| |_|\\__,_|\n" +
                "             |_|                              \n");
        for (String line : cmdMap.values()) {
            res.append(line);
        }
        return res.toString();
    }
}
