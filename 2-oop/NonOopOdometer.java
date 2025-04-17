final String DIGITS = "123456789";

/*
 * When we deal with substrings/ranges, when there is a 'start' and an 'end'
 * You always get the range start <= (x) < end.
 * 
 * In theory there are four options, right?
 * start <= x < end [100, 200) -> end  - start -> best option
 * start < x <= end (100, 200] -> end - start : whole numbers = (-1, limit]
 * start <= x <= end [100, 200] -> end - start + 1 (bad option)
 * start < x < end (100, 200) -> end - start - 1 (bad option)
 * 
 * String.substring(start, end)
 * EW Dijkstra
 */


int smallest(int size) {
    return Integer.valueOf(DIGITS.substring(0, size));
}

int largest(int size) {
    return Integer.valueOf(DIGITS.substring(DIGITS.length() - size));
}

boolean isAscending(int n) {
    if (n < 10) {
        return true;
    }
    else if (n % 10 <= (n / 10) % 10) {
        return false;
    } else {
        return isAscending(n / 10);
    }
}

int size(int n) {
    return String.valueOf(n).length();
}

int nextReading(int reading) {
    if (!isAscending(reading)) {
        throw new IllegalArgumentException("Reading " + reading + " is not a valid Odometer reading");
    }

    if (reading == largest(size(reading))) {
        return smallest(size(reading));
    } else {
        do {
            reading++;
        } while (!isAscending(reading));
        return reading;
    }
}

int prevReading(int reading) {
    if (!isAscending(reading)) {
        throw new IllegalArgumentException("Reading " + reading + " is not a valid Odometer reading");
    }

    if (reading == smallest(size(reading))) {
        return largest(size(reading));
    } else {
        do {
            reading--;
        } while (!isAscending(reading));
        return reading;
    }
}

int nthReadingAfter(int reading, int n) {
    while(n-- > 0) {
        reading = nextReading(reading);
    }
    return reading;
}


int distance(int start, int end) {
    if (!isAscending(start) || !isAscending(end)) {
        throw new IllegalArgumentException("...");
    }
    if (size(start) != size(end)) {
        throw new IllegalArgumentException("The start and end readings should " +
                            "be the same size; you gave (" + start + ", " + end + ")" );
    }

    int distance = 0;
    while (start != end) {
        start = nextReading(start);
        distance++;
    }
    return distance;
}



void main() {
    //println(isAscending(1234));
    //println(isAscending(1023));
    //println(isAscending(4321));

    //println(largest(4));
    //println(smallest(6));

    //println(nextReading(1234));
    //println(nextReading(56789));
    //println(largest(size(56789)));
    // for (int i = 1; i < 10; i++) {
    //     println(largest(i));
    // }
    //println(nextReading(349));

    //println(distance(123, 456));
    int odometerReading1 = 123;
    int odometerReading2 = 789;

    int x;
    
    println(distance(123, 789));
    println(distance(789, 123));

}