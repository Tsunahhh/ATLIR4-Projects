package bmr.model;

public enum Activity {
    SEDENTARY("Sédentaire", 1.2),
    LIGHTLY_ACTIVE("Peu actif",1.375),
    MODERATELY_ACTIVE("Actif", 1.55),
    VERY_ACTIVE("Très actif",1.725),
    EXTREME_ACTIVE("Extrêmement actif", 1.9);
    private final double value;
    private final String name;

    Activity(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Activity getActivity(String name) {
        for (Activity activity : Activity.values()) {
            if (name.equals(activity.getName())) {
                return activity;
            }
        }
        return null;
    }
}
