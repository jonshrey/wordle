import java.util.ArrayList;
import java.util.Collections;

public class Term implements Comparable<Term> {
    int coeff;
    int exp;
    String vbl;

    private static final String CARET = "^";
    /**
     * 
     * @param repr Of the format <coeff><vbl>^<exp>, such as -3z^13
     */
    public Term(String vbl, String repr) {
        this.vbl = vbl;
        var parts = repr.split("\\" + CARET);
        if (repr.contains(vbl)) {
            this.exp = parts.length > 1 ? Integer.valueOf(parts[1]) : 1;
            var leftLen = parts[0].length();
            this.coeff = leftLen > 1 ? Integer.valueOf(parts[0].substring(0, leftLen - 1)) : 1;
        } else {
            this.exp = 0;
            this.coeff = Integer.valueOf(repr);
        }
    }

    public String toString() {
        var coeffAsStr = this.coeff == 1 ? "" : String.valueOf(this.coeff);
        if (this.exp == 0) {
            return String.valueOf(coeffAsStr);
        } else if (this.exp == 1) {
            return coeffAsStr + this.vbl;
        } else {
            return coeffAsStr + this.vbl + CARET + this.exp;
        }
    }

    @Override
    public int compareTo(Term o) {
        return Integer.compare(this.exp, o.exp);
    }

    public static void main(String[] args) {
        Term t1 = new Term("x", "-25x^15");
        Term t2 = new Term("x", "13x^13");
        Term t3 = new Term("x", "2x^2");
        Term t4 = new Term("x", "-x");
        Term t5 = new Term("x", "-5");
        var terms = new ArrayList<Term>();
        terms.add(t1);
        terms.add(t2);
        terms.add(t3);
        Collections.sort(terms);
        System.out.println(terms);
    }
}
