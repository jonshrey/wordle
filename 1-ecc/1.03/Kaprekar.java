final int BASE = 10;
List<Integer> numToDigits(int num) {
    var digits = new ArrayList<Integer>();
    while (num > 0) {
        digits.addFirst(num % BASE);
        num /= BASE;
    }
    return digits;
}

int digitsToNum(List<Integer> digits) {
    int num = 0;
    for (var digit : digits) {
        num = num * BASE + digit;
    }
    return num;
}

int digitsToNumReduce(List<Integer> digits) {
    return digits.stream().reduce(0, (num, digit) -> num * BASE + digit);
}

List<Integer> oddDigits(List<Integer> digits) {
    return digits.stream().filter(digit -> digit % 2 != 0).toList();
}

List<Integer> cubed(List<Integer> digits) {
    return digits.stream().map(digit -> digit * digit * digit).toList();
}

void padWithZeroesUpto(List<Integer> digits, int newSize) {
    while (digits.size() < newSize) {
        digits.addFirst(0);
    }
}

List<Integer> padWithZeroesImmutable(List<Integer> digits, int newSize) {
    var copy = new ArrayList<Integer>(digits);
    padWithZeroesUpto(copy, newSize);
    return copy;
}

void main() {
    var digits = numToDigits(12);
    padWithZeroesUpto(digits, 4);
    println(digits);
}