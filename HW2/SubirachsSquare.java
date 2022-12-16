public class SubirachsSquare {
    int allSum = 132;
    int rowSum = 33;
    int[] square = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};

    public SubirachsSquare() {

    }

    public int rowSumCount(int size) {
        int count = 0;
        int iteration, bitCount, tracker = 0xFFFF;
        int tempSum = 0;

        while (tracker > 0x0) {
            iteration = tracker;
            bitCount = Integer.bitCount(iteration);

            for (int i = 0; i < 16; i++) {

                if (bitCount != size) break;

                if ((iteration & 0x1) == 1) {
                    tempSum += square[i];
                }
                iteration >>= 0x1;
            }
            if (tempSum == 33) count += 1;
            tempSum = 0;
            tracker -= 1;
        }
        return count;
    }

    public int rowSumCountAll() {
        int count = 0;
        int iteration, tracker = 0xFFFF;
        int tempSum = 0;

        while (tracker > 0x0) {
            iteration = tracker;
            for (int i = 0; i < 16; i++) {
                if ((iteration & 0x1) == 1) {
                    tempSum += square[i];
                }
                iteration >>= 0x1;
            }
            if (tempSum == 33) count += 1;
            tempSum = 0;
            tracker -= 1;
        }
        return count;
    }

    public int[] countAllSum() {
        int count = 0;
        int iteration, tracker = 0xFFFF;
        int tempSum = 0;
        int[] sumCount = new int[allSum + 1];

        while (tracker > 0x0) {
            iteration = tracker;
            for (int i = 0; i < 16; i++) {
                if ((iteration & 0x1) == 1) {
                    tempSum += square[i];
                }
                iteration >>= 0x1;
            }
            sumCount[tempSum] += 1;
            tempSum = 0;
            tracker -= 1;
        }
        return sumCount;
    }

    public int[] greatestSumCount() {
        int[] result = new int[2];
        int[] sumCounts = countAllSum();
        int highest = 0, index = 0;

        for (int c : sumCounts) {
            if (c > highest) {
                highest = c;
            }
        }
        result[1] = highest;

        for (int i : sumCounts) {
            if (i == highest) break;
            index += 1;
        }
        result[0] = index;

        return result;
    }
}
