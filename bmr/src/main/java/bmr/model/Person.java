package bmr.model;

import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Person implements Observable {
    private double weight;
    private double height;
    private int age;
    private boolean isWoman;

    private Activity freq;

    private List<Observer> observers = new ArrayList<>();

    public Person() {}

    public Person(boolean isWoman, double height, double weight, int age, String freq) {
        this.isWoman = isWoman;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.freq = Activity.getActivity(freq);
    }

    public void set(boolean isWoman, double height, double weight, int age, String freq) {
        this.isWoman = isWoman;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.freq = Activity.getActivity(freq);
        this.notifyObservers();
    }

    public void setWeight(double weight) {
        this.weight = weight;
        notifyObservers();
    }

    public void setHeight(double height) {
        this.height = height;
        notifyObservers();
    }

    public void setAge(int age) {
        this.age = age;
        notifyObservers();
    }

    public void setWoman(boolean woman) {
        isWoman = woman;
        notifyObservers();
    }

    public void setFreq(String freq) {
        this.freq = Activity.getActivity(freq);
        notifyObservers();
    }

    public double bmr() {
        double res;
        if (!isWoman) {
            res = 13.7 * weight + 5 * height - 6.8 * age + 66;
        } else {
            res = 9.6 * weight + 1.8 * height - 4.7 * age + 655;
        }
        return res;
    }

    public double calories() {
        return bmr() * freq.getValue();
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (var obs : observers) {
            obs.update();
        }
    }
}
