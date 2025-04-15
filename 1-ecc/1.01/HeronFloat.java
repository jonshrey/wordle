final float EPSILON = 0;

boolean goodEnough(float num, float guess) {
    return Math.abs(guess - num / guess) <= EPSILON;
    //return guess == num / guess;
}

float betterGuess(float num, float sqrtGuess) {
    return (sqrtGuess + num / sqrtGuess) / 2;
}

private float sqrt(float num, float guess) {
    while (!goodEnough(num, guess)) {
        guess = betterGuess(num, guess);    
    }

    return guess;
}

public float sqrt(float num) {
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
        float num = Float.valueOf(args[0]);
        System.out.println(sqrt(num));
    }
}