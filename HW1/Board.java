public class Board {

    // Three (3) green markers,
    private int green = 3;

    // Seven (7) yellow markers, and
    private int yellow = 7;

    // Five (5) orange markers.
    private int orange = 5;

    // Two players take turns removing as many markers of a single color as they wish.
    // The player who removes the last marker wins.

    public Board() {
    }

    public void reset() {
        green = 3;
        yellow = 7;
        orange = 5;
    }

    public void remove(PieceColor color, int amount) {
        switch(color) {
            case GREEN:
                green -= amount;
                break;
            case YELLOW:
                yellow -= amount;
                break;
            case ORANGE:
                orange -= amount;
                break;
        }
    }

    public int getNimSum() {
        return green ^ yellow ^ orange;
    }

    public void nimTurn() {

        int nimGY = green ^ yellow;
        int nimGO = green ^ orange;
        int nimOY = orange ^ yellow;

        int amt;

        if (green > nimOY) {
            amt = green - nimOY;
            System.out.println("CPU TURN: Removing " + amt + " green pieces.");
            green -= amt;
        } else if (yellow > nimGO) {
            amt = yellow - nimGO;
            System.out.println("CPU TURN: Removing " + amt + " yellow pieces.");
            yellow -= amt;
        } else if (orange > nimGY) {
            amt = orange - nimGY;
            System.out.println("CPU TURN: Removing " + amt + " yellow pieces.");
            orange -= amt;
        }
    }

    public int getRemaining(PieceColor color) {
        switch(color) {
            case GREEN:
                return green;
            case YELLOW:
                return yellow;
            case ORANGE:
                return orange;
        }
        return -1;
    }

    public boolean isEmpty() {
        return (green == 0 && yellow == 0 && orange == 0);
    }

    public void print() {
        System.out.println();
        System.out.println("THE BOARD");
        System.out.println("> Green:  " + green);
        System.out.println("> Yellow: " + yellow);
        System.out.println("> Orange: " + orange);
        System.out.println();
    }
}
