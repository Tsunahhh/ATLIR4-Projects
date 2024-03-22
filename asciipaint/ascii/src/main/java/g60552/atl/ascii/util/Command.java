package g60552.atl.ascii.util;

/**
 * Command interface
 */
public interface Command {
    /**
     * Execute the command
     */
    void execute();

    /**
     * Unexecute the command
     */
    void unexecute();
}
