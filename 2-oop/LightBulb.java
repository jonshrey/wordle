public class LightBulb {
    private boolean isOn;

    public static double TUNGSTEN_RESISTANCE = 5.6e-8;

    public LightBulb(boolean initialState) {
        isOn = initialState;
    }

    public void toggle() {
        this.isOn = !this.isOn;
    }

    public String toString() {
        return this.isOn? "💡" : "⛭";
    }
}
