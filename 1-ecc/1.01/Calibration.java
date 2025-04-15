int largest(int a, int b, int c) {
    var nums = List.of(a, b, c);
    return Collections.max(nums);
}

//The best comment is one that doesn't exist.
//Do obvious things obviously in a way that unnecessitates comments.
int largestB(Integer ...nums) {
    int largest = nums[0];
    for (var num : nums) {
        if (largest < num) {
            largest = num;
        }
    }
    return largest;
}

int largestC(List<Integer> nums) {
    return Collections.max(nums);
}

boolean isExactlyTwoTrue(boolean a, boolean b, boolean c) {
    var bools = List.of(a, b, c);
    return Collections.frequency(bools, true) == 2;
}

int toInt(boolean x) {
    return x ? 1 : 0;
}

boolean isExactlyTwoTrueB(boolean a, boolean b, boolean c) {
    return toInt(a) + toInt(b) + toInt(c) == 2;
}

void main() {
    println(largestB(5, 30, 9, 1, 1000, 589285923));
    println(isExactlyTwoTrue(true, false, true));
}