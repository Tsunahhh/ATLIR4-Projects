package esi.atl.g60552.othello.util;

import java.util.Stack;

/**
 * Class that manages the commands.
 */
public class CommandManager {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    /**
     * Add a command to the manager.
     * @param newCmd the command to add
     */
    public void add(Command newCmd) {
        newCmd.execute();
        undoStack.push(newCmd);
        redoStack.clear();
    }

    /**
     * Undo the last command.
     */
    public void undo() {
        if (!undoStack.empty()) {
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.push(command);
        } else {
            //throw new RuntimeException("Nothing to undo!");
        }
    }

    /**
     * Redo the last command.
     */
    public void redo() {
        if (!redoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        } else {
            //throw new RuntimeException("Nothing to redo!");
        }
    }

    /**
     * Check if the last command is redoable.
     * @return true if the last command is redoable
     */
    public boolean isRedoable() {
        return !redoStack.isEmpty();
    }

    /**
     * Check if the last command is undoable.
     * @return true if the last command is undoable
     */
    public boolean isUndoable() {
        return !undoStack.isEmpty();
    }
}
