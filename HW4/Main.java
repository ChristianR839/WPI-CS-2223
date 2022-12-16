import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("HOMEWORK 4");
        System.out.println("CHRISTIAN RUA");
        System.out.println();

        GaussJordanElimination g = new GaussJordanElimination();

        g.answerQuestions();

        System.out.println("PART 3: GAUSS-JORDAN ELIMINATION");
        System.out.println();

        g.gaussJordanElimination(g.system);

        DynamicProgramming d = new DynamicProgramming();

        System.out.println("PART 4: DYNAMIC PROGRAMMING");
        System.out.println();

        d.mostPreciousPath(d.room);
    }
}