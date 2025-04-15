public class Odometer {
    private int reading;
    private int size;

    public Odometer(int size) {
        this.size = size;
        this.reading = OdometerUtil.smallest(size);
    }

    public void setReading(int reading) {
        if (!OdometerUtil.isAscending(reading)) {
            System.err.println("Your reading must have its digits in strictly ascending order;" +
                    "no change has been made to the reading");
            throw new IllegalArgumentException(Messages.ILLEGAL_ARGUMENT);
        } else {
            this.reading = reading;
        }
    }

    public int getReading() {
        return reading;
    }

    public void increment() {
        if (!OdometerUtil.isAscending(reading)) {
            throw new IllegalArgumentException("Reading " + reading + " is not a valid Odometer reading");
        }

        if (reading == OdometerUtil.largest(OdometerUtil.size(reading))) {
            reading = OdometerUtil.smallest(OdometerUtil.size(reading));
        } else {
            do {
                reading++;
            } while (!OdometerUtil.isAscending(reading));
        }
    }

    public int distanceTo(Odometer other) {
        if (this.size != other.size) {
            throw new IllegalArgumentException(Messages.SIZE_MISMATCH);
        }

        Odometer copy = new Odometer(this.size);
        copy.setReading(this.reading);
        int distance = 0;
        while (copy.reading != other.reading) {
            copy.increment();
            distance++;
        }
        return distance;
    }

    public String toString() {
        return "<" + reading + ">";
    }
}
