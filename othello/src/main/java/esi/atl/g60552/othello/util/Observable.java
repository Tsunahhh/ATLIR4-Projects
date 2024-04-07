package esi.atl.g60552.othello.util;

/**
 * Interface for the Observer pattern.
 */
public interface Observable {

    /**
     * Register an observer.
     * @param observer observer to register
     */
    void registerObserver(Observer observer);

    /**
     * Unregister an observer.
     * @param observer observer to unregister
     */
    void unregisterObserver(Observer observer);

    /**
     * Notify observers for updates.
     */
    void notifyObservers();
}
