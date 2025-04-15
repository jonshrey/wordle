public class Tester {
    public static void main(String[] args) {

        // Odometer o2 = new Odometer(5);
        // System.out.println(o2.getReading());
        // o2.setReading(34568);
        // System.out.println(o2); 

        // o2.increment();

        // System.out.println(o2);

        // Odometer o3 = new Odometer(5);

        // System.out.println(o3.distanceTo(o2));

        // System.out.println(o3);

        LightBulb l1 = new LightBulb(false);
        LightBulb l2 = new LightBulb(true);

        l1.toggle();
        l2.toggle();

        LightBulb.TUNGSTEN_RESISTANCE = 5.5e-8;

        System.out.println(LightBulb.TUNGSTEN_RESISTANCE);
        System.out.println(l1);
        System.out.println(l2);
    }
}
