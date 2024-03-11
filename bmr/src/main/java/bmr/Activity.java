package bmr;

public enum Activity {
    NEVER(1.2),
    LOW(1.375),
    MID(1.55),
    HIGHT(1.725),
    EXTREME(1.9);
    private double act;

    Activity(double v) {
        this.act = v;
    }

    public double getAct() {
        return act;
    }
}
