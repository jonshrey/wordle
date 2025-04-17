import java.util.ArrayList;
import java.util.List;
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

        // LightBulb l1 = new LightBulb(false);
        // LightBulb l2 = new LightBulb(true);

        // l1.toggle();
        // l2.toggle();

        // LightBulb.TUNGSTEN_RESISTANCE = 5.5e-8;

        // System.out.println(LightBulb.TUNGSTEN_RESISTANCE);
        // System.out.println(l1);
        // System.out.println(l2);

        // ImmutableOdometer o1 = new ImmutableOdometer(5);
        // o1.nextReading();
        // System.out.println(o1);
        // o1 = o1.nextReading();
        // System.out.println(o1);

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));

        Human h = new Human();
        Robot r = new Robot();

        List<Talkative> entities = List.of(h, r);

        for (var entity: entities) {
            System.out.println(entity.talk());
        }

    }
}
