public class Human implements Talkative, Growable {
    private int age;

    public Human() {
        this.age = 0;
    }

    @Override
    public String talk() {
        return "hello world";
    }

    @Override
    public void grow() {
        this.age++;
    }
}
