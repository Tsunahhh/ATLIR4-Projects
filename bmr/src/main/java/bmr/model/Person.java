package bmr.model;

import bmr.model.Activity;

public class Person {
    private double weight;
    private double height;
    private int age;
    private boolean isWoman;

    private Activity freq;

    Person() {

    }

    public Person(boolean isWoman, double height, double weight, int age, Activity freq) {
        this.isWoman = isWoman;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.freq = freq;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWoman(boolean woman) {
        isWoman = woman;
    }

    public void setFreq(Activity freq) {
        this.freq = freq;
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
        return bmr() * freq.getAct();
    }
}
