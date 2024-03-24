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
     * Reverse the command
     */
    void unexecute();
}
