public class OdometerUtil {
    private static final String DIGITS = "123456789";

    public static int smallest(int size) {
        return Integer.valueOf(DIGITS.substring(0, size));
    }
    
    public static int largest(int size) {
        return Integer.valueOf(DIGITS.substring(DIGITS.length() - size));
    }
    
    public static boolean isAscending(int n) {
        if (n < 10) {
            return true;
        }
        else if (n % 10 <= (n / 10) % 10) {
            return false;
        } else {
            return isAscending(n / 10);
        }
    }

    public static int size(int n) {
        return String.valueOf(n).length();
    }
}
