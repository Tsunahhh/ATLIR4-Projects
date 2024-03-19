package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

import java.util.Stack;

public class CommandManager {
    Stack<Command> undoStack = new Stack<>();
    Stack<Command> redoStack = new Stack<>();

    public void add(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
    }
    public void undo() {
        if (!undoStack.empty()) {
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.add(command);
        }
    }

    public void redo() {
        if (!redoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.add(command);
        }
    }
}
