public class ImmutableOdometer {
    private int reading;
    private int size;

    public ImmutableOdometer(int size) {
        this.size = size;
        this.reading = OdometerUtil.smallest(size);
    }

    public ImmutableOdometer(int size, int reading) {
        if (!OdometerUtil.isAscending(reading)) {
            throw new IllegalArgumentException("Reading must be ascending!");
        } else {
            this.size = size;
            this.reading = reading;
        }
    }

    public int getReading() {
        return this.reading;
    }

    public ImmutableOdometer nextReading() {
        var copy = new ImmutableOdometer(this.size, this.reading);
        if (copy.reading == OdometerUtil.largest(copy.size)) {
            copy.reading = OdometerUtil.smallest(copy.size);
        } else {
            do {
                copy.reading++;
            } while (!OdometerUtil.isAscending(copy.reading));
        }
        return copy;
    }

    public String toString() {
        return "<" + this.reading + ">";
    }
}