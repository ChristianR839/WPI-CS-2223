public class LucasNumber {

    public LucasNumber() {

    }

    public int find(int n) {
        if (n == 0) return 2;
        else if (n == 1) return 1;

        return find(n-1) + find(n-2);
    }

    public int findCustom(int n) {
        if (n == 0) return 7;
        else if (n == 1) return 22;

        return find(n-1) + find(n-2);
    }

    public TimedCalculation findTimed(int n) {
        long startTime, endTime;

        startTime = System.nanoTime();
        long value = find(n);
        endTime = System.nanoTime();
        long elapsed = endTime - startTime;

        return new TimedCalculation(n, value, elapsed);
    }

    public TimedCalculation findTimedCustom(int n) {
        long startTime, endTime;

        startTime = System.nanoTime();
        long value = findCustom(n);
        endTime = System.nanoTime();
        long elapsed = endTime - startTime;

        return new TimedCalculation(n, value, elapsed);
    }

    public float valueRatio(TimedCalculation tCalc, TimedCalculation tCalcNext) {
        return (float) tCalcNext.value / (float) tCalc.value;
    }

    public float timeRatio(TimedCalculation tCalc, TimedCalculation tCalcNext) {
        return (float) tCalcNext.elapsed / (float) tCalc.elapsed;
    }
}
