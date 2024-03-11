package bmr;

public class BMRCalc {
    private double bmr;

    BMRCalc(String genre, double height, double weight, int age) {
        if (genre.equals("Homme")) {
            bmr = 13.7 * weight + 5 * height - 6.8 * age + 66;
        } else {
            bmr = 9.6 * weight + 1.8 * height - 4.7 * age + 655;
        }
    }
    public double bmr() {
        return bmr;
    }

    public double cal(Activity act) {
        return bmr * act.getAct();
    }
}
