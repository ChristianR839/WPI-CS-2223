public class Main {

    public static void main(String[] args) {

        System.out.println("HOMEWORK 5");
        System.out.println("CHRISTIAN RUA");

        System.out.println();

        System.out.println("PARTS 1-3:\n");

        ClosedHashing h = new ClosedHashing(123, 293);

        h.hashAllWords();
        h.printTable();
        h.hashingQuestions();

        System.out.println();

        System.out.println("PART 4 (interactive!):\n");

        DijkstrasAlgorithm d = new DijkstrasAlgorithm();

        d.shortestPath();
    }
}
