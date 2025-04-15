final String SEP = ", ";

record Student(String name, int score, Optional<Integer> rank) { }

Student readLine(String line) {
    var parts = line.split(SEP);
    return new Student(parts[0], Integer.valueOf(parts[1]), Optional.empty());
}

//ASSUMING THE FIRST LINE IS NOT "STUDENT NAME, SCORE"
//IF YOU RUN THIS ON THE ORIGINAL FILE IT WILL FAIL!!!
List<Student> readFile(String filename) throws IOException {
    return Files.lines(Path.of(filename)).map(line -> readLine(line)).toList();
}

List<Student> withAddedRanks(List<Student> students) {
    students = new ArrayList<Student>(students);
    students.sort((Student a, Student b) -> -Integer.compare(a.score(), b.score()));
    for (int i = 0; i < students.size(); i++) {
        var current = students.remove(i);
        students.add(i, new Student(current.name(), current.score(), Optional.of(i + 1)));
    }

    for (int i = 1; i < students.size(); i++) {
        var current = students.get(i);
        var prev = students.get(i - 1);
        if (current.score() == prev.score()) {
            students.remove(i);
            students.add(i, new Student(current.name(), current.score(), prev.rank()));
        }
    }
    return students;
}

void main() throws IOException {
    var students = readFile("students.csv");
    students = withAddedRanks(students);
    println(students);
}