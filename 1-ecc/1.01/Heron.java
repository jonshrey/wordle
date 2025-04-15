final double EPSILON = Double.MIN_VALUE;

boolean goodEnough(double num, double guess) {
    return Math.abs(guess - num / guess) <= EPSILON;
}

double betterGuess(double num, double sqrtGuess) {
    return (sqrtGuess + num / sqrtGuess) / 2;
}

private double sqrt(double num, double guess) {
    while (!goodEnough(num, guess)) {
        guess = betterGuess(num, guess);    
    }

    return guess;
}

public double sqrt(double num) {
    if (num == 0) {
        return 0;
    } else if (num < 0) {
        throw new RuntimeException("The negative number " + num + " does not have a real square root");
    }
    return sqrt(num, num / 2);
}

void main(String[] args) {
    if (args.length < 1) {
        System.err.println("Usage: java --enable-preview Heron.java <number>");
    } else {
        double num = Double.valueOf(args[0]);
        System.out.println(sqrt(num));
    }
}