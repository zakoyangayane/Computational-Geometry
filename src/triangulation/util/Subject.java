package triangulation.util;

public interface Subject {
    void addObserver(Observer ob);

    void removeObserver(Observer ob);

    void notifyObservers(int state);
}
