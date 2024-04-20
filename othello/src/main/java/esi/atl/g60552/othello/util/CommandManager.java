package esi.atl.g60552.othello.util;

import java.util.Stack;

public class CommandManager {
    Stack<Command> undoStack = new Stack<>();
    Stack<Command> redoStack = new Stack<>();
    CommandManager() {
    }

    public void add(Command newCmd) {
        newCmd.execute();
        undoStack.push(newCmd);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.empty()) {
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.push(command);
        } else {
            throw new RuntimeException("Nothing to undo!");
        }
    }

    public void redo() {
        if (!redoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        } else {
            throw new RuntimeException("Nothing to redo!");
        }
    }
}
