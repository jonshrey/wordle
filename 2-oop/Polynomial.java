import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Polynomial {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String PLUSMINUS = "+-";
    private static final String SPACE = " ";
    private static final String VBL = "x";

    private List<Term> terms;

    public Polynomial(String repr) {
        repr = repr
                .replace(SPACE, "")
                .replace(MINUS, PLUSMINUS)
                .replace(MINUS + VBL, MINUS + 1 + VBL);
        this.terms = Arrays.stream(repr.split("\\" + PLUS))
                            .filter(part -> part.length() > 0)
                            .map(part -> new Term(VBL, part))
                            .sorted()
                            .toList();
    }

    public String toString() {
        return this.terms.stream()
                            .map(term -> term.toString())
                            .collect(Collectors.joining(PLUS))
                            .replace(PLUSMINUS, MINUS)
                            .replace(1 + VBL, VBL);
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial("-25x^15+13x^3+2x^2-x-5");
        System.out.println(p);
    }
    
}
