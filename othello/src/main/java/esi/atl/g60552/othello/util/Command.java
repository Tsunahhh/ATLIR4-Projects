package esi.atl.g60552.othello.util;

/**
 * Interface for the Command pattern.
 */
public interface Command {
    /**
     * Execute the command.
     */
    void execute();
    /**
     * Unexecute the command.
     */
    void unexecute();
}
