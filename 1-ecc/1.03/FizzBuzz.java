final List<String> FBs = List.of("FIZZBUZZ", "", "", "FIZZ", "", 
                                "BUZZ", "FIZZ", "", "", "FIZZ", "BUZZ", "", "FIZZ", "", "");

String fb(int n) {
    int position  = n % FBs.size();
    return FBs.get(position).equals("") ?  String.valueOf(n) : FBs.get(position);
}

List<String> fizzBuzz(int limit) {
    return IntStream.range(1, limit + 1).mapToObj(n -> fb(n)).toList();
}

void main(String[] args) {
    if (args.length < 1) {
        System.err.println("Usage: java --enable-preview FizzBuzz.java <limit>");
    } else {
        int limit = Integer.valueOf(args[0]);
        println(fizzBuzz(limit));
    }
}

//DATA IS CODE