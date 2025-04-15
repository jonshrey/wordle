boolean select(int n) {
    return (n % 3) * (n % 5) == 0;
}

int euler001(int limit) {
    int k = 1;
    int total = 0;
    while (k < limit) {
        if (select(k)) {
            total += k;
        }
        k++;
    }
    return total;
}

//You don't yet have to be proficient in this idiom. You'll learn, slowly.
int euler001B(int limit) {
    int multiplesOfThree = IntStream.iterate(3, i -> i < limit, i -> i + 3).sum();
    int multiplesOfFive = IntStream.iterate(5, i -> i < limit, i -> i + 5 ).sum();
    int multiplesOfFifteen = IntStream.iterate(15, i -> i < limit, i -> i + 15).sum();
    return multiplesOfThree + multiplesOfFive - multiplesOfFifteen;
}

void main(String[] args) {
    if (args.length < 1) {
        System.err.println("usage: java --enable-preview Euler <limit>");
    } else {
        int limit = Integer.valueOf(args[0]);
        System.out.println(euler001B(limit));
    }
}