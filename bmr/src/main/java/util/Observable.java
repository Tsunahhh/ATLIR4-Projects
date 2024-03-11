package util;

public interface Observable {
    void register(Observer obs);
    void unregister(Observer obs);
    void notifyObservers();
}
