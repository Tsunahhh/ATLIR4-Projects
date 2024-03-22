package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

import java.util.Stack;

/**
 * Represent a manager of commands
 */
public class CommandManager {
    Stack<Command> undoStack = new Stack<>();
    Stack<Command> redoStack = new Stack<>();

    /**
     * Add a new command to the manager.
     * @param command the command
     */
    public void add(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
    }

    /**
     * undo the last command
     */
    public void undo() {
        if (!undoStack.empty()) {
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.add(command);
        }
    }

    /**
     * redo the last undo
     */
    public void redo() {
        if (!redoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.add(command);
        }
    }
}
