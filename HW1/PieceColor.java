import java.util.Random;

public enum PieceColor {
    GREEN,
    YELLOW,
    ORANGE;

    private static final Random rand = new Random();

    public static PieceColor randomColor() {
        PieceColor[] colors = values();
        return colors[rand.nextInt(colors.length)];
    }
}