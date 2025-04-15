boolean isPalindrome(String str) {
    var len = str.length();
    if (len <= 1) {
        return true;
    }
    if (str.charAt(0) != str.charAt(len - 1)) {
        return false;
    }
    return isPalindrome(str.substring(1, len - 1));
}

int nextPalindrome(int n) {
    if (isPalindrome(String.valueOf(n + 1))) {
        return n + 1;
    } else {
        return nextPalindrome(n + 1);
    }
}

void main(String[] args) {
    if (args.length < 1) {
        System.err.println("Usage: java --enable-preview Palindrome.java <number>");
    } else {
        int n = Integer.valueOf(args[0]);
        System.out.println(nextPalindrome(n));
    }
}