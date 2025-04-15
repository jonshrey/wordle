int lastDigit(int n) {
    return n % 10;
}

int dropLastDigit(int n) {
    return n / 10;
}

int digitSum(int n) {
    int sum = 0;
    while (n > 0) {
        sum += lastDigit(n);
        n = dropLastDigit(n);
    }
    return sum;
}

void main(String[] args) {
    if (args.length < 1) {
        System.err.println("usage: java --enable-preview Numbers.java 1234");
    } else {

        for (String arg : args) {
            System.out.println(arg);
        }

        int n = Integer.valueOf(args[0]);
        System.out.println("The sum of its digits is: " + digitSum(n));
    }
}